package de.conterra.babelfish;

import de.conterra.babelfish.plugin.Plugin;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * defines a class, which provides the app configuration
 *
 * @author ChrissW-R1
 * @version 0.2.1
 * @since 0.1.0
 */
public class Config {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
	
	/**
	 * the {@link URI} to the {@link Config} {@link File}
	 *
	 * @since 0.1.0
	 */
	private static URL configFilePath = null;
	/**
	 * the {@link Element} id of the shutdown delay
	 *
	 * @since 0.1.0
	 */
	private static final String shutdownDelayNode = "shutdownDelay";
	/**
	 * the {@link Element} id of the manager enabled setting
	 *
	 * @since 0.1.0
	 */
	private static final String managerEnabledNode = "managerEnabled";
	
	/**
	 * the delay, after the shutdown would be called if no requests are registered in this delay
	 *
	 * @since 0.1.0
	 */
	private static long shutdownDelay = 5000;
	/**
	 * should the {@link Plugin} manager be enabled?
	 *
	 * @since 0.1.0
	 */
	private static boolean managerEnabled = false;
	
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private Config() {
	}
	
	/**
	 * gives the delay, after the shutdown would be called if no requests are registered in this delay
	 *
	 * @return the delay of shutdown
	 *
	 * @since 0.1.0
	 */
	public static long getShutdownDelay() {
		return Config.shutdownDelay;
	}
	
	/**
	 * sets the delay, after the shutdown would be called if no requests are registered in this delay
	 *
	 * @param delay the delay of shutdown
	 * @since 0.1.0
	 */
	public static void setShutdownDelay(long delay) {
		Config.shutdownDelay = delay;
	}
	
	/**
	 * should the {@link Plugin} manager be enabled?
	 *
	 * @return {@code true}, if the {@link Plugin} manager should be enabled
	 *
	 * @since 0.1.0
	 */
	public static boolean isManagerEnabled() {
		return Config.managerEnabled;
	}
	
	/**
	 * sets if the {@link Plugin} manager should be enabled
	 *
	 * @param managerEnabled {@code true}, if the {@link Plugin} manager should be enabled
	 * @since 0.1.0
	 */
	public static void setManagerEnabled(boolean managerEnabled) {
		Config.managerEnabled = managerEnabled;
	}
	
	/**
	 * initialize the complete configuration from the {@link Config} {@link File}
	 *
	 * @return {@code true}, if the {@link File} was successfully loaded
	 *
	 * @since 0.1.0
	 */
	public static boolean load() {
		try {
			if (Config.configFilePath == null)
				Config.configFilePath = new URL(Initializer.BASE_URL.toString() + "/config.xml");
			
			URI uri = Config.configFilePath.toURI();
			
			Config.LOGGER.debug("Try loading configuration from: " + uri);
			
			File file = new File(uri);
			
			if (!(file.exists())) {
				Config.LOGGER.debug("Config file doesn't exists.");
				return true;
			}
			
			Element rootNode = (new SAXBuilder()).build(file).getRootElement();
			
			try {
				long delay = Long.parseLong(rootNode.getChildText(Config.shutdownDelayNode));
				Config.setShutdownDelay(delay);
				Config.LOGGER.debug("Loaded shutdown delay: " + delay);
			} catch (NumberFormatException e) {
				Config.LOGGER.warn("Couldn't load shutdown delay from config file!", e);
			}
			
			try {
				boolean manager = Boolean.parseBoolean(rootNode.getChildText(Config.managerEnabledNode));
				Config.setManagerEnabled(manager);
				Config.LOGGER.debug("Loaded setting, if the plugin manager should be enabled: " + manager);
			} catch (NumberFormatException e) {
				Config.LOGGER.warn("Couldn't load setting, if the plugin manager should be enabled!", e);
			}
		} catch (NullPointerException | MalformedURLException | URISyntaxException | IllegalArgumentException e) {
			Config.LOGGER.warn("Couldn't load config file!", e);
			
			Config.configFilePath = null;
			
			return false;
		} catch (JDOMException | IOException e) {
			Config.LOGGER.error("An error occurred on parsing the config file!", e);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * writes the complete configuration to the {@link Config} {@link File}
	 *
	 * @return {@code true}, if the {@link File} was successfully written
	 *
	 * @since 0.1.0
	 */
	public static boolean save() {
		try {
			Element rootNode = new Element("config");
			
			Config.LOGGER.debug("Add " + Config.shutdownDelayNode + " node to the XML.");
			Element shutdownDelay = new Element(Config.shutdownDelayNode);
			shutdownDelay.setText("" + Config.getShutdownDelay());
			rootNode.addContent(shutdownDelay);
			
			Config.LOGGER.debug("Add " + Config.managerEnabledNode + " node to the XML.");
			Element managerEnabled = new Element(Config.managerEnabledNode);
			managerEnabled.setText("" + Config.isManagerEnabled());
			rootNode.addContent(managerEnabled);
			
			Document doc = new Document(rootNode);
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			FileWriter writer = new FileWriter(new File(Config.configFilePath.toURI()));
			out.output(doc, writer);
			writer.close();
			
			Config.LOGGER.debug("Successfully saved the config file.");
		} catch (NullPointerException | URISyntaxException | IOException e) {
			Config.LOGGER.error("An error occurred on writing configuration to file!", e);
			
			return false;
		}
		
		return true;
	}
}