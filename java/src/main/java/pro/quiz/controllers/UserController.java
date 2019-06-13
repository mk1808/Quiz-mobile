package pro.quiz.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.quiz.models.Question;
import pro.quiz.models.User;
import pro.quiz.services.UserService;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

@RestController
@RequestMapping("/api/users")

public class UserController {


private final UserService userService;
	public UserController(UserService userService) {
		this.userService=userService;
}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/update")
	ResponseEntity updateUser(@RequestBody User user) {
		
	
		
			User myUser = this.userService.updateUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(myUser);
		
	}
	///admin
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	ResponseEntity getUser(@PathVariable Long id) {
			
			User myUser = this.userService.getUserById(id);
			return ResponseEntity.status(HttpStatus.OK).body(myUser);
		
	}
	
	/////admin
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin/update")
	ResponseEntity updateUserByAdmin(@RequestBody User user) {
		
	
		
			User myUser = this.userService.updateUserByAdmin(user);
			return ResponseEntity.status(HttpStatus.OK).body(myUser);
		
	}
	
	
}
