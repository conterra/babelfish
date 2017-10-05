package de.conterra.babelfish.plugin.v10_02;

import de.conterra.babelfish.plugin.v10_11.RestService;

/**
 * defines an {@link Enum} with all possible {@link Capability}s, which could a {@link RestService} handle
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public enum Capability {
	/**
	 * a query request
	 *
	 * @since 0.1.0
	 */
	QUERY("Query"),
	/**
	 * an editing request
	 *
	 * @since 0.1.0
	 */
	EDITING("Editing"),
	/**
	 * a create request
	 *
	 * @since 0.1.0
	 */
	CREATE("Create"),
	/**
	 * an update request
	 *
	 * @since 0.1.0
	 */
	UPDATE("Update"),
	/**
	 * a delete request
	 *
	 * @since 0.1.0
	 */
	DELETE("Delete");
	
	/**
	 * the unique identifier to the {@link Capability}
	 */
	private final String id;
	
	/**
	 * constructur, with given id
	 *
	 * @param id the id of the {@link Capability}
	 * @since 0.4.0
	 */
	Capability(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
