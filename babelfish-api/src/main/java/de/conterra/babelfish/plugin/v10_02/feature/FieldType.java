package de.conterra.babelfish.plugin.v10_02.feature;

import org.jdom.Document;
import org.joda.time.DateTime;

import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

/**
 * defines types of {@link Field}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://resources.esri.com/help/9.3/arcgisserver/adf/java/help/api/arcgiswebservices/com/esri/arcgisws/EsriFieldType.html">ArcGIS
 *      REST API</a>
 */
public enum FieldType
{
	/**
	 * an {@link Integer}
	 * 
	 * @since 0.1
	 */
	Integer,
	/**
	 * an {@link Integer} of 2 {@link Byte}s
	 * 
	 * @since 0.1
	 */
	SmallInteger,
	/**
	 * a {@link Double}
	 * 
	 * @since 0.1
	 */
	Double,
	/**
	 * a {@link Double} only with 32 bits
	 * 
	 * @since 0.1
	 */
	Single,
	/**
	 * a {@link String}
	 * 
	 * @since 0.1
	 */
	String,
	/**
	 * a {@link DateTime}
	 * 
	 * @since 0.1
	 */
	Date,
	/**
	 * a {@link GeometryObject}
	 * 
	 * @since 0.1
	 */
	Geometry,
	/**
	 * a unique {@link Object} identifier
	 * 
	 * @since 0.1
	 */
	OID,
	/**
	 * a {@link Byte} array of various data
	 * 
	 * @since 0.1
	 */
	Blob,
	/**
	 * a global unique identifier
	 * 
	 * @since 0.1
	 */
	GlobalID,
	Raster,
	/**
	 * a general unique identifier
	 * 
	 * @since 0.1
	 */
	GUID,
	/**
	 * data packed into a valid XML {@link Document}
	 * 
	 * @since 0.1
	 */
	XML;
	
	@Override
	public String toString()
	{
		return "esriFieldType" + this.name();
	}
}