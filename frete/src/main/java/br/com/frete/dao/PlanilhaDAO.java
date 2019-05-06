/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao;

import br.com.frete.dao.generic.GenericDAO;
import br.com.frete.entity.Planilha;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author luciano
 */
public class PlanilhaDAO extends GenericDAO<Planilha>{
    
    public Planilha obter(Transportadora transportadora) {
        EntityManager em = getEntityManager();
        String sql = " select p from Planilha p "
                + " where p.transportadora = :transportadora ";
        
        Query query = em.createQuery(sql);
        Planilha resposta = null;
        try {
            List<Planilha> planilhas = (List<Planilha>) query
                    .setParameter("transportadora", transportadora)
                    .getResultList();
            
            if (planilhas != null
                    && planilhas.size() == 1) {
                resposta = planilhas.get(0);
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
