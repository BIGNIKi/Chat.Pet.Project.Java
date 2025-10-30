package org.example.chat.infrastructure.features.commands.exceptions;

public final class ArgumentCountException extends RuntimeException
{
	public ArgumentCountException(String message) {
	    super(message);
	}
}
