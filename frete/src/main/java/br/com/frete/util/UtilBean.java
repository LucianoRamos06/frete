/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.util;

import br.com.frete.entity.Usuario;
import br.com.frete.rn.UsuarioRN;
import java.math.BigDecimal;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author bpmlab
 */
public class UtilBean {

    /**
     * Recupera, do escopo da requisição, o valor do parâmetro com nome
     * informado.
     *
     * @param parametro nome do parâmetro que guarda o valor que se deseja
     * recuperar
     * @return
     */
    public static String obterValor(String parametro) {
        if (parametro == null) {
            return null;
        } else {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            return currentInstance.getExternalContext().getRequestParameterMap().get(parametro);
        }
    }

    public static void adicionarValor(String parametro, String valor) {
        if (parametro != null && valor != null) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.getExternalContext().getRequestParameterMap().put(parametro, valor);
        }
    }

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeInformacao(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, "Alerta", detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        currentInstance.addMessage(null, fm);
    }

    public static String obterEmailDaSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext e = facesContext.getExternalContext();
        if (e != null) {
            return e.getRemoteUser();
        } else {
            return null;
        }
    }

    public static Object daSessao(String chave) {
        if (chave == null) {
            return null;
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessao = externalContext.getSessionMap();
            return sessao.get(chave);
        }
    }

    public static void naSessao(String chave, Object valor) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessao = externalContext.getSessionMap();
        //Preciosismo: remover antes de inserir
        sessao.remove(chave);
        sessao.put(chave, valor);
    }

    public static Usuario obterContaLogada() {
        final String CHAVE_CONTA = "conta";
        Object objUsuario = UtilBean.daSessao(CHAVE_CONTA);
        if (objUsuario == null) {
            String login = UtilBean.obterEmailDaSessao();
            UsuarioRN USUARIO_RN = new UsuarioRN();
            Usuario usuario = USUARIO_RN.obter(login);
            Usuario usuarioTemp = null;
            //Clonando o usuário logado
            if (usuario != null) {
                usuarioTemp = new Usuario();
                usuarioTemp.setId(usuario.getId());
                usuarioTemp.setLogin(usuario.getLogin());
                usuarioTemp.setLogin(usuario.getLogin());
                usuarioTemp.setPerfil(usuario.getPerfil());
            }
            UtilBean.naSessao(CHAVE_CONTA, usuarioTemp);
            return usuarioTemp;
        } else {
            return (Usuario) objUsuario;
        }
    }

    public static void atualizarContaLogada(Usuario usuario) {
        final String CHAVE_PESSOA = "conta";
        if (usuario != null) {
            naSessao(CHAVE_PESSOA, usuario);
        }
    }

    public static BigDecimal converter(String valor) {
        valor = valor.replace(".", "").replace(",", ".");
        return new BigDecimal(valor);
    }

}
