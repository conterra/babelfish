package de.conterra.babelfish.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Map;

/**
 * utilities class of additional functions to {@link Servlet}s
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class ServletUtils {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private ServletUtils() {
	}
	
	/**
	 * gives a URL path with the parameters as GET method
	 *
	 * @param path       the base URL path
	 * @param parameters a {@link Map} of all parameters
	 * @return the URL path with all parameters in GET method
	 *
	 * @since 0.1.0
	 */
	public static String getPathWithParameters(String path, Map<? extends String, ? extends String[]> parameters) {
		String result = path;
		
		for (String key : parameters.keySet()) {
			log.debug("Add parameter " + key + " to the URL.");
			for (String value : (String[]) (parameters.get(key)))
				result += "&" + key + "=" + value;
		}
		
		log.debug("Replace first '&' in URL to '?'.");
		result = result.replaceFirst("&", "?");
		
		return result;
	}
	
	/**
	 * gives the {@link URL} of a {@link HttpServletRequest}
	 *
	 * @param request the {@link HttpServletRequest} to get the {@link URL} of
	 * @return the {@link URL} of {@code request}
	 *
	 * @since 0.1.0
	 */
	public static String getUrl(HttpServletRequest request) {
		String baseUrl = request.getScheme() + "://" + request.getServerName();
		
		if (request.getServerPort() != 80 && request.getServerPort() != 443)
			baseUrl += ":" + request.getServerPort();
		
		baseUrl += request.getContextPath();
		
		return baseUrl;
	}
	
	/**
	 * gives the root {@link URL} of the Babelfish
	 *
	 * @param requestUrl  the requested {@link URL}
	 * @param servletPath the path to the {@link HttpServlet}, without wild-cards (e.g. "/plugins" or "/about")
	 * @return the root {@link URL}
	 *
	 * @since 0.1.0
	 */
	public static String getRootUrl(String requestUrl, String servletPath) {
		if (requestUrl.endsWith(servletPath))
			return requestUrl.substring(0, requestUrl.length() - servletPath.length());
		
		return requestUrl;
	}
}