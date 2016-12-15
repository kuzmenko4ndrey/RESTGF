/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Neophron
 */
@XmlRootElement(name = "table")
public class Table {
    
    private final TableScheme scheme;
    private final String name;
    private final ArrayList<TableRow> rows = new ArrayList<>();
    
    protected static Table createTable(String tname, String tscheme) {
        boolean f = true;
        //some parse shit
        if (f) {
            //return new Table(tname, tscheme);
        }
        return null;
    }
    
    protected Table(String tname, TableScheme tscheme) {
        name = tname;
        scheme = tscheme;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPictureIndex() {
        return scheme.getPicIndex();
    }
    
    protected boolean addRow(List<String> data) {
        TableRow r = scheme.createRow();
        if (r.setData(data)) {
            rows.add(r);
            return true;
        } else {
            return false;
        }
    }
    
    protected void deleteRow(int n) {
        rows.remove(n);
    }
    
    protected void deleteRepeatedRows() {
        for (int i = 0; i < rows.size(); i++) {
            for (int j = i + 1; j < rows.size(); j++) {
                if (rows.get(i).equals(rows.get(j))) {
                    rows.remove(j);
                    j--;
                }
            }
        }
    }
    
    public ArrayList<String> toStringArray() {
        ArrayList<String> res = new ArrayList<>();
        for (TableRow tr : rows) {
            res.add(tr.toString());
        }
        return res;
    }
    
    private ArrayList<TableRow> getRows() {
        ArrayList<TableRow> res = new ArrayList<>();
        for (TableRow tr : rows) {
            TableRow trr = scheme.createRow();
            trr.setData(tr.getData());
            res.add(trr);
        }
        return res;
    }
    
    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (TableRow tr : rows) {
            res.add(tr.getData());
        }
        return res;
    }
    
    protected ArrayList<String> getDiffBetwnTabls(Table t2) {
        Table t = this.scheme.createTable("result");
        for (ArrayList<String> al : this.getData()) {
            t.addRow(al);
        }
        if (t2 != null && t.scheme.equals(t2.scheme)) {
            for (int i = 0; i < t.rows.size(); i++) {
                for (int j = 0; j < t2.rows.size(); j++) {
                    if (t.rows.get(i).equals(t2.rows.get(j))) {
                        t.rows.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return t.toStringArray();
    }
    
    protected Table getDiffBetwnTablsT(Table t2) {
        Table t = this.scheme.createTable("result");
        for (ArrayList<String> al : this.getData()) {
            t.addRow(al);
        }
        if (t2 != null && t.scheme.equals(t2.scheme)) {
            for (int i = 0; i < t.rows.size(); i++) {
                for (int j = 0; j < t2.rows.size(); j++) {
                    if (t.rows.get(i).equals(t2.rows.get(j))) {
                        t.rows.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return t;
    }
    
    public ArrayList<String> getStrScheme() {
        return scheme.toStringArray();
    }
    
    public ArrayList<String> getColNames() {
        return scheme.getColNames();
    }
    
} 
