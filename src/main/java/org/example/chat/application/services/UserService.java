package org.example.chat.application.services;

import java.util.List;

public interface UserService
{
	/**
	 * @param name
	 * @return is user added
	 */
	boolean addUser(String name);
	
	int getNumOfUser();
	
	boolean isUserExist(String name);
	
	List<String> getUserNames();
}
