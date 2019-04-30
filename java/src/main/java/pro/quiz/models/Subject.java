package pro.quiz.models;

import java.time.Duration;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "Subject")
public class Subject {

	@Id 
	@GeneratedValue 
	private Long id; 
	

	@NotNull 
	private String name;
	
	//private idAuthor;
	
	private Long noQuestions;
	
	@NotNull
	private Boolean multipleChoice;
	
	@NotNull
	private Boolean separatePage;
	
	
	private Boolean canBack;
	
	@NotNull
	private Boolean limitedTime;
	
	private Duration time;
	
	private String course;
	
	private String description;
	
	@NotNull
	private Boolean randomize;
	
	@NotNull
	private String subject;


	@OneToMany(mappedBy="subject")
	private List <Question> questions;
	
	
	
	public Subject(){}
	
	public Subject(Long id, @NotNull String name, Long noQuestions, @NotNull Boolean multipleChoice,
			@NotNull Boolean separatePage, Boolean canBack, @NotNull Boolean limitedTime, Duration time, String course,
			String description, @NotNull Boolean randomize, @NotNull String subject,List<Question> questions ) {
		super();
		this.id = id;
		this.name = name;
		this.noQuestions = noQuestions;
		this.multipleChoice = multipleChoice;
		this.separatePage = separatePage;
		this.canBack = canBack;
		this.limitedTime = limitedTime;
		this.time = time;
		this.course = course;
		this.description = description;
		this.randomize = randomize;
		this.subject = subject;
		this.questions=questions;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNoQuestions() {
		return noQuestions;
	}

	public void setNoQuestions(Long noQuestions) {
		this.noQuestions = noQuestions;
	}

	public Boolean getMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(Boolean multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

	public Boolean getSeparatePage() {
		return separatePage;
	}

	public void setSeparatePage(Boolean separatePage) {
		this.separatePage = separatePage;
	}

	public Boolean getCanBack() {
		return canBack;
	}

	public void setCanBack(Boolean canBack) {
		this.canBack = canBack;
	}

	public Boolean getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(Boolean limitedTime) {
		this.limitedTime = limitedTime;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRandomize() {
		return randomize;
	}

	public void setRandomize(Boolean randomize) {
		this.randomize = randomize;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	

}
