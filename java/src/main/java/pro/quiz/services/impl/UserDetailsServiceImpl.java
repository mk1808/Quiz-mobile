package pro.quiz.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.quiz.models.User;
import pro.quiz.repositories.UserRepository;
import pro.quiz.services.UserService;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
   
    UserRepository userRepository;
    UserService userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
    	
        User user = userRepository.getUserByUsername(login)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or email : " + login)
        );

        return UserPrinciple.build(user);
    }
}
	