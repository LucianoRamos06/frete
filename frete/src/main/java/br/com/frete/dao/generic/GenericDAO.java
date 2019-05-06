/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.dao.generic;

import java.util.List;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class GenericDAO<T> implements InterfaceDAO<T> {

    private EntityManager entityManager;

    public GenericDAO() {
    }

    protected EntityManager getEntityManager() {
        if (entityManager == null
                || !entityManager.isOpen()) {
            entityManager = FabricaEntityManager.obterFabrica().createEntityManager();
        }
        return entityManager;
    }

    protected void closeEntityManager() {
        if (entityManager != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            //Conclui a transação ativa, se existir
            if (transaction != null
                    && transaction.isActive()) {
                transaction.rollback();
            }

            //Fecha o EntityManager (release ao pool)
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean criar(T o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            getEntityManager().persist(o);
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

    @Override
    public boolean excluir(T o) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            em.remove(entityManager.merge(o));
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

    @Override
    public boolean alterar(T o) {
//        System.out.println("GenericDAO.alterar");
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            getEntityManager().merge(o);
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

    @Override
    public T obter(Class<T> classe, Object id) {
//        System.out.println("GenericDAO.obter(" + classe + ")");
        if (id == null) {
            return null;
        }
        String query = classe.getSimpleName() + ".findById";
        EntityManager em = getEntityManager();
        final TypedQuery<T> q = em.createNamedQuery(query, classe);
        T resposta = null;
        try {
            List<T> objs = null;
            objs = q.setParameter("id", id).getResultList();
            if (objs != null && !objs.isEmpty()) {
                resposta = objs.get(0);
                em.refresh(resposta);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    @Override
    public List<T> obterTodos(Class<T> classe) {
//        System.out.println("GenericDAO.obterTodos(" + classe + ")");
        String query = classe.getSimpleName() + ".findAll";
        EntityManager em = getEntityManager();
        TypedQuery<T> q = em.createNamedQuery(query, classe);
        List<T> resposta = null;
        try {
            resposta = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<T> obterLista(Class<T> classe,
            List<String> criterios,
            List valores,
            final boolean AND) {
//        System.out.println("GenericDAO.obterLista");
        if (criterios == null
                || valores == null
                || criterios.size() != valores.size()
                || criterios.size() < 1) {
            return null;
        }
        String query = "SELECT o FROM " + classe.getSimpleName() + " o WHERE ";
        query += " o." + criterios.get(0) + " = :" + criterios.get(0);
        final String CONECTIVO = AND ? " AND " : " OR ";
        for (int i = 1; i < criterios.size(); i++) {
            query += CONECTIVO + " o." + criterios.get(i) + " = :" + criterios.get(i);
        }
        EntityManager em = getEntityManager();
        final TypedQuery<T> q = em.createQuery(query, classe);
        List<T> resposta = null;
        try {
            for (int i = 0; i < valores.size(); i++) {
                q.setParameter(criterios.get(i), valores.get(i));
            }
            resposta = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public void limparContexto() {
//        System.out.println("GenericDAO.limparContexto");
        try {
            if (getEntityManager() != null
                    && getEntityManager().isOpen()) {
                getEntityManager().clear();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void limparCache() {
//        System.out.println("GenericDAO.limparCache");
        try {
            if (entityManager != null && entityManager.isOpen()) {
                Cache cache = entityManager.getEntityManagerFactory().getCache();
                if (cache != null) {
                    cache.evictAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void limparCache(Class<T> classe) {
//        System.out.println("GenericDAO.limparCache(" + classe + ")");
        try {
            if (entityManager != null && entityManager.isOpen()) {
                Cache cache = entityManager.getEntityManagerFactory().getCache();
                if (cache != null) {
                    cache.evict(classe);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
