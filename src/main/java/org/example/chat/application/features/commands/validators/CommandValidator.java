package org.example.chat.application.features.commands.validators;

import org.example.chat.infrastructure.features.commands.exceptions.ArgumentCountException;

public interface CommandValidator
{
	void validate();
}
