package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.PolyhedralSurface;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.geometry.primitive.SurfaceInterpolation;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;

import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a {@link Polygon}
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class Polygon
extends GeometryObject
implements org.opengis.geometry.coordinate.Polygon
{
	/**
	 * the {@link org.opengis.geometry.coordinate.Polygon}
	 * 
	 * @since 0.1
	 */
	private final org.opengis.geometry.coordinate.Polygon polygon;
	
	/**
	 * constructor, with given {@link org.opengis.geometry.coordinate.Polygon}
	 * 
	 * @since 0.1
	 * 
	 * @param polygon the {@link org.opengis.geometry.coordinate.Polygon}
	 */
	public Polygon(org.opengis.geometry.coordinate.Polygon polygon)
	{
		this.polygon = polygon;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof org.opengis.geometry.coordinate.Polygon && this.polygon.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return this.polygon.toString();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.getBoundary().getRepresentativePoint().getDirectPosition().getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope()
	{
		return this.getBoundary().getEnvelope();
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		GeometryFactory factory = JTSFactoryFinder.getGeometryFactory();
		
		List<Coordinate> coords = new LinkedList<>();
		
		for (Point point : this.getExteriorPoints())
		{
			try
			{
				coords.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(point.getDirectPosition(), crs)));
			}
			catch (TransformException e)
			{
			}
		}
		if (coords.size() > 1)
			coords.add(coords.get(0));
		
		LinearRing exterior = factory.createLinearRing(coords.toArray(new Coordinate[coords.size()]));
		
		List<LinearRing> interior = new LinkedList<>();
		
		for (Collection<? extends Point> ring : this.getInteriorPoints())
		{
			coords = new LinkedList<>();
			
			for (Point point : ring)
			{
				try
				{
					coords.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(point.getDirectPosition(), crs)));
				}
				catch (TransformException e)
				{
				}
			}
			if (coords.size() > 1)
				coords.add(coords.get(0));
			
			interior.add(factory.createLinearRing(coords.toArray(new Coordinate[coords.size()])));
		}
		
		return factory.createPolygon(exterior, interior.toArray(new LinearRing[interior.size()]));
	}
	
	@Override
	public double getArea()
	{
		return this.polygon.getArea();
	}
	
	@Override
	public SurfaceBoundary getBoundary()
	{
		return this.polygon.getBoundary();
	}
	
	@Override
	public SurfaceInterpolation getInterpolation()
	{
		return this.polygon.getInterpolation();
	}
	
	@Override
	public int getNumDerivativesOnBoundary()
	{
		return this.polygon.getNumDerivativesOnBoundary();
	}
	
	@Override
	public double getPerimeter()
	{
		return this.polygon.getPerimeter();
	}
	
	@Override
	public List<Surface> getSpanningSurface()
	{
		return this.polygon.getSpanningSurface();
	}
	
	@Override
	public PolyhedralSurface getSurface()
	{
		return this.polygon.getSurface();
	}
	
	@Override
	public double[] getUpNormal(DirectPosition arg0)
	{
		return this.polygon.getUpNormal(arg0);
	}
	
	/**
	 * gives a {@link Collection} of all control {@link Point}s of the exterior
	 * {@link Ring}
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Collection} of all control {@link Point}s of the
	 *         exterior {@link Ring}
	 */
	public Collection<? extends Point> getExteriorPoints()
	{
		List<Point> result = new LinkedList<>();
		
		for (OrientableCurve curve : this.getBoundary().getExterior().getGenerators())
			result.add(curve.getBoundary().getStartPoint());
		
		return result;
	}
	
	/**
	 * gives a {@link Collection} of all interior {@link Ring}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Collection} of all interior {@link Ring}s
	 */
	public Collection<? extends Collection<? extends Point>> getInteriorPoints()
	{
		List<List<Point>> result = new LinkedList<>();
		
		for (Ring ring : this.getBoundary().getInteriors())
		{
			List<Point> pts = new LinkedList<>();
			
			for (OrientableCurve curve : ring.getGenerators())
				pts.add(curve.getBoundary().getStartPoint());
			
			result.add(pts);
		}
		
		return result;
	}
	
	/**
	 * gives the minimum dimension of all control {@link Point}s
	 * 
	 * @since 0.1
	 * 
	 * @return the dimension
	 */
	public int getDimension()
	{
		int result = Integer.MAX_VALUE;
		
		List<Collection<? extends Point>> points = new LinkedList<>();
		
		points.add(this.getExteriorPoints());
		points.addAll(this.getInteriorPoints());
		
		for (Collection<? extends Point> ring : points)
		{
			for (Point point : ring)
			{
				int dim = point.getDirectPosition().getDimension();
				
				if (dim < result)
					result = dim;
				
				if (result <= 0)
					return 0;
			}
		}
		
		if (result == Integer.MAX_VALUE)
			return 0;
		
		return result;
	}
}