package org.example.chat.infrastructure.features.chat;

import java.util.Arrays;

/**
 * Класс отражает ключ, состоящий из пары строк, где перестановки строк не играет роли для расчета equals и hashCode.
 */
public record PairKeyString(String first, String second)
{
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		var other = (PairKeyString) obj;
		
		// Сортируем строки для игнорирования порядка
		String[] thisSorted = {this.first, this.second};
		String[] otherSorted = {other.first, other.second};
		Arrays.sort(thisSorted);
		Arrays.sort(otherSorted);
		
		return Arrays.equals(thisSorted, otherSorted);
	}
}
