/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao;

import br.com.frete.dao.generic.GenericDAO;
import br.com.frete.entity.Participante;
import br.com.frete.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author luciano
 */
public class ParticipanteDAO extends GenericDAO<Participante>{
    
    /**
     * Método transacional que persistirá no banco de dados o participante e seu usuário
     * @param u
     * @param p
     * @return 
     */
    public boolean criar(Participante p, Usuario u) {
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
            p.setUsuario(u);
            getEntityManager().persist(p);

            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
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
     * Método que busca uma participante por usuário
     * @param usuario
     * @return 
     */
    public Participante obter(Usuario usuario) {
        EntityManager em = getEntityManager();
        String sql = " select p from Participante p "
                + " where p.usuario = :usuario ";
        
        Query query = em.createQuery(sql);
        Participante resposta = null;
        try {
            List<Participante> participantes = (List<Participante>) query
                    .setParameter("usuario", usuario)
                    .getResultList();
            
            if (participantes != null
                    && participantes.size() == 1) {
                resposta = participantes.get(0);
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
