package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.application.services.UserService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;
import org.example.chat.infrastructure.features.commands.validators.ArgumentCountValidator;
import org.example.chat.infrastructure.features.commands.validators.PossibilityToAddUserValidator;

import java.util.Objects;

public final class AddCommand extends CommandBase
{
	private final UserService userService;
	private final WriterService writerService;
	
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(Constants.ADD_USER_COMMAND, Constants.ADD_COMMAND_HELP);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private AddCommand()
	{
		super(null);
		userService = null;
		writerService = null;
	}
	
	public AddCommand(ForCommandsDataDto data, UserService userService, WriterService writerService)
	{
		super(data);
		this.userService = userService;
		this.writerService = writerService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(userService);
		Objects.requireNonNull(writerService);
		
		// валидация числа аргументов
		CommandValidator checkArguments = new ArgumentCountValidator(data.parts(), 2);
		checkArguments.validate();
		
		// валидация возможности добавления пользователя (уже существует?)
		var userToAdd = data.parts()[1];
		CommandValidator checkOnExistingValidator = new PossibilityToAddUserValidator(userService, userToAdd);
		checkOnExistingValidator.validate();
		
		userService.addUser(userToAdd);
		
		return new ResultOfCommandDataDto();
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
}
