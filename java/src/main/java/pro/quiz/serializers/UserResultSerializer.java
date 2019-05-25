package pro.quiz.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pro.quiz.models.Subject;
import pro.quiz.models.UserResult;

public class UserResultSerializer extends StdSerializer<UserResult> {

	public UserResultSerializer() {
		this(null);
	}
	
	public UserResultSerializer(Class<UserResult> t) {
		super(t);
	}
	

	@Override 
	public void serialize(UserResult value, JsonGenerator jgen, SerializerProvider provider)
	throws IOException, JsonProcessingException{
		
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeNumberField("idUser", value.getUser().getId());
		jgen.writeNumberField("idSubject", value.getSubject().getId());
		jgen.writeNumberField("result", value.getResult());
		jgen.writeEndObject();
		
	}
	
}
