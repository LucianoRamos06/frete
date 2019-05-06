/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao;

import br.com.frete.dao.generic.GenericDAO;
import br.com.frete.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author luciano
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    /**
     * Método que verifica se o login de um usuário está correto
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean autenticar(String login, String senha) {
        EntityManager em = getEntityManager();
        String sql = "select u from Usuario u "
                + "where u.login = :login "
                + "and u.senha = :senha ";
        Query query = em.createQuery(sql);
        try {
            Usuario resposta = null;
            List<Usuario> contas = (List<Usuario>) query
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getResultList();
            if (contas != null
                    && contas.size() == 1) {
                resposta = contas.get(0);
                em.refresh(resposta);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return false;
    }

    /**
     * DAO que obtem um usuário pelo seu login
     * @param login
     * @return 
     */
    public Usuario obter(String login) {
        EntityManager em = getEntityManager();
        String sql = "select u from Usuario u "
                + "where u.login = :login ";
        Query query = em.createQuery(sql);
        Usuario resposta = null;
        try {
            List<Usuario> contas = (List<Usuario>) query
                    .setParameter("login", login)
                    .getResultList();
            if (contas != null
                    && contas.size() == 1) {
                resposta = contas.get(0);
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
