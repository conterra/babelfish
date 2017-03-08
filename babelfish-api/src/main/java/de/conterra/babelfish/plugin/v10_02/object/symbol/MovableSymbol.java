package de.conterra.babelfish.plugin.v10_02.object.symbol;

/**
 * defines a {@link BaseSymbol}, which could be moved with an offset and turned
 * by an angle
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class MovableSymbol
		extends BaseSymbol {
	/**
	 * the angle
	 *
	 * @since 0.1.0
	 */
	private double angle = 0;
	/**
	 * the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	private int xOffset = 0;
	/**
	 * the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	private int yOffset = 0;
	
	/**
	 * gives the angle
	 *
	 * @return the angle
	 *
	 * @since 0.1.0
	 */
	public double getAngle() {
		return this.angle;
	}
	
	/**
	 * sets the angle
	 *
	 * @param angle the angle to set
	 * @since 0.1.0
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * gives the offset in x-direction
	 *
	 * @return the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	public int getxOffset() {
		return this.xOffset;
	}
	
	/**
	 * sets the offset in x-direction
	 *
	 * @param xOffset the offset to set
	 * @since 0.1.0
	 */
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	/**
	 * gives the offset in y-direction
	 *
	 * @return the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	public int getyOffset() {
		return this.yOffset;
	}
	
	/**
	 * sets the offset in y-direction
	 *
	 * @param yOffset the offset to set
	 * @since 0.1.0
	 */
	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}