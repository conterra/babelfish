package de.conterra.babelfish.plugin.v10_02.object.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.geotools.geometry.GeneralDirectPosition;
import org.geotools.geometry.iso.coordinate.DirectPositionImpl;
import org.geotools.geometry.iso.primitive.PointImpl;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.Position;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

import com.vividsolutions.jts.geom.Geometry;

import de.conterra.babelfish.util.ContainerEnvelope;

/**
 * defines a Spatial Reference
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.1
 */
public class SpatialReference
extends GeometryObject
implements CoordinateReferenceSystem
{
	/**
	 * the {@link CoordinateReferenceSystem}
	 * 
	 * @since 0.1
	 */
	private final CoordinateReferenceSystem crs;
	
	/**
	 * constructor, with given {@link CoordinateReferenceSystem}
	 * 
	 * @since 0.1
	 * 
	 * @param crs the {@link CoordinateReferenceSystem}
	 */
	public SpatialReference(CoordinateReferenceSystem crs)
	{
		this.crs = crs;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof CoordinateReferenceSystem && this.crs.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return this.crs.toString();
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.crs;
	}
	
	@Override
	public Envelope getEnvelope()
	{
		ArrayList<Position> pos = new ArrayList<>();
		pos.add(new GeneralDirectPosition(this.crs));
		
		return new ContainerEnvelope(pos);
	}
	
	@Override
	public Geometry toGeometry(CoordinateReferenceSystem crs)
	{
		return (new Point(new PointImpl(new DirectPositionImpl(crs)))).toGeometry(crs);
	}
	
	@Override
	public boolean overlaps(GeometryObject o)
	{
		return false;
	}
	
	@Override
	public Extent getDomainOfValidity()
	{
		return this.crs.getDomainOfValidity();
	}
	
	@Override
	public InternationalString getScope()
	{
		return this.crs.getScope();
	}
	
	@Override
	public Collection<GenericName> getAlias()
	{
		return this.crs.getAlias();
	}
	
	@Override
	public Set<ReferenceIdentifier> getIdentifiers()
	{
		return this.crs.getIdentifiers();
	}
	
	@Override
	public ReferenceIdentifier getName()
	{
		return this.crs.getName();
	}
	
	@Override
	public InternationalString getRemarks()
	{
		return this.crs.getRemarks();
	}
	
	@Override
	public String toWKT()
	throws UnsupportedOperationException
	{
		return this.crs.toWKT();
	}
	
	@Override
	public CoordinateSystem getCoordinateSystem()
	{
		return this.crs.getCoordinateSystem();
	}
}