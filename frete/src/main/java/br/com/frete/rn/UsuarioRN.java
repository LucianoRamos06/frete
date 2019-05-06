/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.rn;

import br.com.frete.connection.EnderecoConnection;
import br.com.frete.dao.ParticipanteDAO;
import br.com.frete.dao.TransportadoraDAO;
import br.com.frete.dao.UsuarioDAO;
import br.com.frete.entity.Participante;
import br.com.frete.entity.Endereco;
import br.com.frete.entity.Telefone;
import br.com.frete.entity.Transportadora;
import br.com.frete.entity.Usuario;
import br.com.frete.pojo.EnderecoResult;
import br.com.frete.util.FreteUtil;
import javax.xml.bind.ValidationException;

/**
 *
 * @author luciano
 */
public class UsuarioRN {

    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    /**
     * Método responsável por validar o login de um usuário
     *
     * @param email
     * @param senha
     * @return
     */
    public boolean autenticar(String email, String senha) {
        return USUARIO_DAO.autenticar(email, senha);
    }

    /**
     * Método que obtem um usuário por login
     *
     * @param login
     * @return
     */
    public Usuario obter(String login) {
        return USUARIO_DAO.obter(login);
    }

    /**
     * Método que valida e salva um participante
     *
     * @param u
     * @param p
     * @param senhaConfirmacao
     * @return
     * @throws ValidationException
     */
    public boolean criarConta(Usuario u, Participante p, String senhaConfirmacao) throws ValidationException {
        if (u == null || p == null || senhaConfirmacao == null) {
            throw new ValidationException("Preencha todos os campos");
        }
        if (!u.getSenha().equals(senhaConfirmacao)) {
            throw new ValidationException("As senhas digitadas são diferentes");
        }

        if (!FreteUtil.isCPFouCNPJ(p.getCpf())) {
            throw new ValidationException("CPF inválido");
        }

        u.setSenha(FreteUtil.encriptarSHA256(u.getSenha()));
        p.setCpf(FreteUtil.removeSimbolosPontuacoes(p.getCpf()));

        final ParticipanteDAO PARTICIPANTE_DAO = new ParticipanteDAO();
        return PARTICIPANTE_DAO.criar(p, u);
    }

    /**
     * Método que valida e salva um participante
     *
     * @param u
     * @param t
     * @param senhaConfirmacao
     * @param cep
     * @param fone
     * @return
     * @throws ValidationException
     */
    public boolean criarConta(Usuario u, Transportadora t, String senhaConfirmacao, String cep, String fone) throws ValidationException {
        if (u == null || t == null || senhaConfirmacao == null || cep == null || fone == null) {
            throw new ValidationException("Preencha todos os campos");
        }
        if (!u.getSenha().equals(senhaConfirmacao)) {
            throw new ValidationException("As senhas digitadas são diferentes");
        }

        if (!FreteUtil.isCPFouCNPJ(t.getCnpj())) {
            throw new ValidationException("CNPJ inválido");
        }

        if (!FreteUtil.isCep(cep)) {
            throw new ValidationException("CEP inválido");
        }

        cep = FreteUtil.removeSimbolosPontuacoes(cep);
        EnderecoResult enderecoResult = EnderecoConnection.acessarServico(cep);

        if (!enderecoResult.isSucesso()) {
            throw new ValidationException("CEP não encontrado!");
        }
        
        fone = FreteUtil.removeCharacterPhone(fone);
        if(!FreteUtil.isCelularOuFixo(fone)){
            throw new ValidationException("Telefone inválido!");
        }

        
        Endereco e = new Endereco();
        Telefone f = new Telefone();
        
        u.setSenha(FreteUtil.encriptarSHA256(u.getSenha()));
        t.setCnpj(FreteUtil.removeSimbolosPontuacoes(t.getCnpj()));
        
        e.setEstado(enderecoResult.getUf());
        e.setEstado(enderecoResult.getLogradouro());
        e.setCidade(enderecoResult.getLocalidade());
        e.setCep(cep);
        
        f.setDdd(fone.substring(0, 2));
        f.setNumero(fone.substring(2, fone.length()));
        
        final TransportadoraDAO TRANSPORTADORA_DAO = new TransportadoraDAO();
        return TRANSPORTADORA_DAO.criar(t, u, e, f);
    }
}
