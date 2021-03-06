package com.SnippetWorld.imp;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.SnippetWorld.data.UserData;
import com.SnippetWorld.model.User;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class UsersManager {
	
	static List<User> users = new ArrayList<User>();
	static Key key;

	static UsersManager userManager = null;
	
	public static UsersManager getInstance() {
		if (userManager == null) {
			userManager = new UsersManager();
			key = MacProvider.generateKey();
		}
		return userManager;
	}
	
	public Key getKey() {
		return key;
	}
	
	// GET ALL USERS
		public List<User> getUsers() {
			UserData userData = UserData.getInstance();
			
			
			return userData.getUsers();
		}

		public static boolean checkCredentials(String username, String password) {
			
			UserData userData = UserData.getInstance();
			return userData.checkCredentials(username, password);
			
		}

		public void createUser(String username, String email, String password, String name) {
			
			UserData userData = UserData.getInstance();
			

			userData.createUser(new User(username,  password, email,name));
			
		}
		
		// GET A USER = USERID
		public List<User> getUser(String userID) {
			UserData userData = UserData.getInstance();
			return userData.getUser(userID);
		}

		//CHANGE PASSWORD USERID
		public Response MudaPassword(String username, String pass, String NovaPassword, String Iduser) {
			
			List<User> user = UserData.getInstance().getUser(username);
			
			String userPassword = null;
			for (User user_Temp : user) {
				userPassword = user_Temp.getPassword();
			}
			
			
			
			// CHECKS IF THE USER LOGGED == USER TO UPDATE
			if (username.equals(Iduser) && (pass.equals(userPassword))) {
				UserData.getInstance().updatePassword(username, NovaPassword);
				
			} else {
				System.out.println("VOCE NAO PODE ALTERAR A PASS");
			}
			return null;
		}

		

}
