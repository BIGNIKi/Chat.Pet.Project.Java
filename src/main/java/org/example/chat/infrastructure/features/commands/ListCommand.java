package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.services.UserService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;

import java.util.Objects;

public final class ListCommand extends CommandBase
{
	private final WriterService writerService;
	private final UserService userService;
	
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(
		Constants.PRINT_LIST_OF_USERS_COMMAND,
		Constants.LIST_COMMAND_HELP
	);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private ListCommand()
	{
		super(null);
		writerService = null;
		userService = null;
	}
	
	public ListCommand(ForCommandsDataDto data, WriterService writerService, UserService userService)
	{
		super(data);
		this.writerService = writerService;
		this.userService = userService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(writerService);
		Objects.requireNonNull(userService);
		
		var numOfUsers = userService.getNumOfUser();
		var strToPrint = Constants.USERS_COUNT_INFO.formatted(numOfUsers);
		writerService.write(strToPrint);
		if(numOfUsers > 0)
		{
			var userNames = userService.getUserNames();
			var strBuilder = new StringBuilder();
			for(int i = 0; i < userNames.size(); i++)
			{
				if(i != 0)
				{
					strBuilder.append(", ");
				}
				strBuilder.append(userNames.get(i));
			}
			writerService.write(strBuilder.toString());
		}
		
		return new ResultOfCommandDataDto();
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
}
