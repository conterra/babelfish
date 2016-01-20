package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.Set;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.Precision;
import org.opengis.geometry.TransfiniteSet;
import org.opengis.geometry.UnmodifiableGeometryException;
import org.opengis.geometry.complex.Complex;
import org.opengis.geometry.complex.Composite;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Bearing;
import org.opengis.geometry.primitive.OrientablePrimitive;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.geometry.primitive.PrimitiveBoundary;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a {@link Point}
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class Point
extends GeometryObject
implements Cloneable, org.opengis.geometry.primitive.Point
{
	/**
	 * the {@link org.opengis.geometry.primitive.Point}
	 * 
	 * @since 0.1
	 */
	private final org.opengis.geometry.primitive.Point point;
	
	/**
	 * constructor, with given {@link org.opengis.geometry.primitive.Point}
	 * 
	 * @since 0.1
	 * 
	 * @param point the {@link org.opengis.geometry.primitive.Point}
	 */
	public Point(org.opengis.geometry.primitive.Point point)
	{
		this.point = point;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof org.opengis.geometry.primitive.Point && this.point.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return this.point.toString();
	}
	
	@Override
	public Geometry clone()
	throws CloneNotSupportedException
	{
		return this.point.clone();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.point.getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope()
	{
		return this.point.getEnvelope();
	}
	
	@Override
	public com.vividsolutions.jts.geom.Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		try
		{
			return JTSFactoryFinder.getGeometryFactory().createPoint(GeoUtils.getJtsCoordinate(GeoUtils.transform(this.getDirectPosition(), crs)));
		}
		catch (TransformException e)
		{
		}
		
		return null;
	}
	
	@Override
	public boolean contains(DirectPosition arg0)
	{
		return this.point.contains(arg0);
	}
	
	@Override
	public boolean contains(TransfiniteSet arg0)
	{
		return this.point.contains(arg0);
	}
	
	@Override
	public TransfiniteSet difference(TransfiniteSet arg0)
	{
		return this.point.difference(arg0);
	}
	
	@Override
	public double distance(Geometry arg0)
	{
		return this.point.distance(arg0);
	}
	
	@Override
	public boolean equals(TransfiniteSet arg0)
	{
		return this.point.equals(arg0);
	}
	
	@Override
	public Bearing getBearing(Position arg0)
	{
		return this.point.getBearing(arg0);
	}
	
	@Override
	public PrimitiveBoundary getBoundary()
	{
		return this.point.getBoundary();
	}
	
	@Override
	public Geometry getBuffer(double arg0)
	{
		return this.point.getBuffer(arg0);
	}
	
	@Override
	public DirectPosition getCentroid()
	{
		return this.point.getCentroid();
	}
	
	@Override
	public Complex getClosure()
	{
		return this.point.getClosure();
	}
	
	@Override
	public Set<Complex> getComplexes()
	{
		return this.point.getComplexes();
	}
	
	@Override
	public Composite getComposite()
	{
		return this.point.getComposite();
	}
	
	@Override
	public Set<Primitive> getContainedPrimitives()
	{
		return this.point.getContainedPrimitives();
	}
	
	@Override
	public Set<Primitive> getContainingPrimitives()
	{
		return this.point.getContainingPrimitives();
	}
	
	@Override
	public Geometry getConvexHull()
	{
		return this.point.getConvexHull();
	}
	
	@Override
	public int getCoordinateDimension()
	{
		return this.point.getCoordinateDimension();
	}
	
	@Override
	public int getDimension(DirectPosition arg0)
	{
		return this.point.getDimension(arg0);
	}
	
	@Override
	public DirectPosition getDirectPosition()
	{
		return this.point.getDirectPosition();
	}
	
	@Override
	public Set<? extends Complex> getMaximalComplex()
	{
		return this.point.getMaximalComplex();
	}
	
	@Override
	public Geometry getMbRegion()
	{
		return this.point.getMbRegion();
	}
	
	@Override
	public Precision getPrecision()
	{
		return this.point.getPrecision();
	}
	
	@Override
	public OrientablePrimitive[] getProxy()
	{
		return this.point.getProxy();
	}
	
	@Override
	public DirectPosition getRepresentativePoint()
	{
		return this.point.getRepresentativePoint();
	}
	
	@Override
	public TransfiniteSet intersection(TransfiniteSet arg0)
	{
		return this.point.intersection(arg0);
	}
	
	@Override
	public boolean intersects(TransfiniteSet arg0)
	{
		return this.point.intersects(arg0);
	}
	
	@Override
	public boolean isCycle()
	{
		return this.point.isCycle();
	}
	
	@Override
	public boolean isMutable()
	{
		return this.point.isMutable();
	}
	
	@Override
	public boolean isSimple()
	{
		return this.point.isSimple();
	}
	
	@Override
	public void setDirectPosition(DirectPosition arg0)
	throws UnmodifiableGeometryException
	{
		this.point.setDirectPosition(arg0);
	}
	
	@Override
	public TransfiniteSet symmetricDifference(TransfiniteSet arg0)
	{
		return this.point.symmetricDifference(arg0);
	}
	
	@Override
	public Geometry toImmutable()
	{
		return this.point.toImmutable();
	}
	
	@Override
	public Geometry transform(CoordinateReferenceSystem arg0, MathTransform arg1)
	throws TransformException
	{
		return this.point.transform(arg0, arg1);
	}
	
	@Override
	public Geometry transform(CoordinateReferenceSystem arg0)
	throws TransformException
	{
		return this.point.transform(arg0);
	}
	
	@Override
	public TransfiniteSet union(TransfiniteSet arg0)
	{
		return this.point.union(arg0);
	}
}