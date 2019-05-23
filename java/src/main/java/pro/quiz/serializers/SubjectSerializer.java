package pro.quiz.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;

public class SubjectSerializer extends StdSerializer<Subject> {
	
	public SubjectSerializer() {
		this(null);
	}
	
	public SubjectSerializer(Class<Subject> t) {
		super(t);
	}
	
	
	@Override 
	public void serialize(Subject value, JsonGenerator jgen, SerializerProvider provider)
	throws IOException, JsonProcessingException{
		
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeNumberField("idUser", value.getUser().getId());
		jgen.writeStringField("name", value.getName());
		if(value.getNoQuestions()==null) {
			jgen.writeNullField("noQuestions");
		}else {
		
		jgen.writeNumberField("noQuestions", value.getNoQuestions());
		}
		jgen.writeBooleanField("multipleChoice", value.getMultipleChoice());
		jgen.writeBooleanField("separatePage", value.getSeparatePage());
		
		if(value.getCanBack()==null) {
			jgen.writeNullField("canBack");
		}else {
		
		jgen.writeBooleanField("canBack", value.getCanBack());
		
		}
		
		jgen.writeBooleanField("limitedTime", value.getLimitedTime());
		if(value.getTime()==null) {
			jgen.writeNullField("time");
		}else {
			jgen.writeNumberField("time", value.getTime());	
		}
		
		if(value.getCourse()==null) {
			jgen.writeNullField("course");
		}else 
		{
			jgen.writeStringField("course", value.getCourse());
		}
		
		if(value.getDescription()==null) {
			jgen.writeNullField("description");
		}else {
		
		jgen.writeStringField("description", value.getDescription());
		}
		
		jgen.writeBooleanField("randomize", value.getRandomize());
		jgen.writeStringField("subject", value.getSubject());

		jgen.writeArrayFieldStart("questions");
		
		
		
		//jgen.writeNullField("thyju");
		
		for (Question question:value.getQuestions()) {

			jgen.writeStartObject();
			jgen.writeNumberField("id", question.getId());
			jgen.writeStringField("text", question.getText());
			jgen.writeStringField("code", question.getCode());
			jgen.writeStringField("image", question.getImage());
			
			jgen.writeArrayFieldStart("answers");
			
			for (Answer answer:question.getAnswers()) {

				jgen.writeStartObject();
				jgen.writeNumberField("id", answer.getId());
				jgen.writeStringField("text", answer.getText());
				jgen.writeBooleanField("status", answer.getStatus());
				jgen.writeEndObject();
			}
			

			jgen.writeEndArray();
			
			
			jgen.writeEndObject();
		}
		
		jgen.writeEndArray();

		jgen.writeEndObject();
		
		
}
}