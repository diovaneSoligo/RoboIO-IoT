/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlatomadasphantom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Evil
 */
public class ControlaTomadasPhantom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String stringURL = "http://192.168.90.119:1000/?acao=002";
        String resposta = "";
        try {
            URL url = new URL(stringURL);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = in.readLine()) != null) sb.append(inputLine);
            resposta = sb.toString();
            System.out.println("--> "+resposta);
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
