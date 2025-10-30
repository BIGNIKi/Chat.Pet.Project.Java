package org.example.chat.infrastructure.features.commands.exceptions;

public class UserAlreadyExistsException extends RuntimeException
{
	public UserAlreadyExistsException(String message)
	{
		super(message);
	}
}
