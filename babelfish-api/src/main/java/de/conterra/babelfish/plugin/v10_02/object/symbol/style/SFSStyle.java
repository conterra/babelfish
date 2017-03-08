package de.conterra.babelfish.plugin.v10_02.object.symbol.style;

/**
 * defines a style of Simple Fill Symbols
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public enum SFSStyle
		implements SymbolStyle {
	/**
	 * non-visible symbol
	 *
	 * @since 0.1.0
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
	public String toString() {
		return "esriSFS" + this.name();
	}
}