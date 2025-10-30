package org.example.chat.infrastructure.services;

import org.example.chat.application.features.commands.CommandFactory;
import org.example.chat.application.services.ChatService;
import org.example.chat.application.services.ReaderService;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;
import org.example.chat.infrastructure.features.chat.ChatMode;
import org.example.chat.infrastructure.features.chat.Command;
import org.example.chat.infrastructure.features.loops.LoopHandlerDto;
import org.example.chat.infrastructure.features.extensions.StringExtensions;

public class ChatCoreService implements ChatService
{
	private final WriterService writerService;
	private final ReaderService readerService;
	private final CommandFactory commandFactory;
	private final String currentUserName;
	
	private ChatMode chatMode = ChatMode.DEFAULT;
	/**
	 * Пользователь, с которым общаемся, когда chatMode == ChatMode.WITH_USER
	 */
	private String userCommunicateWith;
	
	public ChatCoreService(
		WriterService writerService,
		ReaderService readerService,
		CommandFactory commandFactory,
		String currentUserName)
	{
		this.writerService = writerService; this.readerService = readerService;
		this.commandFactory = commandFactory;
		this.currentUserName = currentUserName;
	}
	
	@Override
	public void execute()
	{
		do
		{
			if(chatMode == ChatMode.WITH_USER)
			{
				writerService.writeWithNoLineBreak(Constants.TALL_TO_USER_MODE_INFO.formatted(userCommunicateWith));
			}
			else
			{
				writerService.writeWithNoLineBreak("# ");
			}
			
			var someText = readerService.readLine();
			
			if(StringExtensions.startsWith(someText, '/'))
			{
				var loopHandler = processCommand(someText);
				if(loopHandler.isBreakLoop())
				{
					break;
				}
				else if(loopHandler.isContinueLoop())
				{
					continue;
				}
			}
			else
			{
				processChatMessage(someText);
			}
			
		} while(true);
	}
	
	private LoopHandlerDto processCommand(String someText)
	{
		var methodResult = new LoopHandlerDto();
		
		var commandParts = someText
			.substring(1)
			.split(" ");
		if(commandParts.length == 0)
		{
			writerService.write(Constants.COMMAND_INCORRECT_ERROR);
			methodResult.setContinueLoop(true);
			return methodResult;
		}
		
		if(Command.constantsToCommands.containsKey(commandParts[0]))
		{
			var commandType = Command.constantsToCommands.get(commandParts[0]);
			var command = commandFactory.makeCommand(commandType, commandParts, currentUserName);
			var result = command.execute();
			if(result.isExitFlag())
			{
				methodResult.setBreakLoop(true);
				return methodResult;
			}
			else if(result.isChatModeChanged())
			{
				var newMode = result.getChatModeToSet();
				chatMode = newMode;
				if(newMode == ChatMode.WITH_USER)
				{
					userCommunicateWith = result.getUserCommunicateWith();
				}
			}
		}
		else
		{
			writerService.write(Constants.COMMAND_INCORRECT_ERROR);
		}
		
		methodResult.setContinueLoop(true);
		return methodResult;
	}
	
	private void processChatMessage(String someText)
	{
		if(chatMode == ChatMode.DEFAULT)
		{
			var commandType = Command.SEND;
			String[] commandParts = {"send", currentUserName, someText};
			var command = commandFactory.makeCommand(commandType, commandParts, currentUserName);
			command.execute();
			writerService.write(someText);
		}
		else if(chatMode == ChatMode.WITH_USER)
		{
			var commandType = Command.SEND;
			String[] commandParts = {"send", userCommunicateWith, someText};
			var command = commandFactory.makeCommand(commandType, commandParts, currentUserName);
			command.execute();
		}
	}
}
