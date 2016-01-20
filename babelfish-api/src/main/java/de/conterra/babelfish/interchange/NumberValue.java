package de.conterra.babelfish.interchange;

/**
 * defines a {@link Value}, which stores a {@link Number}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class NumberValue
extends Value
{
	/**
	 * the {@link Number} to store
	 * 
	 * @since 0.1
	 */
	private Number value;
	
	/**
	 * constructor, with given value
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to store
	 */
	public NumberValue(Number value)
	{
		this.value = value;
	}
	
	/**
	 * gives the value
	 * 
	 * @since 0.1
	 * 
	 * @return the value
	 */
	public Number getValue()
	{
		return this.value;
	}
	
	/**
	 * sets the value
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to set
	 */
	public void setValue(Number value)
	{
		this.value = value;
	}
	
	@Override
	public boolean isEmpty()
	{
		return this.value == null || Double.isNaN(this.value.doubleValue());
	}
}