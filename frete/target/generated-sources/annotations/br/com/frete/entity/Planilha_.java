package br.com.frete.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Planilha.class)
public abstract class Planilha_ {

	public static volatile SingularAttribute<Planilha, BigDecimal> valorPadraoKilometro;
	public static volatile SingularAttribute<Planilha, BigDecimal> percentualValorFrete;
	public static volatile ListAttribute<Planilha, RegraDeTransporte> regrasDeTransporte;
	public static volatile SingularAttribute<Planilha, BigDecimal> percentualCris;
	public static volatile SingularAttribute<Planilha, BigDecimal> valorDespascho;
	public static volatile SingularAttribute<Planilha, Long> id;
	public static volatile SingularAttribute<Planilha, Transportadora> transportadora;

}

