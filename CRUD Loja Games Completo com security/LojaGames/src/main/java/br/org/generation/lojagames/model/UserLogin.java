package br.org.generation.lojagames.model;
//Aqui só uso as informações necessárias para login, aqui não pede endereço e etc, só vou usar pra login, acabou o login, acabou a função dela, tanto que nem gaurdo isso como entity num banco de dados, e na usuario guarda.
public class UserLogin {
	private String nome; 

	private String usuario;

	private String senha;

	private String token;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
