package de.conterra.babelfish.plugin.v10_02;

import de.conterra.babelfish.plugin.v10_11.RestService;

/**
 * defines an {@link Enum} with all possible {@link Capability}s, which could a {@link RestService} handle
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public enum Capability {
	/**
	 * a query request
	 *
	 * @since 0.1.0
	 */
	Query,
	/**
	 * an editing request
	 *
	 * @since 0.1.0
	 */
	Editing,
	/**
	 * a create request
	 *
	 * @since 0.1.0
	 */
	Create,
	/**
	 * an update request
	 *
	 * @since 0.1.0
	 */
	Update,
	/**
	 * a delete request
	 *
	 * @since 0.1.0
	 */
	Delete;
	
	@Override
	public String toString() {
		return this.name();
	}
}