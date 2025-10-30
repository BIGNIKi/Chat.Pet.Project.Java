package org.example.chat.infrastructure.services;

import org.example.chat.application.services.ChatHistoryService;
import org.example.chat.application.services.UserService;
import org.example.chat.infrastructure.features.chat.PairKeyString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatHistoryServiceImpl implements ChatHistoryService
{
	private final UserService userService;
	
	private final Map<PairKeyString, List<String>> msgHistory = new HashMap<>();
	
	public ChatHistoryServiceImpl(UserService userService)
	{
		this.userService = userService;
	}
	
	@Override
	public boolean SendMessage(String sourceUser, String destUser, String msg)
	{
		if(userService.isUserExist(destUser))
		{
			var key = new PairKeyString(sourceUser, destUser);
			if(msgHistory.containsKey(key))
			{
				var chatList = msgHistory.get(key);
				chatList.add(msg);
			}
			else
			{
				var listToAdd = new ArrayList<String>();
				listToAdd.add(msg);
				msgHistory.put(key, listToAdd);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}
