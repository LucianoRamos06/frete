/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luciano
 */
@Entity
@Table(name = "transportadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportadora.findAll", query = "SELECT t FROM Transportadora t"),
    @NamedQuery(name = "Transportadora.findById", query = "SELECT t FROM Transportadora t WHERE t.id = :id")
})
public class Transportadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cadastro;
    @Size(max = 500)
    @Column(name = "foto")
    private String foto;
    @Size(max = 500)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Size(max = 500)
    @Column(name = "razao_social")
    private String razaoSocial;
    @Size(max = 14)
    @Column(name = "cnpj")
    private String cnpj;
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "planilha", referencedColumnName = "id")
    @OneToOne(optional = true)
    private Planilha planilha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportadora")
    private List<Telefone> telefones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportadora")
    private List<Endereco> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Planilha getPlanilha() {
        return planilha;
    }

    public void setPlanilha(Planilha planilha) {
        this.planilha = planilha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.cadastro);
        hash = 89 * hash + Objects.hashCode(this.foto);
        hash = 89 * hash + Objects.hashCode(this.nomeFantasia);
        hash = 89 * hash + Objects.hashCode(this.razaoSocial);
        hash = 89 * hash + Objects.hashCode(this.cnpj);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transportadora other = (Transportadora) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cadastro, other.cadastro)) {
            return false;
        }
        if (!Objects.equals(this.foto, other.foto)) {
            return false;
        }
        if (!Objects.equals(this.nomeFantasia, other.nomeFantasia)) {
            return false;
        }
        if (!Objects.equals(this.razaoSocial, other.razaoSocial)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transportadora{" + "id=" + id + ", cadastro=" + cadastro + ", foto=" + foto + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + '}';
    }

}
