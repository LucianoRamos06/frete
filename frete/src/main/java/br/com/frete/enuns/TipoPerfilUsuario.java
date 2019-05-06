/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.enuns;

/**
 *
 * @author luciano
 */
public enum TipoPerfilUsuario {

    PARTICIPANTE('P', "Participante"),
    ADMINISTRADOR('A', "Administrador"),
    TRANSPORTADORA('T', "Tranpostadora");

    private TipoPerfilUsuario(char tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    private char tipo;
    private String descricao;

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static boolean isAdminstrador(Character tipo) {
        if (tipo == null) {
            return false;
        } else {
            return ADMINISTRADOR.equals(obter(tipo));
        }
    }

    public static boolean isTransportadora(Character tipo) {
        if (tipo == null) {
            return false;
        } else {
            return TRANSPORTADORA.equals(obter(tipo));
        }
    }

    public static boolean isParticipante(Character tipo) {
        if (tipo == null) {
            return false;
        } else {
            return PARTICIPANTE.equals(obter(tipo));
        }
    }
    

    public static TipoPerfilUsuario obter(char tipo) {
        TipoPerfilUsuario resposta = PARTICIPANTE;
        for (TipoPerfilUsuario v : values()) {
            if (v.getTipo() == tipo) {
                resposta = v;
                break;
            }
        }
        return resposta;
    }
}
