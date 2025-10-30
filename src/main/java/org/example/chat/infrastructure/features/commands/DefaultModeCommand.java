package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.infrastructure.features.chat.ChatMode;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;

import java.util.Objects;

public final class DefaultModeCommand extends CommandBase
{
	private final WriterService writerService;
	
	private final HelpInfoDto helpInfoDto = new HelpInfoDto(
		Constants.GO_TO_DEFAULT_MODE_COMMAND,
		Constants.Q_COMMAND_HELP
	);
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private DefaultModeCommand()
	{
		super(null);
		writerService = null;
	}
	
	public DefaultModeCommand(ForCommandsDataDto data, WriterService writerService)
	{
		super(data);
		this.writerService = writerService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(writerService);
		
		var result = new ResultOfCommandDataDto();
		if(data.parts().length == 1)
		{
			result.setChatModeTo(ChatMode.DEFAULT, null);
		}
		else
		{
			writerService.write(Constants.COMMAND_INCORRECT_ERROR);
		}
		
		return result;
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return helpInfoDto;
	}
}
