package de.conterra.babelfish.plugin;

import java.util.concurrent.ExecutionException;

/**
 * This {@link ExecutionException} would be thrown, if a {@link ServiceBuilder} run into an {@link Exception} even though the request was valid
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class BuildingException
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
	 * @param message the message with detail information
	 * @since 0.1.0
	 */
	public BuildingException(String message) {
		super(message);
	}
	
	/**
	 * standard constructor
	 *
	 * @param message the message with detail information
	 * @param cause   the cause
	 * @since 0.1.0
	 */
	public BuildingException(String message, Throwable cause) {
		super(message, cause);
	}
}