package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;

/**
 * defines a field of {@link Feature}s
 * 
 * @version 0.2
 * @author chwe
 * @since 0.2
 */
public interface Field
extends de.conterra.babelfish.plugin.v10_02.feature.Field
{
	/**
	 * If <code>null</code> values ​​are allowed?
	 * 
	 * @since 0.2
	 * 
	 * @return <code>true</code>, if <code>null</code> values are ​​allowed
	 */
	public boolean isNullable();
}