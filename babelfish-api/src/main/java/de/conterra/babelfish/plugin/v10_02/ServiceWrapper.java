package de.conterra.babelfish.plugin.v10_02;

import de.conterra.babelfish.plugin.RestService;
import de.conterra.babelfish.plugin.ServiceBuilder;
import de.conterra.babelfish.plugin.ServiceNotAvailableException;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_02.feature.builder.MasterBuilder;
import de.conterra.babelfish.plugin.v10_02.map.MapService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * defines the {@link de.conterra.babelfish.plugin.ServiceWrapper} of REST API version 10 SP2
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class ServiceWrapper
		implements de.conterra.babelfish.plugin.ServiceWrapper {
	/**
	 * standard constructor<br>
	 * <b>Attention</b>: Has to be exist for {@link de.conterra.babelfish.plugin.ServiceWrapper} instances
	 *
	 * @since 0.1.0
	 */
	public ServiceWrapper() {
	}
	
	@Override
	public Number getVersion() {
		return 10.02;
	}
	
	@Override
	public String[] getRestNames(RestService service) {
		ArrayList<String> nameList = new ArrayList<>();
		
		if (service instanceof FeatureService) {
			nameList.add(FeatureService.REST_NAME);
		}
		if (service instanceof MapService) {
			nameList.add(MapService.REST_NAME);
		}
		
		return nameList.toArray(new String[nameList.size()]);
	}
	
	@Override
	public ServiceBuilder getBuilder(String restName)
	throws ServiceNotAvailableException {
		ServiceBuilder builder;
		
		switch (restName) {
			case FeatureService.REST_NAME:
				builder = new MasterBuilder();
				break;
			case MapService.REST_NAME:
				log.error(MapService.REST_NAME + " currently not implemented.");
			default:
				throw new ServiceNotAvailableException("Unkown service type! No registered handler!");
		}
		
		log.debug("Used builder " + builder.getClass().getName() + " for " + restName + ".");
		return builder;
	}
}
