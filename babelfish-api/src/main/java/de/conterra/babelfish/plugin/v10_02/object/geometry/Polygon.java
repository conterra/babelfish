package de.conterra.babelfish.plugin.v10_02.object.geometry;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import de.conterra.babelfish.util.GeoUtils;
import lombok.extern.slf4j.Slf4j;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.PolyhedralSurface;
import org.opengis.geometry.primitive.*;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * defines a {@link Polygon}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class Polygon
		extends GeometryObject
		implements org.opengis.geometry.coordinate.Polygon {
	/**
	 * the {@link org.opengis.geometry.coordinate.Polygon}
	 *
	 * @since 0.1.0
	 */
	private final org.opengis.geometry.coordinate.Polygon polygon;
	
	/**
	 * constructor, with given {@link org.opengis.geometry.coordinate.Polygon}
	 *
	 * @param polygon the {@link org.opengis.geometry.coordinate.Polygon}
	 * @since 0.1.0
	 */
	public Polygon(org.opengis.geometry.coordinate.Polygon polygon) {
		this.polygon = polygon;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof org.opengis.geometry.coordinate.Polygon && this.polygon.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return this.polygon.hashCode();
	}
	
	@Override
	public String toString() {
		return this.polygon.toString();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem() {
		return this.getBoundary().getRepresentativePoint().getDirectPosition().getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope() {
		return this.getBoundary().getEnvelope();
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs) {
		List<Coordinate> coordsExterior        = new LinkedList<>();
		List<Coordinate> untransCoordsExterior = new LinkedList<>();
		
		boolean trans = true;
		for (Point point : this.getExteriorPoints()) {
			DirectPosition pos = point.getDirectPosition();
			
			untransCoordsExterior.add(GeoUtils.getJtsCoordinate(pos));
			
			if (trans) {
				try {
					coordsExterior.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(pos, crs)));
				} catch (TransformException e) {
					log.warn("A point of the polygons exterior ring couldn't transformed to target CRS! Using untransformed points instead.", e);
				}
			}
		}
		
		List<List<Coordinate>> coordsInterior        = new LinkedList<>();
		List<List<Coordinate>> untransCoordsInterior = new LinkedList<>();
		
		for (Collection<? extends Point> ring : this.getInteriorPoints()) {
			List<Coordinate> coords        = new LinkedList<>();
			List<Coordinate> untransCoords = new LinkedList<>();
			
			for (Point point : ring) {
				DirectPosition pos = point.getDirectPosition();
				untransCoords.add(GeoUtils.getJtsCoordinate(pos));
				
				if (trans) {
					try {
						coords.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(point.getDirectPosition(), crs)));
					} catch (TransformException e) {
						log.warn("A point of a polygons interior ring couldn't transformed to target CRS! Using untransformed points instead.", e);
					}
				}
			}
			
			if (untransCoords.size() > 1) {
				untransCoords.add(untransCoords.get(0));
			}
			if (coords.size() > 1) {
				coords.add(coords.get(0));
			}
			
			coordsInterior.add(coords);
			untransCoordsInterior.add(untransCoords);
		}
		
		if (!trans) {
			coordsExterior = untransCoordsExterior;
			coordsInterior = untransCoordsInterior;
		}
		
		GeometryFactory factory = JTSFactoryFinder.getGeometryFactory();
		
		if (coordsExterior.size() > 1) {
			coordsExterior.add(coordsExterior.get(0));
		}
		LinearRing exterior = factory.createLinearRing(coordsExterior.toArray(new Coordinate[coordsExterior.size()]));
		
		LinearRing[] interior = new LinearRing[coordsInterior.size()];
		for (int i = 0; i < interior.length; i++) {
			List<Coordinate> coords = coordsInterior.get(i);
			interior[i] = factory.createLinearRing(coords.toArray(new Coordinate[coords.size()]));
		}
		
		return factory.createPolygon(exterior, interior);
	}
	
	@Override
	public double getArea() {
		return this.polygon.getArea();
	}
	
	@Override
	public SurfaceBoundary getBoundary() {
		return this.polygon.getBoundary();
	}
	
	@Override
	public SurfaceInterpolation getInterpolation() {
		return this.polygon.getInterpolation();
	}
	
	@Override
	public int getNumDerivativesOnBoundary() {
		return this.polygon.getNumDerivativesOnBoundary();
	}
	
	@Override
	public double getPerimeter() {
		return this.polygon.getPerimeter();
	}
	
	@Override
	public List<Surface> getSpanningSurface() {
		return this.polygon.getSpanningSurface();
	}
	
	@Override
	public PolyhedralSurface getSurface() {
		return this.polygon.getSurface();
	}
	
	@Override
	public double[] getUpNormal(DirectPosition arg0) {
		return this.polygon.getUpNormal(arg0);
	}
	
	/**
	 * gives a {@link Collection} of all control {@link Point}s of the exterior {@link Ring}
	 *
	 * @return a {@link Collection} of all control {@link Point}s of the exterior {@link Ring}
	 *
	 * @since 0.1.0
	 */
	public Collection<? extends Point> getExteriorPoints() {
		List<Point> result = new LinkedList<>();
		
		for (OrientableCurve curve : this.getBoundary().getExterior().getGenerators()) {
			result.add(curve.getBoundary().getStartPoint());
		}
		
		return result;
	}
	
	/**
	 * gives a {@link Collection} of all interior {@link Ring}s
	 *
	 * @return a {@link Collection} of all interior {@link Ring}s
	 *
	 * @since 0.1.0
	 */
	public Collection<? extends Collection<? extends Point>> getInteriorPoints() {
		List<List<Point>> result = new LinkedList<>();
		
		for (Ring ring : this.getBoundary().getInteriors()) {
			List<Point> pts = new LinkedList<>();
			
			for (OrientableCurve curve : ring.getGenerators()) {
				pts.add(curve.getBoundary().getStartPoint());
			}
			
			result.add(pts);
		}
		
		return result;
	}
	
	/**
	 * gives the minimum dimension of all control {@link Point}s
	 *
	 * @return the dimension
	 *
	 * @since 0.1.0
	 */
	public int getDimension() {
		int result = Integer.MAX_VALUE;
		
		List<Collection<? extends Point>> points = new LinkedList<>();
		
		points.add(this.getExteriorPoints());
		points.addAll(this.getInteriorPoints());
		
		for (Collection<? extends Point> ring : points) {
			for (Point point : ring) {
				int dim = point.getDirectPosition().getDimension();
				
				if (dim < result) {
					result = dim;
				}
				
				if (result <= 0) {
					return 0;
				}
			}
		}
		
		if (result == Integer.MAX_VALUE) {
			return 0;
		}
		
		return result;
	}
}
