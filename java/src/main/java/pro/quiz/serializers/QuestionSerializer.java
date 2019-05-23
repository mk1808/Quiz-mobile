package pro.quiz.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;

public class QuestionSerializer extends StdSerializer<Question>{
	public QuestionSerializer() {
		this(null);
	}
	
	public QuestionSerializer(Class<Question> t) {
		super(t);
	}
	
	@Override 
	public void serialize(Question value, JsonGenerator jgen, SerializerProvider provider)
	throws IOException, JsonProcessingException{
		
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeNumberField("idSubject", value.getSubject().getId());
		jgen.writeStringField("text", value.getText());
		
		if(value.getCode()==null) {
			jgen.writeNullField("code");
		}else {
		
		jgen.writeStringField("code", value.getCode());
		}
		
		if(value.getImage()==null) {
			jgen.writeNullField("image");
		}else {
		
		jgen.writeStringField("image", value.getImage());
		}
		
		jgen.writeArrayFieldStart("answers");
		
		for (Answer answer:value.getAnswers()) {

			jgen.writeStartObject();
			jgen.writeNumberField("id", answer.getId());
			jgen.writeNumberField("idQuestion", value.getId());
			jgen.writeStringField("text", answer.getText());
			jgen.writeBooleanField("status", answer.getStatus());
			System.out.println(answer.getText());
			jgen.writeEndObject();
		}
		
		jgen.writeEndArray();

		jgen.writeEndObject();
		
	}
	
		
}
