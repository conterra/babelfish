package de.conterra.babelfish.output;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.util.ServletUtils;

/**
 * A {@link BaseServlet}, which provides an about site
 * 
 * @version 0.2.3
 * @author chwe
 * @since 0.1
 */
@WebServlet(description = "A Servlet, which provides an about site", urlPatterns =
{
		"/about",
		"/about/*"
})
public class AboutServlet
extends BaseServlet
{
	/**
	 * generated serial unique version identifier
	 * 
	 * @since 0.1
	 */
	private static final long serialVersionUID = -9045519926848091839L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		this.doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		this.doRequest(request, response);
	}
	
	/**
	 * handles the {@link HttpServletRequest}, same on GET and POST
	 * 
	 * @since 0.1
	 * 
	 * @param request the {@link HttpServletRequest}
	 * @param response the {@link HttpServletResponse}
	 * @throws IOException if an input or output exception occurs
	 * @see AboutServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see AboutServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
	throws IOException
	{
		Initializer.init(this, false);
		
		PrintWriter writer = response.getWriter();
		
		String rootPath = ServletUtils.getRootUrl(ServletUtils.getUrl(request), AboutServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
		String title = "About the tRESTle&nbsp;Babelfish";
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html><head>");
		writer.println("<base href=\"" + rootPath + "\" />");
		writer.println("<link rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" href=\"" + rootPath + "/favicon.ico\" />");
		writer.println("<link rel=\"icon\" type=\"image/vnd.microsoft.icon\" href=\"" + rootPath + "/favicon.ico\" />");
		writer.println("<title>" + title + "</title>");
		writer.println("</head><body style=\"background:url(" + rootPath + "/ArcGIS/rest/info/thumbnail) no-repeat; background-position:center center; background-attachment:fixed;\">");
		writer.println("<div style=\"position: fixed; width: 100%; height: 100%; background-color: #ffffff; opacity: 0.9; filter: alpha(opacity=90); /* For IE8 and earlier */\">");
		writer.println(Header.getHeader(rootPath));
		writer.println("<h1 align=\"center\">" + title + "</h1>");
		writer.println("<p align=\"justify\">The Babelfish is a plugin-based utility to provide any kind of data as an <a href=\"" + Initializer.HELP_URL + "\" target=\"_blank\">ArcGIS Server REST API</a>.</p>");
		writer.println("<p>Developed by:<br />");
		writer.println("<div style=\"padding-left: 1em;\">con terra - Gesellschaft f&uuml;r Angewandte Informationstechnologie mbH<br />");
		writer.println("Martin-Luther-King-Weg 24<br />");
		writer.println("D-48155 M&uuml;nster<br />");
		writer.println("Germany<br />");
		writer.println("<a href=\"http://conterra.de/\" target=\"_blank\">conterra.de</a></div></p>");
		writer.println("</div></body></html>");
	}
}