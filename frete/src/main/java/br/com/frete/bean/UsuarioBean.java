/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.bean;

import br.com.frete.entity.Participante;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import br.com.frete.rn.ParticipanteRN;
import br.com.frete.rn.TransportadoraRN;
import br.com.frete.util.UtilBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luciano
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private Transportadora transportadora = new Transportadora();
    private Participante participante = new Participante();
    private String formUsuario = new String();
    
    private final ParticipanteRN PARTICIPANTE_RN = new ParticipanteRN();
    private final TransportadoraRN TRANSPORTADORA_RN = new TransportadoraRN();

    @PostConstruct
    public void init() {
        usuario = UtilBean.obterContaLogada();

        if (usuario.getPerfil() == 'P') {
            formUsuario = "_formPerfilParticipante.xhtml";
            participante = PARTICIPANTE_RN.obter(usuario);
        } else {
            formUsuario = "_formPerfilTransportadora.xhtml";
            transportadora = TRANSPORTADORA_RN.obter(usuario);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public String getFormUsuario() {
        return formUsuario;
    }

    public void setFormUsuario(String formUsuario) {
        this.formUsuario = formUsuario;
    }
}
