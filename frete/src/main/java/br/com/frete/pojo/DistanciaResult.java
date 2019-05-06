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
public class DistanciaResult implements Serializable{
    private String[] destination_addresses;
    private String[] origin_addresses;
    private Row[] rows;
    private String status;

    public String[] getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(String[] destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public String[] getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(String[] origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

   

    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double quilometros(){
        return Double.parseDouble(rows[0].getElements()[0].getDistance().getText().replace(" ", "").replace("km", ""));
    }
    
}
