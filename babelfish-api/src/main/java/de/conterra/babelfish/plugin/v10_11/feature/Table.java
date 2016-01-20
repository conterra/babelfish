package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Table} with meta data
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Table<T extends FeatureObject>
extends de.conterra.babelfish.plugin.v10_02.feature.Table<T>, Layer<T>
{
}