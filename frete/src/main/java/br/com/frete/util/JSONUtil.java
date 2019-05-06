/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author bpmlab
 */
public class JSONUtil {

    public static <T> String toString(final T o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (o == null) {
                return null;
            } else {
                return mapper.writeValueAsString(o);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static <T> T fromString(final String s, final Class<T> classe) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (s == null) {
                return null;
            } else {
                return mapper.readValue(s, classe);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
}
