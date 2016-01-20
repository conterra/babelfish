package de.conterra.babelfish.plugin.v10_02.object.feature;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conterra.babelfish.plugin.v10_02.feature.Field;

/**
 * defines a Feature Object
 * 
 * @version 0.3
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/feature.html">ArcGIS
 *      REST API</a>
 */
public class FeatureObject
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.3
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(FeatureObject.class);
	
	/**
	 * the {@link Map} of attributes
	 * 
	 * @since 0.1
	 */
	private final LinkedHashMap<Field, Object> attributes = new LinkedHashMap<>();
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public FeatureObject()
	{
	}
	
	/**
	 * constructor, with given attributes
	 * 
	 * @since 0.1
	 * 
	 * @param attributes the meta-attributes
	 */
	public FeatureObject(Map<? extends Field, ? extends Object> attributes)
	{
		this.attributes.putAll(attributes);
	}
	
	/**
	 * <p>
	 * Is the given value a valid attribute type?
	 * </p>
	 * <p>
	 * Valid types are:
	 * </p>
	 * <ul>
	 * <li>{@link Number}</li>
	 * <li>{@link String}</li>
	 * <li>{@link Boolean}</li>
	 * <li>{@link DateTime}</li>
	 * </ul>
	 * 
	 * @since 0.2
	 * 
	 * @param value the attribute value to check
	 * @return <code>true</code>, if the value is a valid attribute type
	 */
	public static boolean isValidType(Object value)
	{
		return value instanceof Number || value instanceof String || value instanceof Boolean || value instanceof DateTime;
	}
	
	/**
	 * gives a {@link Map} of all attributes
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map} of all attributes
	 */
	public Map<? extends Field, ? extends Object> getAttributes()
	{
		return new LinkedHashMap<>(this.attributes);
	}
	
	/**
	 * gives an attribute by its field
	 * 
	 * @since 0.1
	 * 
	 * @param field the field of the wanted attribute
	 * @return the attribute on field <code>field</code>
	 * @see Map#get(Object)
	 */
	public Object getAttribute(Field field)
	{
		return this.getAttributes().get(field);
	}
	
	/**
	 * gives an attributes by its id
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the wanted attribute
	 * @return the attribute on field <code>id</code>
	 * @see FeatureObject#getAttribute(Field)
	 */
	public Object getAttribute(String id)
	{
		for (Field field : this.getAttributes().keySet())
		{
			String fieldName = field.getName();
			
			if (fieldName.equalsIgnoreCase(id))
			{
				FeatureObject.LOGGER.debug("Found " + field.getType() + " field with name " + fieldName);
				
				return this.getAttribute(field);
			}
		}
		
		FeatureObject.LOGGER.debug("No field with name " + id + " found!");
		return null;
	}
	
	/**
	 * special method to get attributes by <a
	 * href="http://josql.sourceforge.net/manual/accessors.html"
	 * target="_blank">JoSQL</a><br>
	 * <span title="inexpert solution">&#128163;: The signature must not be
	 * changed and the name is not descriptive!</span>
	 * <p>
	 * If the signature changes, something terrible will happen!<br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_.-^^---....,,--</code><br>
	 * <code>&nbsp;_--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--_</code>
	 * <br>
	 * <code>&lt;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt;)</code>
	 * <br>
	 * <code>|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</code>
	 * <br>
	 * <code>&nbsp;\._&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_./</code>
	 * <br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;```--.&nbsp;.&nbsp;,&nbsp;;&nbsp;.--'''</code>
	 * <br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;|&nbsp;&nbsp;&nbsp;|</code>
	 * <br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.-=||&nbsp;&nbsp;|&nbsp;|=-.</code>
	 * <br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`-=#$%&amp;%$#=-'</code><br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;;&nbsp;&nbsp;:|</code>
	 * <br>
	 * <code>&nbsp;_____.,-#%&amp;$@%#&amp;#~,._____</code>
	 * </p>
	 * 
	 * @since 0.1
	 * 
	 * @param id the field id to get the attribute value from
	 * @return the attribute value on <code>id</code>
	 */
	public String get(String id)
	{
		for (Field field : this.getAttributes().keySet())
		{
			if (field.getName().equalsIgnoreCase(id))
				return this.getAttribute(field).toString();
		}
		
		return "";
	}
	
	/**
	 * adds an attribute
	 * 
	 * @since 0.1
	 * 
	 * @param id the attributes id
	 * @param attribute the attribute to add<br>
	 *        <b>Attention</b>: Only type {@link Number}, {@link String},
	 *        {@link Boolean} and {@link DateTime} are allowed!
	 * @return the previous attribute stored with id <code>id</code>
	 * @throws IllegalArgumentException if <code>attribute</code> had a wrong
	 *         type
	 * @see Map#put(Object, Object)
	 */
	public Object addAttribute(Field id, Object attribute)
	throws IllegalArgumentException
	{
		if (attribute == null)
			return null;
		
		if (FeatureObject.isValidType(attribute))
			return this.attributes.put(id, attribute);
		
		throw new IllegalArgumentException("Only attributes of types Number, String or Boolean are supported! Type " + attribute.getClass().getCanonicalName() + " was tried!");
	}
	
	/**
	 * removes an attribute
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the attribute to remove
	 * @return the attribute, which was removed or <code>null</code>, if no
	 *         attribute was stored with id <code>id</code>
	 * @see Map#remove(Object)
	 */
	public Object removeAttribute(Field id)
	{
		return this.attributes.remove(id);
	}
	
	/**
	 * removes all attributes
	 * 
	 * @since 0.1
	 * 
	 * @see Map#clear()
	 */
	public void clearAttributes()
	{
		this.attributes.clear();
	}
}