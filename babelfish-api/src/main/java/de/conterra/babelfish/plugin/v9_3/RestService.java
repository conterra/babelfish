package de.conterra.babelfish.plugin.v9_3;

import de.conterra.babelfish.plugin.InnerVersionWrapper;

/**
 * describes an ArcGIS-REST service on version 9.3
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
@InnerVersionWrapper(ServiceWrapper.class)
public interface RestService
		extends de.conterra.babelfish.plugin.RestService {
	/**
	 * gives a description, which data the service provide
	 *
	 * @return a service description
	 *
	 * @since 0.3.1
	 */
	public String getServiceDescription();
}