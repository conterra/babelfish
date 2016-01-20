package de.conterra.babelfish.plugin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines container, to register {@link RestService}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ServiceContainer
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceContainer.class);
	/**
	 * a {@link Set} of all registered {@link RestService}s
	 * 
	 * @since 0.1
	 */
	private static final Set<RestService> services = Collections.newSetFromMap(new ConcurrentHashMap<RestService, Boolean>());
	
	/**
	 * private standard constructor to prevent initialization
	 * 
	 * @since 0.1
	 */
	private ServiceContainer()
	{
	}
	
	/**
	 * gives a {@link Set} of all registered {@link RestService}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all registered {@link RestService}s
	 */
	public static Set<? extends RestService> getServices()
	{
		return new HashSet<>(ServiceContainer.services);
	}
	
	/**
	 * gives a {@link Set} of all registered {@link RestService}s of a
	 * {@link Plugin}
	 * 
	 * @since 0.1
	 * 
	 * @param pluginName the name of the {@link Plugin} to get the
	 *        {@link RestService}s of
	 * @return a {@link Set} of all registered {@link RestService}s of the given
	 *         {@link Plugin}
	 */
	public static Set<? extends RestService> getServices(String pluginName)
	{
		Set<RestService> result = new HashSet<>();
		
		for (RestService service : ServiceContainer.getServices())
		{
			if (ServiceContainer.toUrlSaveString(service.getPlugin().getName()).equalsIgnoreCase(ServiceContainer.toUrlSaveString(pluginName)))
				result.add(service);
		}
		
		return result;
	}
	
	/**
	 * parse an id to prevent special characters in URLs
	 * 
	 * @since 0.1
	 * 
	 * @param id the id to parse
	 * @return the parsed id
	 */
	public static String toUrlSaveString(String id)
	{
		if (id == null)
			return "";
		
		return id.replaceAll("[^a-zA-Z0-9_-]", "_");
	}
	
	/**
	 * gives a registered {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @param pluginName the name of the {@link RestService} {@link Plugin}
	 * @param serviceName the unique id of the {@link RestService}<br>
	 *        (<b>Attention</b>: The id will be parsed, to a valid form, if it
	 *        contains non valid characters. See
	 *        {@link ServiceContainer#toUrlSaveString(String)} for more
	 *        information.)
	 * @return the {@link RestService} of <code>id</code> or <code>null</code>,
	 *         if no {@link RestService} with this parameters was stored
	 */
	public static RestService getService(String pluginName, String serviceName)
	{
		for (RestService service : ServiceContainer.getServices(pluginName))
		{
			if (ServiceContainer.toUrlSaveString(service.getId()).equalsIgnoreCase(ServiceContainer.toUrlSaveString(serviceName)))
				return service;
		}
		
		return null;
	}
	
	/**
	 * registers a {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link RestService} to register
	 * @return <code>true</code> if the {@link RestService} was successfully
	 *         registered
	 * @throws NullPointerException if the given {@link RestService} is
	 *         <code>null</code>
	 * @throws IllegalArgumentException if the {@link Plugin}, {@link Plugin}
	 *         name or the id of the given {@link RestService} is
	 *         <code>null</code> or empty
	 */
	public static boolean registerService(RestService service)
	throws NullPointerException, IllegalArgumentException
	{
		if (service == null)
			throw new NullPointerException("All parameters are need! No one must be null!");
		
		String serviceId = service.getId();
		if (serviceId == null || serviceId.isEmpty())
			throw new IllegalArgumentException("The service id couldn't be empty!");
		
		Plugin plugin = service.getPlugin();
		if (plugin == null)
			throw new IllegalArgumentException("The plugin couldn't be null");
		String pluginName = plugin.getName();
		if (pluginName == null || pluginName.isEmpty())
			throw new IllegalArgumentException("The plugin name couldn't be empty");
		
		if (ServiceContainer.getService(pluginName, serviceId) != null)
		{
			ServiceContainer.LOGGER.warn("There is already a service with id " + serviceId + " registered for the plugin " + pluginName + "!");
			
			return false;
		}
		
		ServiceContainer.LOGGER.debug("Register a new service on plugin " + ServiceContainer.toUrlSaveString(pluginName) + " with id " + ServiceContainer.toUrlSaveString(serviceId));
		
		return ServiceContainer.services.add(service);
	}
	
	/**
	 * unregisters a {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link RestService}, which should be unregister
	 * @return <code>true</code>, if the {@link RestService} was successfully
	 *         unregistered
	 */
	public static boolean unregisterService(RestService service)
	{
		ServiceContainer.services.remove(service);
		
		return true;
	}
}