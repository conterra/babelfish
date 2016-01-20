package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Image;

/**
 * defines a picture based {@link BaseSymbol}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public abstract class PictureSymbol
extends MovableSymbol
{
	/**
	 * the {@link Image} to show
	 * 
	 * @since 0.1
	 */
	private Image image;
	/**
	 * the width to show the {@link Image}
	 * 
	 * @since 0.1
	 */
	private double width;
	/**
	 * the height to show the {@link Image}
	 * 
	 * @since 0.1
	 */
	private double height;
	
	/**
	 * constructor, with given {@link Image}
	 * 
	 * @since 0.1
	 * 
	 * @param image the {@link Image}
	 */
	public PictureSymbol(Image image)
	{
		this.image = image;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		if (this.width < 0)
			this.width = 0;
		if (this.height < 0)
			this.height = 0;
	}
	
	/**
	 * gives the {@link Image}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Image}
	 */
	public Image getImage()
	{
		return this.image;
	}
	
	/**
	 * sets the {@link Image}
	 * 
	 * @since 0.1
	 * 
	 * @param image the {@link Image} to set
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	/**
	 * gives the width
	 * 
	 * @since 0.1
	 * 
	 * @return the width
	 */
	public double getWidth()
	{
		return this.width;
	}
	
	/**
	 * sets the width
	 * 
	 * @since 0.1
	 * 
	 * @param width the width to set
	 * @throws IllegalArgumentException if <code>width</code> is negative
	 */
	public void setWidth(double width)
	throws IllegalArgumentException
	{
		if (width < 0)
			throw new IllegalArgumentException("The width could not be negative!");
		
		this.width = width;
	}
	
	/**
	 * gives the height
	 * 
	 * @since 0.1
	 * 
	 * @return the height
	 */
	public double getHeight()
	{
		return this.height;
	}
	
	/**
	 * sets the height
	 * 
	 * @since 0.1
	 * 
	 * @param height the height to set
	 * @throws IllegalArgumentException if <code>heigth</code> is negative
	 */
	public void setHeight(double height)
	throws IllegalArgumentException
	{
		if (height < 0)
			throw new IllegalArgumentException("The height could not be negative!");
		
		this.height = height;
	}
}