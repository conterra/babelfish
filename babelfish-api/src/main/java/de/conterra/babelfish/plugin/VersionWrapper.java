package de.conterra.babelfish.plugin;

import de.conterra.babelfish.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

/**
 * defines a class, which searches for {@link ServiceWrapper} by a {@link RestService}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class VersionWrapper {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(VersionWrapper.class);
	/**
	 * official version number of the current REST API version
	 *
	 * @since 0.1.0
	 */
	public static final Number CURRENT_VERSION = (new de.conterra.babelfish.plugin.v10_11.ServiceWrapper()).getVersion();
	
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private VersionWrapper() {
	}
	
	/**
	 * searches for a {@link ServiceWrapper}
	 *
	 * @param service the {@link RestService} to get the {@link ServiceWrapper} of
	 * @return the {@link ServiceWrapper} to handle the REST API version of {@code service}
	 *
	 * @throws ServiceNotAvailableException if no {@link ServiceWrapper} could found
	 * @since 0.1.0
	 */
	public static ServiceWrapper getServiceWrapper(RestService service)
			throws ServiceNotAvailableException {
		ServiceWrapper serviceWrapper = null;
		
		VersionWrapper.LOGGER.debug("Search for wrapper in every superclass and interface.");
		
		for (Class<?> superType : ClassUtils.getSuperTypes(service.getClass())) {
			for (Annotation anno : superType.getAnnotations()) {
				if (anno instanceof InnerVersionWrapper) {
					try {
						ServiceWrapper newServiceWrapper = ((((InnerVersionWrapper) anno).value()).newInstance());
						
						if (serviceWrapper == null
								|| newServiceWrapper.getVersion().doubleValue() > serviceWrapper.getVersion().doubleValue())
							serviceWrapper = newServiceWrapper;
					} catch (InstantiationException | IllegalAccessException e) {
						VersionWrapper.LOGGER.error("Find wrapper information, but couldn't instantiate it.", e);
					}
				}
			}
		}
		
		if (serviceWrapper == null)
			throw new ServiceNotAvailableException("No valid wrapper found to handle the service: " + service.getClass().getName());
		
		VersionWrapper.LOGGER.debug("Found valid wrapper " + serviceWrapper.getClass().getName() + " (API v" + serviceWrapper.getVersion() + ")");
		
		return serviceWrapper;
	}
}