/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luciano
 */
@Entity
@Table(name = "regra_de_transporte")
@XmlRootElement
public class RegraDeTransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilometro_inicial")
    private Long kilometroInicial;
    @NotNull
    @Column(name = "kilometro_final")
    private Long kilometroFinal;
    @NotNull
    @Column(name = "kilometro_valor")
    BigDecimal kilometroValor;
    @JoinColumn(name = "planilha", referencedColumnName = "id")
    @ManyToOne
    private Planilha planilha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKilometroInicial() {
        return kilometroInicial;
    }

    public void setKilometroInicial(Long kilometroInicial) {
        this.kilometroInicial = kilometroInicial;
    }

    public Long getKilometroFinal() {
        return kilometroFinal;
    }

    public void setKilometroFinal(Long kilometroFinal) {
        this.kilometroFinal = kilometroFinal;
    }

    public BigDecimal getKilometroValor() {
        return kilometroValor;
    }

    public void setKilometroValor(BigDecimal kilometroValor) {
        this.kilometroValor = kilometroValor;
    }

    public Planilha getPlanilha() {
        return planilha;
    }

    public void setPlanilha(Planilha planilha) {
        this.planilha = planilha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegraDeTransporte)) {
            return false;
        }
        RegraDeTransporte other = (RegraDeTransporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.frete.entity.RegraDeTransporte[ id=" + id + " ]";
    }

}
