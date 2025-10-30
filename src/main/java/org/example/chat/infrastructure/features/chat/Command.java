package org.example.chat.infrastructure.features.chat;

import org.example.chat.infrastructure.features.Constants;

import java.util.Map;

public enum Command
{
	ADD,
	LIST,
	SEND,
	GO,
	Q,
	EXIT,
	HELP;
	
	public static final Map<String, Command> constantsToCommands = Map.ofEntries(
		Map.entry(Constants.ADD_USER_COMMAND, Command.ADD),
		Map.entry(Constants.PRINT_LIST_OF_USERS_COMMAND, Command.LIST),
		Map.entry(Constants.SEND_MSG_TO_USER_COMMAND, Command.SEND),
		Map.entry(Constants.GO_TO_ANOTHER_CONTACT_COMMAND, Command.GO),
		Map.entry(Constants.GO_TO_DEFAULT_MODE_COMMAND, Command.Q),
		Map.entry(Constants.CLOSE_APPLICATION_COMMAND, Command.EXIT),
		Map.entry(Constants.HELP_COMMAND, Command.HELP)
	);
}
