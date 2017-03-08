package de.conterra.babelfish.plugin.v10_11;

import de.conterra.babelfish.plugin.InnerVersionWrapper;

/**
 * describes an ArcGIS-REST service on version 10.1 SP1
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.1.0
 */
@InnerVersionWrapper(ServiceWrapper.class)
public interface RestService
		extends de.conterra.babelfish.plugin.v10_02.RestService {
	/**
	 * gives an additional description to the service description
	 *
	 * @return an additional description
	 *
	 * @see de.conterra.babelfish.plugin.v10_02.RestService#getServiceDescription()
	 * @since 0.1.0
	 */
	public String getDescription();
}