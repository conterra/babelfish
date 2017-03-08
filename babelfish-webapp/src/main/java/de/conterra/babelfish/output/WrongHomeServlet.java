package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * defines a {@link Servlet}, which redirects to the right root path
 *
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.1.0
 */
@WebServlet(description = "redirects requests with wrong home path", urlPatterns =
		{
				"/*"
		})
public class WrongHomeServlet
		extends BaseServlet {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = -601368017332082983L;
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(WrongHomeServlet.class);
	
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
	 * @throws IOException if an input or output exception occurs
	 * @see WrongHomeServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see WrongHomeServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * @since 0.1.0
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (!(Initializer.init(this, false))) {
			String msg = "Initialization failed!";
			MainServlet.LOGGER.error(msg);
			response.sendError(500, msg);
			return;
		}
		
		Map<? extends String, ? extends String[]> parameterMap = request.getParameterMap();
		
		String path = request.getPathInfo();
		String faviconPath = FaviconServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		if (path.equalsIgnoreCase(faviconPath)) {
			WrongHomeServlet.LOGGER.debug("Redirect user to right favicon URL.");
			response.sendRedirect(ServletUtils.getPathWithParameters(faviconPath, parameterMap));
			return;
		}
		
		WrongHomeServlet.LOGGER.debug("Get redirect address from main servlet.");
		String root = MainServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		root = root.substring(1, root.length() - 1) + "services/";
		String dest = root;
		
		Matcher matcher = Pattern.compile("/").matcher(path);
		matcher.find();
		while (matcher.find())
			dest = "../" + dest;
		
		String subPath = path.substring(1);
		
		if (!(root.toLowerCase(Locale.ROOT).contains(subPath.toLowerCase(Locale.ROOT)))) {
			dest += subPath;
			
			WrongHomeServlet.LOGGER.debug("Find user fault, by part of right root address. (auto-complete)");
		}
		
		WrongHomeServlet.LOGGER.debug("Redirect user to right root address.");
		
		response.sendRedirect(ServletUtils.getPathWithParameters(dest, parameterMap));
	}
}