package org.example.chat.infrastructure.features.commands.dtos;

import org.example.chat.infrastructure.features.chat.ChatMode;

public final class ResultOfCommandDataDto
{
	private boolean exitFlag = false;
	private boolean isChatModeChanged = false;
	private ChatMode chatModeToSet;
	private String userCommunicateWith;
	
	// Getters and setters
	
	public boolean isExitFlag()
	{
		return exitFlag;
	}
	
	public void setExitFlag()
	{
		this.exitFlag = true;
	}
	
	public boolean isChatModeChanged()
	{
		return isChatModeChanged;
	}
	
	public ChatMode getChatModeToSet()
	{
		return chatModeToSet;
	}
	
	public String getUserCommunicateWith()
	{
		return userCommunicateWith;
	}
	
	// Main logic
	
	public ResultOfCommandDataDto() {
	
	}
	
	public void setChatModeTo(ChatMode newMode, String userCommunicateWith)
	{
		isChatModeChanged = true;
		chatModeToSet = newMode;
		this.userCommunicateWith = userCommunicateWith;
	}
}
