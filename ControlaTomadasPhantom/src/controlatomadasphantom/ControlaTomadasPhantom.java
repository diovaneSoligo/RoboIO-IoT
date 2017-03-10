/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlatomadasphantom;

import DAO.BuscaDadosOnOff;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import modelo.OnOff;

/**
 *
 * @author Evil
 */
public class ControlaTomadasPhantom {
/******************************************************************************/
/******************************************************************************/
    static class Busca implements Runnable {
        int user = 1;//ID do usuario
        
        @Override
        public void run() {
            BuscaDadosOnOff X = new BuscaDadosOnOff();
            
            ArrayList<OnOff> Y = new ArrayList<OnOff>();
            
            Y = (ArrayList<OnOff>) X.OnOff(user);
            
            int tamanho = 0;
            while(tamanho < Y.size()){
                System.out.println("IP: "+Y.get(tamanho).getIp_disp()+" com: "+Y.get(tamanho).getEstado());
                Executa exec = new Executa(Y.get(tamanho).getIp_disp(),Y.get(tamanho).getEstado());
                new Thread(exec).start();
                tamanho++;
            }
            System.out.println("...ENCERRA THREAD 1...");
            
        }
    }
/******************************************************************************/
/******************************************************************************/    
   static class Executa implements Runnable{
       
       String IP;
       String estado;
       
       public Executa (String IP,String estado){
       this.IP = IP;
       this.estado = estado;
       }
       
        @Override
        public void run() {
            String stringURL = "http://"+IP+":1000/?acao=00"+estado+"";
            System.out.println("IP DE CONEXAO: "+stringURL);
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
            
            System.out.println("...ENCERRA THREAD 2...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        
   }
/******************************************************************************/
/******************************************************************************/   
    public static void main(String[] args) {
        
        while(true){
            Busca X = new Busca();
            new Thread(X).start();
            try { Thread.sleep (10000); } catch (InterruptedException ex) {}
        }
    }
/******************************************************************************/    
}