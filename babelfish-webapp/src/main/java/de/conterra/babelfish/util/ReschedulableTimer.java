package de.conterra.babelfish.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * defines a {@link Timer}, which could be restarted
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class ReschedulableTimer
		extends Timer {
	/**
	 * the task to execute
	 *
	 * @since 0.1.0
	 */
	private final Runnable task;
	/**
	 * the {@link TimerTask}, which executes the {@code task}
	 *
	 * @since 0.1.0
	 */
	private TimerTask timerTask = null;
	
	/**
	 * constructor, with given task
	 *
	 * @param task the task to execute
	 * @since 0.1.0
	 */
	public ReschedulableTimer(Runnable task) {
		this.task = task;
	}
	
	/**
	 * cancels the current execution and restarts the {@link Timer}
	 *
	 * @param delay the new delay in milliseconds
	 * @since 0.1.0
	 */
	public synchronized void reschedule(long delay) {
		if (this.timerTask != null)
			this.timerTask.cancel();
		
		this.timerTask = new TimerTask() {
			@Override
			public void run() {
				ReschedulableTimer.this.task.run();
			}
		};
		
		super.schedule(this.timerTask, delay);
	}
}