package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.repository.IUsuarioDAO;

@Service
public class LoginUsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = usuarioDAO.findByNombreUsuario(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		
		List<GrantedAuthority> tipos = new ArrayList<>();
		
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipoUsuario());
		tipos.add(grantedAuthority);
		
		UserDetails user = new User(username,usuarioEncontrado.getPassword(), tipos);
		
		
		return user;
	}

}
