package de.conterra.babelfish.plugin;

import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conterra.babelfish.util.ClassUtils;

/**
 * defines a class, which searches for {@link ServiceWrapper} by a
 * {@link RestService}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class VersionWrapper
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(VersionWrapper.class);
	/**
	 * official version number of the current REST API version
	 * 
	 * @since 0.1
	 */
	public static final Number CURRENT_VERSION = (new de.conterra.babelfish.plugin.v10_11.ServiceWrapper()).getVersion();
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private VersionWrapper()
	{
	}
	
	/**
	 * searches for a {@link ServiceWrapper}
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link RestService} to get the {@link ServiceWrapper}
	 *        of
	 * @return the {@link ServiceWrapper} to handle the REST API version of
	 *         <code>service</code>
	 * @throws ServiceNotAvailableException if no {@link ServiceWrapper} could
	 *         found
	 */
	public static ServiceWrapper getServiceWrapper(RestService service)
	throws ServiceNotAvailableException
	{
		ServiceWrapper serviceWrapper = null;
		
		VersionWrapper.LOGGER.debug("Search for wrapper in every superclass and interface.");
		
		for (Class<?> superType : ClassUtils.getSuperTypes(service.getClass()))
		{
			for (Annotation anno : superType.getAnnotations())
			{
				if (anno instanceof InnerVersionWrapper)
				{
					try
					{
						ServiceWrapper newServiceWrapper = ( ( ((InnerVersionWrapper)anno).value()).newInstance());
						
						if (serviceWrapper == null
						|| newServiceWrapper.getVersion().doubleValue() > serviceWrapper.getVersion().doubleValue())
							serviceWrapper = newServiceWrapper;
					}
					catch (InstantiationException | IllegalAccessException e)
					{
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