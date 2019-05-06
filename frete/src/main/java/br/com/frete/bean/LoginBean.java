/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.bean;

import br.com.frete.entity.Usuario;
import br.com.frete.enuns.TipoPerfilUsuario;
import br.com.frete.util.UtilBean;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author AdminBPMLAB
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    public boolean erroAoAutenticar(Map<String, String> parametrosDaRequisicao) {
        boolean resposta = false;
        for (String chave : parametrosDaRequisicao.keySet()) {
            if (chave.equals("e") && parametrosDaRequisicao.get(chave).equals("-1")) {
                resposta = true;
                break;
            }
        }
        return resposta;
    }

    public Usuario getContaLogada() {
        return UtilBean.obterContaLogada();
    }

    public boolean isAdministrador() {
        if (getContaLogada() == null) {
            return Boolean.FALSE;
        }
        return TipoPerfilUsuario.isAdminstrador(getContaLogada().getPerfil());
    }

    public boolean isTransportadora() {
        if (getContaLogada() == null) {
            return Boolean.FALSE;
        }
        return TipoPerfilUsuario.isTransportadora(getContaLogada().getPerfil());

    }

    public boolean isParticipante() {
        if (getContaLogada() == null) {
            return Boolean.FALSE;
        }
        return TipoPerfilUsuario.isParticipante(getContaLogada().getPerfil());
    }

}
