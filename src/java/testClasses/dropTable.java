/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClasses;

import java.util.HashMap;
import java.util.Map;
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
public class dropTable {
    
    private Client client;
    private String REST_SERVICE_URL = "http://localhost:8080/RESTGF2/rest/DBMS/";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";

    private void init() {
        this.client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        dropTable tester = new dropTable();
        tester.init();
        tester.testDropTable();
    }
    
    private void testDropTable() {
        Form form = new Form();
        Map<String, Object> m = new HashMap<>();
        m.put("tname", "123");
        String callResult = client
                .target(REST_SERVICE_URL)
                .path("/tables/{tname}")
                .resolveTemplate("tname", "123")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        System.out.println(callResult);

    }
    
}
