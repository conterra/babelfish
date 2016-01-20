package de.conterra.babelfish.helloWorld;

import java.awt.Image;
import java.util.Map;
import java.util.Set;

import de.conterra.babelfish.plugin.ServiceContainer;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.PopupType;
import de.conterra.babelfish.plugin.v10_02.feature.Query;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.feature.Type;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Point;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelingInfo;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;

/**
 * defines a &quot;Hello World!&quot; {@link FeatureLayer}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class HelloWorldLayer
implements FeatureLayer<Point, GeometryFeatureObject<Point>>
{
	/**
	 * standard constructor
	 * 
	 * @since 0.3.1
	 */
	public HelloWorldLayer()
	{
	}
	
	@Override
	public int getId()
	{
		return 0;
	}
	
	@Override
	public String getName()
	{
		return ServiceContainer.toUrlSaveString(HelloWorldPlugin.HELLO_WORLD);
	}
	
	@Override
	public String getDescription()
	{
		return HelloWorldPlugin.HELLO_WORLD;
	}
	
	@Override
	public String getCopyrightText()
	{
		return "";
	}
	
	@Override
	public PopupType getPopupType()
	{
		return PopupType.None;
	}
	
	@Override
	public Field getObjectIdField()
	{
		return LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
	}
	
	@Override
	public Field getGlobalIdField()
	{
		return LayerWrapper.DEFAULT_GLOBAL_ID_FIELD;
	}
	
	@Override
	public Field getDisplayField()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Field getTypeIdField()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Type<GeometryFeatureObject<Point>>> getSubTypes()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Template<GeometryFeatureObject<Point>>> getTemplates()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Query<GeometryFeatureObject<Point>> getQuery()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<? extends String, ? extends Image> getImages()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<? extends Feature<GeometryFeatureObject<Point>>> getFeatures()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Class<Point> getGeometryType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMinScale()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getMaxScale()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public RendererObject getRenderer()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTranparency()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public LabelingInfo getLabelingInfo()
	{
		// TODO Auto-generated method stub
		return null;
	}
}