package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * This {@link ExecutionException} would be thrown, if a {@link ServiceBuilder} couldn't interpret a request
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class WrongRequestException
		extends ExecutionException {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public WrongRequestException() {
	}
	
	/**
	 * constructor, with given message
	 *
	 * @param message the message with detail information
	 * @since 0.1.0
	 */
	public WrongRequestException(String message) {
		super(message);
	}
}