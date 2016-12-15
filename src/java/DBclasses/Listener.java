/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBclasses;

import DBclasses.TableRow.Cell;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Neophron
 */
@Path("/DBMS")
public class Listener {

    private DBM dbm;
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";

    public Listener() {
        try {
            this.dbm = new DBM("e:\\we");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("/tables")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addTable(@FormParam("table name") String tname, @FormParam("table csheme") String tscheme) throws IOException {
        String[] tschem = tscheme.split("$");
        if (dbm.addTable(tname, tschem)) {
            dbm.save("E:\\");
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @POST
    @Path("/tables/{tname}")
    @Produces(MediaType.APPLICATION_XML)
    public String dropTable(@PathParam("tname") String tname) throws IOException {
        dbm.dropTable(tname);
        dbm.save("E:\\");
        return tname;
    }

    @PUT
    @Path("/rows/{tname}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addRow(@PathParam("tname") String tname, @FormParam("data") String data) throws IOException {
        if (dbm.addRow(tname, data.split("$"))) {
            dbm.save("E:\\");
            return SUCCESS_RESULT;
        }
        return data.split("$")[0];
    }

    @POST
    @Path("/rows/{tname}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteRow(@PathParam("tname") String tname, @FormParam("row number") int n) throws IOException {
        dbm.deleteRow(tname, n);
        dbm.save("E:\\");
        return getTable(tname);
    }

    @POST
    @Path("/function/{tname}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteRepeatedRows(@PathParam("tname") String tname) throws IOException {
        dbm.deleteRepeatedRows(tname);
        dbm.save("E:\\");
        return getTable(tname);
    }

    @GET
    @Path("/function/{tname1}/{tname2}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getDiffBetwnTabls(@PathParam("tname1") String tname1, @PathParam("tname2") String tname2) {
        List<String> l = Arrays.asList(dbm.getDiffBetwnTabls(tname1, tname2));
        String res = "<table>";
        for (int i = 0; i < l.size(); i++) {
            res += "<row>";
            res += l.get(i);
            res += "</row>";
        }
        res += "</table>";
        return res;
    }

    @GET
    @Path("/tables")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getTableNames() {
        String res = "<tables>";
        for (int i = 0; i < dbm.getTableNames().size(); i++) {
            res += "<tableName>";
            res += dbm.getTableNames().get(i).toString();
            res += "</tableName>";
        }
        res += "</tables>";
        return res;
    }

    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String check() {
        return "<result>YUP</result>";
    }

//    @GET
//    @Path("/cell")
//    @Produces(MediaType.APPLICATION_XML)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public List<Cell> cell() {
//        ArrayList<Cell> al = new ArrayList<>();
//        al.add(new Cell());
//        al.add(new Cell());
//        al.add(new Cell());
//        return al;
//    }
    @GET
    @Path("/tables/{tname}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String getTable(@PathParam("tname") String tname) {
        List<String> l = Arrays.asList(dbm.tableToStringArray(tname));
        String res = "<table>";
        for (int i = 0; i < l.size(); i++) {
            res += "<row>";
            res += l.get(i);
            res += "</row>";
        }
        res += "</table>";
        return res;
    }

}
