package org.example.chat.infrastructure.services;

import org.example.chat.application.services.WriterService;

public class ConsoleWriterService implements WriterService
{
	
	@Override
	public void write(String message)
	{
		System.out.println(message);
	}
	
	@Override
	public void writeWithNoLineBreak(String message)
	{
		System.out.print(message);
	}
}
