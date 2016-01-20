package de.conterra.babelfish.interchange;

/**
 * defines a {@link Value}, which stores a <code>boolean</code>
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class BooleanValue
extends Value
{
	/**
	 * the stored <code>boolean</code>
	 * 
	 * @since 0.1
	 */
	private boolean value;
	
	/**
	 * constructor, with given value
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to store
	 */
	public BooleanValue(boolean value)
	{
		this.value = value;
	}
	
	/**
	 * gives the stored value
	 * 
	 * @since 0.1
	 * 
	 * @return the stored value
	 */
	public boolean getValue()
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
	public void setValue(boolean value)
	{
		this.value = value;
	}
	
	@Override
	public boolean isEmpty()
	{
		return false;
	}
}