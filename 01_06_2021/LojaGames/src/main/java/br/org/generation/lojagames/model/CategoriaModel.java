package br.org.generation.lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity //model que gera uma tabela
@Table(name = "tb_categoria") //aonde vai o nome da tabela
public class CategoriaModel {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private long id;
	
	
	@NotNull
	private String tipo; // Nome da categoria 
	
	@NotNull
	private String descricao; // Descrição da categoria

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	
	

}
