/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Neophron
 */
public class DBM{

    private DB db;
    
    public DBM() {
        
    }
    
    public DBM(String path) throws FileNotFoundException {
        db = DB.load(path);
    }

    public void createNewDB(String name) {
        db = new DB(name);
    }

    public void loadDBFrom(String path) throws FileNotFoundException {
        db = DB.load(path);
    }

    public DB getDB() {
        return db;
    }

    public void writeHello() {
        System.out.println("Hello");
    }

    public boolean addTable(String tname, String[] tscheme) {
        TableScheme ts = new TableScheme();
        for (String se : tscheme) {
            String[] sh = se.split(";");
            ts.add(sh[0], sh[1]);
        }
        return db.addTable(tname, ts);
    }

    public void dropTable(String tname) {
        db.dropTable(tname);
    }

    public boolean addRow(String tname, String[] data) {
        ArrayList<String> d = new ArrayList<>(Arrays.asList(data));
        return db.addRow(tname, d);
    }

    public void deleteRow(String tname, int n) {
        db.deleteRow(tname, n);
    }

    public void deleteRepeatedRows(String tname) {
        db.deleteRepeatedRows(tname);
    }

    public String[] tableToStringArray(String tname) {
        ArrayList<String> al = db.tableToStringArray(tname);
        String[] res = new String[al.size()];
        for (int i = 0; i < al.size(); i++) {
            res[i] = al.get(i);
        }
        return res;
    }

    public String[] getDiffBetwnTabls(String tname1, String tname2) {
        ArrayList<String> al = db.getDiffBetwnTabls(tname1, tname2);
        String[] res = new String[al.size()];
        for (int i = 0; i < al.size(); i++) {
            res[i] = al.get(i);
        }
        return res;
    }
    public Table getDiffBetwnTablsT(String tname1, String tname2) {
        return db.getDiffBetwnTablsT(tname1, tname2);
    }

    public void save(String path) throws FileNotFoundException, IOException {
        db.save(path);
    }

    public List<String> getTableNames() {
        ArrayList<String> al = db.getTableNames();
//        String[] res = new String[al.size()];
//        for (int i = 0; i < al.size(); i++) {
//            res[i] = al.get(i);
//        }
        return al;
    }

    public String[][] getTableData(String tname) {
        ArrayList<ArrayList<String>> al = db.getTableData(tname);
        String[][] res = new String[al.size()][al.get(0).size()];
        for (int i = 0; i < al.size(); i++) {
            for (int j = 0; j < al.get(i).size(); j++) {
                res[i][j] = al.get(i).get(j);
            }
        }
        return res;
    }
    
    public Table getTable(String tname) {
        return db.getTable(tname);
    }

    public int getPictureIndex(String tname) {
        return db.getPictureIndex(tname);
    }

}
