package pro.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.User;
import pro.quiz.models.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {

	List<UserResult> getUserResultsByUser(User user);
}
