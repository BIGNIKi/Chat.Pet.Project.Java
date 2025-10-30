package org.example.chat.infrastructure.features.commands;

import org.example.chat.application.features.commands.CommandBase;
import org.example.chat.application.features.commands.dtos.ForCommandsDataDto;
import org.example.chat.application.features.commands.dtos.HelpInfoDto;
import org.example.chat.application.services.WriterService;
import org.example.chat.infrastructure.features.commands.dtos.ResultOfCommandDataDto;
import org.reflections.Reflections;

import java.util.*;

public final class HelpCommand extends CommandBase
{
	private final WriterService writerService;
	private static String cachedHelp;
	
	/**
	 * Нужен для рефлексии! См. Class HelpCommand
	 */
	@SuppressWarnings("unused")
	private HelpCommand()
	{
		super(null);
		writerService = null;
	}
	
	public HelpCommand(ForCommandsDataDto data, WriterService writerService)
	{
		super(data);
		this.writerService = writerService;
	}
	
	@Override
	public ResultOfCommandDataDto execute()
	{
		Objects.requireNonNull(writerService);
		
		if(cachedHelp == null)
		{
			var helpInfos = getHelpInfos();
			
			cachedHelp = prepareStringToWrite(helpInfos);
		}
		
		writerService.write(cachedHelp);
		
		return new ResultOfCommandDataDto();
	}
	
	@Override
	public HelpInfoDto getHelpInfo()
	{
		return null;
	}
	
	private List<HelpInfoDto> getHelpInfos()
	{
		List<HelpInfoDto> helpInfos = new ArrayList<>();
		
		String packageName = this.getClass().getPackage().getName();
		var reflections = new Reflections(packageName);
		
		Set<Class<? extends CommandBase>> subTypes = reflections.getSubTypesOf(CommandBase.class);
		
		for(var clazz : subTypes)
		{
			try
			{
				var constructor = clazz.getDeclaredConstructor();
				
				constructor.setAccessible(true);
				
				var instance = constructor.newInstance();
				
				var helpInfo = instance.getHelpInfo();
				
				if(helpInfo != null)
				{
					helpInfos.add(helpInfo);
				}
			}
			catch(ReflectiveOperationException e)
			{
				// Обработка ошибок: нет конструктора, исключение при создании/вызове
				System.err.println("Ошибка при создании экземпляра " + clazz.getName() + ": " + e.getMessage());
			}
			catch(Exception e)
			{
				System.err.println("Неожиданная ошибка для " + clazz.getName() + ": " + e.getMessage());
			}
		}
		
		return helpInfos;
	}
	
	private String prepareStringToWrite(List<HelpInfoDto> helpInfos)
	{
		var sortedList = helpInfos
			.stream()
			.sorted(Comparator.comparing(HelpInfoDto::commandName))
			.toList();
		
		int maxLengthOfCommand = sortedList
			.stream()
			.mapToInt(x -> x.commandName().length())
			.max()
			.orElse(0);
		
		var sb = new StringBuilder();
		for(int i = 0; i < sortedList.size(); i++)
		{
			var dto = sortedList.get(i);
			var commandPart = " " + dto.commandName();
			var padding = maxLengthOfCommand - dto.commandName().length() + 1;
			var paddingStr = String.format("%" + padding + "s", "");
			var line = commandPart + paddingStr + dto.helpInfo();
			sb.append(line);
			if(i < sortedList.size() - 1)
			{
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
}
