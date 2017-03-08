package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon;

/**
 * defines a {@link LabelingPlacement} of a {@link Polygon}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class PolygonPlacement
		extends LabelingPlacement {
	@Override
	public String getId() {
		return super.getId() + "PolygonPlacementAlwaysHorizontal";
	}
}