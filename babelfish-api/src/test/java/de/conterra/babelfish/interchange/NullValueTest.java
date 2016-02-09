package de.conterra.babelfish.interchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link NullValue}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class NullValueTest
{
	/**
	 * the test object
	 * 
	 * @since 0.3.1
	 */
	private NullValue value;
	
	/**
	 * initialize the test object
	 * 
	 * @since 0.3.1
	 */
	@Before
	public void setUp()
	{
		this.value = new NullValue();
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.NullValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty()
	{
		Assert.assertTrue(this.value.isEmpty());
	}
}