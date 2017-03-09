package de.conterra.babelfish.util;

import lombok.extern.slf4j.Slf4j;

/**
 * defines a class with enhanced {@link Math} functions
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
@Slf4j
public class MathUtils {
	/**
	 * private standard constructor, to prevent intialization
	 *
	 * @since 0.1.0
	 */
	private MathUtils() {
	}
	
	/**
	 * gives the minimum {@link Number} of an array of {@link Number}s
	 *
	 * @param numbers an array of {@link Number}s to search
	 * @return the minimum {@link Number} of {@code numbers}
	 *
	 * @throws IllegalArgumentException if {@code numbers} is empty
	 * @since 0.1.0
	 */
	public static Number min(Number[] numbers)
			throws IllegalArgumentException {
		if (numbers.length < 1) {
			String msg = "At least one number was needed!";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		Number result = Double.POSITIVE_INFINITY;
		
		log.debug("Start iterating over all given numbers.");
		for (Number number : numbers) {
			if (number.doubleValue() < result.doubleValue()) {
				log.debug("Found a smaller number: " + number + " < " + result);
				
				result = number;
			}
		}
		
		log.debug("Smallest number is " + result);
		return result;
	}
	
	/**
	 * gives the maximum {@link Number} of an array of {@link Number}s
	 *
	 * @param numbers an array of {@link Number}s to search
	 * @return the maximum {@link Number} of {@code numbers}
	 *
	 * @throws IllegalArgumentException if {@code numbers} is empty
	 * @since 0.1.0
	 */
	public static Number max(Number[] numbers)
			throws IllegalArgumentException {
		if (numbers.length < 1) {
			String msg = "At least one number was needed!";
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		Number result = Double.NEGATIVE_INFINITY;
		
		for (Number number : numbers) {
			if (number.doubleValue() > result.doubleValue()) {
				log.debug("Found a greater number: " + number + " > " + result);
				
				result = number;
			}
		}
		
		log.debug("Greatest number is " + result);
		return result;
	}
}