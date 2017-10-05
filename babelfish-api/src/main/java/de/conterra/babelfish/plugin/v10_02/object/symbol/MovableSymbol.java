package de.conterra.babelfish.plugin.v10_02.object.symbol;

import lombok.Getter;
import lombok.Setter;

/**
 * defines a {@link BaseSymbol}, which could be moved with an offset and turned
 * by an angle
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public abstract class MovableSymbol
		extends BaseSymbol {
	/**
	 * the angle
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private double angle   = 0;
	/**
	 * the offset in x-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private int    xOffset = 0;
	/**
	 * the offset in y-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private int    yOffset = 0;
}
