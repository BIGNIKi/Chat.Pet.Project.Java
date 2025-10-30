package org.example.chat.application.services;

public abstract class ReaderService implements AutoCloseable
{
	public abstract String readLine();
}
