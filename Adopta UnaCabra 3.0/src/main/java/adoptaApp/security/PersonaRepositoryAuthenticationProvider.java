package adoptaApp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import adoptaApp.entity.Persona;
import adoptaApp.repository.PersonaRepository;

@Component
public class PersonaRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PersonaRepository personaRep;
	
	@Autowired
	private UserAuthComponent userAuth;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Persona user = personaRep.findByCorreo(authentication.getName());

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		String password = (String) authentication.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		userAuth.setLoggedUser(user);

		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}

		return new UsernamePasswordAuthenticationToken(user.getNombre(), password,roles);
	}

	
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
