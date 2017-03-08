package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.*;

/**
 * defines a Picture Fill Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class PictureFillSymbol
		extends PictureSymbol {
	/**
	 * the outline
	 *
	 * @since 0.1.0
	 */
	private SimpleLineSymbol outline;
	/**
	 * the scale of the {@link Image} in x-direction
	 *
	 * @since 0.1.0
	 */
	private double xScale = 0;
	/**
	 * the scale of the {@link Image} in y-direction
	 *
	 * @since 0.1.0
	 */
	private double yScale = 0;
	
	/**
	 * constructor, with given {@link Image}
	 *
	 * @param image the {@link Image}
	 * @since 0.1.0
	 */
	public PictureFillSymbol(Image image) {
		super(image);
	}
	
	@Override
	public String getType() {
		return super.getType() + "PFS";
	}
	
	/**
	 * gives the outline
	 *
	 * @return the outline
	 *
	 * @since 0.1.0
	 */
	public SimpleLineSymbol getOutline() {
		return this.outline;
	}
	
	/**
	 * sets the outline
	 *
	 * @param outline the outline to set
	 * @since 0.1.0
	 */
	public void setOutline(SimpleLineSymbol outline) {
		this.outline = outline;
	}
	
	/**
	 * gives the scale of the {@link Image} in x-direction
	 *
	 * @return the scale in x-direction
	 *
	 * @since 0.1.0
	 */
	public double getxScale() {
		return this.xScale;
	}
	
	/**
	 * sets the scale of the {@link Image} in x-direction
	 *
	 * @param xScale the scale to set
	 * @since 0.1.0
	 */
	public void setxScale(double xScale) {
		this.xScale = xScale;
	}
	
	/**
	 * gives the scale of the {@link Image} in y-direction
	 *
	 * @return the scale of the {@link Image} in y-direction
	 *
	 * @since 0.1.0
	 */
	public double getyScale() {
		return this.yScale;
	}
	
	/**
	 * sets the scale of the {@link Image} in y-direction
	 *
	 * @param yScale the scale to set
	 * @since 0.1.0
	 */
	public void setyScale(double yScale) {
		this.yScale = yScale;
	}
}