package de.conterra.babelfish;

import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.PluginAdapter;
import de.conterra.babelfish.util.ReschedulableTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * this class initialize all necessary subsystems
 *
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.1.0
 */
public class Initializer {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);
	
	/**
	 * the root {@link URL} to the folder to store files (like configurations, plugins, etc.)
	 *
	 * @since 0.2.1
	 */
	public static URL BASE_URL = null;
	
	/**
	 * the {@link ReschedulableTimer}, which calls the {@link Initializer#shutdown()}, if no requests are registered for a while
	 *
	 * @since 0.1.0
	 */
	public static final ReschedulableTimer SHUTDOWN_TIMER = new ReschedulableTimer(() -> {
		if (!Initializer.shutdown())
			Initializer.LOGGER.warn("Shutdown failed!");
	});
	
	/**
	 * the {@link URL} of the ArcGIS Server REST API (hosted by ESRI Inc.)
	 *
	 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/">ArcGIS REST API</a>
	 * @since 0.1.0
	 */
	public static final String HELP_URL = "http://resources.arcgis.com/en/help/rest/apiref/";
	/**
	 * the default Babelfish icon / logo
	 *
	 * @since 0.1.0
	 */
	private static Image DEFAULT_ICON = null;
	/**
	 * flag, which will be set, while plugins were be loaded
	 *
	 * @since 0.3.0
	 */
	private static volatile boolean loadPluginsFlag = false;
	
	/**
	 * private standard constructor, to prevent initialization of this class
	 *
	 * @since 0.1.0
	 */
	private Initializer() {
	}
	
	/**
	 * initialize (e.g. search and load plugins)
	 *
	 * @param servlet     the {@link HttpServlet} to get the path information from
	 * @param loadPlugins {@code true}, if the plugins folder should be scanned and all plugins loaded
	 * @return {@code true}, if the initialization finished successfully
	 *
	 * @since 0.1.0
	 */
	public static boolean init(HttpServlet servlet, boolean loadPlugins) {
		Initializer.LOGGER.debug("Start initialization");
		boolean result = true;
		
		ServletContext context = servlet.getServletContext();
		
		if (Initializer.BASE_URL == null) {
			Initializer.LOGGER.debug("Define base URL to store files.");
			
			try {
				URL url = new URL(context.getResource("/").toString());
				URI uri = url.toURI();
				
				String scheme = uri.getScheme();
				if (scheme.equalsIgnoreCase("File"))
					Initializer.BASE_URL = url;
				else if (scheme.equalsIgnoreCase("JAR")) {
					String path = uri.toString();
					path = path.substring(0, path.indexOf("!/"));
					path = path.substring(0, path.lastIndexOf("."));
					path = path.substring(path.indexOf(":") + 1);
					
					Initializer.BASE_URL = new URL(path);
				} else if (scheme.equalsIgnoreCase("JNDI"))
					Initializer.BASE_URL = new URL(("file:///" + context.getRealPath("/")).replaceAll(" ", "%20"));
				
				Initializer.LOGGER.debug("Set the base URL to: " + Initializer.BASE_URL.toURI().toString());
			} catch (NullPointerException | MalformedURLException | URISyntaxException e) {
				Initializer.LOGGER.error("Couldn't found a valid URL!", e);
			}
		}
		
		if (Config.load())
			Initializer.LOGGER.debug("Configuration successfully loaded.");
		else
			Initializer.LOGGER.warn("Couldn't load configuration!");
		
		Initializer.LOGGER.debug("Reschedule the shutdown timer.");
		Initializer.SHUTDOWN_TIMER.reschedule(Config.getShutdownDelay());
		
		if (Initializer.getDefaultIcon() == null) {
			Initializer.LOGGER.debug("Load default Babelfish icon.");
			
			try {
				Initializer.DEFAULT_ICON = ImageIO.read(context.getResource("/WEB-INF/classes/icon.png"));
			} catch (IOException e) {
				Initializer.LOGGER.error("Couldn't load the default Babelfish icon!", e);
				
				result = false;
			}
		}
		
		try {
			URL url = null;
			
			long start = System.currentTimeMillis();
			while (PluginAdapter.getPluginsFolder() == null && (System.currentTimeMillis() - start) < 5000) {
				File file = new File(new File(Initializer.BASE_URL.toURI()), PluginAdapter.PLUGINS_FOLDER_PATH.substring(1));
				Initializer.LOGGER.debug("Try to create plugins folder. URL: " + file.getName());
				if (!(file.exists()) && !(file.mkdirs()))
					Initializer.LOGGER.warn("Couldn't create plugin folder.");
				
				url = file.toURI().toURL();
				if (url != null)
					PluginAdapter.setPluginsFolder(url);
			}
			
			if (loadPlugins) {
				long abortDelay = Config.getShutdownDelay();
				if (abortDelay > 60000)
					abortDelay = 60000;
				
				long abortTime = System.currentTimeMillis() + abortDelay;
				while (Initializer.loadPluginsFlag && System.currentTimeMillis() < abortTime)
					;
				
				if (!Initializer.loadPluginsFlag) {
					Initializer.loadPluginsFlag = true;
					Initializer.LOGGER.debug("Set flag, while plugins were loaded.");
					url = PluginAdapter.getPluginsFolder();
					
					if (url != null) {
						File folder = new File(url.toURI());
						
						if (folder.isDirectory()) {
							for (File file : folder.listFiles()) {
								String fileName = file.getAbsolutePath();
								Initializer.LOGGER.debug("Found file in plugin folder: " + fileName);
								
								if (file.isDirectory()) {
									Initializer.LOGGER.debug("The file " + fileName + " is a directory.");
									continue;
								}
								
								try {
									PluginAdapter.loadPlugin(file);
								} catch (IOException e) {
									Initializer.LOGGER.debug("The file " + fileName + " is not a valid plugin JAR!", e);
								}
							}
						}
					} else {
						Initializer.LOGGER.warn("The plugins folder doesn't exists!");
						result = false;
					}
					
					Initializer.LOGGER.debug("Release flag of plugin loading.");
					Initializer.loadPluginsFlag = false;
				}
			}
		} catch (URISyntaxException | IOException e) {
			Initializer.LOGGER.warn("An error occurred while loading plugins!", e);
			
			return false;
		}
		
		return result;
	}
	
	/**
	 * Unloads all {@link Plugin}s and the {@link Plugin} API (JSPF)
	 *
	 * @return {@code true}, if all {@link Plugin}s were successfully unloaded
	 *
	 * @since 0.1.0
	 */
	private static boolean shutdown() {
		boolean result = true;
		
		for (Plugin plugin : PluginAdapter.getPlugins()) {
			String pluginName = plugin.getName();
			
			if (PluginAdapter.unloadPlugin(plugin))
				Initializer.LOGGER.debug("Plugin " + pluginName + " successfully unloaded.");
			else {
				Initializer.LOGGER.error("Couldn't unload plugin " + pluginName + ".");
				
				result = false;
			}
		}
		
		Initializer.LOGGER.debug("Call garbage collector.");
		System.gc();
		
		if (Config.save())
			Initializer.LOGGER.debug("Configuration successfully saved.");
		else
			Initializer.LOGGER.warn("Couldn't save the configuration!");
		
		return result;
	}
	
	/**
	 * gives the default Babelfish icon
	 *
	 * @return the default Babelfish icon
	 *
	 * @since 0.1.0
	 */
	public static Image getDefaultIcon() {
		return Initializer.DEFAULT_ICON;
	}
}