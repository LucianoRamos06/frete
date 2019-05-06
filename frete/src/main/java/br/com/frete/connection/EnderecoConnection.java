package br.com.frete.connection;

import br.com.frete.pojo.EnderecoResult;
import br.com.frete.util.InternetUtil;
import br.com.frete.util.JSONUtil;

/**
 *
 * @author RAMOS
 */
public class EnderecoConnection {

    private static final String CONTEXTO_URL = "https://viacep.com.br/ws/";
    private static final String FORMATO = "/json";

    private static String montarUrl(String cep) {
        if (cep == null || cep.isEmpty()) {
            return null;
        } else {
            return CONTEXTO_URL + cep + FORMATO;
        }
    }

    public static EnderecoResult acessarServico(String cep) {
        String jsonEndereco = InternetUtil.acessarURL(montarUrl(cep));
        EnderecoResult enderecoResult = JSONUtil.fromString(jsonEndereco, EnderecoResult.class);
        return enderecoResult;
    }
}
