package de.conterra.babelfish.helloWorld;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Relationship;
import de.conterra.babelfish.plugin.v10_02.feature.Table;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;

/**
 * defines a &quot;Hello World!&quot; {@link FeatureService}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class HelloWorldService
implements FeatureService
{
	/**
	 * the only instance of a {@link HelloWorldService}<br>
	 * (singleton pattern)
	 * 
	 * @since 0.3.1
	 */
	public static final HelloWorldService INSTANCE = new HelloWorldService();
	
	/**
	 * standard constructor
	 * 
	 * @since 0.3.1
	 */
	private HelloWorldService()
	{
	}
	
	@Override
	public String getServiceDescription()
	{
		return HelloWorldPlugin.HELLO_WORLD;
	}
	
	@Override
	public Plugin getPlugin()
	{
		return HelloWorldPlugin.getInstance();
	}
	
	@Override
	public String getId()
	{
		return ServiceContainer.toUrlSaveString(HelloWorldPlugin.HELLO_WORLD);
	}
	
	@Override
	public Image getIcon()
	{
		return null;
	}
	
	@Override
	public Set<? extends Layer<? extends FeatureObject>> getLayers()
	{
		return this.getFeatureLayers();
	}
	
	@Override
	public Set<? extends FeatureLayer<?, ? extends GeometryFeatureObject<?>>> getFeatureLayers()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Table<? extends FeatureObject>> getTables()
	{
		return new HashSet<>();
	}
	
	@Override
	public Set<? extends Relationship<? extends FeatureObject, ? extends FeatureObject>> getRelationships()
	{
		return new HashSet<>();
	}
}