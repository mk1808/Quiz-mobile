package pro.quiz.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Answer")
public class Answer {

	@Id 
	@GeneratedValue 
	private Long id; 
	
	@NotNull
	private String text;
	
	@NotNull
	private Boolean status;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="question_id")
	private Question question;

	
	public Answer() {}
	
	
	public Answer(Long id, @NotNull String text, @NotNull Boolean status, Question question) {
		super();
		this.id = id;
		this.text = text;
		this.status = status;
		this.question = question;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
}
