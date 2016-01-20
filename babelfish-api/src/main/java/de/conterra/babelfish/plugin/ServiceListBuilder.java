package de.conterra.babelfish.plugin;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;

/**
 * defines a class to build the overview list over all registered
 * {@link RestService}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ServiceListBuilder
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceListBuilder.class);
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private ServiceListBuilder()
	{
	}
	
	/**
	 * builds the overview list over all registered {@link RestService}s
	 * 
	 * @since 0.1
	 * 
	 * @param pluginName the {@link Plugin} name, which is used to create a
	 *        folder or <code>null</code>, if the overview of all {@link Plugin}
	 *        s should be created
	 * @return an {@link ObjectValue}, which contains the {@link RestService}
	 *         overview list
	 */
	public static ObjectValue build(String pluginName)
	{
		ObjectValue result = new ObjectValue();
		
		result.addContentNotEmpty("currentVersion", new NumberValue(VersionWrapper.CURRENT_VERSION));
		ArrayValue folders = new ArrayValue();
		ArrayValue services = new ArrayValue();
		
		Set<String> folderNames = new HashSet<>();
		Set<? extends RestService> serviceList = ServiceContainer.getServices();
		for (RestService service : serviceList)
		{
			if (pluginName == null || pluginName.isEmpty())
			{
				String folderName = ServiceContainer.toUrlSaveString(service.getPlugin().getName());
				String folderNameLower = folderName.toLowerCase();
				
				if ( ! (folderNames.contains(folderNameLower)))
				{
					folders.addValue(new StringValue(folderName));
					
					folderNames.add(folderNameLower);
				}
			}
			else if (ServiceContainer.toUrlSaveString(service.getPlugin().getName()).equalsIgnoreCase(ServiceContainer.toUrlSaveString(pluginName)))
			{
				String serviceId = ServiceContainer.toUrlSaveString(service.getId());
				
				try
				{
					for (String type : VersionWrapper.getServiceWrapper(service).getRestNames(service))
					{
						ObjectValue serviceValue = new ObjectValue();
						serviceValue.addContent("name", new StringValue(pluginName + "/" + serviceId));
						serviceValue.addContent("type", new StringValue(type));
						services.addValue(serviceValue);
					}
				}
				catch (ServiceNotAvailableException e)
				{
					ServiceListBuilder.LOGGER.error("Couldn't add service " + serviceId + " to the list, because no valid wrapper found!", e);
				}
			}
		}
		
		result.addContent("folders", folders);
		result.addContent("services", services);
		
		return result;
	}
}