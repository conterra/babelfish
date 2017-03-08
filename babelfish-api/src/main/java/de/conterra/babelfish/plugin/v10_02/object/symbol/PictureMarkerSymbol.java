package de.conterra.babelfish.plugin.v10_02.object.symbol;

import java.awt.*;

/**
 * defines a Picture Marker Symbol
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class PictureMarkerSymbol
		extends PictureSymbol {
	/**
	 * constructor, with given {@link Image}
	 *
	 * @param image the {@link Image}
	 * @since 0.1.0
	 */
	public PictureMarkerSymbol(Image image) {
		super(image);
	}
	
	@Override
	public String getType() {
		return super.getType() + "PMS";
	}
}