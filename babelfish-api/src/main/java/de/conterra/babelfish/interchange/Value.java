package de.conterra.babelfish.interchange;

/**
 * base class of interchange format
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public abstract class Value
{
	/**
	 * has the {@link Value} no content?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link Value} has no content
	 */
	public abstract boolean isEmpty();
}