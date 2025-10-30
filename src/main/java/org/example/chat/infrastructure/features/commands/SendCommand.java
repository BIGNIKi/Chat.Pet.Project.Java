package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.services.ChatHistoryService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SendCommand extends CommandBase
{
	private final WriterService writerService;
	private final ChatHistoryService chatHistoryService;
	private final String currentUserName;
	
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(
		Constants.SEND_MSG_TO_USER_COMMAND,
		Constants.SEND_COMMAND_HELP
	);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private SendCommand()
	{
		super(null);
		writerService = null;
		chatHistoryService = null;
		currentUserName = null;
	}
	
	public SendCommand(
		ForCommandsDataDto data,
		WriterService writerService,
		ChatHistoryService chatHistoryService,
		String currentUserName)
	{
		super(data);
		this.writerService = writerService;
		this.chatHistoryService = chatHistoryService;
		this.currentUserName = currentUserName;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(chatHistoryService);
		Objects.requireNonNull(writerService);
		
		if(data.parts().length >= 3)
		{
			
			var userName = data.parts()[1];
			
			var message = buildMessage(data.parts());
			// SendMessage под капотом проверяет, существует ли пользователь
			var isSuccess = chatHistoryService.SendMessage(currentUserName, userName, message);
			if(!isSuccess)
			{
				writerService.write(Constants.NO_DESTINATION_USER);
			}
		}
		else
		{
			writerService.write(Constants.COMMAND_INCORRECT_ERROR);
		}
		
		return new ResultOfCommandDataDto();
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
	
	private static String buildMessage(String[] parts)
	{
		return Arrays
			.stream(parts, 2, parts.length)
			.collect(Collectors.joining(" "));
	}
}
