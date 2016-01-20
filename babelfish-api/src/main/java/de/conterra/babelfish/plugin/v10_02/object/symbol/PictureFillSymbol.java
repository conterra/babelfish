package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Image;

/**
 * defines a Picture Fill Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class PictureFillSymbol
extends PictureSymbol
{
	/**
	 * the outline
	 * 
	 * @since 0.1
	 */
	private SimpleLineSymbol outline;
	/**
	 * the scale of the {@link Image} in x-direction
	 * 
	 * @since 0.1
	 */
	private double xScale = 0;
	/**
	 * the scale of the {@link Image} in y-direction
	 * 
	 * @since 0.1
	 */
	private double yScale = 0;
	
	/**
	 * constructor, with given {@link Image}
	 * 
	 * @since 0.1
	 * 
	 * @param image the {@link Image}
	 */
	public PictureFillSymbol(Image image)
	{
		super(image);
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "PFS";
	}
	
	/**
	 * gives the outline
	 * 
	 * @since 0.1
	 * 
	 * @return the outline
	 */
	public SimpleLineSymbol getOutline()
	{
		return this.outline;
	}
	
	/**
	 * sets the outline
	 * 
	 * @since 0.1
	 * 
	 * @param outline the outline to set
	 */
	public void setOutline(SimpleLineSymbol outline)
	{
		this.outline = outline;
	}
	
	/**
	 * gives the scale of the {@link Image} in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the scale in x-direction
	 */
	public double getxScale()
	{
		return this.xScale;
	}
	
	/**
	 * sets the scale of the {@link Image} in x-direction
	 * 
	 * @since 0.1
	 * 
	 * @param xScale the scale to set
	 */
	public void setxScale(double xScale)
	{
		this.xScale = xScale;
	}
	
	/**
	 * gives the scale of the {@link Image} in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @return the scale of the {@link Image} in y-direction
	 */
	public double getyScale()
	{
		return this.yScale;
	}
	
	/**
	 * sets the scale of the {@link Image} in y-direction
	 * 
	 * @since 0.1
	 * 
	 * @param yScale the scale to set
	 */
	public void setyScale(double yScale)
	{
		this.yScale = yScale;
	}
}