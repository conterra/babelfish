package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.LinkedList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.coordinate.LineSegment;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.ParamForPoint;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveBoundary;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a {@link MultiLine} only with one {@link LineString}<br>
 * A {@link Polyline} without gaps
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class Polyline
extends MultiLine
implements LineString
{
	/**
	 * constructor, with given {@link LineString}
	 * 
	 * @since 0.1
	 * 
	 * @param line the {@link LineString}
	 */
	public Polyline(LineString line)
	{
		super.addLine(line);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof LineString && super.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return this.getLine().toString();
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		List<Coordinate> coords = new LinkedList<>();
		
		for (Position pos : this.getControlPoints())
		{
			try
			{
				coords.add(GeoUtils.getJtsCoordinate(GeoUtils.transform(pos.getDirectPosition(), crs)));
			}
			catch (TransformException e)
			{
			}
		}
		
		return JTSFactoryFinder.getGeometryFactory().createLineString(coords.toArray(new Coordinate[coords.size()]));
	}
	
	@Override
	@Deprecated
	public boolean addLine(LineString line)
	{
		return false;
	}
	
	@Override
	@Deprecated
	public boolean removeLine(LineString line)
	{
		return false;
	}
	
	@Override
	public List<LineSegment> asLineSegments()
	{
		return this.getLine().asLineSegments();
	}
	
	@Override
	public LineString asLineString(double arg0, double arg1)
	{
		return this.getLine().asLineString(arg0, arg1);
	}
	
	@Override
	public DirectPosition forConstructiveParam(double arg0)
	{
		return this.getLine().forConstructiveParam(arg0);
	}
	
	@Override
	public DirectPosition forParam(double arg0)
	{
		return this.getLine().forParam(arg0);
	}
	
	@Override
	public CurveBoundary getBoundary()
	{
		return this.getLine().getBoundary();
	}
	
	@Override
	public Curve getCurve()
	{
		return this.getLine().getCurve();
	}
	
	@Override
	public double getEndConstructiveParam()
	{
		return this.getLine().getEndConstructiveParam();
	}
	
	@Override
	public double getEndParam()
	{
		return this.getLine().getEndParam();
	}
	
	@Override
	public DirectPosition getEndPoint()
	{
		return this.getLine().getEndPoint();
	}
	
	@Override
	public CurveInterpolation getInterpolation()
	{
		return this.getLine().getInterpolation();
	}
	
	@Override
	public int getNumDerivativesAtEnd()
	{
		return this.getLine().getNumDerivativesAtEnd();
	}
	
	@Override
	public int getNumDerivativesAtStart()
	{
		return this.getLine().getNumDerivativesAtStart();
	}
	
	@Override
	public int getNumDerivativesInterior()
	{
		return this.getLine().getNumDerivativesInterior();
	}
	
	@Override
	public ParamForPoint getParamForPoint(DirectPosition arg0)
	{
		return this.getLine().getParamForPoint(arg0);
	}
	
	@Override
	public PointArray getSamplePoints()
	{
		return this.getLine().getSamplePoints();
	}
	
	@Override
	public double getStartConstructiveParam()
	{
		return this.getLine().getStartConstructiveParam();
	}
	
	@Override
	public double getStartParam()
	{
		return this.getLine().getStartParam();
	}
	
	@Override
	public DirectPosition getStartPoint()
	{
		return this.getLine().getStartPoint();
	}
	
	@Override
	public double[] getTangent(double arg0)
	{
		return this.getLine().getTangent(arg0);
	}
	
	@Override
	public double length(double arg0, double arg1)
	{
		return this.getLine().length(arg0, arg1);
	}
	
	@Override
	public double length(Position arg0, Position arg1)
	{
		return this.getLine().length(arg0, arg1);
	}
	
	@Override
	public CurveSegment reverse()
	{
		return this.getLine().reverse();
	}
	
	/**
	 * gives the {@link LineString}
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link LineString}
	 */
	private LineString getLine()
	{
		return this.getLines().iterator().next();
	}
}