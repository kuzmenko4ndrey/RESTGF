/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclasses;

import DBclasses.TableRow.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neophron
 */
public class TableScheme {

    private static class SchemeElement {

        String name;
        String type;

        private SchemeElement(String name, String type) {
            this.name = name;
            this.type = type;
        }

        private SchemeElement getClone() {
            return new SchemeElement(name, type);
        }
    }

    protected Object classCastChecker(Object o, int n) {
        Object t = null;
        try {
            if (header.get(n).type.equals("Double")) {
                t = Double.parseDouble(o.toString());
            }
            if (header.get(n).type.equals("Integer")) {
                t = Integer.parseInt(o.toString());
            }
            if (header.get(n).type.equals("Character")) {
                t = o.toString().charAt(0);
            }
            if (header.get(n).type.equals("String")) {
                t = (String) o;
            }
            if (header.get(n).type.equals("Picture")) {
                t = (String) o;
                if (!Picture.isPicture(t.toString())) {
                    throw new ClassCastException();
                }
            }
            return t;
        } catch (Exception ex) {
            return null;
        }
    }

    private final ArrayList<SchemeElement> header = new ArrayList<>();
    
    public void add(String name, String type) {
        SchemeElement c = new SchemeElement(name, type);
        header.add(c);
    }

    protected void addDouble(String name) {
        SchemeElement c = new SchemeElement(name, "Double");
        header.add(c);
    }

    protected void addInt(String name) {
        SchemeElement c = new SchemeElement(name, "Integer");
        header.add(c);
    }

    protected void addChar(String name) {
        SchemeElement c = new SchemeElement(name, "Character");
        header.add(c);
    }

    protected void addString(String name) {
        SchemeElement c = new SchemeElement(name, "String");
        header.add(c);
    }

    protected void addPic(String name) {
        SchemeElement c = new SchemeElement(name, "Picture");
        header.add(c);
    }

    protected TableRow createRow() {
        ArrayList<Cell> c = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
            Cell cl = new Cell();
            cl.setName(header.get(i).name);
            cl.setType(header.get(i).type);
            c.add(cl);
        }
        TableRow r = new TableRow(c, this);
        return r;
    }
    
    protected int getPicIndex() {
        int k = -1;
        for (int i = 0; i < header.size(); i++) {
            if (header.get(i).type.equals("Picture")) {
                k = i;
            }
        }
        return k;
    }

    protected Table createTable(String tname) {
        TableScheme s = new TableScheme();
        for (SchemeElement el : header) {
            s.header.add(el.getClone());
        }
        Table t = new Table(tname, s);
        return t;
    }

    @Override
    public boolean equals(Object o) {
        try {
            TableScheme ts = (TableScheme) o;
            for (int i = 0; i < Math.min(header.size(), ts.header.size()); i++) {
                if (!header.get(i).type.equals(ts.header.get(i).type)) {
                    return false;
                }
            }
            return header.size() == ts.header.size();
        } catch (ClassCastException ex) {
            return false;
        }
    }
    
    public ArrayList<String> toStringArray() {
        ArrayList<String> res = new ArrayList<>();
        for (SchemeElement se : header) {
            res.add(se.name + ";" + se.type);
        }
        return res;
    }
    
    public ArrayList<String> getColNames() {
        ArrayList<String> res = new ArrayList<>();
        for (SchemeElement se : header) {
            res.add(se.name);
        }
        return res;
    }

}
