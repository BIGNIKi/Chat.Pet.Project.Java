package org.example.chat.infrastructure.features.extensions;

public class StringExtensions
{
	public static boolean startsWith(String thisObj, char ch)
	{
		if(thisObj == null || thisObj.isEmpty())
		{
			return false;
		}
		
		return thisObj.charAt(0) == ch;
	}
}
