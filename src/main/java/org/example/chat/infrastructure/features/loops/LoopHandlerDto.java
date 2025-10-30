package org.example.chat.infrastructure.features.loops;

public final class LoopHandlerDto
{
	private boolean continueLoop;
	private boolean breakLoop;
	
	public boolean isContinueLoop()
	{
		return continueLoop;
	}
	
	public void setContinueLoop(boolean continueLoop)
	{
		this.continueLoop = continueLoop;
	}
	
	public boolean isBreakLoop()
	{
		return breakLoop;
	}
	
	public void setBreakLoop(boolean breakLoop)
	{
		this.breakLoop = breakLoop;
	}
}
