package de.conterra.babelfish.plugin;

/**
 * describes a {@link Plugin}, which could loaded from a JAR
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Plugin {
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * initialize the {@link Plugin} (e.g. registers all {@link RestService}s)
	 *
	 * @return {@code true}, if the {@link Plugin} initialized successfully
	 *
	 * @since 0.1.0
	 */
	public boolean init();
	
	/**
	 * Called, when the {@link Plugin} is unload
	 *
	 * @return {@code true}, if the shutdown was successfully finished
	 *
	 * @since 0.1.0
	 */
	public boolean shutdown();
}