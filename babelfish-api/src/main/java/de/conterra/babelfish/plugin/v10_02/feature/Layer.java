package de.conterra.babelfish.plugin.v10_02.feature;

import java.awt.Image;
import java.util.Map;
import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a {@link Layer}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public interface Layer<T extends FeatureObject>
{
	/**
	 * gives the unique id of the {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return the unique id
	 */
	public int getId();
	
	/**
	 * gives the name of the {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * gives a description of the {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Layer} description or <code>null</code> if none is set
	 */
	public String getDescription();
	
	/**
	 * gives a text, which define the copyright of the {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @return a copyright text of the {@link Layer} or <code>null</code> if
	 *         none set
	 */
	public String getCopyrightText();
	
	/**
	 * gives the {@link PopupType}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link PopupType}<br>
	 *         if {@link PopupType#None} this layer have no {@link Popup}s
	 */
	public PopupType getPopupType();
	
	/**
	 * gives the {@link Field} of the object id
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Field} of the object id or <code>null</code>, if the
	 *         {@link Field} should be auto generated<br>
	 *         Couldn't be <code>null</code>, if this {@link Layer} is an origin
	 *         {@link Layer} of a {@link Relationship}!
	 */
	public Field getObjectIdField();
	
	/**
	 * gives the {@link Field} of the global id
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Field} of the global id
	 */
	public Field getGlobalIdField();
	
	/**
	 * gives the display {@link Field}
	 * 
	 * @since 0.1
	 * 
	 * @return the display {@link Field}
	 */
	public Field getDisplayField();
	
	/**
	 * gives the {@link Field} of the type
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Field} of the type
	 */
	public Field getTypeIdField();
	
	/**
	 * gives a {@link Set} of all sub-types
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all sub-types or <code>null</code>, if it have
	 *         no sub-types (in this case, you need to give a {@link Set} on
	 *         {@link Layer#getTemplates()})
	 * @see Layer#getTemplates()
	 */
	public Set<? extends Type<T>> getSubTypes();
	
	/**
	 * gives a {@link Set} of all {@link Template}s, if this {@link Layer} have
	 * no sub-types
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Template}s or <code>null</code>, if
	 *         this {@link Layer} <u>has</u> sub-types
	 * @see Layer#getSubTypes()
	 */
	public Set<? extends Template<T>> getTemplates();
	
	/**
	 * gives the {@link Query}, which should be used for filtering
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Query} to use or <code>null</code>, if a
	 *         {@link DefaultQuery} should be used instead
	 */
	public Query<T> getQuery();
	
	/**
	 * gives a {@link Set} of all {@link Feature}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link Feature}s
	 * @see TimeLayer#getTimeFeatures()
	 */
	public Set<? extends Feature<T>> getFeatures();
	
	/**
	 * gives a {@link Map} of all {@link Image}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map} of all {@link Image}s
	 */
	public Map<? extends String, ? extends Image> getImages();
}