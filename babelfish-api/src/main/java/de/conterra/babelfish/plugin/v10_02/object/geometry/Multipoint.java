package de.conterra.babelfish.plugin.v10_02.object.geometry;

import com.vividsolutions.jts.geom.Coordinate;
import de.conterra.babelfish.util.GeoUtils;
import lombok.extern.slf4j.Slf4j;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.*;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.aggregate.MultiPoint;
import org.opengis.geometry.complex.Complex;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * defines a Multipoint
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class Multipoint
		extends GeometryObject
		implements Cloneable, MultiPoint {
	/**
	 * the {@link MultiPoint}
	 *
	 * @since 0.1.0
	 */
	private final MultiPoint multiPoint;
	
	/**
	 * constructor, with given {@link MultiPoint}
	 *
	 * @param multiPoint the {@link MultiPoint}
	 * @since 0.1.0
	 */
	public Multipoint(MultiPoint multiPoint) {
		this.multiPoint = multiPoint;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof MultiPoint && this.multiPoint.equals(obj);
	}
	
	@Override
	public String toString() {
		return this.multiPoint.toString();
	}
	
	@Override
	public Geometry clone()
	throws CloneNotSupportedException {
		return this.multiPoint.clone();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem() {
		return this.multiPoint.getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope() {
		return this.multiPoint.getEnvelope();
	}
	
	@Override
	public com.vividsolutions.jts.geom.Geometry toGeometry(CoordinateReferenceSystem crs) {
		List<Coordinate> coords        = new LinkedList<>();
		List<Coordinate> untransCoords = new LinkedList<>();
		
		boolean trans = true;
		for (Point point : this.getElements()) {
			DirectPosition pos = point.getDirectPosition();
			
			untransCoords.add(GeoUtils.getJtsCoordinate(pos));
			
			if (trans) {
				try {
					coords.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(pos, crs)));
				} catch (TransformException e) {
					log.warn("A coordinate of the multi point couldn't transformed to target CRS! Using untransformed coordinates instead.", e);
					trans = false;
				}
			}
		}
		if (!trans) {
			coords = untransCoords;
		}
		
		return JTSFactoryFinder.getGeometryFactory().createMultiPoint(coords.toArray(new Coordinate[coords.size()]));
	}
	
	@Override
	public boolean contains(DirectPosition arg0) {
		return this.multiPoint.contains(arg0);
	}
	
	@Override
	public boolean contains(TransfiniteSet arg0) {
		return this.multiPoint.contains(arg0);
	}
	
	@Override
	public TransfiniteSet difference(TransfiniteSet arg0) {
		return this.multiPoint.difference(arg0);
	}
	
	@Override
	public double distance(Geometry arg0) {
		return this.multiPoint.distance(arg0);
	}
	
	@Override
	public boolean equals(TransfiniteSet arg0) {
		return this.multiPoint.equals(arg0);
	}
	
	@Override
	public Boundary getBoundary() {
		return this.multiPoint.getBoundary();
	}
	
	@Override
	public Geometry getBuffer(double arg0) {
		return this.multiPoint.getBuffer(arg0);
	}
	
	@Override
	public DirectPosition getCentroid() {
		return this.multiPoint.getCentroid();
	}
	
	@Override
	public Complex getClosure() {
		return this.multiPoint.getClosure();
	}
	
	@Override
	public Geometry getConvexHull() {
		return this.multiPoint.getConvexHull();
	}
	
	@Override
	public int getCoordinateDimension() {
		return this.multiPoint.getCoordinateDimension();
	}
	
	@Override
	public int getDimension(DirectPosition arg0) {
		return this.multiPoint.getDimension(arg0);
	}
	
	@Override
	public Set<Point> getElements() {
		return this.multiPoint.getElements();
	}
	
	@Override
	public Set<? extends Complex> getMaximalComplex() {
		return this.multiPoint.getMaximalComplex();
	}
	
	@Override
	public Geometry getMbRegion() {
		return this.multiPoint.getMbRegion();
	}
	
	@Override
	public Precision getPrecision() {
		return this.multiPoint.getPrecision();
	}
	
	@Override
	public DirectPosition getRepresentativePoint() {
		return this.multiPoint.getRepresentativePoint();
	}
	
	@Override
	public TransfiniteSet intersection(TransfiniteSet arg0) {
		return this.multiPoint.intersection(arg0);
	}
	
	@Override
	public boolean intersects(TransfiniteSet arg0) {
		return this.multiPoint.intersects(arg0);
	}
	
	@Override
	public boolean isCycle() {
		return this.multiPoint.isCycle();
	}
	
	@Override
	public boolean isMutable() {
		return this.multiPoint.isMutable();
	}
	
	@Override
	public boolean isSimple() {
		return this.multiPoint.isSimple();
	}
	
	@Override
	public TransfiniteSet symmetricDifference(TransfiniteSet arg0) {
		return this.multiPoint.symmetricDifference(arg0);
	}
	
	@Override
	public Geometry toImmutable() {
		return this.multiPoint.toImmutable();
	}
	
	@Override
	public Geometry transform(CoordinateReferenceSystem arg0, MathTransform arg1)
	throws TransformException {
		return this.multiPoint.transform(arg0, arg1);
	}
	
	@Override
	public Geometry transform(CoordinateReferenceSystem arg0)
	throws TransformException {
		return this.multiPoint.transform(arg0);
	}
	
	@Override
	public TransfiniteSet union(TransfiniteSet arg0) {
		return this.multiPoint.union(arg0);
	}
}
