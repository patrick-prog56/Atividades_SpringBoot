package br.org.generation.lojagames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



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
	
	
	//relacionamento está sendo feito abaixo.
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)// One to Many pois uma categoria pode ter muitos produtos. Vai ser mapeado pela categoria e o cascade serve pra dizer que a categoria vai mandar emtudo, ou seja, se eu apagar a categoria, apago todos os produtos que tem dentro dela. O que alterar em categoria altera tudo que está dentro dela= mappedBy
	@JsonIgnoreProperties("categoria")//pra não gerar o looping, deve ser categoria pois está na classe CategoriaModel.
	private List<ProdutoModel> produto;// dentro da categoria eu faço uma lista de todos os produtos que vai nesta categoria, foi feio lista pois podem ser vários produtos dentro dessa categoria. ele faz isso para todas as categorias faz a lista de todos os produtos que pertencem a categoria inclusive fiz também o getter and setter depois que fiz o relacionamento, não pode esquecer disso. 

	
	//Abaixo eu fiz o generate dos getters and setters para poder usar os atributos acima dessa classe em outras classes.
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

	public List<ProdutoModel> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoModel> produto) {
		this.produto = produto;
	}


	
	

}
