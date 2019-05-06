/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao;

import br.com.frete.dao.generic.GenericDAO;
import br.com.frete.entity.Endereco;
import br.com.frete.entity.Telefone;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author luciano
 */
public class TransportadoraDAO extends GenericDAO<Transportadora> {

    /**
     * Método transacional que persistirá no banco de dados da transportadora e
     * seu usuário
     *
     * @param u
     * @param t
     * @param e
     * @param f
     * @return
     */
    public boolean criar(Transportadora t, Usuario u, Endereco e, Telefone f) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            getEntityManager().persist(u);
            t.setUsuario(u);
            getEntityManager().persist(t);
            e.setTransportadora(t);
            f.setTransportadora(t);
            getEntityManager().persist(e);
            getEntityManager().persist(f);

            transaction.commit();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            if (transaction != null
                    && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
    
    /**
     * Método que busca uma trasportadora por usuário
     * @param usuario
     * @return 
     */
    public Transportadora obter(Usuario usuario) {
        EntityManager em = getEntityManager();
        String sql = " select t from Transportadora t "
                + " where t.usuario = :usuario ";
        
        Query query = em.createQuery(sql);
        Transportadora resposta = null;
        try {
            List<Transportadora> transportadoras = (List<Transportadora>) query
                    .setParameter("usuario", usuario)
                    .getResultList();
            
            if (transportadoras != null
                    && transportadoras.size() == 1) {
                resposta = transportadoras.get(0);
                em.refresh(resposta);
            }
            return resposta;

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

}
