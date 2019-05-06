/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.frete.dao.generic;

import java.util.List;

/**
 *
 * @author fabio
 */
public interface InterfaceDAO<T> {
    public boolean criar(T o);
    public boolean alterar(T o);
    public boolean excluir(T o);
    public T obter(Class<T> classe, Object id);
    public List<T> obterTodos(Class<T> classe);
}
