package de.conterra.babelfish.plugin.v10_02.object.labeling;

import de.conterra.babelfish.plugin.v10_02.object.labeling.placement.LabelingPlacement;
import de.conterra.babelfish.plugin.v10_02.object.symbol.TextSymbol;
import lombok.Getter;
import lombok.Setter;

/**
 * defines a {@link LabelClass}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public class LabelClass {
	/**
	 * the {@link LabelingPlacement}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private LabelingPlacement placement;
	/**
	 * the expression
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private String            expression;
	/**
	 * uses this {@link LabelClass} coded values?
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private boolean codedValues = false;
	/**
	 * the {@link TextSymbol}
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private TextSymbol symbol;
	/**
	 * the minimum scale to show this label
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private double minScale = 0;
	/**
	 * the maximum scale to show this label
	 *
	 * @since 0.1.0
	 */
	@Getter
	@Setter
	private double maxScale = 0;
	
	/**
	 * constructor, with {@link LabelingPlacement}, expression and {@link TextSymbol}
	 *
	 * @param placement  the {@link LabelingPlacement}
	 * @param expression the expression
	 * @param symbol     the {@link TextSymbol}
	 * @since 0.1.0
	 */
	public LabelClass(LabelingPlacement placement, String expression, TextSymbol symbol) {
		this.placement = placement;
		this.expression = expression;
		this.symbol = symbol;
	}
}
