package pro.quiz.services.impl;

import org.springframework.stereotype.Service;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.repositories.AnswerRepository;
import pro.quiz.services.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
	private final AnswerRepository answerRepository;

	public AnswerServiceImpl(AnswerRepository answerRepository) {
		super();
		this.answerRepository = answerRepository;
	}
	
	public Answer createAnswer(Answer answer) {
		return this.answerRepository.save(answer);
	}
	
	
	public String deleteAnswer(Long id) 
	{
		this.answerRepository.deleteById(id);
		return "deleted";
	}
	
	
}
