package de.conterra.babelfish.plugin;

import java.util.Map;

import de.conterra.babelfish.interchange.ObjectValue;

/**
 * describes a class to handle any request of a {@link RestService}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface ServiceBuilder
{
	/**
	 * gives a builded {@link ObjectValue} of any request to the
	 * {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link RestService} to handle the request to
	 * @param path the sub path of the {@link RestService}
	 * @param parameters parameters to control the output
	 * @return the builded {@link ObjectValue} or <code>null</code> if
	 *         <code>path</code> requested a non existent object
	 * @throws ServiceNotAvailableException if not known, how to handle the kind
	 *         of <code>service</code>
	 * @throws WrongRequestException if not understood the request (like wrong
	 *         syntax)
	 * @throws BuildingException if an {@link Exception} occurred even though
	 *         the request was valid
	 */
	public ObjectValue build(RestService service, String[] path, Map<? extends String, ? extends String> parameters)
	throws ServiceNotAvailableException, WrongRequestException,
	BuildingException;
}