package de.conterra.babelfish.plugin.v10_02;

import de.conterra.babelfish.plugin.InnerVersionWrapper;

/**
 * describes an ArcGIS-REST service on version 10 SP2
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.1.0
 */
@InnerVersionWrapper(ServiceWrapper.class)
public interface RestService
		extends de.conterra.babelfish.plugin.v9_3.RestService {
}