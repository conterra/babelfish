package de.conterra.babelfish.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * defines a {@link Timer}, which could be restarted
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ReschedulableTimer
extends Timer
{
	/**
	 * the task to execute
	 * 
	 * @since 0.1
	 */
	private final Runnable task;
	/**
	 * the {@link TimerTask}, which executes the <code>task</code>
	 * 
	 * @since 0.1
	 */
	private TimerTask timerTask = null;
	
	/**
	 * constructor, with given task
	 * 
	 * @since 0.1
	 * 
	 * @param task the task to execute
	 */
	public ReschedulableTimer(Runnable task)
	{
		this.task = task;
	}
	
	/**
	 * cancels the current execution and restarts the {@link Timer}
	 * 
	 * @since 0.1
	 * 
	 * @param delay the new delay in milliseconds
	 */
	public synchronized void reschedule(long delay)
	{
		if (this.timerTask != null)
			this.timerTask.cancel();
		
		this.timerTask = new TimerTask()
		{
			@Override
			public void run()
			{
				ReschedulableTimer.this.task.run();
			}
		};
		
		super.schedule(this.timerTask, delay);
	}
}