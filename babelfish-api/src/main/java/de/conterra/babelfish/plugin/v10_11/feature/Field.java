package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;

/**
 * defines a field of {@link Feature}s
 *
 * @author ChrissW-R1
 * @version 0.2.0
 * @since 0.2.0
 */
public interface Field
		extends de.conterra.babelfish.plugin.v10_02.feature.Field {
	/**
	 * If {@code null} values ​​are allowed?
	 *
	 * @return {@code true}, if {@code null} values are allowed
	 *
	 * @since 0.2.0
	 */
	public boolean isNullable();
}