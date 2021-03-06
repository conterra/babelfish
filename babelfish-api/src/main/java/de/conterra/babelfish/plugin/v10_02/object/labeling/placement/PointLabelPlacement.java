package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Point;

/**
 * defines a {@link LabelingPlacement} of a {@link Point}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class PointLabelPlacement
		extends LabelingPlacement {
	/**
	 * the {@link VerticalPlacement}
	 *
	 * @since 0.1.0
	 */
	private final VerticalPlacement vPlace;
	/**
	 * the {@link HorizontalPointPlacement}
	 *
	 * @since 0.1.0
	 */
	private final HorizontalPointPlacement hPlace;
	
	/**
	 * constructor, with given partial placements
	 *
	 * @param vPlace the {@link VerticalPlacement}
	 * @param hPlace the {@link HorizontalPointPlacement}
	 * @since 0.1.0
	 */
	public PointLabelPlacement(VerticalPlacement vPlace, HorizontalPointPlacement hPlace) {
		this.vPlace = vPlace;
		this.hPlace = hPlace;
	}
	
	@Override
	public String getId() {
		return super.getId() + "PointLabelPlacement" + this.vPlace.toString() + this.hPlace.toString();
	}
}