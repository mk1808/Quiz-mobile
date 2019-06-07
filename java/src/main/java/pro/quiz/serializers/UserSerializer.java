package pro.quiz.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pro.quiz.models.Answer;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.models.UserResult;

public class UserSerializer extends StdSerializer<User>{
	
	public UserSerializer() {
		this(null);
	}
	
	public UserSerializer(Class<User> t) {
		super(t);
	}
	
	
	@Override 
	public void serialize(User value, JsonGenerator jgen, SerializerProvider provider)
	throws IOException, JsonProcessingException{
		
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("email", value.getEmail());
		jgen.writeStringField("username", value.getUsername());
	//	jgen.writeStringField("password", value.getPassword());
		jgen.writeStringField("name", value.getName());
		jgen.writeStringField("surname", value.getSurname());
		jgen.writeStringField("course", value.getCourse());
		jgen.writeStringField("role", value.getRole().getName().toString());
		jgen.writeArrayFieldStart("userResults");
		
		for (UserResult result:value.getUserResults()) {

			jgen.writeStartObject();
			jgen.writeNumberField("id", result.getId());
			jgen.writeNumberField("idUser", result.getUser().getId());
			jgen.writeNumberField("idSubject", result.getSubject().getId());
			jgen.writeNumberField("result", result.getResult());
			jgen.writeEndObject();
		}

		jgen.writeEndArray();
		jgen.writeEndObject();
			
}
}