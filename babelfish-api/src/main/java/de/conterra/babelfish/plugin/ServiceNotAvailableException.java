package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * {@link ExecutionException}, which should be thrown, if a service gives a not valid answer<br>
 * For example it runs in an {@link Exception} on working the request, too.
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class ServiceNotAvailableException
		extends ExecutionException {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = -9176728755591268754L;
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public ServiceNotAvailableException() {
	}
	
	/**
	 * constructor, with given message
	 *
	 * @param message the message with detail information
	 * @since 0.1.0
	 */
	public ServiceNotAvailableException(String message) {
		super(message);
	}
}