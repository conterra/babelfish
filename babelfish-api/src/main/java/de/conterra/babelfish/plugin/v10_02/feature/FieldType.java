package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import org.jdom.Document;
import org.joda.time.DateTime;

/**
 * defines types of {@link Field}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://resources.esri.com/help/9.3/arcgisserver/adf/java/help/api/arcgiswebservices/com/esri/arcgisws/EsriFieldType.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum FieldType {
	/**
	 * an {@link Integer}
	 *
	 * @since 0.1.0
	 */
	Integer,
	/**
	 * an {@link Integer} of 2 {@link Byte}s
	 *
	 * @since 0.1.0
	 */
	SmallInteger,
	/**
	 * a {@link Double}
	 *
	 * @since 0.1.0
	 */
	Double,
	/**
	 * a {@link Double} only with 32 bits
	 *
	 * @since 0.1.0
	 */
	Single,
	/**
	 * a {@link String}
	 *
	 * @since 0.1.0
	 */
	String,
	/**
	 * a {@link DateTime}
	 *
	 * @since 0.1.0
	 */
	Date,
	/**
	 * a {@link GeometryObject}
	 *
	 * @since 0.1.0
	 */
	Geometry,
	/**
	 * a unique {@link Object} identifier
	 *
	 * @since 0.1.0
	 */
	OID,
	/**
	 * a {@link Byte} array of various data
	 *
	 * @since 0.1.0
	 */
	Blob,
	/**
	 * a global unique identifier
	 *
	 * @since 0.1.0
	 */
	GlobalID,
	Raster,
	/**
	 * a general unique identifier
	 *
	 * @since 0.1.0
	 */
	GUID,
	/**
	 * data packed into a valid XML {@link Document}
	 *
	 * @since 0.1.0
	 */
	XML;
	
	@Override
	public String toString() {
		return "esriFieldType" + this.name();
	}
}