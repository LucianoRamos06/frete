/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.bean;

import br.com.frete.entity.Transportadora;
import br.com.frete.rn.TransportadoraRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luciano
 */
@ManagedBean
@ViewScoped
public class TransportadoraBean {
    //Atributos
    List<Transportadora> trasponortadoras;
    
    //NR's
    private final TransportadoraRN TRANPORTADORA_RN = new TransportadoraRN();
    
    @PostConstruct
    public void init() {
        trasponortadoras = TRANPORTADORA_RN.listar();
    }

    public List<Transportadora> getTrasponortadoras() {
        return trasponortadoras;
    }

    public void setTrasponortadoras(List<Transportadora> trasponortadoras) {
        this.trasponortadoras = trasponortadoras;
    }
    
}
