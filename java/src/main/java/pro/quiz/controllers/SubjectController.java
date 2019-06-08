package pro.quiz.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import pro.quiz.messages.SignInForm;
import pro.quiz.messages.SubjectForm;
import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.repositories.AnswerRepository;
import pro.quiz.repositories.QuestionRepository;
import pro.quiz.services.AnswerService;
import pro.quiz.services.QuestionService;
import pro.quiz.services.SubjectService;
import pro.quiz.services.UserService;
import pro.quiz.services.impl.UserPrinciple;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

private final SubjectService subjectService;
private final QuestionRepository questionRepository;
private final UserService userService;
private final QuestionService questionService;
private final AnswerService answerService;
private final AnswerRepository answerRepository;
	
	public SubjectController(SubjectService subjectService,
			QuestionRepository questionRepository, UserService userService,
			AnswerRepository answerRepository, QuestionService questionService, AnswerService answerService ) {
		this.subjectService=subjectService;
		this.questionRepository=questionRepository;
		this.userService=userService;
		this.answerRepository=answerRepository;
		this.questionService=questionService;
		this.answerService=answerService;
}
	//demo
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
	//	Question question=questionRepository.getQuestionById(questions.get(0).getId());
		String course=questions.get(0).getSubject().getCourse();
		
	//questions.get(0).setSubject(question.getSubject());
	//questions.get(1).setSubject(question.getSubject());
	//questions.get(2).setSubject(question.getSubject());
	//questions.get(3).setSubject(question.getSubject());
	
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
	

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{course}")
	ResponseEntity getSubjectsByCourse(@PathVariable String course) 
	{
		
		List<Subject> subjects=new ArrayList<Subject>();
		subjects=this.subjectService.getSubjectsByCourse(course);
		

		return  ResponseEntity.status(HttpStatus.OK).body(subjects);
	 
	}
	
	
	/////admin
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user/{id}")
	ResponseEntity getSubjectsByUser(@PathVariable Long id) 
	{
		User user = this.userService.getUserById(id);
		System.out.println("abc");
		List <Subject> subjects=this.subjectService.getSubjectsByUser(user);
		return  ResponseEntity.status(HttpStatus.OK).body(subjects);
	 
	}
	
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/withAnswers/{id}")
	ResponseEntity getSubjectByIdWithAnswersForAdmin(@PathVariable Long id) 
	{
		
		Subject subject=this.subjectService.getSubjectById(id);
		return  ResponseEntity.status(HttpStatus.OK).body(subject);
	 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	ResponseEntity deleteSubjectById(@PathVariable Long id)  
	{
		
		Subject subject=this.subjectService.getSubjectById(id);
		String response=new String();
		if (subject!=null) {
		List<Question> questions = this.questionRepository.getQuestionsBySubject(subject);
		List <Answer> answers = new ArrayList<Answer>();
		for (int i=0; i<questions.size(); i++) {
			List <Answer> singleAnswers=this.answerRepository.getAnswersByQuestion(questions.get(i));
			for (int j=0; j<singleAnswers.size(); j++) {
				answers.add(singleAnswers.get(j));
			}
		}
		
		
		for(Answer a:answers) {
			this.answerService.deleteAnswer(a.getId());
		};
		
		for(Question q:questions) {
			this.questionService.deleteQuestion(q.getId());
		};
		
		
		response=this.subjectService.deleteSubject(id);
		return  ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("nie znaleziono");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	ResponseEntity createSubject(@RequestBody Subject subject) {
		// UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
	      
		Subject newSubject = this.subjectService.createSubject(subject);
		return ResponseEntity.status(HttpStatus.OK).body(newSubject);
	}
	
	
	//////experymenty
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create/test")
	ResponseEntity createSubject(@RequestBody SubjectForm subjectForm) {
		// UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
	    User user=new User();
	    user=userService.getUserById(subjectForm.getUserId());
	  //  Subject newSubject=subjectForm;
	//  newSubject.setUser(user);
	    Subject newSubject= new Subject(
	    		null,
	    		subjectForm.getName(),
	    		subjectForm.getNoQuestions(),
	    		subjectForm.getMultipleChoice(),
	    		subjectForm.getSeparatePage(),
	    		subjectForm.getCanBack(),
	    		subjectForm.getLimitedTime(),
	    		subjectForm.getTime(),
	    		subjectForm.getCourse(),
	    		subjectForm.getDescription(),
	    		subjectForm.getRandomize(),
	    		subjectForm.getSubject(),
	    		subjectForm.getQuestions(),
	    		subjectForm.getUserResults(),
	    		user);
	    		
		Subject mySubject = this.subjectService.createSubject(newSubject);
		subjectForm.setId(mySubject.getId());
		return ResponseEntity.status(HttpStatus.OK).body(subjectForm);
	}
	
	
	
	
}	