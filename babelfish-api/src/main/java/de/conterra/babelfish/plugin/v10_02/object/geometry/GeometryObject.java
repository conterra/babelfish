package de.conterra.babelfish.plugin.v10_02.object.geometry;

import de.conterra.babelfish.util.GeoUtils;
import de.conterra.babelfish.util.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Geometry;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

/**
 * defines a Geometry Object
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/geometry.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
@Slf4j
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
		
		if (Point.class.isAssignableFrom(type)) {
			result += "Point";
		} else if (Polyline.class.isAssignableFrom(type)) {
			result += "Polyline";
		} else if (Polygon.class.isAssignableFrom(type)) {
			result += "Polygon";
		} else if (Multipoint.class.isAssignableFrom(type)) {
			result += "Multipoint";
		} else if (Envelope.class.isAssignableFrom(type)) {
			result += "Envelope";
		} else {
			return null;
		}
		
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
		if (o == null || o instanceof SpatialReference) {
			return false;
		} else if (this instanceof Envelope || o instanceof Envelope) {
			if (this instanceof Envelope && o instanceof Envelope) {
				CoordinateReferenceSystem crs = this.getCoordinateReferenceSystem();
				
				Envelope thisEnv  = (Envelope) this;
				Envelope otherEnv = (Envelope) o;
				
				DirectPosition[] positions;
				
				try {
					positions = GeoUtils.transformAll(new DirectPosition[]{
							thisEnv.getLowerCorner(),
							thisEnv.getUpperCorner(),
							otherEnv.getLowerCorner(),
							otherEnv.getUpperCorner()
					}, crs);
				} catch (TransformException e) {
					log.debug("Couldn't transform all corners of both envelopes to CRS: " + crs, e);
					
					crs = otherEnv.getCoordinateReferenceSystem();
					
					try {
						positions = GeoUtils.transformAll(new DirectPosition[]{
								thisEnv.getLowerCorner(),
								thisEnv.getUpperCorner(),
								otherEnv.getLowerCorner(),
								otherEnv.getUpperCorner()
						}, crs);
					} catch (TransformException e1) {
						log.warn("Couldn't transform all corners of both envelopes to the same CRS!", e);
						
						positions = null;
					}
				}
				
				if (positions != null) {
					double[] lT = positions[0].getCoordinate();
					double[] uT = positions[1].getCoordinate();
					double[] lO = positions[2].getCoordinate();
					double[] uO = positions[3].getCoordinate();
					
					int dim = MathUtils.min(new Integer[]
							                        {
									                        lT.length,
									                        uT.length,
									                        lO.length,
									                        uO.length
							                        }).intValue();
					
					for (int i = 0; i < dim; i++) {
						if ((
								    uT[i] < uO[i]
								    && uT[i] < lO[i]
						    ) || (
								    uO[i] < uT[i]
								    && uO[i] < lT[i]
						    )) {
							return false;
						}
					}
					
					return true;
				}
			} else {
				return new Envelope(this.getEnvelope()).overlaps(new Envelope(o.getEnvelope()));
			}
		}
		
		if (!((new Envelope(this.getEnvelope())).overlaps(o))) {
			return false;
		}
		
		com.vividsolutions.jts.geom.Geometry thisGeo  = this.toGeometry();
		com.vividsolutions.jts.geom.Geometry otherGeo = o.toGeometry(this.getCoordinateReferenceSystem());
		
		if (thisGeo == null || otherGeo == null) {
			return false;
		}
		
		return thisGeo.overlaps(otherGeo);
	}
}
