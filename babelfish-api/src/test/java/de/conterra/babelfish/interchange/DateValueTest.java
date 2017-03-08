package de.conterra.babelfish.interchange;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link DateValue}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class DateValueTest {
	/**
	 * the test object
	 *
	 * @since 0.3.1
	 */
	private DateValue value;
	/**
	 * the time of the test object
	 *
	 * @since 0.3.1
	 */
	private DateTime time = new DateTime(2016, 2, 9, 17, 4, DateTimeZone.forOffsetHours(1));
	
	/**
	 * initialize the test object
	 *
	 * @since 0.3.1
	 */
	@Before
	public void setUp() {
		this.value = new DateValue(this.time);
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.DateValue#getTime()}.
	 */
	@Test
	public void testGetTime() {
		Assert.assertEquals(this.time.toLocalDate(), this.value.getTime().toLocalDate());
	}
}