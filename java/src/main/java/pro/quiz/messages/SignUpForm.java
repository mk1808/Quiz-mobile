package pro.quiz.messages;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpForm {
		@NotNull
	    private String name;

	    @NotNull
	    @Size(min = 3, max = 50)
	    private String username;

	    @NotNull
	    @Size(max = 60)
	    @Email
	    private String email;

	    @NotNull
	    private String role;
	    
	    @NotNull
	    @Size(min = 6, max = 40)
	    private String password;
	    
	    @NotNull
	    private String surname;
	    private String course;

	    
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getRole() {
	    	return this.role;
	    }
	    
	    public void setRole(String role) {
	    	this.role = role;
	    }
}
