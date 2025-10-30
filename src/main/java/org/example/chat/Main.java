package org.example.chat;

import org.example.chat.application.features.commands.CommandFactory;
import org.example.chat.application.services.*;
import org.example.chat.infrastructure.features.commands.exceptions.ValidationErrorHandlerImpl;
import org.example.chat.infrastructure.features.commands.factory.CommandFactoryImpl;
import org.example.chat.infrastructure.services.*;

public class Main
{
	public static void main(String[] args)
	{
		var currentUserName = "Nikita";
		try(ReaderService readerService = new ConsoleReaderService())
		{
			WriterService writerService = new ConsoleWriterService();
			UserService userService = new UserServiceImpl();
			userService.addUser(currentUserName);
			ChatHistoryService chatHistoryService = new ChatHistoryServiceImpl(userService);
			CommandFactory commandFactory = new CommandFactoryImpl(userService, writerService, chatHistoryService);
			ValidationErrorHandler validationErrorHandler = new ValidationErrorHandlerImpl(writerService);
			ChatService chatService = new ChatCoreService(
				writerService,
				readerService,
				commandFactory,
				currentUserName,
				validationErrorHandler
			);
			
			chatService.execute();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}