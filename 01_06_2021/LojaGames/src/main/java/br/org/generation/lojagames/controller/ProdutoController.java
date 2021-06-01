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

import br.org.generation.lojagames.model.ProdutoModel;
import br.org.generation.lojagames.repository.ProdutoRepository;




@RestController
@RequestMapping ("/produtos")
@CrossOrigin("*")
public class ProdutoController {

	
	@Autowired
	private ProdutoRepository repository;
	
	
	//==================Find All============================
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> GetAll (){
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	
	//==================Find By Id==========================================
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> GetById (@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	
	//==================Find By Nome=================================================================
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoModel>> GetByNome (@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	//=============================================POST====================================
	@PostMapping
	public ResponseEntity<ProdutoModel> post (@RequestBody ProdutoModel nome) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(repository.save(nome));
	}
	
	//=============================================PUT=================================
	@PutMapping
	public ResponseEntity<ProdutoModel> put (@RequestBody ProdutoModel nome) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(nome));	
	}
	
	
	//=============================================DELETE=================================
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}






}
