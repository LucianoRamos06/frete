package br.com.frete.bean;

import br.com.frete.entity.Participante;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import br.com.frete.enuns.TipoPerfilUsuario;
import br.com.frete.rn.UsuarioRN;
import br.com.frete.util.UtilBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.ValidationException;

@ManagedBean
@ViewScoped
public class ContaBean {

    private Usuario usuario = new Usuario();
    private Transportadora transportadora = new Transportadora();
    private Participante participante = new Participante();

    private String include = new String();
    private String tipoConta = new String();
    private String confirmarSenha = new String();
    private String cep = new String();
    private String fone = new String();

    private final UsuarioRN USUARIO_RN = new UsuarioRN();

    @PostConstruct
    public void init() {
        tipoFormulario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    public void tipoFormulario() {
        if (tipoConta == null || tipoConta.equals("")) {
            this.include = "_formParticipante.xhtml";
        } else {
            if (tipoConta.charAt(0) == (TipoPerfilUsuario.PARTICIPANTE.getTipo())) {
                this.include = "_formParticipante.xhtml";
            } else if (tipoConta.charAt(0) == (TipoPerfilUsuario.TRANSPORTADORA.getTipo())) {
                this.include = "_formTransportadora.xhtml";
            }
        }
    }

    public void criarContaParticipante() {
        try {
            usuario.setPerfil(tipoConta.charAt(0));
            if (USUARIO_RN.criarConta(usuario, participante, confirmarSenha)) {
                UtilBean.criarMensagemDeInformacao("Cadastro concluído!");
                limparVariaveis();
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível realizar o cadastro");
            }
        }  catch (ValidationException ex) {
            UtilBean.criarMensagemDeAviso(ex.getMessage());
        } catch (Exception ex) {
            UtilBean.criarMensagemDeErro("Um erro inesperado aconteceu");
        }
    }

    public void criarContaTransportadora() {
        try {
            usuario.setPerfil(tipoConta.charAt(0));
            if (USUARIO_RN.criarConta(usuario, transportadora, confirmarSenha, cep, fone)) {
                UtilBean.criarMensagemDeInformacao("Cadastro concluído!");
                limparVariaveis();
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível realizar o cadastro");
            }
        }  catch (ValidationException ex) {
            UtilBean.criarMensagemDeAviso(ex.getMessage());
        } catch (Exception ex) {
            UtilBean.criarMensagemDeErro("Um erro inesperado aconteceu");
        }
    }


    private void limparVariaveis() {
        usuario = new Usuario();
        participante = new Participante();
        transportadora = new Transportadora();
        confirmarSenha = new String();
        cep = new String();
        fone = new String();
    }
}
