package pro.quiz.controllers;

import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.services.AnswerService;
import pro.quiz.services.QuestionService;
import pro.quiz.services.SubjectService;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

private final QuestionService questionService;
private final SubjectService subjectService;
private final AnswerService answerService;	
	public QuestionController(QuestionService questionService, SubjectService subjectService,
			AnswerService answerService) {
		this.questionService=questionService;
		this.subjectService=subjectService;
		this.answerService=answerService;
}
	

	@GetMapping("demo/{id}")
	ResponseEntity getQuestionsBySubject(@PathVariable int id) throws AuthenticationException
	{
		Long idl=Long.valueOf(id);
		Subject subject=this.subjectService.getSubjectById(idl);
		
		if(!subject.getCourse().equals("demoweb")&&!subject.getCourse().equals("demojava")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("brak dostepu");
		
		} else {
		List<Question> questions=this.questionService.getQuestionsBySubject(subject);
		return  ResponseEntity.status(HttpStatus.OK).body(questions);
	}
	 
}
	
	
	
}
