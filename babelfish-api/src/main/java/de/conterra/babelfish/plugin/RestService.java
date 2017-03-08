package de.conterra.babelfish.plugin;

import java.awt.*;

/**
 * describes an ArcGIS-REST service
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface RestService {
	/**
	 * gives the {@link Plugin} of this {@link RestService}
	 *
	 * @return the {@link Plugin}
	 *
	 * @since 0.1.0
	 */
	public Plugin getPlugin();
	
	/**
	 * gives the unique identifier
	 *
	 * @return the unique id
	 *
	 * @since 0.1.0
	 */
	public String getId();
	
	/**
	 * gives the icon / logo / thumbnail
	 *
	 * @return the icon or {@code null} to use the Babelfish project icon as default
	 *
	 * @since 0.1.0
	 */
	public Image getIcon();
}