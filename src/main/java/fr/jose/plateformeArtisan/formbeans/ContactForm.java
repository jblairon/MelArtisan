package fr.jose.plateformeArtisan.formbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactForm {
	
	@NotEmpty
	@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="Invalid Email")
	private String email;
	
	@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="Invalid Email")
	private String emailTo;
	
	@NotEmpty
	@Size(min=4, max=150)
	private String subject;
	
	@NotEmpty
	@Size(min=4, max=1000)
	private String message;

	


	public ContactForm(
			@NotEmpty @Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email") String email,
			@NotEmpty @Size(min = 4, max = 150) String subject, @NotEmpty @Size(min = 4, max = 1000) String message) {
		super();
		this.email = email;
		this.subject = subject;
		this.message = message;
	}

	public ContactForm() {
		super();
	}
	
	

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	
}
