package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a vertical alignment
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public enum VerticalAlignment
{
	/**
	 * alignment on the baseline
	 * 
	 * @since 0.1
	 */
	BASELINE,
	/**
	 * top alignment
	 * 
	 * @since 0.1
	 */
	TOP,
	/**
	 * alignment in the middle
	 * 
	 * @since 0.1
	 */
	MIDDLE,
	/**
	 * bottom alignment
	 * 
	 * @since 0.1
	 */
	BOTTOM;
	
	@Override
	public String toString()
	{
		return this.name().toLowerCase();
	}
}