package org.example.chat.infrastructure.services;

import org.example.chat.application.services.ReaderService;

import java.util.Scanner;

public class ConsoleReaderService extends ReaderService
{
	private final Scanner scanner;
	
	public ConsoleReaderService()
	{
		scanner = new Scanner(System.in);
	}
	
	public String readLine()
	{
		return scanner.nextLine();
	}
	
	@Override
	public void close()
	{
		scanner.close();
	}
}
