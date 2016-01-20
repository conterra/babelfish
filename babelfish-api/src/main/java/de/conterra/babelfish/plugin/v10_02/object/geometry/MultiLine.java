package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.geotools.geometry.iso.coordinate.PointArrayImpl;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiLineString;

import de.conterra.babelfish.util.ContainerEnvelope;

/**
 * defines a {@link Collection} of {@link LineString}s
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class MultiLine
extends GeometryObject
{
	/**
	 * the {@link List} of {@link LineString}s
	 * 
	 * @since 0.1
	 */
	private final List<LineString> lines = new LinkedList<>();
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public MultiLine()
	{
	}
	
	/**
	 * constructor, with given {@link LineString}s
	 * 
	 * @since 0.1
	 * 
	 * @param lines the {@link Collection} of {@link LineString}s to add
	 */
	public MultiLine(Collection<? extends LineString> lines)
	{
		this();
		
		this.lines.addAll(lines);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof MultiLine)
		{
			MultiLine other = (MultiLine)obj;
			
			Set<LineString> otherLines = new HashSet<>();
			otherLines.addAll(other.getLines());
			
			Set<LineString> thisLines = new HashSet<>();
			thisLines.addAll(this.getLines());
			
			if (otherLines.size() != thisLines.size())
				return false;
			
			for (LineString otherLine : otherLines)
			{
				boolean found = false;
				
				for (LineString thisLine : thisLines)
				{
					if (otherLine.equals(thisLine))
					{
						found = true;
						break;
					}
					
					if ( !found)
						return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		String res = "[\r\n";
		
		for (LineString line : this.getLines())
			res += "\t" + line + "\r\n";
		
		res += "]";
		
		return res;
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.getControlPoints().getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope()
	{
		return new ContainerEnvelope(this.getControlPoints());
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		List<com.vividsolutions.jts.geom.LineString> jtsLines = new LinkedList<>();
		
		for (LineString line : this.getLines())
			jtsLines.add((com.vividsolutions.jts.geom.LineString) ( (new Polyline(line)).toGeometry()));
		
		return new MultiLineString(jtsLines.toArray(new com.vividsolutions.jts.geom.LineString[jtsLines.size()]), JTSFactoryFinder.getGeometryFactory());
	}
	
	/**
	 * gives the {@link Collection} of {@link LineString}s
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link Collection} of {@link LineString}s
	 */
	public Collection<? extends LineString> getLines()
	{
		return new LinkedList<>(this.lines);
	}
	
	/**
	 * adds a {@link LineString} to the {@link Collection}
	 * 
	 * @since 0.1
	 * 
	 * @param line the {@link LineString} to add
	 * @return <code>true</code>, if the {@link LineString} was successfully
	 *         added
	 */
	public boolean addLine(LineString line)
	{
		return this.lines.add(line);
	}
	
	/**
	 * removes a {@link LineString} from the {@link Collection}
	 * 
	 * @since 0.1
	 * 
	 * @param line the {@link LineString} to remove
	 * @return <code>true</code>, if the {@link Collection} had contained the
	 *         given {@link LineString}
	 */
	public boolean removeLine(LineString line)
	{
		return this.lines.remove(line);
	}
	
	/**
	 * gives a {@link PointArray} of all control {@link Point}s of all
	 * {@link LineString}s
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link PointArray} of all control {@link Point}s
	 */
	public PointArray getControlPoints()
	{
		List<Position> positions = new LinkedList<>();
		
		for (LineString line : this.getLines())
			positions.addAll(line.getControlPoints());
		
		return new PointArrayImpl(positions);
	}
}