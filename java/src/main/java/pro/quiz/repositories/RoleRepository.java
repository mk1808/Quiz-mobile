package pro.quiz.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
	
}
