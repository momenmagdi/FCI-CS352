package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User {
	private long id;
	private String name;
	private String email;
	private String password;

	private static User currentActiveUser;

	private User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;

	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	public static User getCurrentActiveUser() {
		return currentActiveUser;
	}

	public static User getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser = new User(object.get("name").toString(), object
					.get("email").toString(), object.get("password").toString());
			currentActiveUser
					.setId(Long.parseLong(object.get("id").toString()));
			return currentActiveUser;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
