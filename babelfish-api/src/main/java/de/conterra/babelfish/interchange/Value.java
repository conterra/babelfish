package de.conterra.babelfish.interchange;

/**
 * base class of interchange format
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class Value {
	/**
	 * has the {@link Value} no content?
	 *
	 * @return {@code true}, if this {@link Value} has no content
	 *
	 * @since 0.1.0
	 */
	public abstract boolean isEmpty();
}