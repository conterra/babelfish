package de.conterra.babelfish.sample.feature;

import de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelingInfo;

/**
 * defines a very simple {@link FeatureLayer}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <G> the geometry type
 * @param <F> the {@link FeatureObject} type
 */
public abstract class SimpleFeatureLayer<G extends GeometryObject, F extends GeometryFeatureObject<G>>
extends SimpleLayer<F>
implements FeatureLayer<G, F>
{
	/**
	 * constructor, with all necessary attributes
	 * 
	 * @since 0.1
	 * 
	 * @param id the unique identifier
	 * @param name the name shown to the user
	 * @param desc the layer description
	 */
	public SimpleFeatureLayer(int id, String name, String desc)
	{
		super(id, name, desc);
	}
	
	@Override
	public int getMinScale()
	{
		return 0;
	}
	
	@Override
	public int getMaxScale()
	{
		return 0;
	}
	
	@Override
	public int getTranparency()
	{
		return 0;
	}
	
	@Override
	public LabelingInfo getLabelingInfo()
	{
		return null;
	}
}