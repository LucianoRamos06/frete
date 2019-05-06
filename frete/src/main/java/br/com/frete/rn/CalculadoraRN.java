/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.rn;

import br.com.frete.connection.DistanciaConnection;
import br.com.frete.entity.Planilha;
import br.com.frete.pojo.CalculadoraPOJO;
import br.com.frete.pojo.DistanciaResult;
import br.com.frete.util.FreteUtil;
import java.math.BigDecimal;
import javax.xml.bind.ValidationException;

/**
 *
 * @author luciano
 */
public class CalculadoraRN {

    public double calcularDistancia(String cepOrigem, String cepDestino) throws ValidationException {
        if (cepOrigem == null || cepDestino == null) {
            throw new ValidationException("Digite os dois CEP's");
        }

        if (!FreteUtil.isCep(cepOrigem)) {
            throw new RuntimeException("CEP de origem inválido!");
        }

        if (!FreteUtil.isCep(cepDestino)) {
            throw new RuntimeException("CEP de destino inválido!");
        }

        DistanciaResult distancia = DistanciaConnection.acessarServico(cepOrigem, cepDestino);

        return distancia.quilometros();
    }

    public CalculadoraPOJO calcularFrete(Planilha p, CalculadoraPOJO c) {
        BigDecimal pesoOuCubagem;

        if (c.getPesoKilos().compareTo(c.getCubagem()) == 1) {
            pesoOuCubagem = c.getPesoKilos();
        } else {
            pesoOuCubagem = c.getCubagem();
        }

        BigDecimal valorFrete = c.getPesoKilos().multiply(p.getValorPadraoKilometro().multiply(c.getDistanciaQuilometros()));
        BigDecimal valorMercadoria = c.getPorcentageValorMercadoria().multiply(p.getPercentualValorFrete());
        BigDecimal valorGris = c.getPorcentageValorMercadoria().multiply(p.getPercentualCris());

        BigDecimal freteBase = valorFrete.add(p.getValorDespascho()).add(valorMercadoria).add(valorGris);

        c.setValorGris(valorGris);
        c.setValorMercadoria(valorMercadoria);
        c.setValorFrete(valorFrete);
        c.setValorTotal(freteBase);
        
        return c;

    }

}
