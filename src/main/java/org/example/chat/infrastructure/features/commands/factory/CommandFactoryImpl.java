package org.example.chat.infrastructure.features.commands.factory;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.CommandFactory;
import org.example.chat.application.services.ChatHistoryService;
import org.example.chat.application.services.UserService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.chat.Command;
import org.example.chat.infrastructure.features.commands.*;
import org.example.chat.infrastructure.features.commands.DefaultModeCommand;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;

public final class CommandFactoryImpl implements CommandFactory
{
	private final UserService userService;
	private final WriterService writerService;
	private final ChatHistoryService chatHistoryService;
	
	public CommandFactoryImpl(
		UserService userService,
		WriterService writerService,
		ChatHistoryService chatHistoryService)
	{
		this.userService = userService;
		this.writerService = writerService;
		this.chatHistoryService = chatHistoryService;
	}
	
	@Override
	public CommandBase makeCommand(Command commandType, String[] parts, String currentUserName)
	{
		var forCommandData = new ForCommandsDataDto(parts);
		
		return switch(commandType)
		{
			case Command.ADD -> new AddCommand(forCommandData, userService, writerService);
			case Command.LIST -> new ListCommand(forCommandData, writerService, userService);
			case Command.SEND -> new SendCommand(
				forCommandData,
				writerService,
				chatHistoryService,
				currentUserName
			);
			case Command.GO -> new GoCommand(forCommandData, writerService, userService);
			case Command.Q -> new DefaultModeCommand(forCommandData, writerService);
			case Command.EXIT -> new ExitCommand(forCommandData);
			case Command.HELP -> new HelpCommand(forCommandData, writerService);
		};
	}
}
