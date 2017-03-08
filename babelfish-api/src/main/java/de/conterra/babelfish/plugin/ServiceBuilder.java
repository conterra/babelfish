package de.conterra.babelfish.plugin;

import de.conterra.babelfish.interchange.ObjectValue;

import java.util.Map;

/**
 * describes a class to handle any request of a {@link RestService}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface ServiceBuilder {
	/**
	 * gives a builded {@link ObjectValue} of any request to the {@link RestService}
	 *
	 * @param service    the {@link RestService} to handle the request to
	 * @param path       the sub path of the {@link RestService}
	 * @param parameters parameters to control the output
	 * @return the builded {@link ObjectValue} or {@code null} if {@code path} requested a non existent object
	 *
	 * @throws ServiceNotAvailableException if not known, how to handle the kind of {@code service}
	 * @throws WrongRequestException        if not understood the request (like wrong syntax)
	 * @throws BuildingException            if an {@link Exception} occurred even though the request was valid
	 * @since 0.1.0
	 */
	public ObjectValue build(RestService service, String[] path, Map<? extends String, ? extends String> parameters)
			throws ServiceNotAvailableException, WrongRequestException, BuildingException;
}