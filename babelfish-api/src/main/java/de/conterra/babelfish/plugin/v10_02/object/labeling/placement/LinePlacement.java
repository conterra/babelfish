package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;

/**
 * defines a {@link LabelingPlacement} of a {@link Polyline}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class LinePlacement
extends LabelingPlacement
{
	/**
	 * the {@link VerticalPlacement}
	 * 
	 * @since 0.1
	 */
	private final VerticalPlacement vPlace;
	/**
	 * the {@link HorizontalLinePlacement}
	 * 
	 * @since 0.1
	 */
	private final HorizontalLinePlacement hPlace;
	
	/**
	 * constructor, with given partial placements
	 * 
	 * @since 0.1
	 * 
	 * @param vPlace the {@link VerticalPlacement}
	 * @param hPlace the {@link HorizontalLinePlacement}
	 */
	public LinePlacement(VerticalPlacement vPlace, HorizontalLinePlacement hPlace)
	{
		this.vPlace = vPlace;
		this.hPlace = hPlace;
	}
	
	@Override
	public String getId()
	{
		return super.getId() + "LinePlacement" + this.vPlace.toString() + this.hPlace.toString();
	}
}