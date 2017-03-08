package de.conterra.babelfish.interchange;

/**
 * defines an empty value
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class NullValue
		extends ObjectValue {
	@Override
	public boolean isEmpty() {
		return true;
	}
}