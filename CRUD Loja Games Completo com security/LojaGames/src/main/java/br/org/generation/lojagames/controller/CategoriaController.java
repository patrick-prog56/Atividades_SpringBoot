package br.org.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.org.generation.lojagames.model.CategoriaModel;
import br.org.generation.lojagames.repository.CategoriaRepository;

@RestController //indica que essa classe será a controladora 
@RequestMapping ("/categorias")// indica que essa será o começo padrão da URL que vai tratar da classe de categoria, ou seja tipo e descrição, que é o que foi criado na tabela de categoria
@CrossOrigin("*") // este permite que possa conectar com a parte do front end , ele aceita e recebe tudo.
public class CategoriaController {//criado automatico quando cria a classe

	
	@Autowired// da acesso e permite o spring gerenciar todos os procdimentos feitos aqui, 
	private CategoriaRepository repository; // indica que o repositório será pego por meio da classe CategoriaRepositório
	
	
	//==================Find All==============================================================================
	@GetMapping // Dentro deste método Get eu preciso obter algo, ou seja, algo vai ser mostrado a mim por isso é Get. Se eu não passar nada, automaticamente ele pega o começo padrão da URL = ("/categorias") e mostra tudo que vai ter nessa classe, por isso é find All By Id.
	public ResponseEntity<List<CategoriaModel>> GetAll () { // É pego a classe:  "CategoriaModel" pois ele vai mandar pegar tudo que tem nessa classe e ele está dentro de uma lista pois tudo que está dentro dessa classe será colocado nesta lista e ele vai mostrar tudo que está nesta lista.
		return ResponseEntity.ok(repository.findAll()); //caso for ok e essa condição de cima aconteça ele retorna pegando tudo dentro do repositório com find all e joga nessa lista, e mostra tudo do repositório que foi guardado dentro da lista 
		
		
	}
	
	
	//==================Find By Id==========================================
	@GetMapping("/{id}") // Estou passando neste método Get, um endpoint que vai se somar ao anterior ("/categorias") e vai mostrar por id cada categoria de acordo com o Id que eu passar. Também está entre chaves pois é o que será digitado por mim, por isso eu tenho uma anotação PathVariable pois esse valor que vou digitar pode variar
	public ResponseEntity<CategoriaModel> GetById (@PathVariable long id) { // não é passado como lista pois ID é único então a pesquisa não vai trazer varias coisas relacionadas ao mesmo iD, só traz um resultado relacionado aquele id.
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	
	//==================Find By Descrição========================================================
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModel>> GetByDescricao (@PathVariable String descricao) {// Aqui ele e passado como list pois quando eu digitar algo na descrição para procurar ele vai me jogar na lista tudo que for relacionado aquela palavra da descrição que coloquei, ou seja, vai aparecer varias descrições que tem aquela palavra, então ele pode me dar vários resultados.
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));// esse find foi aquela consulta personalizada feita lá na interface repository.
	}
	
	
	//=============================================POST====================================
	@PostMapping
	public ResponseEntity<CategoriaModel> post (@RequestBody CategoriaModel categoria) { //ele vai na CategoriaModel e cria o que for postado lá, por isso o responseEntity CategoriaModel ele chama a body para poder ser possível criar algo, ou seja, aquele formato Json para colocar todas as características através deste @RequestBody, eu passo este ultimo nome como categoria posi está sendo criado uma categoria, e chamei assim pra ficar mais fácil porém poderia ser qualquer nome desde que fosse igaul ao que vou colocar nao final da linha debaixo tbm
		return  ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));// aqui ele retorna um status de ok se der certo o procedimento acima e salva isso no repositório com o nome de categoria, no final da linha eu passso o nome categoria pois é pra bater com o que cooquei no final da linha de cima, tem que ser igual pra que ele salve dentro desta categoria, pois criei esse metodo em cima para fazer essa ação com o nome de categoria.
	}
	
	//=============================================PUT==================================
	@PutMapping
	public ResponseEntity<CategoriaModel> put (@RequestBody CategoriaModel categoria) {//mesma coisa que o post porém muda o método para put, e como ele modifica algo ee vai retornar um "ok" se der certo e não mais created como era no post pois o post cria.
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));	
	}
	
	
	//=============================================DELETE=================================
	@DeleteMapping("/{id}")// passo para excluir pelo Id pois como o ID é único ele va excluir exatamente a postagem ou até especificamente a categoria que eu queira.
	public void delete (@PathVariable long id) { // pathvariable para eu poder digitar algo ali em cimm no id, ou seja ele varia esse delete dependendo do que eu digitar nesse id, se eu digitar 1 o id 1 de uma postagem ou categoria, se eu digitar 2 ele apaga o que está no id 2 ou de postagem ou de categoria.
		repository.deleteById(id);
	}






}
