package de.conterra.babelfish.output;

import de.conterra.babelfish.interchange.DataValue;
import de.conterra.babelfish.util.DataUtils;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@link Servlet} to provide an image of the conterra logo
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.2.3
 */
@Slf4j
@WebServlet(
		description = "Servlet to provide an image of the conterra logo",
		urlPatterns =
				{
						"/res/*",
						"/resources/*"
				})
public class ResourcesServlet
		extends HttpServlet {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.2.3
	 */
	private static final long serialVersionUID = 3L;
	
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
	 * handles the {@link HttpServletRequest}, same on GET and POST
	 *
	 * @param request  the {@link HttpServletRequest}
	 * @param response the {@link HttpServletResponse}
	 * @throws IOException if any in- or output {@link Exception} occurred
	 * @see ResourcesServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see ResourcesServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * @since 0.2.3
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pathInfo = request.getPathInfo().substring(1);
		String[] target = pathInfo.split("/");
		
		String path = null;
		String filename = null;
		
		log.debug("Target resource is " + pathInfo + ".");
		
		if (target[0].equalsIgnoreCase("conterraLogo")) {
			log.debug("The conterra logo is requested.");
			
			path = "conterra_Logo_RGB.PNG";
			filename = "conterraLogo.png";
		} else if (target[0].equalsIgnoreCase("logoLettering")) {
			log.debug("The Babelfish logo with lettering is requested.");
			
			path = "logo_lettering.png";
			filename = "logoLettering.png";
		}
		
		if (path != null || filename != null)
			ByteOutput.output(new DataValue(DataUtils.toByteArray(ImageIO.read(this.getServletContext().getResource("/WEB-INF/classes/" + path)))), response, filename);
	}
}