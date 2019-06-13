package pro.quiz.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.models.UserResult;
import pro.quiz.services.UserService;

public class SubjectDeserializer  extends StdDeserializer<Subject> {
	
	@Autowired
	private UserService userService;
	
	public SubjectDeserializer() {
		this(null);
	}
	
	public SubjectDeserializer(Class <?> vc) {
		super(vc);
		
	}
	

	@Override
	public Subject deserialize(JsonParser jp, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException{
		JsonNode node=jp.getCodec().readTree(jp);
		ObjectMapper jsonObjectMapper=new ObjectMapper();
		int id=-1;
		if(node.has("id")) {
		if (node.get("id").canConvertToInt())
		id = (Integer)((IntNode)node.get("id")).intValue();
		}
		
		int user=1;
		if(node.get("idUser").canConvertToInt())
		user = (Integer)((IntNode)node.get("idUser")).intValue();
		
		String name = (String)(node.get("name")).asText();
		
		String course=null;
		if(node.has("course")) {
		if(node.get("course").isNull()) { course=null;}
		else {
			course = (String)(node.get("course")).asText();
			}
		}
		
		
		String description=null;
		if(node.has("description")) {
		if(node.get("description").isNull()) {description=null;}
		else {
			description= (String)(node.get("description")).asText();
			}
		}
		
		String subject = (String)(node.get("subject")).asText();
		
		Long noQuestions=null;
		if(node.has("noQuestions")) { 
		if(node.get("noQuestions").isNull()) {}
		else 
			{
				if(node.get("noQuestions").canConvertToInt())
				noQuestions = (Long)((IntNode)node.get("noQuestions")).longValue();
			}
		}
		
		Long time=null;
		if(node.has("time")) {
		if(node.get("time").isNull()) {	}
		else 
			{
			    if(node.get("time").canConvertToInt())
			    time=(Long)((IntNode)node.get("time")).longValue();
				
			}
		}
		
		Boolean multipleChoice=false;
		if(node.get("multipleChoice").isBoolean())
		multipleChoice = (Boolean)((BooleanNode)node.get("multipleChoice")).asBoolean();
		

		Boolean separatePage=false;
		if(node.get("separatePage").isBoolean())
		separatePage = (Boolean)((BooleanNode)node.get("separatePage")).asBoolean();
		

		Boolean canBack=null;
		if(node.has("canBack")) {
		if(node.get("canBack").isNull()) {}
		else {
			if(node.get("canBack").isBoolean())
			canBack = (Boolean)((BooleanNode)node.get("canBack")).asBoolean();

		}
		}

		Boolean limitedTime=false;
		if(node.get("limitedTime").isBoolean())
		limitedTime = (Boolean)((BooleanNode)node.get("limitedTime")).asBoolean();

		Boolean randomize=false;
		if(node.get("randomize").isBoolean())
		randomize = (Boolean)((BooleanNode)node.get("randomize")).asBoolean();

		List <Question> questions= new ArrayList<Question>();
		
		if(node.has("questions")) {		
		final JsonNode arrNode=new ObjectMapper().readTree(node.toString()).get("questions");
		if(arrNode.isArray()) {
			for(final JsonNode objectNode:arrNode) {
		
				Question question = jsonObjectMapper.treeToValue(objectNode, Question.class);
				questions.add(question);
			}
		}
	}
		
		List <UserResult> userResults= new ArrayList<UserResult>();
		
		if(node.has("userResults")) {	
		final JsonNode arrNode2=new ObjectMapper().readTree(node.toString()).get("userResults");
		if(arrNode2.isArray()) {
			for(final JsonNode objectNode:arrNode2) {
		
				UserResult userResult = jsonObjectMapper.treeToValue(objectNode, UserResult.class);
				userResults.add(userResult);
			}
		}
		}

		
		User userObject = this.userService.getUserById(Long.valueOf(user));
		return new Subject(Long.valueOf(id),name,noQuestions,multipleChoice,separatePage,
				canBack, limitedTime,time,course, description,
				randomize, subject, questions, userResults, userObject);
		
		/*
		 public Subject(
		 //Long id, 
		 //@NotNull String name, 
		 //Long noQuestions, 
		 //	@NotNull Boolean multipleChoice,
		 //	@NotNull Boolean separatePage, 
		//	Boolean canBack, 
		//	@NotNull Boolean limitedTime, 
			//Long time, 
			//String course,
			//String description, 
		//	@NotNull Boolean randomize,
			//@NotNull String subject,
		//	List<Question> questions,
		//	List <UserResult> userResults, 
			//User user  ) {
	
		 * */
		
		
	}
}
