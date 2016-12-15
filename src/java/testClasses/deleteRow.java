/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClasses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Neophron
 */
public class deleteRow {

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:8080/RESTGF2/rest/DBMS/";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";

    private void init() {
        this.client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        deleteRow tester = new deleteRow();
        tester.init();
        tester.testDeleteRow();
    }

    private void testDeleteRow() {
        Form form = new Form();
        form.param("row number", "1");
        
        String callResult = client
                .target(REST_SERVICE_URL)
                .path("/rows/{tname}")
                .resolveTemplate("tname", 123)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

        String result = PASS;
        if (!SUCCESS_RESULT.equals(callResult)) {
            result = FAIL;
        }

        System.out.println("Test case name: testDeleteUser, Result: " + result);
    }

}
