package pro.quiz.services;

import java.util.Optional;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;

public interface RoleService {
	
	Optional<Role> findByName(RoleName roleName);
}
