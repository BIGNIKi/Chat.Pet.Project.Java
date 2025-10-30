package org.example.chat.application.services;

public interface ChatHistoryService
{
	/**
	 * @param sourceUser
	 * @param destUser
	 * @param msg
	 * @return true - сообщение отправлено. false - нет.
	 */
	boolean SendMessage(String sourceUser, String destUser, String msg);
}
