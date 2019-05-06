/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao;

import br.com.frete.entity.Participante;
import br.com.frete.entity.Telefone;
import br.com.frete.entity.Usuario;

/**
 *
 * @author luciano
 */
public class Main {
    public static void main(String[] args) {
        Participante p = new Participante();
        Telefone t = new Telefone();
        Usuario u = new Usuario();
        
        u.setPerfil("U".charAt(0));
        u.setLogin("carlos.eduardo");
        u.setSenha("123456");
        
        t.setDdd("91");
        t.setNumero("981132452");
        
        p.setNome("Carlos Eduardo");
        p.setCpf("01756798715");
        p.setSexo("M".charAt(0));
//        p.setTelefone(t);
//        p.setUsuario(u);
        
        ParticipanteDAO dao = new ParticipanteDAO();
        dao.criar(p, u);
       
    }
}
