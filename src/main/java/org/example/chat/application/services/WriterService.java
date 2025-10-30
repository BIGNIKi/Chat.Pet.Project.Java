package org.example.chat.application.services;

public interface WriterService
{
	void write(String message);
	
	void writeWithNoLineBreak(String message);
}
