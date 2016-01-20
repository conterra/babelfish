package de.conterra.babelfish.interchange;

/**
 * defines a {@link Value}, which contains a {@link String}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class StringValue
extends Value
{
	/**
	 * the value to store
	 * 
	 * @since 0.1
	 */
	private String value;
	
	/**
	 * constructor, with given value
	 * 
	 * @since 0.1
	 * 
	 * @param value the value to store
	 */
	public StringValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public StringValue()
	{
		this("");
	}
	
	/**
	 * gives the value
	 * 
	 * @since 0.1
	 * 
	 * @return the value
	 */
	public String getValue()
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
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public boolean isEmpty()
	{
		return this.value == null || this.value.isEmpty();
	}
}