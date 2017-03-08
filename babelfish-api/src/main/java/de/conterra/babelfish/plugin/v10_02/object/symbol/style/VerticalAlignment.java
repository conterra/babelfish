package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a vertical alignment
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public enum VerticalAlignment {
	/**
	 * alignment on the baseline
	 *
	 * @since 0.1.0
	 */
	BASELINE,
	/**
	 * top alignment
	 *
	 * @since 0.1.0
	 */
	TOP,
	/**
	 * alignment in the middle
	 *
	 * @since 0.1.0
	 */
	MIDDLE,
	/**
	 * bottom alignment
	 *
	 * @since 0.1.0
	 */
	BOTTOM;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}