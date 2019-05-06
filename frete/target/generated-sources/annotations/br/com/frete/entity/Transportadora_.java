package br.com.frete.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transportadora.class)
public abstract class Transportadora_ {

	public static volatile SingularAttribute<Transportadora, String> nomeFantasia;
	public static volatile SingularAttribute<Transportadora, String> foto;
	public static volatile ListAttribute<Transportadora, Endereco> enderecos;
	public static volatile SingularAttribute<Transportadora, Usuario> usuario;
	public static volatile SingularAttribute<Transportadora, Long> id;
	public static volatile SingularAttribute<Transportadora, Date> cadastro;
	public static volatile SingularAttribute<Transportadora, String> cnpj;
	public static volatile SingularAttribute<Transportadora, String> razaoSocial;
	public static volatile ListAttribute<Transportadora, Telefone> telefones;
	public static volatile SingularAttribute<Transportadora, Planilha> planilha;

}

