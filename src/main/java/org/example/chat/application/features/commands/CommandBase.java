package org.example.chat.application.features.commands;

import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;

public abstract class CommandBase
{
	protected final ForCommandsDataDto data;
	
	public CommandBase(ForCommandsDataDto data)
	{
		this.data = data;
	}
	
	public abstract ResultOfCommandDataDto execute();
	
	public abstract HelpInfoDto getHelpInfo();
}
