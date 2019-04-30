package pro.quiz.controllers;

import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import pro.quiz.models.Subject;
import pro.quiz.services.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

private final SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService=subjectService;
}
	
	@GetMapping("/demo/{course}")
	ResponseEntity getDemoByCourse(@PathVariable String course) throws AuthenticationException
	{
		if(!course.equals("demoweb")&&!course.equals("demojava")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("brak dostepu");
		
		} else {
		Subject subject=this.subjectService.getDemoSubjectByCourse(course);
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	}
	 
}
}