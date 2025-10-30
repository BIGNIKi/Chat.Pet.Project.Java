package org.example.chat.infrastructure.features.commands.exceptions;

import org.example.chat.application.services.ValidationErrorHandler;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.Constants;

public final class ValidationErrorHandlerImpl implements ValidationErrorHandler
{
	private final WriterService writerService;
	
	public ValidationErrorHandlerImpl(WriterService writerService) {
		this.writerService = writerService;
	}
	
	@Override
	public void handle(Exception ex)
	{
		if(ex instanceof ArgumentCountException)
		{
			writerService.write(Constants.COMMAND_INCORRECT_ERROR);
		}
		else if(ex instanceof UserAlreadyExistsException)
		{
			writerService.write(Constants.USER_ALREADY_EXISTS);
		}
		else if(ex instanceof UserNotExistsException)
		{
			writerService.write(Constants.NO_DESTINATION_USER);
		}
	}
}
