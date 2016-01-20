package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * {@link ExecutionException}, which should be thrown, if a service gives a not
 * valid answer<br>
 * For example it runs in an {@link Exception} on working the request, too.
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ServiceNotAvailableException
extends ExecutionException
{
	/**
	 * generated serial version unique identifier
	 * 
	 * @since 0.1
	 */
	private static final long serialVersionUID = -9176728755591268754L;
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public ServiceNotAvailableException()
	{
	}
	
	/**
	 * constructor, with given message
	 * 
	 * @since 0.1
	 * 
	 * @param message the message with detail information
	 */
	public ServiceNotAvailableException(String message)
	{
		super(message);
	}
}