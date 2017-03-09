package de.conterra.babelfish.plugin.v9_3;

import de.conterra.babelfish.plugin.RestService;
import de.conterra.babelfish.plugin.ServiceBuilder;
import de.conterra.babelfish.plugin.ServiceNotAvailableException;
import de.conterra.babelfish.plugin.v9_3.map.MapService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * defines the {@link de.conterra.babelfish.plugin.ServiceWrapper} of REST API version 9.3
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.3.1
 */
@Slf4j
public class ServiceWrapper
		implements de.conterra.babelfish.plugin.ServiceWrapper {
	/**
	 * standard constructor<br>
	 * <b>Attention</b>: Has to be exist for {@link de.conterra.babelfish.plugin.ServiceWrapper} instances
	 *
	 * @since 0.3.1
	 */
	public ServiceWrapper() {
	}
	
	@Override
	public Number getVersion() {
		return 9.3;
	}
	
	@Override
	public String[] getRestNames(RestService service) {
		ArrayList<String> nameList = new ArrayList<>();
		
		if (service instanceof MapService) {
			nameList.add(MapService.REST_NAME);
		}
		
		return nameList.toArray(new String[nameList.size()]);
	}
	
	@Override
	public ServiceBuilder getBuilder(String restName)
			throws ServiceNotAvailableException {
		switch (restName) {
			case MapService.REST_NAME:
				log.error(MapService.REST_NAME + " currently not implemented.");
			default:
				throw new ServiceNotAvailableException("Unkown service type! No registered handler!");
		}
	}
}