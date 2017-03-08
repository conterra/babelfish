package de.conterra.babelfish.interchange;

import org.joda.time.DateTime;

/**
 * defines a {@link Value}, which stores a timestamp
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class DateValue
		extends NumberValue {
	/**
	 * constructor, with given {@link DateTime}
	 *
	 * @param value the value to store
	 * @since 0.1.0
	 */
	public DateValue(DateTime value) {
		super(value.getMillis());
	}
	
	public DateTime getTime() {
		return new DateTime(this.getValue());
	}
}