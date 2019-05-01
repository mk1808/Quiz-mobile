package pro.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.Question;
import pro.quiz.models.Subject;



public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> getQuestionsBySubject(Subject subject);
	Question getQuestionById(Long id);
}
