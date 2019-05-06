/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.connection;

import br.com.frete.pojo.DistanciaResult;
import br.com.frete.util.InternetUtil;
import br.com.frete.util.JSONUtil;



/**
 *
 * @author RAMOS
 */
public class DistanciaConnection {

    private static final String CONTEXTO_URL = "https://maps.googleapis.com/maps/api/distancematrix/";
    private static final String FORMATO = "json?";
    private static final String FINAL_URL = "&mode=driving&language=pt-BR&sensor=false&key=AIzaSyD69kt8S4s23M_oAr7gVb1HU454bTGa8co";

    public static String montarUrl(String cepOrigem, String cepDestino) {
        if (cepOrigem == null || cepOrigem.isEmpty() || cepDestino == null || cepDestino.isEmpty()) {
            return null;
        } else {
            return CONTEXTO_URL + FORMATO + "origins=" + cepOrigem + "&destinations=" + cepDestino + FINAL_URL;
        }
    }

    public static DistanciaResult acessarServico(String cepOrigem, String cepDestino) {
        String url = montarUrl(cepOrigem, cepDestino);
        String jsonDistancia = InternetUtil.acessarURL(url);
        DistanciaResult distanciaResult = JSONUtil.fromString(jsonDistancia, DistanciaResult.class);
        return distanciaResult;
    }
}
