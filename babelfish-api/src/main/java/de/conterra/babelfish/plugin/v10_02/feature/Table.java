package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Table}, with meta data
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 */
public interface Table<T extends FeatureObject>
extends Layer<T>
{
}