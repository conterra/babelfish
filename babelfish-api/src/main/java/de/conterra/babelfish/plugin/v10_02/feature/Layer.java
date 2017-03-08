package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * describes a {@link Layer}
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public interface Layer<T extends FeatureObject> {
	/**
	 * gives the unique id of the {@link Layer}
	 *
	 * @return the unique id
	 *
	 * @since 0.1.0
	 */
	public int getId();
	
	/**
	 * gives the name of the {@link Layer}
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives a description of the {@link Layer}
	 *
	 * @return a {@link Layer} description or {@code null} if none is set
	 *
	 * @since 0.1.0
	 */
	public String getDescription();
	
	/**
	 * gives a text, which define the copyright of the {@link Layer}
	 *
	 * @return a copyright text of the {@link Layer} or {@code null} if none set
	 *
	 * @since 0.1.0
	 */
	public String getCopyrightText();
	
	/**
	 * gives the {@link PopupType}
	 *
	 * @return the {@link PopupType}<br>
	 * if {@link PopupType#None} this layer have no {@link Popup}s
	 *
	 * @since 0.1.0
	 */
	public PopupType getPopupType();
	
	/**
	 * gives the {@link Field} of the object id
	 *
	 * @return the {@link Field} of the object id or {@code null}, if the {@link Field} should be auto generated<br>
	 * Couldn't be {@code null}, if this {@link Layer} is an origin {@link Layer} of a {@link Relationship}!
	 *
	 * @since 0.1.0
	 */
	public Field getObjectIdField();
	
	/**
	 * gives the {@link Field} of the global id
	 *
	 * @return the {@link Field} of the global id
	 *
	 * @since 0.1.0
	 */
	public Field getGlobalIdField();
	
	/**
	 * gives the display {@link Field}
	 *
	 * @return the display {@link Field}
	 *
	 * @since 0.1.0
	 */
	public Field getDisplayField();
	
	/**
	 * gives the {@link Field} of the type
	 *
	 * @return the {@link Field} of the type
	 *
	 * @since 0.1.0
	 */
	public Field getTypeIdField();
	
	/**
	 * gives a {@link Set} of all sub-types
	 *
	 * @return a {@link Set} of all sub-types or {@code null}, if it have no sub-types (in this case, you need to give a {@link Set} on {@link Layer#getTemplates()})
	 *
	 * @see Layer#getTemplates()
	 * @since 0.1.0
	 */
	public Set<? extends Type<T>> getSubTypes();
	
	/**
	 * gives a {@link Set} of all {@link Template}s, if this {@link Layer} have no sub-types
	 *
	 * @return a {@link Set} of all {@link Template}s or {@code null}, if this {@link Layer} <u>has</u> sub-types
	 *
	 * @see Layer#getSubTypes()
	 * @since 0.1.0
	 */
	public Set<? extends Template<T>> getTemplates();
	
	/**
	 * gives the {@link Query}, which should be used for filtering
	 *
	 * @return the {@link Query} to use or {@code null}, if a {@link DefaultQuery} should be used instead
	 *
	 * @since 0.1.0
	 */
	public Query<T> getQuery();
	
	/**
	 * gives a {@link Set} of all {@link Feature}s
	 *
	 * @return a {@link Set} of all {@link Feature}s
	 *
	 * @see TimeLayer#getTimeFeatures()
	 * @since 0.1.0
	 */
	public Set<? extends Feature<T>> getFeatures();
	
	/**
	 * gives a {@link Map} of all {@link Image}s
	 *
	 * @return a {@link Map} of all {@link Image}s
	 *
	 * @since 0.1.0
	 */
	public Map<? extends String, ? extends Image> getImages();
}