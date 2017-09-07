package de.conterra.babelfish.plugin;

import de.conterra.babelfish.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * defines container, to register {@link RestService}s
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class ServiceContainer {
	/**
	 * a {@link Set} of all registered {@link RestService}s
	 *
	 * @since 0.1.0
	 */
	private static final Set<RestService> services = Collections.newSetFromMap(new ConcurrentHashMap<RestService, Boolean>());
	
	/**
	 * private standard constructor to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private ServiceContainer() {
	}
	
	/**
	 * gives a {@link Set} of all registered {@link RestService}s
	 *
	 * @return a {@link Set} of all registered {@link RestService}s
	 *
	 * @since 0.1.0
	 */
	public static Set<? extends RestService> getServices() {
		return new HashSet<>(ServiceContainer.services);
	}
	
	/**
	 * gives a {@link Set} of all registered {@link RestService}s of a {@link Plugin}
	 *
	 * @param pluginName the name of the {@link Plugin} to get the {@link RestService}s of
	 * @return a {@link Set} of all registered {@link RestService}s of the given {@link Plugin}
	 *
	 * @since 0.1.0
	 */
	public static Set<? extends RestService> getServices(String pluginName) {
		Set<RestService> result = new HashSet<>();
		
		for (RestService service : ServiceContainer.getServices()) {
			if (ServiceContainer.toUrlSaveString(service.getPlugin().getName()).equalsIgnoreCase(ServiceContainer.toUrlSaveString(pluginName))) {
				result.add(service);
			}
		}
		
		return result;
	}
	
	/**
	 * parse an id to prevent special characters in URLs
	 *
	 * @param id the id to parse
	 * @return the parsed id
	 *
	 * @since 0.1.0
	 */
	public static String toUrlSaveString(String id) {
		if (id == null) {
			return StringUtils.EMPTY;
		}
		
		return id.replaceAll("[^\\w-]", StringUtils.NEUTRAL_WORD_DELIMITER);
	}
	
	/**
	 * gives a registered {@link RestService}
	 *
	 * @param pluginName  the name of the {@link RestService} {@link Plugin}
	 * @param serviceName the unique id of the {@link RestService}<br>
	 *                    (<b>Attention</b>: The id will be parsed, to a valid form, if it contains non valid characters. See {@link ServiceContainer#toUrlSaveString(String)} for more information.)
	 * @return the {@link RestService} of {@code id} or {@code null}, if no {@link RestService} with this parameters was stored
	 *
	 * @since 0.1.0
	 */
	public static RestService getService(String pluginName, String serviceName) {
		for (RestService service : ServiceContainer.getServices(pluginName)) {
			if (ServiceContainer.toUrlSaveString(service.getId()).equalsIgnoreCase(ServiceContainer.toUrlSaveString(serviceName))) {
				return service;
			}
		}
		
		return null;
	}
	
	/**
	 * registers a {@link RestService}
	 *
	 * @param service the {@link RestService} to register
	 * @return {@code true} if the {@link RestService} was successfully registered
	 *
	 * @throws NullPointerException     if the given {@link RestService} is {@code null}
	 * @throws IllegalArgumentException if the {@link Plugin}, {@link Plugin} name or the id of the given {@link RestService} is {@code null} or empty
	 * @since 0.1.0
	 */
	public static boolean registerService(RestService service)
	throws NullPointerException, IllegalArgumentException {
		if (service == null) {
			throw new NullPointerException("All parameters are need! No one must be null!");
		}
		
		String serviceId = service.getId();
		if (serviceId == null || serviceId.isEmpty()) {
			throw new IllegalArgumentException("The service id couldn't be empty!");
		}
		
		Plugin plugin = service.getPlugin();
		if (plugin == null) {
			throw new IllegalArgumentException("The plugin couldn't be null");
		}
		String pluginName = plugin.getName();
		if (pluginName == null || pluginName.isEmpty()) {
			throw new IllegalArgumentException("The plugin name couldn't be empty");
		}
		
		if (ServiceContainer.getService(pluginName, serviceId) != null) {
			log.warn("There is already a service with id " + serviceId + " registered for the plugin " + pluginName + "!");
			
			return false;
		}
		
		log.debug("Register a new service on plugin " + ServiceContainer.toUrlSaveString(pluginName) + " with id " + ServiceContainer.toUrlSaveString(serviceId));
		
		return ServiceContainer.services.add(service);
	}
	
	/**
	 * unregisters a {@link RestService}
	 *
	 * @param service the {@link RestService}, which should be unregister
	 * @return {@code true}, if the {@link RestService} was successfully unregistered
	 *
	 * @since 0.1.0
	 */
	public static boolean unregisterService(RestService service) {
		ServiceContainer.services.remove(service);
		
		return true;
	}
}
