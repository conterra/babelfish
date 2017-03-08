package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;

/**
 * defines a {@link LabelingPlacement} of a {@link Polyline}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class LinePlacement
		extends LabelingPlacement {
	/**
	 * the {@link VerticalPlacement}
	 *
	 * @since 0.1.0
	 */
	private final VerticalPlacement vPlace;
	/**
	 * the {@link HorizontalLinePlacement}
	 *
	 * @since 0.1.0
	 */
	private final HorizontalLinePlacement hPlace;
	
	/**
	 * constructor, with given partial placements
	 *
	 * @param vPlace the {@link VerticalPlacement}
	 * @param hPlace the {@link HorizontalLinePlacement}
	 * @since 0.1.0
	 */
	public LinePlacement(VerticalPlacement vPlace, HorizontalLinePlacement hPlace) {
		this.vPlace = vPlace;
		this.hPlace = hPlace;
	}
	
	@Override
	public String getId() {
		return super.getId() + "LinePlacement" + this.vPlace.toString() + this.hPlace.toString();
	}
}