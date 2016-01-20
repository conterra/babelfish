package de.conterra.babelfish.plugin.v10_11;

import de.conterra.babelfish.plugin.InnerVersionWrapper;

/**
 * describes an ArcGIS-REST service on version 10.1 SP1
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.1
 */
@InnerVersionWrapper(ServiceWrapper.class)
public interface RestService
extends de.conterra.babelfish.plugin.v10_02.RestService
{
	/**
	 * gives an additional description to the service description
	 * 
	 * @since 0.1
	 * 
	 * @return an additional description
	 * @see de.conterra.babelfish.plugin.v10_02.RestService#getServiceDescription()
	 */
	public String getDescription();
}