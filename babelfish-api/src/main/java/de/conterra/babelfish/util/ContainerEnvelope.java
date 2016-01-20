package de.conterra.babelfish.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.geotools.geometry.GeneralDirectPosition;
import org.geotools.geometry.iso.coordinate.PointArrayImpl;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines an {@link Envelope}, which contains a {@link List} of
 * {@link DirectPosition}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ContainerEnvelope
implements Envelope
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(ContainerEnvelope.class);
	
	/**
	 * the lower corner of the {@link Envelope}
	 * 
	 * @since 0.1
	 */
	private final GeneralDirectPosition lowerCorner;
	/**
	 * the upper corner of the {@link Envelope}
	 * 
	 * @since 0.1
	 */
	private final GeneralDirectPosition upperCorner;
	
	/**
	 * basic constructor of initialization
	 * 
	 * @since 0.1
	 * 
	 * @param crs the {@link CoordinateReferenceSystem} to use
	 * @throws IllegalArgumentException if <code>crs</code> is <code>null</code>
	 */
	private ContainerEnvelope(CoordinateReferenceSystem crs)
	throws IllegalArgumentException
	{
		if (crs == null)
		{
			String msg = "The coordinate reference system have to be set!";
			ContainerEnvelope.LOGGER.debug(msg);
			throw new IllegalArgumentException(msg);
		}
		
		ContainerEnvelope.LOGGER.debug("Create new lower and upper corner.");
		this.lowerCorner = new GeneralDirectPosition(crs);
		this.upperCorner = new GeneralDirectPosition(crs);
		
		ContainerEnvelope.LOGGER.debug("Fill every existing ordinate with infinity values.");
		for (int i = 0; i < this.getDimension(); i++)
		{
			this.lowerCorner.setOrdinate(i, Double.POSITIVE_INFINITY);
			this.upperCorner.setOrdinate(i, Double.NEGATIVE_INFINITY);
		}
	}
	
	/**
	 * constructor, with given {@link Collection} of {@link Position}s
	 * 
	 * @since 0.1
	 * 
	 * @param positions a {@link List} of all {@link Position}s, to get the
	 *        {@link Envelope} from
	 * @throws IllegalArgumentException if <code>crs</code> is <code>null</code>
	 */
	public ContainerEnvelope(Collection<? extends Position> positions)
	throws IllegalArgumentException
	{
		this( (new PointArrayImpl(new LinkedList<>(positions))).getCoordinateReferenceSystem());
		
		ContainerEnvelope.LOGGER.debug("Set the extrem ordinates of all given positions.");
		this.addPositions(positions);
	}
	
	/**
	 * constructor, with given {@link Envelope}s
	 * 
	 * @since 0.1
	 * 
	 * @param crs the {@link CoordinateReferenceSystem} to use
	 * @param envelopes a {@link Collection} of {@link Envelope}s to add
	 * @throws IllegalArgumentException if <code>crs</code> is <code>null</code>
	 */
	public ContainerEnvelope(CoordinateReferenceSystem crs, Collection<? extends Envelope> envelopes)
	throws IllegalArgumentException
	{
		this(crs);
		
		ContainerEnvelope.LOGGER.debug("Set the extreme ordinates of all given envelopes.");
		for (Envelope env : envelopes)
			this.addEnvelope(env);
	}
	
	/**
	 * creates a valid {@link DirectPosition}, which was cleaned of
	 * initialization values
	 * 
	 * @since 0.1
	 * 
	 * @param pos the {@link DirectPosition} to clean
	 * @return the cleaned <code>pos</code>
	 */
	private static DirectPosition cleanPosition(DirectPosition pos)
	{
		GeneralDirectPosition result = new GeneralDirectPosition(pos);
		
		ContainerEnvelope.LOGGER.debug("Set the infinity values to 0.");
		for (int i = 0; i < result.getDimension(); i++)
		{
			double ordinate = result.getOrdinate(i);
			
			if (Double.isInfinite(ordinate))
				result.setOrdinate(i, 0);
		}
		
		return result;
	}
	
	@Override
	public CoordinateReferenceSystem getCoordinateReferenceSystem()
	{
		return this.lowerCorner.getCoordinateReferenceSystem();
	}
	
	@Override
	public int getDimension()
	{
		return this.lowerCorner.getDimension();
	}
	
	@Override
	public DirectPosition getLowerCorner()
	{
		ContainerEnvelope.LOGGER.debug("Return cleaned position (no infinity values).");
		return ContainerEnvelope.cleanPosition(this.lowerCorner);
	}
	
	@Override
	public DirectPosition getUpperCorner()
	{
		ContainerEnvelope.LOGGER.debug("Return cleaned position (no infinity values).");
		return ContainerEnvelope.cleanPosition(this.upperCorner);
	}
	
	@Override
	public double getMinimum(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.getLowerCorner().getOrdinate(arg0);
	}
	
	@Override
	public double getMaximum(int arg0)
	throws IndexOutOfBoundsException
	{
		return this.getUpperCorner().getOrdinate(arg0);
	}
	
	@Override
	public double getMedian(int dimension)
	throws IndexOutOfBoundsException
	{
		return (this.getMinimum(dimension) + this.getMaximum(dimension)) / 2;
	}
	
	@Override
	public double getSpan(int dimension)
	throws IndexOutOfBoundsException
	{
		return this.getMaximum(dimension) - this.getMinimum(dimension);
	}
	
	/**
	 * adds a {@link Position} to extend the {@link Envelope}
	 * 
	 * @since 0.1
	 * 
	 * @param position the {@link Position} to add
	 * @return whether the {@link Envelope} was extended or not
	 */
	public boolean addPosition(Position position)
	{
		boolean result = false;
		
		DirectPosition posSrc = position.getDirectPosition();
		DirectPosition posDst;
		try
		{
			posDst = GeoUtils.transform(posSrc, this.getCoordinateReferenceSystem());
		}
		catch (TransformException e)
		{
			ContainerEnvelope.LOGGER.warn("Use given position without transformation to the envelope CRS.", e);
			
			posDst = posSrc;
		}
		
		ContainerEnvelope.LOGGER.debug("Set new dimensions of lower and upper corner to infinity values.");
		for (int i = this.getDimension(); this.getDimension() < posDst.getDimension(); i++)
		{
			this.lowerCorner.setOrdinate(i, Double.POSITIVE_INFINITY);
			this.upperCorner.setOrdinate(i, Double.NEGATIVE_INFINITY);
		}
		
		double[] coordinates = posDst.getCoordinate();
		for (int i = 0; i < posDst.getDimension(); i++)
		{
			if (coordinates[i] < this.lowerCorner.getOrdinate(i))
			{
				ContainerEnvelope.LOGGER.debug("Found smaller ordinate on " + i + ". dimension: " + coordinates[i] + " < " + this.lowerCorner.getOrdinate(i));
				
				this.lowerCorner.setOrdinate(i, coordinates[i]);
				result = true;
			}
			if (coordinates[i] > this.upperCorner.getOrdinate(i))
			{
				ContainerEnvelope.LOGGER.debug("Found greater ordinate on " + i + ". dimension: " + coordinates[i] + " < " + this.upperCorner.getOrdinate(i));
				
				this.upperCorner.setOrdinate(i, coordinates[i]);
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * adds a {@link List} of {@link Position}s to extend the {@link Envelope}
	 * 
	 * @since 0.1
	 * 
	 * @param positions a {@link List} of {@link Position}s to add
	 * @return whether the {@link Envelope} was extended or not
	 */
	public boolean addPositions(Collection<? extends Position> positions)
	{
		boolean result = false;
		
		for (Position pos : positions)
		{
			if (this.addPosition(pos))
			{
				ContainerEnvelope.LOGGER.debug("Position " + pos + " have changed the envelope.");
				
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * adds an {@link Envelope} to extend this {@link Envelope}
	 * 
	 * @since 0.1
	 * 
	 * @param envelope the {@link Envelope} to add
	 * @return whether the {@link Envelope} was extended or not
	 */
	public boolean addEnvelope(Envelope envelope)
	{
		List<Position> list = new ArrayList<>();
		
		ContainerEnvelope.LOGGER.debug("Add lower and upper corner of the given envelope to the list of positions to add.");
		list.add(envelope.getLowerCorner());
		list.add(envelope.getUpperCorner());
		
		return this.addPositions(list);
	}
}