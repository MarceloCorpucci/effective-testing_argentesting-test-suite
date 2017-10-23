package com.effectivetesting.gluecode;

import static com.github.restdriver.serverdriver.RestServerDriver.body;
import static com.github.restdriver.serverdriver.RestServerDriver.delete;
import static com.github.restdriver.serverdriver.RestServerDriver.get;
import static com.github.restdriver.serverdriver.RestServerDriver.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.effectivetesting.entities.Entry;
import com.effectivetesting.entities.MinimalEntry;
import com.effectivetesting.entities.Tag;
import com.effectivetesting.entities.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restdriver.serverdriver.http.response.Response;

public class GlueCodeHelper {
	final static String USER_ID = "23";
	final static String DEFAULT_BASE_URL = "http://localhost:8000";
	final static String TAG_ID = "90";
	
	public static void createUser(String userName, String email, String password) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();

		user.setId(USER_ID);
		user.setEmail(email);
		user.setpassword_hash(password);
		user.setName(userName);		
		
		String jsonInString = mapper.writeValueAsString(user);
		
		post(GlueCodeHelper.DEFAULT_BASE_URL + "/api/user", body(jsonInString, "application/json"));
	}
	
	public static void deleteUser() {
		delete(GlueCodeHelper.DEFAULT_BASE_URL + "/api/user/" + GlueCodeHelper.USER_ID);
	}
	
	public static void createEntry(String title, String body, String status, String aTag) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		List<Tag> tags = new ArrayList<Tag>();
		Tag tag = createTag(aTag);
		tags.add(tag);
		
		MinimalEntry entry = new MinimalEntry();
		entry.setId("90");
		entry.setTitle(title);
		entry.setSlug("entry-title");
		entry.setAuthor_id(USER_ID);
		entry.setBody(body);
		entry.setStatus(status=="Draft"? "0" : "1");
		entry.setTags(tags);

		String jsonInString = mapper.writeValueAsString(entry);
		
		post(GlueCodeHelper.DEFAULT_BASE_URL + "/api/entry", body(jsonInString, "application/json"));
	}
	
	public static void deleteEntry(String title) throws JsonParseException, JsonMappingException, IOException {
		Response response = get(GlueCodeHelper.DEFAULT_BASE_URL + "/api/entry");
        
		ObjectMapper mapper = new ObjectMapper();
        Entry entry = mapper.readValue(response.asText(), Entry.class);
        
        for (int index = 0; index < entry.getObjects().size(); index++) {
        	
        	if (entry.getObjects().get(index).getTitle().equals(title)) {
        		
        		if (entry.getObjects().get(index).getTags().size() > 0) {
        			delete(GlueCodeHelper.DEFAULT_BASE_URL + "/api/tag/" + entry.getObjects().get(index).getTags().get(0).getId());
        		}

        		delete(GlueCodeHelper.DEFAULT_BASE_URL + "/api/entry/" + entry.getObjects().get(index).getId());
        	}
        	
        }
        
	}
	
	private static Tag createTag(String aTag) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Tag tag = new Tag();
		tag.setId("90");
		tag.setName(aTag);
		tag.setSlug(aTag);
		
		String jsonInString = mapper.writeValueAsString(tag);
		
		Response response = post(GlueCodeHelper.DEFAULT_BASE_URL + "/api/tag", body(jsonInString, "application/json"));
		
		return mapper.readValue(response.asText(), Tag.class);
	}
}


