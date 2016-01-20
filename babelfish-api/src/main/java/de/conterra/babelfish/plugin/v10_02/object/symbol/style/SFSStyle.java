package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a style of Simple Fill Symbols
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public enum SFSStyle
implements SymbolStyle
{
	/**
	 * non-visible symbol
	 * 
	 * @since 0.1
	 */
	Null,
	BackwardDiagonal,
	ForwardDiagonal,
	Cross,
	DiagonalCross,
	Horizontal,
	Vertical,
	Solid;
	
	@Override
	public String toString()
	{
		return "esriSFS" + this.name();
	}
}