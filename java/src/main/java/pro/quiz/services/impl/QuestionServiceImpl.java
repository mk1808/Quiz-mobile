package pro.quiz.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.repositories.QuestionRepository;
import pro.quiz.repositories.SubjectRepository;
import pro.quiz.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	private final QuestionRepository  questionRepository;
	public  QuestionServiceImpl ( QuestionRepository  questionRepository)
	{
		this.questionRepository= questionRepository;
	}
	
	public List<Question> getQuestionsBySubject(Subject subject){
		return this.questionRepository.getQuestionsBySubject(subject); 
	}
	
	public Question createQuestion(Question question) {
		return this.questionRepository.save(question);
	}
	
	public Question getQuestionById(Long id) {
		
		return this.questionRepository.getQuestionById(id);
	}
	
	public String deleteQuestion(Long id) 
	{
		this.questionRepository.deleteById(id);
		return "deleted";
		
	}
	
	public Question updateQuestion(Question question) {
		Question myQuestion=this.questionRepository.findById(question.getId()).get();
		myQuestion.setImage(question.getImage());
		myQuestion.setText(question.getText());
		myQuestion.setCode(question.getCode());
		
		myQuestion.setAnswers(question.getAnswers());
	
		return this.questionRepository.save(myQuestion);
		
	}

	
}
