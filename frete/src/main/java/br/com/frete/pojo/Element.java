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
public class Element implements Serializable {
    private Distancia distance;
    private Duracao duration;
    private String status;

    public Distancia getDistance() {
        return distance;
    }

    public void setDistance(Distancia distance) {
        this.distance = distance;
    }

    public Duracao getDuration() {
        return duration;
    }

    public void setDuration(Duracao duration) {
        this.duration = duration;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
