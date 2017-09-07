package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;
import de.conterra.babelfish.util.StringUtils;

/**
 * defines a Simple Renderer
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class SimpleRenderer
		extends RendererObject {
	/**
	 * the {@link SymbolObject} to use
	 *
	 * @since 0.1.0
	 */
	private SymbolObject symbol;
	/**
	 * the label
	 *
	 * @since 0.1.0
	 */
	private String       label;
	/**
	 * a description
	 *
	 * @since 0.1.0
	 */
	private String description = StringUtils.EMPTY;
	
	/**
	 * constructor, with given label
	 *
	 * @param symbol the {@link SymbolObject} to use
	 * @param label  the label
	 * @since 0.1.0
	 */
	public SimpleRenderer(SymbolObject symbol, String label) {
		this.symbol = symbol;
		this.label = label;
	}
	
	@Override
	public String getType() {
		return "simple";
	}
	
	/**
	 * gives the {@link SymbolObject} to use
	 *
	 * @return the {@link SymbolObject} to use
	 *
	 * @since 0.1.0
	 */
	public SymbolObject getSymbol() {
		return this.symbol;
	}
	
	/**
	 * sets the {@link SymbolObject} to use
	 *
	 * @param symbol the {@link SymbolObject} to set
	 * @since 0.1.0
	 */
	public void setSymbol(SymbolObject symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * gives the label
	 *
	 * @return the label
	 *
	 * @since 0.1.0
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * sets the label
	 *
	 * @param label the label to set
	 * @since 0.1.0
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * gives the description
	 *
	 * @return the description
	 *
	 * @since 0.1.0
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * sets a description
	 *
	 * @param description a description to set
	 * @since 0.1.0
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
