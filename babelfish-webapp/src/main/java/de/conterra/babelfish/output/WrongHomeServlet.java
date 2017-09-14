package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Servlet;
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
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
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
	private static final long serialVersionUID = 4L;
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		if (!(Initializer.init(this, false))) {
			String msg = "Initialization failed!";
			log.error(msg);
			response.sendError(500, msg);
			return;
		}
		
		Map<? extends String, ? extends String[]> parameterMap = request.getParameterMap();
		
		String path        = request.getPathInfo();
		String faviconPath = FaviconServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		if (path.equalsIgnoreCase(faviconPath)) {
			log.debug("Redirect user to right favicon URL.");
			response.sendRedirect(ServletUtils.getPathWithParameters(faviconPath, parameterMap));
			return;
		}
		
		log.debug("Get redirect address from main servlet.");
		String root = MainServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		root = root.substring(1, root.length() - 1) + "services/";
		String dest = root;
		
		Matcher matcher = Pattern.compile("/").matcher(path);
		matcher.find();
		while (matcher.find()) {
			dest = "../" + dest;
		}
		
		String subPath = path.substring(1);
		
		if (!(root.toLowerCase(Locale.ROOT).contains(subPath.toLowerCase(Locale.ROOT)))) {
			dest += subPath;
			
			log.debug("Find user fault, by part of right root address. (auto-complete)");
		}
		
		log.debug("Redirect user to right root address.");
		
		response.sendRedirect(ServletUtils.getPathWithParameters(dest, parameterMap));
	}
}
