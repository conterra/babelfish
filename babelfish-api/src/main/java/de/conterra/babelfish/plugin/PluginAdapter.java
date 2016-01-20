package de.conterra.babelfish.plugin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines a class of static methods to {@link Plugin}s
 * 
 * @version 0.2.1
 * @author chwe
 * @since 0.1
 */
public class PluginAdapter
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(PluginAdapter.class);
	
	/**
	 * the path to the folder, in which the {@link Plugin} JARs would be saved
	 * 
	 * @since 0.1
	 */
	public static final String PLUGINS_FOLDER_PATH = "/plugins";
	/**
	 * the {@link URL} of the {@link Plugin} API base folder
	 * 
	 * @since 0.1
	 */
	private static URL PLUGINS_FOLDER = null;
	/**
	 * {@link Set} of all registered {@link Plugin}s
	 * 
	 * @since 0.1
	 */
	private static final Map<Plugin, PluginWrapper> PLUGINS = new ConcurrentHashMap<>();
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private PluginAdapter()
	{
	}
	
	/**
	 * gives the {@link URL} of the base folder, in which every {@link Plugin}
	 * JAR was stored
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Plugin} API base folder
	 */
	public static URL getPluginsFolder()
	{
		return PluginAdapter.PLUGINS_FOLDER;
	}
	
	/**
	 * sets the {@link Plugin} API base folder<br>
	 * <b>Attention</b>: Could only called, if the base folder wasn't set yet
	 * 
	 * @since 0.1
	 * 
	 * @param folder the {@link URL} of the {@link Plugin} API base folder to
	 *        set
	 * @return <code>true</code> if the base folder was set to the given one
	 */
	public static boolean setPluginsFolder(URL folder)
	{
		if (folder != null && PluginAdapter.getPluginsFolder() == null)
		{
			PluginAdapter.PLUGINS_FOLDER = folder;
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * gives a {@link Set} of all loaded {@link Plugin}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all loaded {@link Plugin}s
	 */
	public static Set<? extends Plugin> getPlugins()
	{
		return new LinkedHashSet<>(PluginAdapter.PLUGINS.keySet());
	}
	
	/**
	 * gives the {@link Plugin} with the given name<br>
	 * <b>Hint</b>: The method compares the {@link URL} save names
	 * 
	 * @since 0.1
	 * 
	 * @param name the name of the {@link Plugin} to look for
	 * @return the loaded {@link Plugin} with name <code>name</code>
	 */
	public static Plugin getPlugin(String name)
	{
		String saveName = ServiceContainer.toUrlSaveString(name);
		
		for (Plugin plugin : PluginAdapter.getPlugins())
		{
			if (ServiceContainer.toUrlSaveString(plugin.getName()).equalsIgnoreCase(saveName))
				return plugin;
		}
		
		return null;
	}
	
	/**
	 * gives the {@link PluginWrapper} of the given {@link Plugin}
	 * 
	 * @since 0.1
	 * 
	 * @param plugin the {@link Plugin} to get the {@link PluginWrapper} of
	 * @return the {@link PluginWrapper} of the {@link Plugin}, if it is loaded,
	 *         otherwise <code>null</code>
	 */
	public static PluginWrapper getPluginWrapper(Plugin plugin)
	{
		return PluginAdapter.PLUGINS.get(plugin);
	}
	
	/**
	 * gives the {@link URL} of the working folder of a given {@link Plugin}
	 * 
	 * @since 0.1
	 * 
	 * @param plugin the {@link Plugin} to get the working folder of
	 * @return the URL of the working folder
	 * @throws NullPointerException if the {@link Plugin} API was not
	 *         initialized right
	 */
	public static URL getPluginFolder(Plugin plugin)
	throws NullPointerException
	{
		try
		{
			File pluginFolder = new File(new File(PluginAdapter.getPluginsFolder().toURI()), ServiceContainer.toUrlSaveString(plugin.getName()));
			
			pluginFolder.mkdirs();
			
			if (pluginFolder.exists())
				return pluginFolder.toURI().toURL();
		}
		catch (NullPointerException | URISyntaxException | SecurityException | MalformedURLException e)
		{
		}
		
		throw new NullPointerException("The plugin API was not correctly initialized!");
	}
	
	/**
	 * loads {@link Plugin}s of a {@link File}
	 * 
	 * @since 0.1
	 * 
	 * @param file the {@link File} to load the {@link Plugin} from
	 * @return a {@link Set} of all loaded {@link Plugin}s
	 * @throws IOException if the given {@link File} is not a valid
	 *         {@link Plugin} {@link File}
	 */
	public static Set<? extends Plugin> loadPlugin(File file)
	throws IOException
	{
		String fileEx = ".class";
		String path = file.getAbsolutePath();
		Set<Plugin> result = new LinkedHashSet<>();
		
		PluginAdapter.LOGGER.debug("Try loading plugin from " + path + ".");
		
		if (file.isDirectory())
		{
			String msg = "The plugin file have to be a JAR! But " + path + " is a directory!";
			PluginAdapter.LOGGER.error(msg);
			throw new IOException(msg);
		}
		
		try
		{
			JarFile jarFile = new JarFile(file);
			
			URLClassLoader cl = new URLClassLoader(new URL[]
			{
					new URL("jar:file:" + path + "!/")
			}, PluginAdapter.class.getClassLoader());
			
			boolean noPlugin = true;
			
			Enumeration<JarEntry> enumer = jarFile.entries();
			while (enumer.hasMoreElements())
			{
				JarEntry entry = enumer.nextElement();
				
				if (entry.isDirectory() || ( ! (entry.getName().endsWith(fileEx))))
					continue;
				
				String className = entry.getName();
				className = className.substring(0, (className.length()) - (fileEx.length())).replaceAll("/", ".");
				
				try
				{
					Class<?> clazz = cl.loadClass(className);
					
					if (Plugin.class.isAssignableFrom(clazz))
					{
						noPlugin = false;
						
						Plugin plugin = (Plugin) (clazz.newInstance());
						String pluginName = plugin.getName();
						
						if (PluginAdapter.getPlugin(pluginName) != null)
						{
							PluginAdapter.LOGGER.debug("A plugin with the name " + pluginName + " is already loaded!");
							continue;
						}
						
						PluginAdapter.LOGGER.debug("Initialize plugin " + pluginName + ".");
						if (plugin.init())
						{
							PluginAdapter.LOGGER.debug("Plugin " + pluginName + " successfully initialized.");
							
							PluginAdapter.PLUGINS.put(plugin, new PluginWrapper(plugin, jarFile, cl));
							result.add(plugin);
						}
						else
						{
							PluginAdapter.LOGGER.warn("Initialization of plugin " + pluginName + " failed!");
						}
						
						PluginAdapter.LOGGER.debug("Successfully loaded plugin " + pluginName + ".");
					}
				}
				catch (ClassNotFoundException | NoClassDefFoundError e)
				{
					PluginAdapter.LOGGER.debug("Couldn't load class " + className + " of file " + path + ".", e);
				}
				catch (InstantiationException | IllegalAccessException e)
				{
					PluginAdapter.LOGGER.debug("Couldn't instantiate an object of the class " + className + ".", e);
				}
			}
			
			if (result.isEmpty())
			{
				cl.close();
				jarFile.close();
			}
			
			if (noPlugin)
				throw new IOException("Couldn't found any plugin implementation in file " + path + "!");
		}
		catch (MalformedURLException e)
		{
			String msg = "An error occurred on create a class loader of file " + path + "!";
			PluginAdapter.LOGGER.error(msg, e);
			throw new IOException(msg, e);
		}
		catch (SecurityException e)
		{
			String msg = "Have no access rights of the file " + path + "!";
			PluginAdapter.LOGGER.error(msg, e);
			throw new IOException(msg, e);
		}
		catch (IOException e)
		{
			String msg = "Couldn't load file " + path + " as a valid JAR!";
			PluginAdapter.LOGGER.error(msg, e);
			throw new IOException(msg, e);
		}
		
		return result;
	}
	
	/**
	 * unloads a {@link Plugin} and release all handler
	 * 
	 * @since 0.1
	 * 
	 * @param plugin the {@link Plugin} to unload
	 * @return <code>true</code>, if the {@link Plugin} was successfully
	 *         unloaded
	 */
	public static boolean unloadPlugin(Plugin plugin)
	{
		PluginAdapter.LOGGER.debug("Try unloading the plugin " + plugin.getName() + ".");
		
		return PluginAdapter.getPluginWrapper(plugin).shutdown()
		&& (PluginAdapter.PLUGINS.remove(plugin) != null);
	}
}