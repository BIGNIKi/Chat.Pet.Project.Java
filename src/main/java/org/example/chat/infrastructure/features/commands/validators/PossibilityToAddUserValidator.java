package org.example.chat.infrastructure.features.commands.validators;

import org.example.chat.application.features.commands.validators.CommandValidator;
import org.example.chat.application.services.UserService;
import org.example.chat.infrastructure.features.commands.exceptions.UserAlreadyExistsException;

public class PossibilityToAddUserValidator implements CommandValidator
{
	private final UserService userService;
	private final String usernameToCheck;
	
	public PossibilityToAddUserValidator(UserService userService, String usernameToCheck)
	{
		this.userService = userService;
		this.usernameToCheck = usernameToCheck;
	}
	
	@Override
	public void validate()
	{
		if(userService.isUserExist(usernameToCheck))
		{
			throw new UserAlreadyExistsException("User with name = %s already exists".formatted(usernameToCheck));
		}
	}
}
