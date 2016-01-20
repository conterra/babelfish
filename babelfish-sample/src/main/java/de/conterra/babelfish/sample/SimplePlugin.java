package de.conterra.babelfish.sample;

import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.sample.feature.SimpleFeatureService;

/**
 * defines a very simple {@link Plugin}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.1
 */
public class SimplePlugin
implements Plugin
{
	/**
	 * the only instance of a {@link SimplePlugin}<br>
	 * (singleton pattern)
	 * 
	 * @since 0.1
	 */
	private static SimplePlugin instance = null;
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public SimplePlugin()
	{
		if (SimplePlugin.instance == null)
		{
			SimplePlugin.instance = this;
		}
	}
	
	@Override
	public String getName()
	{
		return "Simple Sample";
	}
	
	@Override
	public boolean init()
	{
		return ServiceContainer.registerService(SimpleFeatureService.INSTANCE);
	}
	
	@Override
	public boolean shutdown()
	{
		return ServiceContainer.unregisterService(SimpleFeatureService.INSTANCE);
	}
	
	/**
	 * gives the only instance of a {@link SimplePlugin}
	 * 
	 * @since 0.3.1
	 * 
	 * @return the singleton instance
	 */
	public static SimplePlugin getInstance()
	{
		return SimplePlugin.instance;
	}
}