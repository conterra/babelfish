package de.conterra.babelfish.helloWorld;

import de.conterra.babelfish.plugin.Plugin;

/**
 * defines a &quot;Hello World!&quot; plugin
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class HelloWorldPlugin
implements Plugin
{
	/**
	 * the only instance of a {@link HelloWorldPlugin}<br>
	 * (singleton pattern)
	 * 
	 * @since 0.3.1
	 */
	private static HelloWorldPlugin instance = null;
	/**
	 * the &quot;Hello World!&quot; {@link String}
	 * 
	 * @since 0.3.1
	 */
	public static final String HELLO_WORLD = "Hello World!";
	
	/**
	 * standard constructor
	 * 
	 * @since 0.3.1
	 */
	public HelloWorldPlugin()
	{
		if (HelloWorldPlugin.instance == null)
		{
			HelloWorldPlugin.instance = this;
		}
	}
	
	@Override
	public String getName()
	{
		return HelloWorldPlugin.HELLO_WORLD;
	}
	
	@Override
	public boolean init()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean shutdown()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * gives the only instance of a {@link HelloWorldPlugin}
	 * 
	 * @since 0.3.1
	 * 
	 * @return the singleton instance
	 */
	public static HelloWorldPlugin getInstance()
	{
		return HelloWorldPlugin.instance;
	}
}