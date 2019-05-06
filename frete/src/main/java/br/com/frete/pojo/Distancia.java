/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
/**
 *
 * @author RAMOS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Distancia  implements Serializable{

  private String text;
  private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
