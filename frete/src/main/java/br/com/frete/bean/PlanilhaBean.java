/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.bean;

import br.com.frete.entity.Planilha;
import br.com.frete.entity.Transportadora;
import br.com.frete.rn.PlanilhaRN;
import br.com.frete.rn.TransportadoraRN;
import br.com.frete.util.UtilBean;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luciano
 */
@ManagedBean
@ViewScoped
public class PlanilhaBean {

    //Atributos
    private Transportadora transportadora = new Transportadora();
    private Planilha planilha = new Planilha();

    private String percentualGris = new String();
    private String percentualValorFrete = new String();
    private String valorDespacho = new String();
    private String valorQuilometro = new String();

    //RN's
    private final TransportadoraRN TRANSPORTADORA_RN = new TransportadoraRN();
    private final PlanilhaRN PLANILHA_RN = new PlanilhaRN();

    @PostConstruct
    public void init() {
        transportadora = TRANSPORTADORA_RN.obter(UtilBean.obterContaLogada());
        planilha = PLANILHA_RN.obter(transportadora);
    }

    //GETTER's e SETTER's
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

    public String getPercentualGris() {
        return percentualGris;
    }

    public void setPercentualGris(String percentualGris) {
        this.percentualGris = percentualGris;
    }

    public String getPercentualValorFrete() {
        return percentualValorFrete;
    }

    public void setPercentualValorFrete(String percentualValorFrete) {
        this.percentualValorFrete = percentualValorFrete;
    }

    public String getValorDespacho() {
        return valorDespacho;
    }

    public void setValorDespacho(String valorDespacho) {
        this.valorDespacho = valorDespacho;
    }

    public String getValorQuilometro() {
        return valorQuilometro;
    }

    public void setValorQuilometro(String valorQuilometro) {
        this.valorQuilometro = valorQuilometro;
    }

    public void salvar() {
        try {
            if (planilha == null) planilha = new Planilha();
            
            planilha.setPercentualCris(new BigDecimal(percentualGris));
            planilha.setPercentualValorFrete(new BigDecimal(percentualValorFrete));
            planilha.setValorDespascho(UtilBean.converter(valorDespacho));
            planilha.setValorPadraoKilometro(UtilBean.converter(valorQuilometro));
            planilha.setTransportadora(transportadora);
            PLANILHA_RN.salvar(planilha);
            UtilBean.criarMensagemDeInformacao("Planilha Salva!");
        } catch (Exception e) {
            UtilBean.criarMensagemDeErro(e.getMessage());
        }
    }

}
