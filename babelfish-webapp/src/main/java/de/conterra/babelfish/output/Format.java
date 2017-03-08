package de.conterra.babelfish.output;

/**
 * defines all possible output formats
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public enum Format {
	/**
	 * JSON format (standard exchange format of the REST API)
	 *
	 * @since 0.1.0
	 */
	JSON(new JsonFormatter()),
	/**
	 * format of HTML sites (API review for the user)
	 *
	 * @since 0.1.0
	 */
	HTML(new JsonFormatter()); // TODO create formatter for pretty HTML sites
	
	/**
	 * the class to generate output by the {@link Format}
	 *
	 * @since 0.1.0
	 */
	public final OutlineFormatter formatter;
	
	/**
	 * standard constructor, with given {@link OutlineFormatter}
	 *
	 * @param formatter the {@link OutlineFormatter} to produce the output in this format
	 * @since 0.1.0
	 */
	private Format(OutlineFormatter formatter) {
		this.formatter = formatter;
	}
}