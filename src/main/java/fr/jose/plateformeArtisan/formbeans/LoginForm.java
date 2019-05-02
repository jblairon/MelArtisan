package fr.jose.plateformeArtisan.formbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	
	@NotEmpty
	@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="Invalid Email")
	private String username;
	
	@NotEmpty
	@Size(min=4, max=50)
	private String mdp;
	
	
	private boolean contact;

	
	public LoginForm(String username, String mdp, boolean contact) {
		super();
		this.username = username;
		this.mdp = mdp;
		this.contact = contact;
	}

	public LoginForm() {
		super();
	}
	
	

	public boolean isContact() {
		return contact;
	}

	public void setContact(boolean contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
