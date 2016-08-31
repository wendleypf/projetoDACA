package dirlididi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Usuario {

	private String email;
	@JsonIgnore
	private String senha;

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
