package br.org.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojagames.model.CategoriaModel;


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel,Long> {// aqui ele extende para a Jpa para que ela faça a comunicação com a model pois lá foi feito todo o escopo da criação do banco de dados, aqui fará a ligação e execução disto e o Long é como se fosse uma classe com diversos objetos que irão ser utilizados pelo repository

	
	
	public List<CategoriaModel> findAllByDescricaoContainingIgnoreCase (String descricao);// Essa classe é interface pelo motivo de aqui estar sendo feito apenas uma consulta personalizada, porém aqui dentro desta interface para poder usar na classe controller, não fiz feito do get all nem do get by id pois esses já são nativos  do Spring, ou seja, qualquer consulta que eu queira fazer que não seja nativo do spring eu devo fazer primeiro ela aqui como se fosse uma assinatura na repository para depois poder utilizar na controller
	//a interface não deixa instanciar seus objetos em outras classes, por isso aima está sendo feita apenas uma assinatura para eu poder usar na controller, por esse motivo eu tenho que reafzer lá também, não posso transformar em objeto e só chamar lá.
}
