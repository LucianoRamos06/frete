/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.pojo;

import java.math.BigDecimal;

/**
 *
 * @author luciano
 */
public class CalculadoraPOJO {

    private BigDecimal porcentageValorMercadoria;
    private BigDecimal larguraMetros;
    private BigDecimal alturaMetros;
    private BigDecimal comprimentoMetros;
    private BigDecimal distanciaQuilometros;
    private BigDecimal pesoKilos;
    private BigDecimal valorTotal;
    private BigDecimal valorFrete;
    private BigDecimal valorMercadoria;
    private BigDecimal valorGris;

    public BigDecimal getPorcentageValorMercadoria() {
        return porcentageValorMercadoria;
    }

    public void setPorcentageValorMercadoria(BigDecimal porcentageValorMercadoria) {
        this.porcentageValorMercadoria = porcentageValorMercadoria;
    }

    public BigDecimal getLarguraMetros() {
        return larguraMetros;
    }

    public void setLarguraMetros(BigDecimal larguraMetros) {
        this.larguraMetros = larguraMetros;
    }

    public BigDecimal getAlturaMetros() {
        return alturaMetros;
    }

    public void setAlturaMetros(BigDecimal alturaMetros) {
        this.alturaMetros = alturaMetros;
    }

    public BigDecimal getComprimentoMetros() {
        return comprimentoMetros;
    }

    public void setComprimentoMetros(BigDecimal comprimentoMetros) {
        this.comprimentoMetros = comprimentoMetros;
    }

    public BigDecimal getDistanciaQuilometros() {
        return distanciaQuilometros;
    }

    public void setDistanciaQuilometros(BigDecimal distanciaQuilometros) {
        this.distanciaQuilometros = distanciaQuilometros;
    }

    public BigDecimal getPesoKilos() {
        return pesoKilos;
    }

    public void setPesoKilos(BigDecimal pesoKilos) {
        this.pesoKilos = pesoKilos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorMercadoria() {
        return valorMercadoria;
    }

    public void setValorMercadoria(BigDecimal valorMercadoria) {
        this.valorMercadoria = valorMercadoria;
    }

    public BigDecimal getValorGris() {
        return valorGris;
    }

    public void setValorGris(BigDecimal valorGris) {
        this.valorGris = valorGris;
    }
    
    public BigDecimal getCubagem() {
        return comprimentoMetros.multiply(larguraMetros).multiply(larguraMetros).multiply(new BigDecimal(300));
    }
}
