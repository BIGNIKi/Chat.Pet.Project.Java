package org.example.chat.infrastructure.features;

public class Constants
{
	public static final String ADD_USER_COMMAND = "add";
	public static final String PRINT_LIST_OF_USERS_COMMAND = "list";
	public static final String SEND_MSG_TO_USER_COMMAND = "send";
	public static final String GO_TO_ANOTHER_CONTACT_COMMAND = "go";
	public static final String GO_TO_DEFAULT_MODE_COMMAND = "q";
	public static final String CLOSE_APPLICATION_COMMAND = "exit";
	public static final String HELP_COMMAND = "help";
	public static final String USERS_COUNT_INFO = "Number of users is %s";
	public static final String COMMAND_INCORRECT_ERROR = "Incorrect command input. Write /help for more information";
	public static final String USER_ALREADY_EXISTS = "User already exists";
	public static final String NO_DESTINATION_USER = "Destination user not found";
	public static final String TALL_TO_USER_MODE_INFO = "%s# ";
	public static final String ADD_COMMAND_HELP = "Takes one argument - name of user to add.";
	public static final String LIST_COMMAND_HELP = "Prints all users stored at the system.";
	public static final String SEND_COMMAND_HELP = "Takes two+ argument - user name to send message and message itself.";
	public static final String GO_COMMAND_HELP = "Takes one argument - user name to communicate. Set chat mode which always sends msgs to selected user.";
	public static final String Q_COMMAND_HELP = "Set default chat mode which sends msgs to yourself.";
	public static final String EXIT_COMMAND_HELP = "Closes application.";
}
