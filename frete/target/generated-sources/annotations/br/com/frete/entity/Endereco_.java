package br.com.frete.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> cidade;
	public static volatile SingularAttribute<Endereco, String> estado;
	public static volatile SingularAttribute<Endereco, String> numero;
	public static volatile SingularAttribute<Endereco, String> bairro;
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, Double> lon;
	public static volatile SingularAttribute<Endereco, Long> id;
	public static volatile SingularAttribute<Endereco, Double> lat;
	public static volatile SingularAttribute<Endereco, Transportadora> transportadora;
	public static volatile SingularAttribute<Endereco, String> cep;

}

