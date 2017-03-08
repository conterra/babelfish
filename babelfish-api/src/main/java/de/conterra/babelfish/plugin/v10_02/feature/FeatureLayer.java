package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelingInfo;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;

import java.util.Set;

/**
 * describes a feature layer
 *
 * @param <G> the geometry type
 * @param <F> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface FeatureLayer<G extends GeometryObject, F extends GeometryFeatureObject<G>>
		extends Layer<F> {
	/**
	 * gives a {@link Set} of all {@link Feature}s
	 *
	 * @return a {@link Set} of all {@link Feature}s
	 *
	 * @see TimeLayer#getTimeFeatures()
	 * @since 0.1.0
	 */
	@Override
	public Set<? extends Feature<F>> getFeatures();
	
	/**
	 * gives the geometry type of this layer
	 *
	 * @return the geometry type
	 *
	 * @since 0.1.0
	 */
	public Class<G> getGeometryType();
	
	/**
	 * gives the minimum scale
	 *
	 * @return the minimum scale
	 *
	 * @since 0.1.0
	 */
	public int getMinScale();
	
	/**
	 * gives the maximum scale
	 *
	 * @return the maximum scale
	 *
	 * @since 0.1.0
	 */
	public int getMaxScale();
	
	/**
	 * gives the {@link RendererObject}
	 *
	 * @return the {@link RendererObject}
	 *
	 * @since 0.1.0
	 */
	public RendererObject getRenderer();
	
	/**
	 * gives the alpha-value of transparency
	 *
	 * @return the alpha-value
	 *
	 * @since 0.1.0
	 */
	public int getTranparency();
	
	/**
	 * gives the {@link LabelingInfo}
	 *
	 * @return the {@link LabelingInfo}
	 *
	 * @since 0.1.0
	 */
	public LabelingInfo getLabelingInfo();
}