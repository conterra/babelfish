package de.conterra.babelfish.plugin;

import java.io.IOException;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines a wrapper class of a {@link Plugin}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class PluginWrapper
implements Plugin
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(PluginWrapper.class);
	
	/**
	 * the {@link Plugin}
	 * 
	 * @since 0.1
	 */
	private final Plugin plugin;
	/**
	 * the {@link JarFile}
	 * 
	 * @since 0.1
	 */
	private final JarFile file;
	/**
	 * the {@link URLClassLoader}
	 * 
	 * @since 0.1
	 */
	private final URLClassLoader classLoader;
	
	/**
	 * constructor, with given {@link Plugin}
	 * 
	 * @since 0.1
	 * 
	 * @param plugin the {@link Plugin}
	 * @param file the {@link JarFile}
	 * @param classLoader the {@link URLClassLoader}
	 */
	public PluginWrapper(Plugin plugin, JarFile file, URLClassLoader classLoader)
	{
		this.plugin = plugin;
		this.file = file;
		this.classLoader = classLoader;
	}
	
	@Override
	public String toString()
	{
		return this.getPlugin().toString();
	}
	
	@Override
	public String getName()
	{
		return this.getPlugin().getName();
	}
	
	@Override
	public boolean init()
	{
		return this.getPlugin().init();
	}
	
	@Override
	public boolean shutdown()
	{
		String name = this.getName();
		boolean result = this.getPlugin().shutdown();
		
		if (result)
			PluginWrapper.LOGGER.debug("Successfully executed build-in shutdown process of the plugin " + name + ".");
		else
			PluginWrapper.LOGGER.error("Couldn't shutdown the plugin " + name + " itself!");
		
		boolean notUsedByOther = true;
		for (Plugin plugin : PluginAdapter.getPlugins())
		{
			if (plugin != this.getPlugin())
			{
				PluginWrapper wrapper = PluginAdapter.getPluginWrapper(plugin);
				
				if (wrapper.getClassLoader() == this.getClassLoader() || wrapper.getFile() == this.getFile())
				{
					PluginWrapper.LOGGER.debug("Couldn't unload class loader and file of the plugin " + name + ", because them are although used by the plugin " + plugin.getName() + ".");
					notUsedByOther = false;
					break;
				}
			}
		}
		
		if (notUsedByOther)
		{
			try
			{
				this.getClassLoader().close();
				this.getFile().close();
			}
			catch (IOException e)
			{
				PluginWrapper.LOGGER.error("Couldn't unload plugin " + name + "!", e);
				
				return false;
			}
		}
		
		return result;
	}
	
	/**
	 * gives the {@link Plugin}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Plugin}
	 */
	public Plugin getPlugin()
	{
		return this.plugin;
	}
	
	/**
	 * gives the {@link JarFile}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link JarFile}
	 */
	public JarFile getFile()
	{
		return this.file;
	}
	
	/**
	 * gives the {@link URLClassLoader}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link URLClassLoader}
	 */
	public URLClassLoader getClassLoader()
	{
		return this.classLoader;
	}
}