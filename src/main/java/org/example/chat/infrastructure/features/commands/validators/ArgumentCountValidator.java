package org.example.chat.infrastructure.features.commands.validators;

import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.infrastructure.features.commands.exceptions.ArgumentCountException;

public final class ArgumentCountValidator implements CommandValidator
{
	private final String[] parts;
	private final int shouldBeArguments;
	private boolean lessThan = false;
	
	public ArgumentCountValidator(String[] parts, int shouldBeArguments)
	{
		this.parts = parts;
		this.shouldBeArguments = shouldBeArguments;
	}
	
	public ArgumentCountValidator(String[] parts, int shouldBeArguments, boolean lessThan)
	{
		this.parts = parts;
		this.shouldBeArguments = shouldBeArguments;
		this.lessThan = lessThan;
	}
	
	@Override
	public void validate()
	{
		if(lessThan)
		{
			if(parts.length < shouldBeArguments)
			{
				throw new ArgumentCountException("Command must have %s or more arguments.".formatted(shouldBeArguments));
			}
		}
		else
		{
			if(parts.length != shouldBeArguments)
			{
				throw new ArgumentCountException("Command must have %s arguments.".formatted(shouldBeArguments));
			}
		}
	}
}
