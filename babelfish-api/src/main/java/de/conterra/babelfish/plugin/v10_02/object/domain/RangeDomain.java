package de.conterra.babelfish.plugin.v10_02.object.domain;

/**
 * defines a Range Domain
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class RangeDomain
		extends DomainObject {
	/**
	 * the name
	 *
	 * @since 0.1.0
	 */
	private final String name;
	/**
	 * the minimum of the range
	 *
	 * @since 0.1.0
	 */
	private int min;
	/**
	 * the maximum of the range
	 *
	 * @since 0.1.0
	 */
	private int max;
	
	/**
	 * constructor, with all attributes
	 *
	 * @param name the name
	 * @param min  the minimum of the range
	 * @param max  the maximum of the range
	 * @since 0.1.0
	 */
	public RangeDomain(String name, int min, int max) {
		this.name = name;
		this.min = min;
		this.max = max;
	}
	
	@Override
	public String getType() {
		return "range";
	}
	
	/**
	 * gives the name
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * gives the minimum of the range
	 *
	 * @return the minimum of the range
	 *
	 * @since 0.1.0
	 */
	public int getMin() {
		return this.min;
	}
	
	/**
	 * sets the minimum of the range
	 *
	 * @param min the minimum of the range to set
	 * @since 0.1.0
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * gives the maximum of the range
	 *
	 * @return the maximum of the range
	 *
	 * @since 0.1.0
	 */
	public int getMax() {
		return this.max;
	}
	
	/**
	 * sets the maximum of the range
	 *
	 * @param max the maximum of the range to set
	 * @since 0.1.0
	 */
	public void setMax(int max) {
		this.max = max;
	}
}