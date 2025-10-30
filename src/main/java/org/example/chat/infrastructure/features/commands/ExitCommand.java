package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;

public final class ExitCommand extends CommandBase
{
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(
		Constants.CLOSE_APPLICATION_COMMAND,
		Constants.EXIT_COMMAND_HELP
	);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private ExitCommand()
	{
		super(null);
	}
	
	public ExitCommand(ForCommandsDataDto data)
	{
		super(data);
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		var result = new ResultOfCommandDataDto();
		result.setExitFlag();
		return result;
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
}
