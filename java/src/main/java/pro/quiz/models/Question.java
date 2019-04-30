package pro.quiz.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Question")

public class Question {

	@Id 
	@GeneratedValue 
	private Long id; 
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="subject_id")
	private Subject subject;
	
	@NotNull
	private String text;
	
	private String code;
	
	private String image;

	@OneToMany(mappedBy="question")
	private List <Answer> answers;
	
	
	public Question() {}
	


	public Question(Long id, Subject subject, @NotNull String text, String code, 
			String image, List <Answer> answers ) {
		super();
		this.id = id;
		this.subject = subject;
		this.text = text;
		this.code = code;
		this.image = image;
		this.answers=answers;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
