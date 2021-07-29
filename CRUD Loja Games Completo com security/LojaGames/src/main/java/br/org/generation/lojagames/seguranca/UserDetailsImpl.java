package br.org.generation.lojagames.seguranca;
//BASICAMENTE ESSA CLASSE SERVE PARA DAR AS PERMISSÕES QUE O CLIENTE VAI PODER ACESSAR E CONSTRUIR USUARIO E SENHA
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.lojagames.model.Usuario;
//quando cria o token, é trabalhado por essa classe
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;//obrigatório pois estou utilizando user details, faz uma serealização, controle pra saber se o que está sendo enviado é realmente desta classe
	
	private String userName;
	private String password;// está assim pois ele usa a user details para fazer login, ou seja, crio esses atriutos para receber usuario e senha
	
	public UserDetailsImpl(Usuario user) {//construtor
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() { //fez construtor vazio só para criar um objeto vazio para receber dados depois, se der algum problema ele não retorna nulo.
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {//Aonde ponho as autorizações
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() { //pega senha do token
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {//pega senha do token 
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
