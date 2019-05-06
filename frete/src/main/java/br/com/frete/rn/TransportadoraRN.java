/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.rn;

import br.com.frete.dao.TransportadoraDAO;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import java.util.List;

/**
 *
 * @author luciano
 */
public class TransportadoraRN {
    
    private final TransportadoraDAO TRANSPORTADORA_DAO = new TransportadoraDAO();

    public List<Transportadora> listar(){
        return TRANSPORTADORA_DAO.obterTodos(Transportadora.class);
    }
    
    public Transportadora obter(Usuario usuario){
        return TRANSPORTADORA_DAO.obter(usuario);
    }
    
    public Transportadora obter(Long id){
        return TRANSPORTADORA_DAO.obter(Transportadora.class, id);
    }
}
