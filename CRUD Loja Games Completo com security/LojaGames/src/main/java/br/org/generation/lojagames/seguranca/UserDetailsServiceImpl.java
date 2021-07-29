package br.org.generation.lojagames.seguranca;
//ESSA CLASSE FAZ O MEIO CAMPO ENTRE A CLASSE USUARIO E CLASSE USERDETAILSIMPL, pois ela verifica se foi cadsstrado o usuario e permite login, se o usuario não existir ela da um not found
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException  {
		
		Optional <Usuario> user = userRepository.findByUsuario(userName);//verifica se o usuario existe, se nao existe da o erro, se existir elevai pra user details
		user.orElseThrow(()-> new UsernameNotFoundException(userName + " not found. " ));
		
		return user.map(UserDetailsImpl::new).get();
		
	}
}