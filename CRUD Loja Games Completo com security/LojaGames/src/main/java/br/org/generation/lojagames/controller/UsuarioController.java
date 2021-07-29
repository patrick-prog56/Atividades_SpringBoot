package br.org.generation.lojagames.controller;
//A funão desta classe é só verificar se deu certo tudo e mandar a resposta que é logar, cadastrar ou dar o erro se algo aconteceu errado.


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.org.generation.lojagames.model.UserLogin;
import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.service.UsuarioService;

@RestController
@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/Usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autentication (@RequestBody Optional<UserLogin>user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
}
	
	
	
	@PostMapping("/cadastrar")
	public  ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	
	
	
	
	
}
