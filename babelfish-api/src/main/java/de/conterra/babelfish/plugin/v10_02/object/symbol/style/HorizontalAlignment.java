package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a horizontal alignment
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public enum HorizontalAlignment {
	/**
	 * left alignment
	 *
	 * @since 0.1.0
	 */
	LEFT,
	/**
	 * centered alignment
	 *
	 * @since 0.1.0
	 */
	CENTER,
	/**
	 * right alignment
	 *
	 * @since 0.1.0
	 */
	RIGHT,
	/**
	 * justified alignment
	 *
	 * @since 0.1.0
	 */
	JUSTIFY;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}