package pro.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {

}
