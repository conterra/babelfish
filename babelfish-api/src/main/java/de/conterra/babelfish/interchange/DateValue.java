package de.conterra.babelfish.interchange;

import org.joda.time.DateTime;

/**
 * defines a {@link Value}, which stores a timestamp
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class DateValue
extends NumberValue
{
	/**
	 * constructor, with given {@link DateTime}
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to store
	 */
	public DateValue(DateTime value)
	{
		super(value.getMillis());
	}
}