package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.config.Config;
import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.PluginAdapter;
import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Set;

/**
 * A {@link HttpServlet} to upload and delete {@link Plugin}s
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
@WebServlet(description = "A GUI to upload and delete plugins", urlPatterns =
		{
				"/plugins",
				"/plugins/*"
		})
@MultipartConfig
public class PluginManagerServlet
		extends BaseServlet {
	/**
	 * generated serial unique version identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 4L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!(this.isManagerEnabled(response))) {
			return;
		}
		
		PrintWriter writer = response.getWriter();
		
		this.writeHeader(writer, ServletUtils.getUrl(request));
		
		this.doRequest(request, response);
		
		this.writeFooter(writer);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!(this.isManagerEnabled(response))) {
			return;
		}
		
		PrintWriter writer = response.getWriter();
		
		this.writeHeader(writer, ServletUtils.getUrl(request));
		
		String type = request.getParameter("type");
		
		log.debug("Request type: " + type);
		
		if (type != null) {
			if (type.equalsIgnoreCase("upload")) {
				Part filePart = request.getPart("file");
				
				try {
					String name = PluginManagerServlet.getFilename(filePart);
					
					if (name != null && (!(name.isEmpty()))) {
						InputStream is = filePart.getInputStream();
						File outFile = new File((new URL(PluginAdapter.getPluginsFolder().toString() + "/" + name).toURI()));
						FileOutputStream os = new FileOutputStream(outFile);
						
						IOUtils.copy(is, os);
						
						is.close();
						os.close();
						
						log.debug("A file with name " + name + " was uploaded.");
						
						try {
							PluginAdapter.loadPlugin(outFile);
							
							log.debug("The file " + name + " is a valid plugin file.");
						} catch (IOException e) {
							log.warn("A non valid plugin file " + name + " was uploaded!", e);
							
							try {
								Files.delete(outFile.toPath());
								log.debug("Successfully removed file: " + name);
							} catch (IOException e2) {
								log.error("An error occurred on deleting file: " + name, e2);
							}
						}
					} else {
						log.debug("Empty file upload.");
					}
					
					this.reload(response);
					return;
				} catch (IllegalArgumentException | MalformedURLException | URISyntaxException e) {
					log.error("File couldn't uploaded!", e);
				}
			} else if (type.equalsIgnoreCase("remove")) {
				Plugin plugin = PluginAdapter.getPlugin(request.getParameter("plugin"));
				String pluginName = plugin.getName();
				
				log.debug("Try to remove plugin " + pluginName + ".");
				
				String path = PluginAdapter.getPluginWrapper(plugin).getFile().getName();
				
				if (PluginAdapter.unloadPlugin(plugin)) {
					log.debug("Plugin " + pluginName + " successfully unloaded.");
					
					try {
						Files.delete((new File(path)).toPath());
						
						log.debug("Successfully removed file " + path + " of plugin " + pluginName);
						
						try {
							FileUtils.deleteDirectory(new File(PluginAdapter.getPluginFolder(plugin).toURI()));
							
							log.debug("Successfully removed plugin folder of plugin " + pluginName + ".");
						} catch (NullPointerException | URISyntaxException e) {
							log.error("An error occurred on deleting plugin folder of plugin " + pluginName + "!", e);
						}
						
						this.reload(response);
						return;
					} catch (IOException e) {
						log.error("An error occurred on deleting file " + path + " of plugin " + pluginName + "!", e);
					}
				} else {
					log.debug("Plugin " + pluginName + " couldn't unloaded!");
				}
			}
		}
		
		this.doRequest(request, response);
		
		this.writeFooter(writer);
	}
	
	/**
	 * checks, if the {@link Plugin} manager is enabled by the {@link Config}
	 *
	 * @param response the {@link HttpServletResponse} to give a error page, if it is not enabled
	 * @return {@code true}, if the {@link Plugin} is enabled, {@code false} otherwise
	 *
	 * @throws IOException if an {@link IOException} occurred on sending the error page
	 * @since 0.1.0
	 */
	private boolean isManagerEnabled(HttpServletResponse response)
			throws IOException {
		Initializer.init(this, true);
		
		boolean managerEnabled = Initializer.config.isManagerEnabled();
		
		if (!managerEnabled) {
			String msg = "The plugin manager is disabled!";
			response.sendError(403, msg);
			log.debug(msg);
		}
		
		return managerEnabled;
	}
	
	/**
	 * writes the header (title, meta information, etc.)
	 *
	 * @param writer the {@link PrintWriter} to write to
	 * @param url    the request {@link URL}
	 * @throws IOException if an {@link IOException} occurred while writing
	 * @since 0.1.0
	 */
	private void writeHeader(PrintWriter writer, String url)
			throws IOException {
		String rootPath = ServletUtils.getRootUrl(url, PluginManagerServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html><head>");
		writer.println("<base href=\"" + rootPath + "\" />");
		writer.println("<link rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" href=\"" + rootPath + "/favicon.ico\" />");
		writer.println("<link rel=\"icon\" type=\"image/vnd.microsoft.icon\" href=\"" + rootPath + "/favicon.ico\" />");
		writer.println("<title>Plugin Manager</title>");
		writer.println("</head><body>");
		writer.println(Header.getHeader(rootPath));
	}
	
	/**
	 * writes the footer (closing tags, etc.)
	 *
	 * @param writer the {@link PrintWriter} to write to
	 * @throws IOException if an {@link IOException} occurred while writing
	 * @since 0.1.0
	 */
	private void writeFooter(PrintWriter writer)
			throws IOException {
		writer.println("</body></html>");
	}
	
	/**
	 * handles the {@link HttpServletRequest}, same on GET and POST
	 *
	 * @param request  the {@link HttpServletRequest}
	 * @param response the {@link HttpServletResponse}
	 * @throws IOException if an input or output exception occurs
	 * @see PluginManagerServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see PluginManagerServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * @since 0.1.0
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		String rootPath = ServletUtils.getRootUrl(ServletUtils.getUrl(request), PluginManagerServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0]);
		String pluginRootPath = MainServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0];
		pluginRootPath = rootPath + "/" + pluginRootPath.substring(1, pluginRootPath.length() - 1) + "services/";
		
		Set<? extends Plugin> plugins = PluginAdapter.getPlugins();
		
		writer.println("<h1 align=\"center\">Plugin Manager</h1>");
		writer.println("<h2>Loaded Plugins</h2>");
		writer.println("<table border=\"1\"><thead><tr><th>Plugin</th><th>Operations</th></tr></thead><tbody>");
		if (plugins.size() > 0) {
			for (Plugin plugin : plugins) {
				String pluginName = ServiceContainer.toUrlSaveString(plugin.getName());
				
				writer.println("<tr><td><a href=\"" + pluginRootPath + pluginName + "\" target=\"_blank\">" + pluginName + "</a></td>");
				writer.println("<td><form action=\"\" method=\"POST\"><input type=\"hidden\" name=\"type\" value=\"remove\" /><input type=\"hidden\" name=\"plugin\" value=\"" + pluginName + "\" /><input type=\"submit\" value=\"Remove\" /></form></td></tr>");
			}
		} else {
			writer.println("<tr><td colspan=\"2\">No plugins loaded</td></tr>");
		}
		writer.println("</tbody></table>");
		
		writer.println("<hr />");
		
		writer.println("<h2>Upload new plugin</h2>");
		writer.println("<form action=\"\" method=\"POST\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"type\" value=\"upload\" /><input type=\"file\" name=\"file\" /><br /><input type=\"submit\" value=\"Upload\"/></form>");
		
		writer.println("<hr />");
		
		writer.println("<p align=\"right\"><font size=\"-1\"><a href=\"" + rootPath + "/about\">&copy; 2014 - 2016 by con terra GmbH</a></font></p>");
	}
	
	/**
	 * extracts the filename of an uploaded {@link File}
	 *
	 * @param part the {@link Part} to get the filename from
	 * @return the filename
	 *
	 * @throws IllegalArgumentException if no filename could found in the {@link Part} or the {@link Part} was {@code null}
	 * @since 0.1.0
	 */
	private static String getFilename(Part part)
			throws IllegalArgumentException {
		if (part != null) {
			for (String cd : part.getHeader("content-disposition").split(";")) {
				if (cd.trim().startsWith("filename")) {
					log.debug("Found element with filename.");
					
					String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
					return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
				}
			}
			
			throw new IllegalArgumentException("Couldn't found a filename in part " + part.getName() + "!");
		}
		
		throw new IllegalArgumentException("The given part was null!");
	}
	
	/**
	 * reload the website (redirect the user to same URL)
	 *
	 * @param response the {@link HttpServletResponse}
	 * @throws IOException if an error occurred on redirecting
	 * @see HttpServletResponse#sendRedirect(String)
	 * @since 0.1.0
	 */
	private void reload(HttpServletResponse response)
			throws IOException {
		log.debug("Reload the site.");
		
		response.sendRedirect(PluginManagerServlet.class.getAnnotation(WebServlet.class).urlPatterns()[0].substring(1));
	}
}