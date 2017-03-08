package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Table} with meta data
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Table<T extends FeatureObject>
		extends de.conterra.babelfish.plugin.v10_02.feature.Table<T>, Layer<T> {
}