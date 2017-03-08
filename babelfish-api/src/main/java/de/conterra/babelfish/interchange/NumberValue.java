package de.conterra.babelfish.interchange;

/**
 * defines a {@link Value}, which stores a {@link Number}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class NumberValue
		extends Value {
	/**
	 * the {@link Number} to store
	 *
	 * @since 0.1.0
	 */
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
	
	/**
	 * gives the value
	 *
	 * @return the value
	 *
	 * @since 0.1.0
	 */
	public Number getValue() {
		return this.value;
	}
	
	/**
	 * sets the value
	 *
	 * @param value the value to set
	 * @since 0.1.0
	 */
	public void setValue(Number value) {
		this.value = value;
	}
	
	@Override
	public boolean isEmpty() {
		return this.value == null || Double.isNaN(this.value.doubleValue());
	}
}