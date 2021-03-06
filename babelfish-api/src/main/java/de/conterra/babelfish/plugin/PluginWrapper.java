package de.conterra.babelfish.plugin;

import de.conterra.babelfish.util.DataUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
	@Getter
	private final Plugin         plugin;
	/**
	 * the {@link JarFile}
	 *
	 * @since 0.1.0
	 */
	@Getter
	private final JarFile        file;
	/**
	 * the {@link URLClassLoader}
	 *
	 * @since 0.1.0
	 */
	@Getter
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
		String  name   = this.getName();
		boolean result = this.getPlugin().shutdown();
		
		if (result) {
			log.debug("Successfully executed build-in shutdown process of the plugin " + name + ".");
		} else {
			log.error("Couldn't shutdown the plugin " + name + " itself!");
		}
		
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
			boolean unloaded = DataUtils.closeStream(this.classLoader) && DataUtils.closeStream(this.file);
			if (!unloaded) {
				log.error("Couldn't unload plugin " + name + "! Closing of streams failed!");
			}
		}
		
		return result;
	}
}
