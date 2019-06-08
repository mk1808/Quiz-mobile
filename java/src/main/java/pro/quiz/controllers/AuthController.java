package pro.quiz.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.quiz.messages.JwtResponse;
import pro.quiz.messages.SignInForm;
import pro.quiz.messages.SignUpForm;
import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.models.UserResult;
import pro.quiz.repositories.RoleRepository;
import pro.quiz.repositories.UserRepository;
import pro.quiz.security.jwt.JwtProvider;
import pro.quiz.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")


public class AuthController {
	
	  @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder encoder;

	    @Autowired
	    JwtProvider jwtProvider;

	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInForm loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = jwtProvider.generateJwtToken(authentication);
	        return ResponseEntity.ok(new JwtResponse(jwt));
	    }

	    @PostMapping("/signup")
	    public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	        if(userService.existsByUsername(signUpRequest.getUsername())) {
	            return new ResponseEntity<String>("Fail -> Username is already taken!",
	                    HttpStatus.BAD_REQUEST);
	        }

	        if(userService.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity<String>("Fail -> Email is already in use!",
	                    HttpStatus.BAD_REQUEST);
	        }

	        // Creating user's account
	        Role role;
	        String strRole = signUpRequest.getRole().toString();
      	switch(strRole) {
	    		case "admin":
	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			role=adminRole;
	    			
	    			break;
	    		
	    		default:
	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		role=userRole;       			
      	}
      	List<UserResult> results=new ArrayList<UserResult>();
      	List<Subject> subjects=new ArrayList<Subject>();
	        User user = new User(signUpRequest.getEmail(),
	        		signUpRequest.getUsername(),
	                encoder.encode(signUpRequest.getPassword()),
	                role,
	                signUpRequest.getName(),
	                signUpRequest.getSurname(),
	                signUpRequest.getCourse(),
	                results,
	                subjects
	                );
	        
	       // user.setRole(role);
	        userRepository.save(user);
	        MyMessage msg=new MyMessage("User registeres successfully");
	        return ResponseEntity.status(HttpStatus.OK).body(msg);
	    }
	    
	    


public class MyMessage{
	public String message;
	public MyMessage() {}
	public MyMessage(String msg) {
		this.message=msg;
	};
	
}
}
