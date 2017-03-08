package de.conterra.babelfish.interchange;

/**
 * defines a {@link Value}, which stores a {@code boolean}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class BooleanValue
		extends Value {
	/**
	 * the stored {@code boolean}
	 *
	 * @since 0.1.0
	 */
	private boolean value;
	
	/**
	 * constructor, with given value
	 *
	 * @param value the value to store
	 * @since 0.1.0
	 */
	public BooleanValue(boolean value) {
		this.value = value;
	}
	
	/**
	 * gives the stored value
	 *
	 * @return the stored value
	 *
	 * @since 0.1.0
	 */
	public boolean getValue() {
		return this.value;
	}
	
	/**
	 * sets the value
	 *
	 * @param value the value to set
	 * @since 0.1.0
	 */
	public void setValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
}