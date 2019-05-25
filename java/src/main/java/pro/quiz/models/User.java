package pro.quiz.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import pro.quiz.serializers.SubjectDeserializer;
import pro.quiz.serializers.SubjectSerializer;
import pro.quiz.serializers.UserDeserializer;
import pro.quiz.serializers.UserSerializer;

@Data
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})

@JsonSerialize(using = UserSerializer.class)
@JsonDeserialize(using = UserDeserializer.class)
public class User {
	

	
	@Id 
	@GeneratedValue 
	private Long id; 
	
	@NaturalId 
	@NotNull  
	@Size(max = 50)
    @Email
    private String email; 
	
	@Size(min=5, max = 30)
	@NotNull 
	private String username;


	@NotNull
	@Size(min=6, max = 100)
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
	
	
	@NotNull 
	private Role role;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	
	private String course;
	
	
	@OneToMany(mappedBy="user")
	private List <Subject> subjects;
	
	@OneToMany(mappedBy="user")
	private List <UserResult> userResults;

	
	public User ()
    {
    }
	
	
	
	public User(Long id, @NotNull @Size(max = 50) @Email String email,
			@Size(min = 5, max = 30) @NotNull String username, @NotNull @Size(min = 6, max = 100) String password,
			@NotNull Role role, @NotNull String name, @NotNull String surname, String course, List<Subject> subjects,
			List<UserResult> userResults) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.course = course;
		this.subjects = subjects;
		this.userResults = userResults;
	}



	public User(
	@NotNull @Size(max = 50) @Email String email,
	@Size(min = 5, max = 20) @NotNull String username,
	@NotNull @Size(min = 6, max = 100) String password,
	
	@NotNull Role role, 
	@NotNull String name, 
	@NotNull String surname, 
	String course,
	List <UserResult> userResults,
	List <Subject> subjects) {
		super();
		
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.course = course;
		this.userResults=userResults;
		this.subjects=subjects;
	}
	
	public User(
			
			@NotNull @Size(min = 6, max = 100) String password,
			@NotNull String name, 
			@NotNull String surname, 
			String course) {
				super();
				
				
		
				this.password = password;
				this.name = name;
				this.surname = surname;
				this.course = course;

			}
	
	
	public User(
			@NotNull @Size(max = 50) @Email String email,
			@Size(min = 5, max = 20) @NotNull String username,
			@NotNull @Size(min = 6, max = 100) String password,
			@NotNull Role role, 
			@NotNull String name, 
			@NotNull String surname, 
			String course) {
				super();
				
				this.email = email;
				this.username = username;
				this.password = password;
				this.role = role;
				this.name = name;
				this.surname = surname;
				this.course = course;
				
			}
/*
	public User( @NotNull @Size(max = 50) @Email String email,
			@Size(min = 5, max = 20) @NotNull String username,
			@NotNull @Size(min = 6, max = 100) String password, 
			@NotNull Role role) {
		super();
		
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<UserResult> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResult> userResults) {
		this.userResults = userResults;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
	


}
