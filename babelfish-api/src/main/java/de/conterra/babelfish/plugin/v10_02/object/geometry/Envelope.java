package de.conterra.babelfish.plugin.v10_02.object.geometry;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import de.conterra.babelfish.util.GeoUtils;
import de.conterra.babelfish.util.MathUtils;

/**
 * defines a {@link Envelope}
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class Envelope
extends GeometryObject
implements org.opengis.geometry.Envelope
{
	/**
	 * the {@link org.opengis.geometry.Envelope}
	 * 
	 * @since 0.1
	 */
	private final org.opengis.geometry.Envelope envelope;
	
	/**
	 * constructor, with given {@link org.opengis.geometry.Envelope}
	 * 
	 * @since 0.1
	 * 
	 * @param envelope the {@link org.opengis.geometry.Envelope}
	 */
	public Envelope(org.opengis.geometry.Envelope envelope)
	{
		this.envelope = envelope;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof org.opengis.geometry.Envelope && this.getEnvelope().equals(obj);
	}
	
	@Override
	public String toString()
	{
		return this.envelope.toString();
	}
	
	@Override
	public org.opengis.geometry.Envelope getEnvelope()
	{
		return this.envelope;
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.envelope.getCoordinateReferenceSystem();
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		try
		{
			double[] lower = GeoUtils.transform(this.getLowerCorner(), crs).getCoordinate();
			double[] upper = GeoUtils.transform(this.getUpperCorner(), crs).getCoordinate();
			
			Coordinate[] coords = new Coordinate[]
			{
			new Coordinate(lower[0], lower[1]),
			new Coordinate(lower[0], upper[1]),
			new Coordinate(upper[0], upper[1]),
			new Coordinate(upper[0], lower[1]),
			new Coordinate(lower[0], lower[1]),
			};
			
			GeometryFactory factory = JTSFactoryFinder.getGeometryFactory();
			
			return factory.createPolygon(factory.createLinearRing(coords), null);
		}
		catch (TransformException e)
		{
		}
		
		return null;
	}
	
	@Override
	public int getDimension()
	{
		return this.envelope.getDimension();
	}
	
	@Override
	public DirectPosition getLowerCorner()
	{
		return this.envelope.getLowerCorner();
	}
	
	@Override
	public double getMaximum(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.envelope.getMaximum(arg0);
	}
	
	@Override
	public double getMedian(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.envelope.getMedian(arg0);
	}
	
	@Override
	public double getMinimum(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.envelope.getMinimum(arg0);
	}
	
	@Override
	public double getSpan(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.envelope.getSpan(arg0);
	}
	
	@Override
	public DirectPosition getUpperCorner()
	{
		return this.envelope.getUpperCorner();
	}
	
	/**
	 * checks, if this {@link org.opengis.geometry.Envelope} contains another
	 * one
	 * 
	 * @since 0.3
	 * 
	 * @param other the {@link org.opengis.geometry.Envelope} to check, if this
	 *        one contains it
	 * @return <code>true</code>, if this {@link org.opengis.geometry.Envelope}
	 *         contains the specific one completely
	 * 
	 * @see Envelope#isIn(org.opengis.geometry.Envelope)
	 */
	public boolean contains(org.opengis.geometry.Envelope other)
	{
		try
		{
			CoordinateReferenceSystem crs = this.getCoordinateReferenceSystem();
			
			double[] l1 = this.getLowerCorner().getCoordinate();
			double[] u1 = this.getUpperCorner().getCoordinate();
			double[] l2 = GeoUtils.transform(other.getLowerCorner(), crs).getCoordinate();
			double[] u2 = GeoUtils.transform(other.getUpperCorner(), crs).getCoordinate();
			
			int dim = MathUtils.min(new Integer[]
			{
			l1.length,
			u1.length,
			l2.length,
			u2.length,
			}).intValue();
			
			for (int i = 0; i < dim; i++)
			{
				if (l2[i] < l1[i] || u2[i] > u1[i])
					return false;
			}
		}
		catch (TransformException e)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * checks, if this {@link Envelope} is in another one
	 * 
	 * @since 0.3
	 * 
	 * @param other the super {@link org.opengis.geometry.Envelope}
	 * @return <code>true</code>, if the given
	 *         {@link org.opengis.geometry.Envelope} contains this one
	 *         completely
	 * 
	 * @see Envelope#contains(org.opengis.geometry.Envelope)
	 */
	public boolean isIn(org.opengis.geometry.Envelope other)
	{
		Envelope otherEnv;
		
		if (other instanceof Envelope)
			otherEnv = (Envelope)other;
		else
			otherEnv = new Envelope(other);
		
		return otherEnv.contains(this);
	}
}