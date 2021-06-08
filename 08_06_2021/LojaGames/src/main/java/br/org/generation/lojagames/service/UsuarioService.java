package br.org.generation.lojagames.service;
//ESSA CLASSE TEM AS REGRAS DE NEGÓCIO A CARGO DO DESENVOLVEDOR, OU SEJA, POSSO COLOCAR RESTRIÇÃO DE IDADE, QUE A SENHA VAI SER CRIPTORAFADA E TUDO MAIS
import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.generation.lojagames.model.UserLogin;
import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	
	public Optional<UserLogin> Logar(Optional<UserLogin>user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
		     if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
		    	 
		    	 String auth = user.get().getUsuario() + ":" + user.get().getSenha();
		    	 byte [] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));//cria o token
		    	 String authHeader = "Basic " + new String(encodedAuth);//converte o byte em string
		    	 
		    	 user.get().setToken(authHeader);
		    	 user.get().setNome(usuario.get().getNome());
		    	 
		    	 
		    	 return user;
		     }
		
		
	}
        return null;
	}
}
