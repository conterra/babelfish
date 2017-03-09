package de.conterra.babelfish.plugin;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

/**
 * defines a wrapper class of a {@link Plugin}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class PluginWrapper
		implements Plugin {
	/**
	 * the {@link Plugin}
	 *
	 * @since 0.1.0
	 */
	private final Plugin plugin;
	/**
	 * the {@link JarFile}
	 *
	 * @since 0.1.0
	 */
	private final JarFile file;
	/**
	 * the {@link URLClassLoader}
	 *
	 * @since 0.1.0
	 */
	private final URLClassLoader classLoader;
	
	/**
	 * constructor, with given {@link Plugin}
	 *
	 * @param plugin      the {@link Plugin}
	 * @param file        the {@link JarFile}
	 * @param classLoader the {@link URLClassLoader}
	 * @since 0.1.0
	 */
	public PluginWrapper(Plugin plugin, JarFile file, URLClassLoader classLoader) {
		this.plugin = plugin;
		this.file = file;
		this.classLoader = classLoader;
	}
	
	@Override
	public String toString() {
		return this.getPlugin().toString();
	}
	
	@Override
	public String getName() {
		return this.getPlugin().getName();
	}
	
	@Override
	public boolean init() {
		return this.getPlugin().init();
	}
	
	@Override
	public boolean shutdown() {
		String name = this.getName();
		boolean result = this.getPlugin().shutdown();
		
		if (result)
			log.debug("Successfully executed build-in shutdown process of the plugin " + name + ".");
		else
			log.error("Couldn't shutdown the plugin " + name + " itself!");
		
		boolean notUsedByOther = true;
		for (Plugin plugin : PluginAdapter.getPlugins()) {
			if (plugin != this.getPlugin()) {
				PluginWrapper wrapper = PluginAdapter.getPluginWrapper(plugin);
				
				if (wrapper.getClassLoader() == this.getClassLoader() || wrapper.getFile() == this.getFile()) {
					log.debug("Couldn't unload class loader and file of the plugin " + name + ", because them are although used by the plugin " + plugin.getName() + ".");
					notUsedByOther = false;
					break;
				}
			}
		}
		
		if (notUsedByOther) {
			try {
				this.getClassLoader().close();
				this.getFile().close();
			} catch (IOException e) {
				log.error("Couldn't unload plugin " + name + "!", e);
				
				return false;
			}
		}
		
		return result;
	}
	
	/**
	 * gives the {@link Plugin}
	 *
	 * @return the {@link Plugin}
	 *
	 * @since 0.1.0
	 */
	public Plugin getPlugin() {
		return this.plugin;
	}
	
	/**
	 * gives the {@link JarFile}
	 *
	 * @return the {@link JarFile}
	 *
	 * @since 0.1.0
	 */
	public JarFile getFile() {
		return this.file;
	}
	
	/**
	 * gives the {@link URLClassLoader}
	 *
	 * @return the {@link URLClassLoader}
	 *
	 * @since 0.1.0
	 */
	public URLClassLoader getClassLoader() {
		return this.classLoader;
	}
}