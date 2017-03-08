package de.conterra.babelfish.helloWorld;

import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Point;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelingInfo;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;

import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * defines a &quot;Hello World!&quot; {@link FeatureLayer}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class HelloWorldLayer
		implements FeatureLayer<Point, GeometryFeatureObject<Point>> {
	/**
	 * standard constructor
	 *
	 * @since 0.3.1
	 */
	public HelloWorldLayer() {
	}
	
	@Override
	public int getId() {
		return 0;
	}
	
	@Override
	public String getName() {
		return ServiceContainer.toUrlSaveString(HelloWorldPlugin.HELLO_WORLD);
	}
	
	@Override
	public String getDescription() {
		return HelloWorldPlugin.HELLO_WORLD;
	}
	
	@Override
	public String getCopyrightText() {
		return "";
	}
	
	@Override
	public PopupType getPopupType() {
		return PopupType.None;
	}
	
	@Override
	public Field getObjectIdField() {
		return LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
	}
	
	@Override
	public Field getGlobalIdField() {
		return LayerWrapper.DEFAULT_GLOBAL_ID_FIELD;
	}
	
	@Override
	public Field getDisplayField() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Field getTypeIdField() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Type<GeometryFeatureObject<Point>>> getSubTypes() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Template<GeometryFeatureObject<Point>>> getTemplates() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Query<GeometryFeatureObject<Point>> getQuery() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<? extends String, ? extends Image> getImages() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Feature<GeometryFeatureObject<Point>>> getFeatures() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public Class<Point> getGeometryType() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMinScale() {
		// ToDo Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaxScale() {
		// ToDo Auto-generated method stub
		return 0;
	}
	
	@Override
	public RendererObject getRenderer() {
		// ToDo Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTranparency() {
		// ToDo Auto-generated method stub
		return 0;
	}
	
	@Override
	public LabelingInfo getLabelingInfo() {
		// ToDo Auto-generated method stub
		return null;
	}
}