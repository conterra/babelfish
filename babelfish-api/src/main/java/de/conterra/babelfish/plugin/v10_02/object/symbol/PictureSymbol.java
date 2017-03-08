package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.*;

/**
 * defines a picture based {@link BaseSymbol}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class PictureSymbol
		extends MovableSymbol {
	/**
	 * the {@link Image} to show
	 *
	 * @since 0.1.0
	 */
	private Image image;
	/**
	 * the width to show the {@link Image}
	 *
	 * @since 0.1.0
	 */
	private double width;
	/**
	 * the height to show the {@link Image}
	 *
	 * @since 0.1.0
	 */
	private double height;
	
	/**
	 * constructor, with given {@link Image}
	 *
	 * @param image the {@link Image}
	 * @since 0.1.0
	 */
	public PictureSymbol(Image image) {
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
	 * @return the {@link Image}
	 *
	 * @since 0.1.0
	 */
	public Image getImage() {
		return this.image;
	}
	
	/**
	 * sets the {@link Image}
	 *
	 * @param image the {@link Image} to set
	 * @since 0.1.0
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
	 * gives the width
	 *
	 * @return the width
	 *
	 * @since 0.1.0
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * sets the width
	 *
	 * @param width the width to set
	 * @throws IllegalArgumentException if {@code width} is negative
	 * @since 0.1.0
	 */
	public void setWidth(double width)
			throws IllegalArgumentException {
		if (width < 0)
			throw new IllegalArgumentException("The width could not be negative!");
		
		this.width = width;
	}
	
	/**
	 * gives the height
	 *
	 * @return the height
	 *
	 * @since 0.1.0
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * sets the height
	 *
	 * @param height the height to set
	 * @throws IllegalArgumentException if {@code height} is negative
	 * @since 0.1.0
	 */
	public void setHeight(double height)
			throws IllegalArgumentException {
		if (height < 0)
			throw new IllegalArgumentException("The height could not be negative!");
		
		this.height = height;
	}
}