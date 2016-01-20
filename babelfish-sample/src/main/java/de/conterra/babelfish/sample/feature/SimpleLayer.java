package de.conterra.babelfish.sample.feature;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.PopupType;
import de.conterra.babelfish.plugin.v10_02.feature.Query;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.feature.Type;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * defines a very simple {@link Layer}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 */
public abstract class SimpleLayer<T extends FeatureObject>
implements Layer<T>
{
	/**
	 * the unique identifier
	 * 
	 * @since 0.1
	 */
	private final int id;
	/**
	 * the user friendly name
	 * 
	 * @since 0.1
	 */
	private final String name;
	/**
	 * the layer description
	 * 
	 * @since 0.1
	 */
	private final String desc;
	
	/**
	 * constructor, with all necessary attributes
	 * 
	 * @since 0.1
	 * 
	 * @param id the unique id
	 * @param name the name shown to the user
	 * @param desc the layer description
	 */
	public SimpleLayer(int id, String name, String desc)
	{
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	
	@Override
	public int getId()
	{
		return this.id;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getDescription()
	{
		return this.desc;
	}
	
	@Override
	public String getCopyrightText()
	{
		return "\u00A9 con terra - Gesellschaft für Angewandte Informationstechnologie mbH & the OpenStreetMap contributors";
	}
	
	@Override
	public PopupType getPopupType()
	{
		return PopupType.HtmlText;
	}
	
	@Override
	public Field getObjectIdField()
	{
		return LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
	}
	
	@Override
	public Field getGlobalIdField()
	{
		return null;
	}
	
	@Override
	public Field getDisplayField()
	{
		return SimpleField.DISPLAY_FIELD;
	}
	
	@Override
	public Field getTypeIdField()
	{
		return SimpleField.REQ_TYPE_FIELD;
	}
	
	@Override
	public Set<? extends Type<T>> getSubTypes()
	{
		return new LinkedHashSet<>();
	}
	
	@Override
	public Set<? extends Template<T>> getTemplates()
	{
		return new LinkedHashSet<>();
	}
	
	@Override
	public Query<T> getQuery()
	{
		return null;
	}
	
	@Override
	public Map<? extends String, ? extends Image> getImages()
	{
		Map<String, Image> result = new LinkedHashMap<>();
		
		try
		{
			URL url = new URL("http://sampleserver3.arcgisonline.com/ArcGIS/rest/services/SanFrancisco/311Incidents/FeatureServer/0/images/1DD4FC53");
			result.put("1DD4FC53", ImageIO.read(url));
		}
		catch (IOException e)
		{
		}
		
		return result;
	}
}