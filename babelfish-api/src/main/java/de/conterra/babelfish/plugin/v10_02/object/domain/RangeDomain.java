package de.conterra.babelfish.plugin.v10_02.object.domain;

/**
 * defines a Range Domain
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class RangeDomain
extends DomainObject
{
	/**
	 * the name
	 * 
	 * @since 0.1
	 */
	private final String name;
	/**
	 * the minimum of the range
	 * 
	 * @since 0.1
	 */
	private int min;
	/**
	 * the maximum of the range
	 * 
	 * @since 0.1
	 */
	private int max;
	
	/**
	 * constructor, with all attributes
	 * 
	 * @since 0.1
	 * 
	 * @param name the name
	 * @param min the minimum of the range
	 * @param max the maximum of the range
	 */
	public RangeDomain(String name, int min, int max)
	{
		this.name = name;
		this.min = min;
		this.max = max;
	}
	
	@Override
	public String getType()
	{
		return "range";
	}
	
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * gives the minimum of the range
	 * 
	 * @since 0.1
	 * 
	 * @return the minimum of the range
	 */
	public int getMin()
	{
		return this.min;
	}
	
	/**
	 * sets the minimum of the range
	 * 
	 * @since 0.1
	 * 
	 * @param min the minimum of the range to set
	 */
	public void setMin(int min)
	{
		this.min = min;
	}
	
	/**
	 * gives the maximum of the range
	 * 
	 * @since 0.1
	 * 
	 * @return the maximum of the range
	 */
	public int getMax()
	{
		return this.max;
	}
	
	/**
	 * sets the maximum of the range
	 * 
	 * @since 0.1
	 * 
	 * @param max the maximum of the range to set
	 */
	public void setMax(int max)
	{
		this.max = max;
	}
}