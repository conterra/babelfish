package de.conterra.babelfish.plugin.v9_3;

import de.conterra.babelfish.plugin.InnerVersionWrapper;

/**
 * describes an ArcGIS-REST service on version 9.3
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
@InnerVersionWrapper(ServiceWrapper.class)
public interface RestService
extends de.conterra.babelfish.plugin.RestService
{
	/**
	 * gives a description, which data the service provide
	 * 
	 * @since 0.3.1
	 * 
	 * @return a service description
	 */
	public String getServiceDescription();
}