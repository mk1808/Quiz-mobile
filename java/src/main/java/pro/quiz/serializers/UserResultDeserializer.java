package pro.quiz.serializers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.models.UserResult;
import pro.quiz.services.SubjectService;
import pro.quiz.services.UserService;

public class UserResultDeserializer  extends StdDeserializer<UserResult>{


	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	
	
	public UserResultDeserializer() {
		this(null);
	}
	
	public UserResultDeserializer(Class <?> vc) {
		super(vc);
		
	}

	@Override
	public UserResult deserialize(JsonParser jp, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException{
		JsonNode node=jp.getCodec().readTree(jp);
		ObjectMapper jsonObjectMapper=new ObjectMapper();
		int id=-1;
		if (node.get("id").canConvertToInt())
		id = (Integer)((IntNode)node.get("id")).intValue();
		
		int user=1;
		if(node.get("idUser").canConvertToInt())
		user = (Integer)((IntNode)node.get("idUser")).intValue();
		
		int subject=1;
		if(node.get("idSubject").canConvertToInt())
		subject = (Integer)((IntNode)node.get("idSubject")).intValue();

		Float result=Float.valueOf(0);
		if(node.hasNonNull("result")) {
			result = node.get("result").floatValue();
		}
		
		Subject subjectObject = this.subjectService.getSubjectById(Long.valueOf(subject));
		User userObject = this.userService.getUserById(Long.valueOf(user));
		
		return new UserResult(Long.valueOf(id),result,subjectObject,userObject);
		
		
	}

}
