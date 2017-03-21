package adoptaApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	public PersonaRepositoryAuthenticationProvider authenticationProvider;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//PUBIC PAGES
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**","/less/**","/mail/**","/scss/**","/vendor/**").permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/centros").permitAll();
		http.authorizeRequests().antMatchers("/goatPurchase/{id}").permitAll();
		http.authorizeRequests().antMatchers("/noticias","/noticias/{id}").permitAll();
		http.authorizeRequests().antMatchers("/perfil/{id}").permitAll();
		http.authorizeRequests().antMatchers("/console/**").permitAll();
		http.headers().frameOptions().disable();
		
		//PRIVATE PAGES
		http.authorizeRequests().antMatchers("/goatPurchase/{id}/payment").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/noticias/nueva").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/noticias/nueva/add").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/noticias/{id}/nuevoComentario").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/perfil/{id}/addcabra").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/perfil/{id}/addcabra/nueva").hasAnyRole("USER");
		
		// LOGIN
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginError");

		// LOGOUT
		http.logout().logoutUrl("/logOut").deleteCookies("JSESSIONID", "remember-me");
		http.logout().logoutUrl("/logOut");
		http.logout().logoutSuccessUrl("/login");
		
		// DISABLE CSRF
		http.csrf().disable();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}
