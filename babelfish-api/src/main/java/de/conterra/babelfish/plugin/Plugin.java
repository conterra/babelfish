package de.conterra.babelfish.plugin;

/**
 * describes a {@link Plugin}, which could loaded from a JAR
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface Plugin
{
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * initialize the {@link Plugin} (e.g. registers all {@link RestService}s)
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the {@link Plugin} initialized successfully
	 */
	public boolean init();
	
	/**
	 * Called, when the {@link Plugin} is unload
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the shutdown was successfully finished
	 */
	public boolean shutdown();
}