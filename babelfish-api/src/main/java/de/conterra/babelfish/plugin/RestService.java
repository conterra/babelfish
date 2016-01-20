package de.conterra.babelfish.plugin;

import java.awt.Image;

/**
 * describes an ArcGIS-REST service
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface RestService
{
	/**
	 * gives the {@link Plugin} of this {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Plugin}
	 */
	public Plugin getPlugin();
	
	/**
	 * gives the unique identifier
	 * 
	 * @since 0.1
	 * 
	 * @return the unique id
	 */
	public String getId();
	
	/**
	 * gives the icon / logo / thumbnail
	 * 
	 * @since 0.1
	 * 
	 * @return the icon or <code>null</code> to use the babelfish project icon
	 *         as default
	 */
	public Image getIcon();
}