package de.conterra.babelfish.plugin.v10_02.object.geometry;

import de.conterra.babelfish.util.GeoUtils;
import de.conterra.babelfish.util.MathUtils;
import org.opengis.geometry.Geometry;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

/**
 * defines a Geometry Object
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/geometry.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public abstract class GeometryObject {
	/**
	 * gives the ESRI type
	 *
	 * @param type the {@link GeometryObject} class to get the type of
	 * @return the ESRI type or {@code null}, if the type is unknown
	 *
	 * @since 0.1.0
	 */
	public static String getType(Class<? extends GeometryObject> type) {
		String result = "esriGeometry";
		
		if (Point.class.isAssignableFrom(type))
			result += "Point";
		else if (Polyline.class.isAssignableFrom(type))
			result += "Polyline";
		else if (Polygon.class.isAssignableFrom(type))
			result += "Polygon";
		else if (Multipoint.class.isAssignableFrom(type))
			result += "Multipoint";
		else if (Envelope.class.isAssignableFrom(type))
			result += "Envelope";
		else
			return null;
		
		return result;
	}
	
	/**
	 * gives the {@link CoordinateReferenceSystem} of this complete {@link GeometryObject}
	 *
	 * @return the {@link CoordinateReferenceSystem}
	 *
	 * @since 0.1.0
	 */
	public abstract CoordinateReferenceSystem getCoordinateReferenceSystem();
	
	/**
	 * gives the {@link org.opengis.geometry.Envelope}, which contains this {@link Geometry}
	 *
	 * @return the {@link org.opengis.geometry.Envelope} of this {@link Geometry}
	 *
	 * @since 0.1.0
	 */
	public abstract org.opengis.geometry.Envelope getEnvelope();
	
	/**
	 * converts this {@link GeometryObject} to a JTS {@link com.vividsolutions.jts.geom.Geometry}
	 *
	 * @param crs the {@link CoordinateReferenceSystem} to transform the result
	 * @return the equivalent {@link com.vividsolutions.jts.geom.Geometry} of this or {@code null}, if an error occurred on converting
	 *
	 * @since 0.1.0
	 */
	public abstract com.vividsolutions.jts.geom.Geometry toGeometry(CoordinateReferenceSystem crs);
	
	/**
	 * converts this {@link GeometryObject} to a JTS {@link com.vividsolutions.jts.geom.Geometry} with its own {@link CoordinateReferenceSystem}
	 *
	 * @return the equivalent {@link com.vividsolutions.jts.geom.Geometry} of this or {@code null}, if an error occurred on converting
	 *
	 * @since 0.1.0
	 */
	public com.vividsolutions.jts.geom.Geometry toGeometry() {
		return this.toGeometry(this.getCoordinateReferenceSystem());
	}
	
	/**
	 * checks, if this spatially contains another {@link GeometryObject}
	 *
	 * @param o the other {@link GeometryObject}
	 * @return {@code true}, if this contains {@code o}
	 *
	 * @since 0.1.0
	 */
	public boolean overlaps(GeometryObject o) {
		if (o == null || o instanceof SpatialReference)
			return false;
		else if (this instanceof Envelope || o instanceof Envelope) {
			if (o instanceof Envelope && this instanceof Envelope) {
				try {
					CoordinateReferenceSystem crs = this.getCoordinateReferenceSystem();
					
					Envelope thisEnv = (Envelope) this;
					Envelope otherEnv = (Envelope) o;
					
					double[] l1 = GeoUtils.transform(thisEnv.getLowerCorner(), crs).getCoordinate();
					double[] u1 = GeoUtils.transform(thisEnv.getUpperCorner(), crs).getCoordinate();
					double[] l2 = GeoUtils.transform(otherEnv.getLowerCorner(), crs).getCoordinate();
					double[] u2 = GeoUtils.transform(otherEnv.getUpperCorner(), crs).getCoordinate();
					
					int dim = MathUtils.min(new Integer[]
							{
									l1.length,
									u1.length,
									l2.length,
									u2.length,
							}).intValue();
					
					for (int i = 0; i < dim; i++) {
						if ((u1[i] < u2[i]
								&& u1[i] < l2[i])
								||
								(u2[i] < u1[i]
										&& u2[i] < l1[i]))
							return false;
					}
					
					return true;
				} catch (TransformException e) {
				}
			} else
				return new Envelope(this.getEnvelope()).overlaps(new Envelope(o.getEnvelope()));
		}
		
		if (!((new Envelope(this.getEnvelope())).overlaps(o)))
			return false;
		
		com.vividsolutions.jts.geom.Geometry thisGeo = this.toGeometry();
		com.vividsolutions.jts.geom.Geometry otherGeo =
				o.toGeometry(this.getCoordinateReferenceSystem());
		
		if (thisGeo == null || otherGeo == null)
			return false;
		
		return thisGeo.overlaps(otherGeo);
	}
}