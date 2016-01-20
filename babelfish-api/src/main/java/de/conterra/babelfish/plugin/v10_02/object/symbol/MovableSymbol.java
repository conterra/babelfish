package de.conterra.babelfish.plugin.v10_02.object.symbol;

/**
 * defines a {@link BaseSymbol}, which could be moved with an offset and turned
 * by an angle
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public abstract class MovableSymbol
extends BaseSymbol
{
	/**
	 * the angle
	 * 
	 * @since 0.1
	 */
	private double angle = 0;
	/**
	 * the offset in x-direction
	 * 
	 * @since 0.1
	 */
	private int xOffset = 0;
	/**
	 * the offset in y-direction
	 * 
	 * @since 0.1
	 */
	private int yOffset = 0;
	
	/**
	 * gives the angle
	 * 
	 * @since 0.1
	 * 
	 * @return the angle
	 */
	public double getAngle()
	{
		return this.angle;
	}
	
	/**
	 * sets the angle
	 * 
	 * @since 0.1
	 * 
	 * @param angle the angle to set
	 */
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	
	/**
	 * gives the offset in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the offset in x-direction
	 */
	public int getxOffset()
	{
		return this.xOffset;
	}
	
	/**
	 * sets the offset in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @param xOffset the offset to set
	 */
	public void setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
	}
	
	/**
	 * gives the offset in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the offset in y-direction
	 */
	public int getyOffset()
	{
		return this.yOffset;
	}
	
	/**
	 * sets the offset in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @param yOffset the offset to set
	 */
	public void setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
	}
}