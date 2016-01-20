package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.geotools.referencing.CRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a class, which creates {@link ObjectValue}s of {@link GeometryObject}
 * s
 * 
 * @version 0.2
 * @author chwe
 * @since 0.1
 */
public class GeometryBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private GeometryBuilder()
	{
	}
	
	/**
	 * creates an {@link ArrayValue} of a ordinate pair
	 * 
	 * @since 0.1
	 * 
	 * @param pos the {@link Position} to get the coordinate from
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        given {@link Position} should be used
	 * @return the {@link ArrayValue}, which contains the coordinate
	 */
	private static ArrayValue buildCoords(Position pos, CoordinateReferenceSystem crs)
	{
		ArrayValue result = new ArrayValue();
		
		DirectPosition dirPos = pos.getDirectPosition();
		DirectPosition destPos = dirPos;
		if (crs != null && ! (crs.equals(dirPos.getCoordinateReferenceSystem())))
		{
			try
			{
				destPos = CRS.findMathTransform(dirPos.getCoordinateReferenceSystem(), crs).transform(dirPos, null);
			}
			catch (MismatchedDimensionException | TransformException | FactoryException e)
			{
			}
		}
		result.addValue(new NumberValue(destPos.getOrdinate(0)));
		result.addValue(new NumberValue(destPos.getOrdinate(1)));
		
		if (destPos.getDimension() >= 3)
			result.addValue(new NumberValue(destPos.getOrdinate(2)));
		
		return result;
	}
	
	/**
	 * creates an {@link ArrayValue}, which contains a list of coordinates
	 * 
	 * @since 0.1
	 * 
	 * @param positions the {@link Collection} of {@link Position}s to add to
	 *        the {@link ArrayValue}
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        given {@link Position} should be used
	 * @return an {@link ArrayValue}, which contains {@link ArrayValue}s of all
	 *         coordinates of <code>positions</code>
	 */
	private static ArrayValue buildCoords(Collection<? extends Position> positions, CoordinateReferenceSystem crs)
	{
		ArrayValue result = new ArrayValue();
		
		for (Position pos : positions)
			result.addValue(GeometryBuilder.buildCoords(pos, crs));
		
		return result;
	}
	
	/**
	 * creates an {@link ObjectValue} of a given {@link GeometryObject}
	 * 
	 * @since 0.1
	 * 
	 * @param geometry the {@link GeometryObject} to build
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        given {@link GeometryObject} should be used
	 * @return the created {@link ObjectValue}
	 */
	public static ObjectValue build(GeometryObject geometry, CoordinateReferenceSystem crs)
	{
		ObjectValue result = new ObjectValue();
		
		CoordinateReferenceSystem spatial;
		if (crs == null)
			spatial = geometry.getCoordinateReferenceSystem();
		else
			spatial = crs;
		
		if (geometry instanceof SpatialReference)
		{
			int epsg = GeoUtils.decodeEpsg(spatial);
			
			if (epsg > 0)
				result.addContent("wkid", new NumberValue(epsg));
			else
				result.addContent("wkt", new StringValue(spatial.toWKT()));
		}
		else
		{
			if (geometry instanceof Point)
			{
				Point point = (Point)geometry;
				
				DirectPosition pos = point.getDirectPosition();
				try
				{
					pos = GeoUtils.transform(pos, spatial);
				}
				catch (TransformException e)
				{
				}
				result.addContent("x", new NumberValue(pos.getOrdinate(0)));
				result.addContent("y", new NumberValue(pos.getOrdinate(1)));
				
				if (pos.getDimension() >= 3)
					result.addContent("z", new NumberValue(pos.getOrdinate(2)));
			}
			else if (geometry instanceof MultiLine)
			{
				MultiLine lines = (MultiLine)geometry;
				
				ArrayValue paths = new ArrayValue();
				for (LineString line : lines.getLines())
					paths.addValue(GeometryBuilder.buildCoords(line.getControlPoints(), crs));
				result.addContent("paths", paths);
			}
			else if (geometry instanceof Polygon)
			{
				Polygon polygon = (Polygon)geometry;
				
				List<Collection<? extends org.opengis.geometry.primitive.Point>> rings = new LinkedList<>();
				List<org.opengis.geometry.primitive.Point> exterior = new LinkedList<>(polygon.getExteriorPoints());
				exterior.add(exterior.iterator().next());
				rings.add(exterior);
				for (Collection<? extends org.opengis.geometry.primitive.Point> interPositions : polygon.getInteriorPoints())
				{
					List<org.opengis.geometry.primitive.Point> interior = new LinkedList<>(interPositions);
					interior.add(interior.iterator().next());
					rings.add(interior);
				}
				
				ArrayValue ringValue = new ArrayValue();
				for (Collection<? extends org.opengis.geometry.primitive.Point> ring : rings)
					ringValue.addValue(GeometryBuilder.buildCoords(ring, crs));
				
				result.addContent("rings", ringValue);
			}
			else if (geometry instanceof Multipoint)
			{
				Multipoint mulPoint = (Multipoint)geometry;
				
				result.addContent("points", GeometryBuilder.buildCoords(mulPoint.getElements(), crs));
			}
			else if (geometry instanceof Envelope)
			{
				Envelope env = (Envelope)geometry;
				
				DirectPosition minPos = env.getLowerCorner();
				DirectPosition maxPos = env.getUpperCorner();
				try
				{
					minPos = GeoUtils.transform(env.getLowerCorner(), spatial);
					maxPos = GeoUtils.transform(env.getUpperCorner(), spatial);
				}
				catch (TransformException e)
				{
				}
				
				result.addContent("xmin", new NumberValue(minPos.getOrdinate(0)));
				result.addContent("ymin", new NumberValue(minPos.getOrdinate(1)));
				result.addContent("xmax", new NumberValue(maxPos.getOrdinate(0)));
				result.addContent("ymax", new NumberValue(maxPos.getOrdinate(1)));
				
				if (env.getDimension() >= 3)
				{
					result.addContent("zmin", new NumberValue(minPos.getOrdinate(2)));
					result.addContent("zmax", new NumberValue(maxPos.getOrdinate(2)));
				}
			}
			
			result.addContent("spatialReference", GeometryBuilder.build(new SpatialReference(spatial), null));
		}
		
		return result;
	}
}