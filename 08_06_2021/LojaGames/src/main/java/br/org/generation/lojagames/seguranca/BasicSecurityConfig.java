package br.org.generation.lojagames.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	//@Autowired= cria instancia da classe e utiliza aqui dentro, porém só entende o uso aqui dentro
	@Autowired
	private UserDetailsService UserDetailsService; // injetou dependencia na user details service. 
	
	@Override //sobrecarga. Toda autenticação será feita através da classe user details service 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailsService);
		
		}
		
		//@bean= criar um objeto e deixar disponível para todas as classes utlizarem ele como dependencia, segurar algo e devolver quando pedir, entende o uso em vários lugares. 
		@Bean 
		public PasswordEncoder passwordEncoder() {//Aqui é usado para fazer a criptografia
			return new BCryptPasswordEncoder(); //criptografia
	
		}
		
		@Override 
		protected void configure (HttpSecurity http) throws Exception { //configurar as respostas do http, as requisições
			http.authorizeRequests()
			.antMatchers("/usuarios/logar").permitAll()
			.antMatchers("/usuarios/cadastrar").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable();//faz basicamente a função do token, por isso não precisa manter ele habilitado

		}
	
}

