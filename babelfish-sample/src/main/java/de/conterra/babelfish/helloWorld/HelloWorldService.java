package de.conterra.babelfish.helloWorld;

import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * defines a &quot;Hello World!&quot; {@link FeatureService}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class HelloWorldService
		implements FeatureService {
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
	private HelloWorldService() {
	}
	
	@Override
	public String getServiceDescription() {
		return HelloWorldPlugin.HELLO_WORLD;
	}
	
	@Override
	public Plugin getPlugin() {
		return HelloWorldPlugin.getInstance();
	}
	
	@Override
	public String getId() {
		return ServiceContainer.toUrlSaveString(HelloWorldPlugin.HELLO_WORLD);
	}
	
	@Override
	public Image getIcon() {
		return null;
	}
	
	@Override
	public Set<? extends Layer<? extends FeatureObject>> getLayers() {
		return this.getFeatureLayers();
	}
	
	@Override
	public Set<? extends FeatureLayer<?, ? extends GeometryFeatureObject<?>>> getFeatureLayers() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Table<? extends FeatureObject>> getTables() {
		return new HashSet<>();
	}
	
	@Override
	public Set<? extends Relationship<? extends FeatureObject, ? extends FeatureObject>> getRelationships() {
		return new HashSet<>();
	}
}