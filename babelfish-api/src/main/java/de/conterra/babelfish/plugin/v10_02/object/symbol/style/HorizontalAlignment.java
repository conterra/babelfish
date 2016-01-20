package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a horizontal alignment
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public enum HorizontalAlignment
{
	/**
	 * left alignment
	 * 
	 * @since 0.1
	 */
	LEFT,
	/**
	 * centered alignment
	 * 
	 * @since 0.1
	 */
	CENTER,
	/**
	 * right alignment
	 * 
	 * @since 0.1
	 */
	RIGHT,
	/**
	 * justified alignment
	 * 
	 * @since 0.1
	 */
	JUSTIFY;
	
	@Override
	public String toString()
	{
		return this.name().toLowerCase();
	}
}