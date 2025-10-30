package org.example.chat.infrastructure.features.commands.validators;

import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.application.services.UserService;
import org.example.chat.infrastructure.features.commands.exceptions.UserNotExistsException;

public class UserExistingValidator implements CommandValidator
{
	private final UserService userService;
	private final String usernameToCheck;
	
	public UserExistingValidator(UserService userService, String usernameToCheck)
	{
		this.userService = userService;
		this.usernameToCheck = usernameToCheck;
	}
	
	@Override
	public void validate()
	{
		if(!userService.isUserExist(usernameToCheck))
		{
			throw new UserNotExistsException("User with name = %s doesn't exist".formatted(usernameToCheck));
		}
	}
}
