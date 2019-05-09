package pro.quiz.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import pro.quiz.messages.SignInForm;
import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.repositories.QuestionRepository;
import pro.quiz.services.SubjectService;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

private final SubjectService subjectService;
private final QuestionRepository questionRepository;
	
	public SubjectController(SubjectService subjectService,
			QuestionRepository questionRepository) {
		this.subjectService=subjectService;
		this.questionRepository=questionRepository;
}
	
	@GetMapping("/demo/withoutAnswers/{course}")
	ResponseEntity getDemoByCourseWithoutAnswers(@PathVariable String course) throws AuthenticationException
	{
		if(!course.equals("demoweb")&&!course.equals("demojava")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("brak dostepu");
		
		} else {
		Subject subject=this.subjectService.getDemoSubjectByCourse(course);
	
		for(Question question:subject.getQuestions()) {
		for(Answer answer:question.getAnswers()) {
				answer.setStatus(false);
			}
		}
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	}
	 
	}
	
	@GetMapping("/demo/withAnswers/{course}")
	ResponseEntity getDemoByCourseWithAnswers(@PathVariable String course) throws AuthenticationException
	{
		if(!course.equals("demoweb")&&!course.equals("demojava")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("brak dostepu");
		
		} else {
		Subject subject=this.subjectService.getDemoSubjectByCourse(course);
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	}
	 
	}
	
	@PostMapping("/demo/result")
	ResponseEntity checkAnswersForDemo(@RequestBody List<Question> questions) throws AuthenticationException{
		Question question=questionRepository.getQuestionById(questions.get(0).getId());
		String course=question.getSubject().getCourse();
		questions.get(0).setSubject(question.getSubject());
		if(!course.equals("demoweb")&&!course.equals("demojava")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("brak dostepu");
		
		} else {
			Result result = this.subjectService.checkAnswersForDemo(questions);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}
	
	
	
	//test-niedemo
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/withoutAnswers/{id}")
	ResponseEntity getSubjectByIdWithoutAnswers(@PathVariable Long id) throws AuthenticationException
	{
		
		Subject subject=this.subjectService.getSubjectById(id);
	
		for(Question question:subject.getQuestions()) {
		for(Answer answer:question.getAnswers()) {
				answer.setStatus(false);
			}
		}
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	
	 
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/withAnswers/{id}")
	ResponseEntity getSubjectByIdWithAnswers(@PathVariable Long id) 
	{
		
		Subject subject=this.subjectService.getSubjectById(id);
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	 
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/result")
	ResponseEntity checkAnswersForSubject(@RequestBody List<Question> questions) {
		Question question=questionRepository.getQuestionById(questions.get(0).getId());
		
		questions.get(0).setSubject(question.getSubject());
		
			Result result = this.subjectService.checkAnswersForDemo(questions);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
}