package de.conterra.babelfish.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines a class with enhanced {@link Math} functions
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class MathUtils
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MathUtils.class);
	
	/**
	 * private standard constructor, to prevent intialization
	 * 
	 * @since 0.1
	 */
	private MathUtils()
	{
	}
	
	/**
	 * gives the minimum {@link Number} of an array of {@link Number}s
	 * 
	 * @since 0.1
	 * 
	 * @param numbers an array of {@link Number}s to search
	 * @return the minimum {@link Number} of <code>numbers</code>
	 * @throws IllegalArgumentException if <code>numbers</code> is empty
	 */
	public static Number min(Number[] numbers)
	throws IllegalArgumentException
	{
		if (numbers.length < 1)
		{
			String msg = "At least one number was needed!";
			MathUtils.LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		Number result = Double.POSITIVE_INFINITY;
		
		MathUtils.LOGGER.debug("Start iterating over all given numbers.");
		for (Number number : numbers)
		{
			if (number.doubleValue() < result.doubleValue())
			{
				MathUtils.LOGGER.debug("Found a smaller number: " + number + " < " + result);
				
				result = number;
			}
		}
		
		MathUtils.LOGGER.debug("Smallest number is " + result);
		return result;
	}
	
	/**
	 * gives the maximum {@link Number} of an array of {@link Number}s
	 * 
	 * @since 0.1
	 * 
	 * @param numbers an array of {@link Number}s to search
	 * @return the maximum {@link Number} of <code>numbers</code>
	 * @throws IllegalArgumentException if <code>numbers</code> is empty
	 */
	public static Number max(Number[] numbers)
	throws IllegalArgumentException
	{
		if (numbers.length < 1)
		{
			String msg = "At least one number was needed!";
			MathUtils.LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		Number result = Double.NEGATIVE_INFINITY;
		
		for (Number number : numbers)
		{
			if (number.doubleValue() > result.doubleValue())
			{
				MathUtils.LOGGER.debug("Found a greater number: " + number + " > " + result);
				
				result = number;
			}
		}
		
		MathUtils.LOGGER.debug("Greatest number is " + result);
		return result;
	}
}