package br.com.frete.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MenuBean {

    public MenuBean() {

    }

    //////////////PUBLICO//////////////
    //////////////////////////////////
    public String irCriarConta() {
        return "/publico/criar-conta.xhtml";
    }

    public String irLogin() {
        return "/publico/login.xhtml";
    }
    
    public String irCalculadora() {
        return "/publico/calculadora.xhtml";
    }

    public String irListarTrasnportadoras() {
        return "/publico/transportadora/listar.xhtml";
    }

    ////////////////TRANSPORTADORA//////////////
    ///////////////////////////////////////////
    public String irPlanilha() {
        return "/transportadora/planilha.xhtml";
    }
    
    public String irPlanilhaAtual() {
        return "/transportadora/planilha-atual.xhtml";
    }
    
    ////////////////USUARIO//////////////
    ///////////////////////////////////////////
    public String irDetalhesUsuario() {
        return "/usuario/index.xhtml";
    }
  
}
