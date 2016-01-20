package de.conterra.babelfish.output;

/**
 * defines all possible output formats
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public enum Format
{
	/**
	 * JSON format (standard exchange format of the REST API)
	 * 
	 * @since 0.1
	 */
	JSON(new JsonFormatter()),
	/**
	 * format of HTML sites (API review for the user)
	 * 
	 * @since 0.1
	 */
	HTML(new JsonFormatter()); // TODO create formatter for pretty HTML sites
	
	/**
	 * the class to generate output by the {@link Format}
	 * 
	 * @since 0.1
	 */
	public final OutlineFormatter formatter;
	
	/**
	 * standard constructor, with given {@link OutlineFormatter}
	 * 
	 * @since 0.1
	 * 
	 * @param formatter the {@link OutlineFormatter} to produce the output in
	 *        this format
	 */
	private Format(OutlineFormatter formatter)
	{
		this.formatter = formatter;
	}
}