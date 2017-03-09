package de.conterra.babelfish;

import de.conterra.babelfish.config.Config;
import de.conterra.babelfish.config.ObjectFactory;
import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.PluginAdapter;
import de.conterra.babelfish.util.ReschedulableTimer;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.xml.bind.*;
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
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class Initializer {
	/**
	 * singleton {@link ObjectFactory} to create XML objects
	 *
	 * @since 0.4.0
	 */
	public static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
	/**
	 * {@link Config} object to store all settings
	 *
	 * @since 0.4.0
	 */
	public static Config config;
	
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
			log.warn("Shutdown failed!");
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
		log.debug("Start initialization");
		boolean result = true;
		
		ServletContext context = servlet.getServletContext();
		
		if (Initializer.BASE_URL == null) {
			log.debug("Define base URL to store files.");
			
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
				
				log.debug("Set the base URL to: " + Initializer.BASE_URL.toURI().toString());
			} catch (NullPointerException | MalformedURLException | URISyntaxException e) {
				log.error("Couldn't found a valid URL!", e);
			}
		}
		
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(Config.class).createUnmarshaller();
			JAXBElement<Config> element = (JAXBElement<Config>) unmarshaller.unmarshal(new File(BASE_URL.toString() + "/config.xml"));
			config = element.getValue();
			
			log.debug("Configuration successfully loaded.");
		} catch (JAXBException e) {
			log.warn("Couldn't load configuration! Using default settings instead.");
			
			config = OBJECT_FACTORY.createConfig();
		}
		
		log.debug("Reschedule the shutdown timer.");
		Initializer.SHUTDOWN_TIMER.reschedule(config.getShutdownDelay());
		
		if (Initializer.getDefaultIcon() == null) {
			log.debug("Load default Babelfish icon.");
			
			try {
				Initializer.DEFAULT_ICON = ImageIO.read(context.getResource("/WEB-INF/classes/icon.png"));
			} catch (IOException e) {
				log.error("Couldn't load the default Babelfish icon!", e);
				
				result = false;
			}
		}
		
		try {
			URL url = null;
			
			long start = System.currentTimeMillis();
			while (PluginAdapter.getPluginsFolder() == null && (System.currentTimeMillis() - start) < 5000) {
				File file = new File(new File(Initializer.BASE_URL.toURI()), PluginAdapter.PLUGINS_FOLDER_PATH.substring(1));
				log.debug("Try to create plugins folder. URL: " + file.getName());
				if (!(file.exists()) && !(file.mkdirs()))
					log.warn("Couldn't create plugin folder.");
				
				url = file.toURI().toURL();
				if (url != null)
					PluginAdapter.setPluginsFolder(url);
			}
			
			if (loadPlugins) {
				long abortDelay = config.getShutdownDelay();
				if (abortDelay > 60000)
					abortDelay = 60000;
				
				long abortTime = System.currentTimeMillis() + abortDelay;
				while (Initializer.loadPluginsFlag && System.currentTimeMillis() < abortTime)
					;
				
				if (!Initializer.loadPluginsFlag) {
					Initializer.loadPluginsFlag = true;
					log.debug("Set flag, while plugins were loaded.");
					url = PluginAdapter.getPluginsFolder();
					
					if (url != null) {
						File folder = new File(url.toURI());
						
						if (folder.isDirectory()) {
							for (File file : folder.listFiles()) {
								String fileName = file.getAbsolutePath();
								log.debug("Found file in plugin folder: " + fileName);
								
								if (file.isDirectory()) {
									log.debug("The file " + fileName + " is a directory.");
									continue;
								}
								
								try {
									PluginAdapter.loadPlugin(file);
								} catch (IOException e) {
									log.debug("The file " + fileName + " is not a valid plugin JAR!", e);
								}
							}
						}
					} else {
						log.warn("The plugins folder doesn't exists!");
						result = false;
					}
					
					log.debug("Release flag of plugin loading.");
					Initializer.loadPluginsFlag = false;
				}
			}
		} catch (URISyntaxException | IOException e) {
			log.warn("An error occurred while loading plugins!", e);
			
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
				log.debug("Plugin " + pluginName + " successfully unloaded.");
			else {
				log.error("Couldn't unload plugin " + pluginName + ".");
				
				result = false;
			}
		}
		
		log.debug("Call garbage collector.");
		System.gc();
		
		try {
			Marshaller marshaller = JAXBContext.newInstance(Config.class).createMarshaller();
			marshaller.marshal(config, new File(BASE_URL.toString() + "/config.xml"));
			
			log.debug("Configuration successfully saved.");
		} catch (JAXBException e) {
			e.printStackTrace();
			log.warn("Couldn't save the configuration!");
		}
		
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