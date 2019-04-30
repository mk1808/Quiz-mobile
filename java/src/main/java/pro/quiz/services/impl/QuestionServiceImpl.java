package pro.quiz.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pro.quiz.models.Question;
import pro.quiz.models.Subject;
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
	
}
