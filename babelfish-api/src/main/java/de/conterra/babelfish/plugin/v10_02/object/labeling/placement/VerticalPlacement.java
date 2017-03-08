package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon;

/**
 * defines the {@link VerticalPlacement} of {@link LabelingPlacement}<br>
 * Not defined for {@link Polygon} labels
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum VerticalPlacement {
	Above,
	Below,
	Center;
	
	@Override
	public String toString() {
		return this.name();
	}
}