package de.conterra.babelfish.plugin.v10_02.object.symbol;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * defines a Picture Fill Symbol
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class PictureFillSymbol
		extends PictureSymbol {
	/**
	 * the outline
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private SimpleLineSymbol outline;
	/**
	 * the scale of the {@link Image} in x-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private double xScale = 0;
	/**
	 * the scale of the {@link Image} in y-direction
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
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
}
