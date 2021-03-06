package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;

/**
 * defines a horizontal placements of {@link Polyline} based {@link LabelingPlacement}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum HorizontalLinePlacement {
	After,
	Along,
	Before,
	Start,
	End;
	
	@Override
	public String toString() {
		return this.name();
	}
}