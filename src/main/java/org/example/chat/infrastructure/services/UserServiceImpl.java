package org.example.chat.infrastructure.services;

import org.example.chat.application.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService
{
	private final Set<String> users = new HashSet<>();
	
	@Override
	public boolean addUser(String name)
	{
		if(name == null || users.contains(name))
		{
			return false;
		}
		users.add(name);
		return true;
	}
	
	@Override
	public int getNumOfUser()
	{
		return users.size();
	}
	
	@Override
	public boolean isUserExist(String name)
	{
		return users.contains(name);
	}
	
	@Override
	public List<String> getUserNames()
	{
		return users.stream().toList();
	}
}
