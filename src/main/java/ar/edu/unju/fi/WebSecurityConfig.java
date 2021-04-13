package ar.edu.unju.fi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.service.LoginUsuarioServiceImpl;

/**
 * 
 * @author Alumno
 * 
 *
 */

//@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity //habilitar las opciones de seguridad
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
//	@Autowired //JDBC
//	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private AutenticacionSuccessHandler autenticacion;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//jpa
		String [] resources= {	"/include/**","/css/**","/js/**","/images/**","/webjars/**"
		};
		
		http.authorizeRequests()
		.antMatchers(resources).permitAll()
		.antMatchers("/","/index").permitAll()
		
		.antMatchers("/views/usuarios/").hasAuthority("administrador")
		.antMatchers("/views/consultas/").hasAuthority("consultor")
		.antMatchers("/views/registros/").hasAuthority("registrador")
				
		
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.successHandler(autenticacion)
		.failureUrl("/login?error=true")
		.usernameParameter("username") // formulario login-> name=username
		.passwordParameter("password")
		.and()
		.logout()
		.permitAll()
		.logoutSuccessUrl("/login?logout");
		
		
		
//		http.authorizeRequests() //JDBC
//		.antMatchers("/","/index","/css/**","/js/**","/images/**","/webjars/**").permitAll()
//		.antMatchers("/views/usuarios/").hasAnyRole("ADMIN")
//		.antMatchers("/views/usuarios/create").hasAnyRole("ADMIN")
//		.antMatchers("/views/usuarios/save").hasAnyRole("ADMIN")
//		.antMatchers("/views/usuarios/edit/**").hasAnyRole("ADMIN")
//		.antMatchers("/views/usuarios/delete/**").hasAnyRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login")
//		.permitAll()
//		.and()
//		.logout().permitAll();
	}



//	@Autowired //JDBC
//	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
//		builder.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(passEncoder)
//		// ? parametro representa el nombre del usuario login	
//		.usersByUsernameQuery("SELECT nombre_usuario, password, estado from usuarios where nombre_usuario=?")
//		.authoritiesByUsernameQuery("SELECT nombre_usuario, tipo_usuario from usuarios where nombre_usuario=?");
//		
//	}
	
	@Autowired
	LoginUsuarioServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder);
	}
	
}
