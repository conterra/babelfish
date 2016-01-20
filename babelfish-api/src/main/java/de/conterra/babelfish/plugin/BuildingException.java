package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * This {@link ExecutionException} would be thrown, if a {@link ServiceBuilder}
 * run into an {@link Exception} even though the request was valid
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class BuildingException
extends ExecutionException
{
	/**
	 * generated serial version unique identifier
	 * 
	 * @since 0.1
	 */
	private static final long serialVersionUID = 6153281879488344633L;
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 * 
	 * @param message the message with detail information
	 */
	public BuildingException(String message)
	{
		super(message);
	}
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 * 
	 * @param message the message with detail information
	 * @param cause the cause
	 */
	public BuildingException(String message, Throwable cause)
	{
		super(message, cause);
	}
}