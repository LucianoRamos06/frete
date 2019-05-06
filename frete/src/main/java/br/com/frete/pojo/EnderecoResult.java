package br.com.frete.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author RAMOS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoResult implements Serializable{

    private String cep;
    private String logradouro;
    private String complemento;
    private String localidade;
    private String uf;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isSucesso() {
        return (cep != null || !cep.isEmpty()
                && logradouro != null || !logradouro.isEmpty()
                && localidade != null || !localidade.isEmpty()
                && uf != null || !uf.isEmpty());
    }
}
