package de.conterra.babelfish.plugin.v10_02.object.geometry;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiLineString;
import de.conterra.babelfish.util.ContainerEnvelope;
import org.geotools.geometry.iso.coordinate.PointArrayImpl;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.*;

/**
 * defines a {@link Collection} of {@link LineString}s
 *
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.1.0
 */
public class MultiLine
		extends GeometryObject {
	/**
	 * the {@link List} of {@link LineString}s
	 *
	 * @since 0.1.0
	 */
	private final List<LineString> lines = new LinkedList<>();
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public MultiLine() {
	}
	
	/**
	 * constructor, with given {@link LineString}s
	 *
	 * @param lines the {@link Collection} of {@link LineString}s to add
	 * @since 0.1.0
	 */
	public MultiLine(Collection<? extends LineString> lines) {
		this();
		
		this.lines.addAll(lines);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MultiLine) {
			MultiLine other = (MultiLine) obj;
			
			Set<LineString> otherLines = new HashSet<>();
			otherLines.addAll(other.getLines());
			
			Set<LineString> thisLines = new HashSet<>();
			thisLines.addAll(this.getLines());
			
			if (otherLines.size() != thisLines.size()) {
				return false;
			}
			
			for (LineString otherLine : otherLines) {
				boolean found = false;
				
				for (LineString thisLine : thisLines) {
					if (otherLine.equals(thisLine)) {
						found = true;
						break;
					}
					
					if (!found) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[\r\n");
		for (LineString line : this.getLines()) {
			builder.append("\t" + line + "\r\n");
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem() {
		return this.getControlPoints().getCoordinateReferenceSystem();
	}
	
	@Override
	public Envelope getEnvelope() {
		return new ContainerEnvelope(this.getControlPoints());
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs) {
		List<com.vividsolutions.jts.geom.LineString> jtsLines = new LinkedList<>();
		
		for (LineString line : this.getLines()) {
			jtsLines.add((com.vividsolutions.jts.geom.LineString) ((new Polyline(line)).toGeometry()));
		}
		
		return new MultiLineString(jtsLines.toArray(new com.vividsolutions.jts.geom.LineString[jtsLines.size()]), JTSFactoryFinder.getGeometryFactory());
	}
	
	/**
	 * gives the {@link Collection} of {@link LineString}s
	 *
	 * @return the {@link Collection} of {@link LineString}s
	 *
	 * @since 0.1.0
	 */
	public Collection<? extends LineString> getLines() {
		return new LinkedList<>(this.lines);
	}
	
	/**
	 * adds a {@link LineString} to the {@link Collection}
	 *
	 * @param line the {@link LineString} to add
	 * @return {@code true}, if the {@link LineString} was successfully added
	 *
	 * @since 0.1.0
	 */
	public boolean addLine(LineString line) {
		return this.lines.add(line);
	}
	
	/**
	 * removes a {@link LineString} from the {@link Collection}
	 *
	 * @param line the {@link LineString} to remove
	 * @return {@code true}, if the {@link Collection} had contained the given {@link LineString}
	 *
	 * @since 0.1.0
	 */
	public boolean removeLine(LineString line) {
		return this.lines.remove(line);
	}
	
	/**
	 * gives a {@link PointArray} of all control {@link Point}s of all {@link LineString}s
	 *
	 * @return a {@link PointArray} of all control {@link Point}s
	 *
	 * @since 0.1.0
	 */
	public PointArray getControlPoints() {
		List<Position> positions = new LinkedList<>();
		
		for (LineString line : this.getLines()) {
			positions.addAll(line.getControlPoints());
		}
		
		return new PointArrayImpl(positions);
	}
}
