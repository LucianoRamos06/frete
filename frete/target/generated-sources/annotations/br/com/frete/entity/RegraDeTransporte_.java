package br.com.frete.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RegraDeTransporte.class)
public abstract class RegraDeTransporte_ {

	public static volatile SingularAttribute<RegraDeTransporte, Long> kilometroInicial;
	public static volatile SingularAttribute<RegraDeTransporte, Long> id;
	public static volatile SingularAttribute<RegraDeTransporte, Long> kilometroFinal;
	public static volatile SingularAttribute<RegraDeTransporte, BigDecimal> kilometroValor;
	public static volatile SingularAttribute<RegraDeTransporte, Planilha> planilha;

}

