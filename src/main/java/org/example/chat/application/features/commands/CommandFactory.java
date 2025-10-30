package org.example.chat.application.features.commands;

import org.example.chat.infrastructure.features.chat.Command;

public interface CommandFactory
{
	CommandBase makeCommand(Command commandType, String[] parts, String currentUserName);
}
