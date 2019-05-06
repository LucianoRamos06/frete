/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabio
 */
public class InternetUtil {

    public static String acessarURL(String sURL) {
        String resposta = null;

        BufferedReader in = null;
        try {
            URL myURL = new URL(sURL);
            URLConnection myURLConnection = myURL.openConnection();
            in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream(), Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
                sb.append('\n');
            }
            resposta = sb.toString();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return resposta;
    }

    /**
     * Acrescentar símbolo + no lugar dos espaços e separadores
     *
     * @param valor
     * @return url substituída
     */
    public static String processar(String valor) {
        String resposta = "";
        //Processa a string quando não for vazia E não for nula
        if (valor != null
                && !valor.isEmpty()) {
            String[] split = valor.trim().split("\\s");
            String s = null;
            for (int i = 0; i < split.length; i++) {
                s = split[i];
                resposta += s;
                if (i < split.length - 1) {
                    resposta += "+";
                }
            }
        }
        return resposta;
    }
    
    public static void redirectUrl(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
