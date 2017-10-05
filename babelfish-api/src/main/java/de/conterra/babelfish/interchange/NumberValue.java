package de.conterra.babelfish.interchange;

import lombok.Getter;
import lombok.Setter;

/**
 * defines a {@link Value}, which stores a {@link Number}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class NumberValue
		extends Value {
	/**
	 * the {@link Number} to store
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private Number value;
	
	/**
	 * constructor, with given value
	 *
	 * @param value the value to store
	 * @since 0.1.0
	 */
	public NumberValue(Number value) {
		this.value = value;
	}
	
	@Override
	public boolean isEmpty() {
		return this.value == null || Double.isNaN(this.value.doubleValue());
	}
}
