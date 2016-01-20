package de.conterra.babelfish.plugin.v10_02;

import de.conterra.babelfish.plugin.v10_11.RestService;

/**
 * defines an {@link Enum} with all possible {@link Capability}s, which could a
 * {@link RestService} handle
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public enum Capability
{
	/**
	 * a query request
	 * 
	 * @since 0.1
	 */
	Query,
	/**
	 * an editing request
	 * 
	 * @since 0.1
	 */
	Editing,
	/**
	 * a create request
	 * 
	 * @since 0.1
	 */
	Create,
	/**
	 * an update request
	 * 
	 * @since 0.1
	 */
	Update,
	/**
	 * a delete request
	 * 
	 * @since 0.1
	 */
	Delete;
	
	@Override
	public String toString()
	{
		return this.name();
	}
}