package org.example.chat.infrastructure.features.commands.exceptions;

public class UserNotExistsException extends RuntimeException
{
	public UserNotExistsException(String message)
	{
		super(message);
	}
}
