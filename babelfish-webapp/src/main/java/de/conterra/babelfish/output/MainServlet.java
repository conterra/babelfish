package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.interchange.DataValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.*;
import de.conterra.babelfish.util.DataUtils;
import de.conterra.babelfish.util.ServletUtils;
import de.conterra.babelfish.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * {@link Servlet}, which handles all {@link HttpServletRequest}s on the right root path
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
@WebServlet(description = "handles the HTTP request", urlPatterns =
		{
				"/ArcGIS/rest/*",
				"/arcgis/rest/*",
				"/ARCGIS/REST/*"
		})
public class MainServlet
		extends BaseServlet {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 4L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doRequest(request, response);
	}
	
	/**
	 * handles a request. Independent to the method (GET or POST).
	 *
	 * @param request  the request from the client
	 * @param response the response, which should be sent to the client
	 * @throws IOException if any in- or output {@link Exception} occurred
	 * @since 0.1.0
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		if (!Initializer.init(this, true)) {
			String msg = "Initialization failed";
			log.error(msg);
			response.sendError(500, msg);
			return;
		}
		
		log.debug("Prepare input parameters.");
		Map<? extends String, ? extends String[]> parameterMap = request.getParameterMap();
		HashMap<String, String>                   parameters   = new HashMap<>();
		for (String key : parameterMap.keySet()) {
			String parameter = request.getParameter(key);
			String parameterValue;
			
			try {
				JSONObject json = new JSONObject(parameter);
				
				String url        = json.getString("url");
				byte[] remoteData = DataUtils.toByteArray((new URL(url)).openStream());
				
				if (remoteData.length <= 0) {
					throw new IOException("No external file found!");
				}
				
				log.debug("Found external JSON file for parameter " + key + " at " + url);
				
				JSONObject remoteJson = new JSONObject(new String(remoteData));
				for (String remoteKey : JSONObject.getNames(remoteJson)) {
					json.append(remoteKey, remoteJson.get(remoteKey));
				}
				
				log.debug("All values of the remote JSON file added to the parameter value.");
				
				parameterValue = json.toString();
			} catch (JSONException | IOException e) {
				log.debug("No valid remote JSON file found for parameter " + key, e);
				
				parameterValue = parameter;
			}
			
			log.debug("Add prepared parameter " + key + " to the parameter map.");
			
			parameters.put(key, parameterValue);
		}
		
		Format format          = null;
		String formatParameter = request.getParameter("f");
		
		if (formatParameter != null) {
			if ("help".equalsIgnoreCase(formatParameter)) {
				log.info("Redirect to the API help page of ESRI");
				response.sendRedirect(Initializer.HELP_URL);
				return;
			} else if ("pJSON".equalsIgnoreCase(formatParameter)) {
				format = Format.JSON;
			} else {
				try {
					format = Format.valueOf(formatParameter.toUpperCase(Locale.ROOT));
				} catch (IllegalArgumentException e) {
					log.warn("Requested format " + formatParameter + " wasn't found.", e);
				}
			}
		}
		
		if (format == null) {
			format = Format.JSON;
		}
		
		log.debug("Used output format is " + format);
		
		String target = request.getPathInfo();
		
		if (target == null) {
			target = StringUtils.EMPTY;
		} else {
			target = target.substring(1);
		}
		
		ObjectValue rootObject  = null;
		String[]    targetParts = target.split("/");
		
		if (targetParts[0].isEmpty()) {
			log.debug("Redirect to root URL (with WrongHomeServlet).");
			response.sendRedirect(ServletUtils.getPathWithParameters("/", parameterMap));
			return;
		}
		
		if ("services".equalsIgnoreCase(targetParts[0])) {
			try {
				if (targetParts.length <= 2) {
					String plugin;
					
					try {
						log.debug("Request service list.");
						
						if (ServiceContainer.getServices(targetParts[1]).isEmpty()) {
							String msg = "No registered plugin: " + targetParts[1];
							log.error(msg);
							response.sendError(404, msg);
							return;
						}
						
						plugin = targetParts[1];
					} catch (IndexOutOfBoundsException e) {
						log.debug("Request plugin list.");
						
						plugin = null;
					}
					
					rootObject = ServiceListBuilder.build(ServiceContainer.toUrlSaveString(plugin));
				} else {
					RestService service = ServiceContainer.getService(targetParts[1], targetParts[2]);
					
					if (service == null) {
						String msg = "No registered service: " + targetParts[2];
						log.error(msg);
						response.sendError(404, msg);
						return;
					}
					
					if (targetParts.length < 4) {
						String msg = "Missing service type in request!";
						log.error(msg);
						response.sendError(404, msg);
						return;
					}
					
					if (targetParts.length >= 6
					    && "info".equalsIgnoreCase(targetParts[4])
					    && "thumbnail".equalsIgnoreCase(targetParts[5])) {
						Image icon = service.getIcon();
						
						if (icon == null) {
							icon = Initializer.getDefaultIcon();
						}
						
						rootObject = new DataValue(DataUtils.toByteArray(icon));
					} else {
						String[] servicePath = Arrays.copyOfRange(targetParts, 4, targetParts.length);
						
						log.debug("Search for valid wrapper for requested service.");
						ServiceWrapper serviceWrapper = VersionWrapper.getServiceWrapper(service);
						rootObject = serviceWrapper.getBuilder(targetParts[3]).build(service, servicePath, parameters);
						if (rootObject != null && rootObject.getValue("currentVersion") == null) {
							rootObject.addContentNotEmpty("currentVersion", new NumberValue(serviceWrapper.getVersion()));
						}
					}
				}
			} catch (ServiceNotAvailableException | BuildingException e) {
				log.error(e.getMessage(), e);
				response.sendError(500, e.getMessage());
				
				return;
			} catch (WrongRequestException e) {
				log.error(e.getMessage(), e);
				response.sendError(400, e.getMessage());
				return;
			}
		} else if ("info".equalsIgnoreCase(targetParts[0])) {
			if (targetParts.length >= 2 && "thumbnail".equalsIgnoreCase(targetParts[1])) {
				rootObject = new DataValue(DataUtils.toByteArray(Initializer.getDefaultIcon()));
			} else {
				rootObject = new ObjectValue();
				rootObject.addContent("currentVersion", new NumberValue(VersionWrapper.CURRENT_VERSION));
			}
		}
		
		if (rootObject != null) {
			if (rootObject instanceof DataValue) {
				log.debug("Send output as data stream");
				
				ByteOutput.output((DataValue) rootObject, response, targetParts[targetParts.length - 1]);
			} else {
				log.debug("Print output.");
				
				response.setContentType(format.formatter.getContentType());
				response.setCharacterEncoding(format.formatter.getCharacterEncoding());
				format.formatter.write(rootObject, response.getWriter(), request.getParameterMap());
			}
			
			return;
		}
		
		String msg = "Don't know any response-action for the given request! Request: " + request.getPathInfo();
		log.error(msg);
		response.sendError(400, msg);
	}
}
