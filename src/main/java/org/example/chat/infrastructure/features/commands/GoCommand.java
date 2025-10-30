package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.application.services.UserService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.infrastructure.features.chat.ChatMode;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;
import org.example.chat.infrastructure.features.commands.validators.ArgumentCountValidator;
import org.example.chat.infrastructure.features.commands.validators.UserExistingValidator;

import java.util.Objects;

public final class GoCommand extends CommandBase
{
	private final WriterService writerService;
	private final UserService userService;
	
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(
		Constants.GO_TO_ANOTHER_CONTACT_COMMAND,
		Constants.GO_COMMAND_HELP
	);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private GoCommand()
	{
		super(null);
		writerService = null;
		userService = null;
	}
	
	public GoCommand(ForCommandsDataDto data, WriterService writerService, UserService userService)
	{
		super(data);
		this.writerService = writerService;
		this.userService = userService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(userService);
		Objects.requireNonNull(writerService);
		
		var result = new ResultOfCommandDataDto();
		
		// валидация числа аргументов
		CommandValidator checkArguments = new ArgumentCountValidator(data.parts(), 2);
		checkArguments.validate();
		
		var userName = data.parts()[1];
		
		// валидация наличия пользователя
		CommandValidator userExistingChecker = new UserExistingValidator(userService, userName);
		userExistingChecker.validate();
		
		result.setChatModeTo(ChatMode.WITH_USER, userName);
		
		return result;
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
}
