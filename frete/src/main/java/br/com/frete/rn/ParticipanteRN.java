/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.rn;

import br.com.frete.dao.ParticipanteDAO;
import br.com.frete.entity.Participante;
import br.com.frete.entity.Usuario;

/**
 *
 * @author luciano
 */
public class ParticipanteRN {

    private final ParticipanteDAO PARTICIPANTE_DAO = new ParticipanteDAO();

    public Participante obter(Usuario usuario) {
        return PARTICIPANTE_DAO.obter(usuario);
    }
}
