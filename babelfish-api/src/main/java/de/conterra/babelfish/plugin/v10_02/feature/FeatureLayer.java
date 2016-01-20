package de.conterra.babelfish.plugin.v10_02.feature;

import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelingInfo;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;

/**
 * describes a feature layer
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <G> the geometry type
 * @param <F> the {@link FeatureObject} type
 */
public interface FeatureLayer<G extends GeometryObject, F extends GeometryFeatureObject<G>>
extends Layer<F>
{
	/**
	 * gives a {@link Set} of all {@link Feature}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Feature}s
	 * @see TimeLayer#getTimeFeatures()
	 */
	@Override
	public Set<? extends Feature<F>> getFeatures();
	
	/**
	 * gives the geometry type of this layer
	 * 
	 * @since 0.1
	 * 
	 * @return the geometry type
	 */
	public Class<G> getGeometryType();
	
	/**
	 * gives the minimum scale
	 * 
	 * @since 0.1
	 * 
	 * @return the minimum scale
	 */
	public int getMinScale();
	
	/**
	 * gives the maximum scale
	 * 
	 * @since 0.1
	 * 
	 * @return the maximum scale
	 */
	public int getMaxScale();
	
	/**
	 * gives the {@link RendererObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link RendererObject}
	 */
	public RendererObject getRenderer();
	
	/**
	 * gives the alpha-value of transparency
	 * 
	 * @since 0.1
	 * 
	 * @return the alpha-value
	 */
	public int getTranparency();
	
	/**
	 * gives the {@link LabelingInfo}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link LabelingInfo}
	 */
	public LabelingInfo getLabelingInfo();
}