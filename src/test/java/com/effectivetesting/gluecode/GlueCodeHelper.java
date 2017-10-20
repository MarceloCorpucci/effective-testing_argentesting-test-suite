package com.effectivetesting.gluecode;

import static com.github.restdriver.serverdriver.RestServerDriver.delete;
import static com.github.restdriver.serverdriver.RestServerDriver.get;

import java.io.IOException;

import com.effectivetesting.entities.Entry;
import com.effectivetesting.entities.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restdriver.serverdriver.http.response.Response;

public class GlueCodeHelper {
	final static String USER_ID = "23";
	final static String DEFAULT_BASE_URL = "http://localhost:8000";
	
	public static User createUser(String userName, String email, String password) {
		User user = new User();

		user.setId(USER_ID);
		user.setEmail(email);
		user.setpassword_hash(password);
		user.setName(userName);
		
		return user;
	}
	
	public static void deleteEntry(String title) throws JsonParseException, JsonMappingException, IOException {
		Response response = get(GlueCodeHelper.DEFAULT_BASE_URL + "/api/entry");
        
		ObjectMapper mapper = new ObjectMapper();
        Entry entry = mapper.readValue(response.asText(), Entry.class);
        
        for (int index = 0; index < entry.getObjects().size(); index++) {
        	if (entry.getObjects().get(index).getTitle().equals(title)) {
        		delete(GlueCodeHelper.DEFAULT_BASE_URL + "/api/entry/" + entry.getObjects().get(index).getId());
        	}
        }
        
	}
}
