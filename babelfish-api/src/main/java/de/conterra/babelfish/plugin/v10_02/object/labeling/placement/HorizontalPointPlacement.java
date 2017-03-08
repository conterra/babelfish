package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

/**
 * defines the horizontal placements of {@link LabelingPlacement}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum HorizontalPointPlacement {
	Center,
	Left,
	Right;
	
	@Override
	public String toString() {
		return this.name();
	}
}