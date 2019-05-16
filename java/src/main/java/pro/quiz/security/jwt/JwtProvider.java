package pro.quiz.security.jwt;


import io.jsonwebtoken.*;
import pro.quiz.models.User;
import pro.quiz.services.UserService;
import pro.quiz.services.impl.UserPrinciple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {
	
/*	private final UserService userService;
	public  JwtProvider(UserService userService) {
		this.userService=userService;
}*/

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${quiz.jwtSecret}")
    private String jwtSecret;

    @Value("${quiz.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        
   /*     Optional<User> myUser =this.userService.getUserByUsername(userPrincipal.getUsername());*/
        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		               // .claim("user",myUser.orElse(null)  )
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}