package de.conterra.babelfish.interchange;

import de.conterra.babelfish.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * defines a {@link Value}, which contains a {@link String}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class StringValue
		extends Value {
	/**
	 * the value to store
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private String value;
	
	/**
	 * constructor, with given value
	 *
	 * @param value the value to store
	 * @since 0.1.0
	 */
	public StringValue(String value) {
		this.value = value;
	}
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public StringValue() {
		this(StringUtils.EMPTY);
	}
	
	@Override
	public boolean isEmpty() {
		return this.value == null || this.value.isEmpty();
	}
}
