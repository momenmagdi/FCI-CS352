package com.FCI.SWE.ServicesModels;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;


public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;

	public UserEntity(String name, String email, String password) {
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

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}

	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("users", list.size() + 2);

			employee.setProperty("name", this.name);
			employee.setProperty("email", this.email);
			employee.setProperty("password", this.password);
			datastore.put(employee);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	public static Boolean confirmFrind(String email, String femail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("FriendShip");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		for (Entity Friend : pq.asIterable()) {
			System.out.println(Friend.getProperty("cuurent"));
			if (Friend.getProperty("cuurent").toString().equals(femail)
					&& Friend.getProperty("reciver").toString().equals(email)
					&& Friend.getProperty("bool").toString().equals("false")) {
				System.out.println(" entercond");
				Friend.setProperty("bool", true);
				datastore.put(Friend);

			}
		}
		return true;

	}

	public static Vector<String> getFrindRequsts(String email) {
		Vector<String> friendsEmail = null;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("FriendShip");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (Entity Friend : pq.asIterable()) {

			if (Friend.getProperty("reciver").toString().equals(email)
					&& Friend.getProperty("bool").toString().equals("false")) {
				System.out.println(" entercond");
				friendsEmail.add((String) Friend.getProperty("cuurent")); // datastore.put(Friend);

			}

		}

		System.out.println("*********" + friendsEmail + "r***********");
		return friendsEmail;

	}

	public static String getUserByEmail(String email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("email").toString().equals(email)) {
				return email;
			}
		}

		return null;
	}

	public static Boolean saveFriendShip(String emailOfCurrentUser,
			String emailOfReciveUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("FriendShip");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity Friend = new Entity("FriendShip", list.size() + 1);

		Friend.setProperty("cuurent", emailOfCurrentUser);
		Friend.setProperty("reciver", emailOfReciveUser);
		Friend.setProperty("bool", false);
		datastore.put(Friend);

		return true;

	}
}
