/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.rn;

import br.com.frete.dao.PlanilhaDAO;
import br.com.frete.entity.Planilha;
import br.com.frete.entity.Transportadora;
import javax.xml.bind.ValidationException;

/**
 *
 * @author luciano
 */
public class PlanilhaRN {

    private final PlanilhaDAO PLANILHA_DAO = new PlanilhaDAO();

    public void salvar(Planilha planilha) throws ValidationException {

        boolean resultado = false;

        if (planilha.getId() == null) {
            resultado = PLANILHA_DAO.criar(planilha);
        } else {
            resultado = PLANILHA_DAO.alterar(planilha);
        }

        if (!resultado) {
            throw new ValidationException("Não foi possível salvar!");
        }
    }

    public Planilha obter(Transportadora tranportadora) {
        return PLANILHA_DAO.obter(tranportadora);
    }
}
