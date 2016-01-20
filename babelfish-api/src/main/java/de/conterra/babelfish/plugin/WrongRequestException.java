package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * This {@link ExecutionException} would be thrown, if a {@link ServiceBuilder}
 * couldn't interpret a request
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class WrongRequestException
extends ExecutionException
{
	/**
	 * generated serial version unique identifier
	 * 
	 * @since 0.1
	 */
	private static final long serialVersionUID = -8135379608394945744L;
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public WrongRequestException()
	{
	}
	
	/**
	 * constructor, with given message
	 * 
	 * @since 0.1
	 * 
	 * @param message the message with detail information
	 */
	public WrongRequestException(String message)
	{
		super(message);
	}
}