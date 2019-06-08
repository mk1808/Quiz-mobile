package pro.quiz.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import pro.quiz.models.Question;
import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.models.UserResult;
import pro.quiz.repositories.RoleRepository;
import pro.quiz.services.RoleService;


@Component
public class UserDeserializer  extends StdDeserializer<User>{

	
	@Autowired
	private RoleService roleService;
	
	public UserDeserializer() {
		this(null);
	}
	
	public UserDeserializer(Class <?> vc) {
		super(vc);
		
	}
	

	@Override
	public User deserialize(JsonParser jp, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException{
		JsonNode node=jp.getCodec().readTree(jp);
		ObjectMapper jsonObjectMapper=new ObjectMapper();
		
		int id=-1;
		if (node.get("id").canConvertToInt())
		id = (Integer)((IntNode)node.get("id")).intValue();
		
		
		String email = (String)(node.get("email")).asText();
		String username = (String)(node.get("username")).asText();
		String password = (String)(node.get("password")).asText();
		String name = (String)(node.get("name")).asText();
		String surname = (String)(node.get("surname")).asText();

		String course;
		if(node.get("course").isNull()) { course=null;}
		else {course = (String)(node.get("course")).asText();}
		
		String role = (String)(node.get("role")).asText();
		Role roleObject=new Role();
				
				switch(role) 
				{
	    		case "admin":
	    			roleObject = roleService.findByNameNotOptional(RoleName.ROLE_ADMIN);
	    			break;
	    		
	    		case "user":
	    			roleObject = roleService.findByNameNotOptional(RoleName.ROLE_USER);
	    			break;
				}
		
				List <Subject> subjects= new ArrayList<Subject>();
				
				List <UserResult> results= new ArrayList<UserResult>();
				
				if(node.has("userResults")) {
				final JsonNode arrNode=new ObjectMapper().readTree(node.toString()).get("userResults");
				if(arrNode.isArray()) {
					for(final JsonNode objectNode:arrNode) {
				
						UserResult userResult = jsonObjectMapper.treeToValue(objectNode, UserResult.class);
						results.add(userResult);
					}
				}
				}
				return new User(Long.valueOf(id),email,username,password, 
						roleObject,name,surname,course,subjects,results);
				
					
				
		/*
		int subject=1;
		if(node.get("idSubject").canConvertToInt())
		subject = (Integer)((IntNode)node.get("idSubject")).intValue();
*/
	
		
		
		
	}
}
