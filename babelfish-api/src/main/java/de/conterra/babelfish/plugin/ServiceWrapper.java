package de.conterra.babelfish.plugin;

import java.lang.annotation.Annotation;

/**
 * describes a wrapper, which gives information about a special REST API version<br>
 * <b>Attention</b>: An implementation of this ServiceWrapper has to contain a
 * standard constructor without arguments!<br>
 * To register a new REST API version, create a new {@link ServiceWrapper} and
 * set the {@link Annotation} {@link InnerVersionWrapper} with a class of
 * {@link ServiceBuilder}, which could handle the services on this REST API
 * version, to it.
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface ServiceWrapper
{
	/**
	 * the official version number<br>
	 * (like <code>10.02</code> for 10 SP2 or <code>10.11</code> for 10.1 SP1)
	 * 
	 * @since 0.1
	 * 
	 * @return the official version number
	 */
	public Number getVersion();
	
	/**
	 * gives an array with all REST API names of a {@link RestService}<br>
	 * (like <code>FeatureServer</code> for Feature Services or
	 * <code>NAServer</code> for Network Service)
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link RestService} to get the name of
	 * @return an array with all REST API names of <code>service</code>
	 */
	public String[] getRestNames(RestService service);
	
	/**
	 * gives a {@link ServiceBuilder}, which could handle a request to a
	 * {@link RestService}
	 * 
	 * @since 0.1
	 * 
	 * @param restName the REST API name, which specify the {@link RestService}
	 *        type
	 * @return a {@link ServiceBuilder}, which could handle requests to
	 *         {@link RestService} of type <code>restName</code>
	 * @throws ServiceNotAvailableException if no {@link ServiceBuilder} could
	 *         found for <code>restName</code>
	 */
	public ServiceBuilder getBuilder(String restName)
	throws ServiceNotAvailableException;
}