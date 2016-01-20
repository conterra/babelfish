package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.Image;

/**
 * defines a Picture Marker Symbol
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class PictureMarkerSymbol
extends PictureSymbol
{
	/**
	 * constructor, with given {@link Image}
	 * 
	 * @since 0.1
	 * 
	 * @param image the {@link Image}
	 */
	public PictureMarkerSymbol(Image image)
	{
		super(image);
	}
	
	@Override
	public String getType()
	{
		return super.getType() + "PMS";
	}
}