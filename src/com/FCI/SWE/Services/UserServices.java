package com.FCI.SWE.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {

	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

	}

	@POST
	@Path("/seeFriendRequsts")
	public String seeFriendRequsts() {

		Vector<String> requsts = null;
		requsts = UserEntity.getFrindRequsts(User.getCurrentActiveUser()
				.getEmail());
		System.out.println(requsts);

		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/frindConfirmService")
	public String frindConfirmService(@FormParam("fname") String fname) {
		System.out.println("Servece rest frindConfirmService");
		JSONObject object = new JSONObject();
		String urlParameters = "Status=" + "Failed";
		UserEntity.confirmFrind(User.getCurrentActiveUser().getEmail(), fname);

		return urlParameters;

	}

	@POST
	@Path("/addFriendService")
	public String addFriendService(@FormParam("email") String email) {
		JSONObject object = new JSONObject();
		String curUser = User.getCurrentActiveUser().getEmail();
		String checkEmail = UserEntity.getUserByEmail(email);

		if (checkEmail == null) {
			object.put("Status", "Failed");
		} else {
			object.put("Status", "OK");
			object.put("email", email);
			UserEntity.saveFriendShip(curUser, email);

		}
		return object.toString();

	}
}