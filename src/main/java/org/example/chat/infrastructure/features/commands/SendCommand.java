package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.application.services.ChatHistoryService;
import org.example.chat.application.services.UserService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;
import org.example.chat.infrastructure.features.commands.validators.ArgumentCountValidator;
import org.example.chat.infrastructure.features.commands.validators.UserExistingValidator;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SendCommand extends CommandBase
{
	private final WriterService writerService;
	private final ChatHistoryService chatHistoryService;
	private final String currentUserName;
	private final UserService userService;
	
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
		userService = null;
		writerService = null;
		chatHistoryService = null;
		currentUserName = null;
	}
	
	public SendCommand(
		ForCommandsDataDto data,
		WriterService writerService,
		ChatHistoryService chatHistoryService,
		String currentUserName, UserService userService)
	{
		super(data);
		this.writerService = writerService;
		this.chatHistoryService = chatHistoryService;
		this.currentUserName = currentUserName;
		this.userService = userService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(chatHistoryService);
		Objects.requireNonNull(writerService);
		
		// валидация числа аргументов
		CommandValidator checkArguments = new ArgumentCountValidator(data.parts(), 3, true);
		checkArguments.validate();
		
		var userName = data.parts()[1];
		
		// валидация наличия пользователя
		CommandValidator userExistingChecker = new UserExistingValidator(userService, userName);
		userExistingChecker.validate();
		
		var message = buildMessage(data.parts());
		
		chatHistoryService.SendMessage(currentUserName, userName, message);
		
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
