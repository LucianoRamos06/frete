/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.bean;

import br.com.frete.entity.Planilha;
import br.com.frete.entity.Transportadora;
import br.com.frete.pojo.CalculadoraPOJO;
import br.com.frete.rn.CalculadoraRN;
import br.com.frete.rn.PlanilhaRN;
import br.com.frete.rn.TransportadoraRN;
import br.com.frete.util.UtilBean;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.ValidationException;

/**
 *
 * @author luciano
 */
@ManagedBean
@ViewScoped
public class CalculadoraBean {

    private String cepOrigem = new String();
    private String cepDestino = new String();
    private String valorMercadoria = new String();
    private String peso = new String();
    private String largura = new String();
    private String altura = new String();
    private String comprimento = new String();
    private Double distancia;
    private Planilha planilha = new Planilha();
    private Transportadora transportadora = new Transportadora();
    private CalculadoraPOJO calculadoraPOJO = new CalculadoraPOJO();
    private boolean calculado = false;
    
    private final CalculadoraRN CALCULADORA_RN = new CalculadoraRN();
    private final PlanilhaRN PLANILHA_RN = new PlanilhaRN();
    private final TransportadoraRN TRANSPORTADORA_RN = new TransportadoraRN();

    @PostConstruct
    public void init() {
        String parametro = UtilBean.obterValor("transportadora");
        if (parametro != null) {
            planilha = PLANILHA_RN.obter(TRANSPORTADORA_RN.obter(Long.parseLong(parametro)));
        }
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public String getValorMercadoria() {
        return valorMercadoria;
    }

    public void setValorMercadoria(String valorMercadoria) {
        this.valorMercadoria = valorMercadoria;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getLargura() {
        return largura;
    }

    public void setLargura(String largura) {
        this.largura = largura;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Planilha getPlanilha() {
        return planilha;
    }

    public void setPlanilha(Planilha planilha) {
        this.planilha = planilha;
    }

    public CalculadoraPOJO getCalculadoraPOJO() {
        return calculadoraPOJO;
    }

    public void setCalculadoraPOJO(CalculadoraPOJO calculadoraPOJO) {
        this.calculadoraPOJO = calculadoraPOJO;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public void setCalculado(boolean calculado) {
        this.calculado = calculado;
    }

    public void calcularDistancia() {
        try {
            distancia = CALCULADORA_RN.calcularDistancia(cepOrigem, cepDestino);
        } catch (ValidationException e) {
            UtilBean.criarMensagemDeAviso(e.getMessage());
        } catch (Exception e) {
            UtilBean.criarMensagemDeErro("Um erro ocorreu!");
        }
    }

    public void realizarCalculo() {
        try {
            distancia = CALCULADORA_RN.calcularDistancia(cepOrigem, cepDestino);

            calculadoraPOJO.setAlturaMetros(UtilBean.converter(altura));
            calculadoraPOJO.setComprimentoMetros(UtilBean.converter(comprimento));
            calculadoraPOJO.setLarguraMetros(UtilBean.converter(largura));
            calculadoraPOJO.setPesoKilos(UtilBean.converter(peso));
            calculadoraPOJO.setPorcentageValorMercadoria(UtilBean.converter(valorMercadoria));
            calculadoraPOJO.setDistanciaQuilometros(new BigDecimal(distancia.toString()));

            calculadoraPOJO = CALCULADORA_RN.calcularFrete(planilha, calculadoraPOJO);
            calculado = true;
            UtilBean.criarMensagemDeInformacao("Calculado!");
        } catch (Exception e) {
            UtilBean.criarMensagemDeErro(e.getMessage());
        }
    }
}
