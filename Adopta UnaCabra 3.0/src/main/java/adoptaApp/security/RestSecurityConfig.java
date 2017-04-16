package adoptaApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public PersonaRepositoryAuthenticationProvider PersonaRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");
		
		// URLs that need to be USER to access to it
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/cabras/").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/cabras/{id}/image/upload").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/{id}").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/{id}/follow").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/{id}/unfollow").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/cabra/{id}/purchase").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/image/upload").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/news/").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/news/noticiasfav").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/news/{id}/image/upload").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/comment/").hasRole("USER");
		
		
		// URLs that need to be ADMIN to access to it
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/{id}").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/{id}").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/centros/").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/centros/{id}").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/centros/{id}").hasRole("ADMIN");
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/cabras/all").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/cabras/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/centros/all").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/centros/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/news/all").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/news/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/all").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logOut/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/register/").permitAll();
		
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(PersonaRepoAuthProvider);
	}
}