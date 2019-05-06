/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luciano
 */
@Entity
@Table(name = "planilha")
@XmlRootElement
public class Planilha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "valor_despacho")
    BigDecimal valorDespascho;
    @NotNull
    @Column(name = "valor_padrao_kilometro")
    BigDecimal valorPadraoKilometro;
    @NotNull
    @Column(name = "percentual_valor_frete")
    BigDecimal percentualValorFrete;
    @NotNull
    @Column(name = "percentual_cris")
    BigDecimal percentualCris;
    @JoinColumn(name = "transportadora", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Transportadora transportadora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planilha")
    private List<RegraDeTransporte> regrasDeTransporte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorDespascho() {
        return valorDespascho;
    }

    public void setValorDespascho(BigDecimal valorDespascho) {
        this.valorDespascho = valorDespascho;
    }

    public BigDecimal getValorPadraoKilometro() {
        return valorPadraoKilometro;
    }

    public void setValorPadraoKilometro(BigDecimal valorPadraoKilometro) {
        this.valorPadraoKilometro = valorPadraoKilometro;
    }

    public BigDecimal getPercentualValorFrete() {
        return percentualValorFrete;
    }

    public void setPercentualValorFrete(BigDecimal percentualValorFrete) {
        this.percentualValorFrete = percentualValorFrete;
    }

    public BigDecimal getPercentualCris() {
        return percentualCris;
    }

    public void setPercentualCris(BigDecimal percentualCris) {
        this.percentualCris = percentualCris;
    }

    public List<RegraDeTransporte> getRegrasDeTransporte() {
        return regrasDeTransporte;
    }

    public void setRegrasDeTransporte(List<RegraDeTransporte> regrasDeTransporte) {
        this.regrasDeTransporte = regrasDeTransporte;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
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
        if (!(object instanceof Planilha)) {
            return false;
        }
        Planilha other = (Planilha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.frete.entity.Planilha[ id=" + id + " ]";
    }

}
