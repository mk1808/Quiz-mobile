package pro.quiz.serializers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.services.SubjectService;

public class QuestionDeserializer extends StdDeserializer<Question>{
	@Autowired
	private SubjectService subjectService;
	public QuestionDeserializer() {
		this(null);
	}
	
	public QuestionDeserializer(Class <?> vc) {
		super(vc);
		
	}
	
	/*
	 * Long id, Subject subject, @NotNull String text, String code, 
			String image, List <Answer> answers*/
	
	@Override
	public Question deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException{
		JsonNode node=jp.getCodec().readTree(jp);
		ObjectMapper jsonObjectMapper=new ObjectMapper();
		int id=-1;
		if(node.has("id")) {
		if (node.get("id").canConvertToInt())
		id = (Integer)((IntNode)node.get("id")).intValue();
		}
		
		int subject=1;
		if(node.get("idSubject").canConvertToInt())
		subject = (Integer)((IntNode)node.get("idSubject")).intValue();

		String text = (String)(node.get("text")).asText();
		

		String code=null;
		if(node.has("code")) {
		if(node.get("code").isNull()) { code=null;}
		else {code = (String)(node.get("code")).asText();}
		}
		
		String image=null;
		if(node.has("image")) {
		if(node.get("image").isNull()) { image=null;}
		else {image = (String)(node.get("image")).asText();}
		}
		List <Answer> answers= new ArrayList<Answer>();
				
		final JsonNode arrNode=new ObjectMapper().readTree(node.toString()).get("answers");
		if(arrNode.isArray()) {
			for(final JsonNode objectNode:arrNode) {
		
				Answer answer = jsonObjectMapper.treeToValue(objectNode, Answer.class);
				answers.add(answer);
			}
		}
		
		Subject subjectObiect = this.subjectService.getSubjectById(Long.valueOf(subject));
		return new Question(Long.valueOf(id),subjectObiect,text,code,image,answers);
						
	}
	
}
