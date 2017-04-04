package adoptaApp.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import adoptaApp.entity.Persona;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserAuthComponent {

	private Persona user;

	public Persona getLoggedUser() {
		return user;
	}

	public void setLoggedUser(Persona user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}
	
	public Integer getIdLoggedUser(){
		return user.getId();
	}

}