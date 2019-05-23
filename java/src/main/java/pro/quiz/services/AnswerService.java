package pro.quiz.services;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;

public interface AnswerService {
	
	Answer createAnswer(Answer answer);
	String deleteAnswer(Long id);

}
