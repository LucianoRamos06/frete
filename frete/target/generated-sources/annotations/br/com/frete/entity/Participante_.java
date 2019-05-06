package br.com.frete.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Participante.class)
public abstract class Participante_ {

	public static volatile SingularAttribute<Participante, Telefone> telefone;
	public static volatile SingularAttribute<Participante, String> foto;
	public static volatile SingularAttribute<Participante, Endereco> endereco;
	public static volatile SingularAttribute<Participante, String> cpf;
	public static volatile SingularAttribute<Participante, String> nome;
	public static volatile SingularAttribute<Participante, Usuario> usuario;
	public static volatile SingularAttribute<Participante, Long> id;
	public static volatile SingularAttribute<Participante, Date> cadastro;
	public static volatile SingularAttribute<Participante, Character> sexo;

}

