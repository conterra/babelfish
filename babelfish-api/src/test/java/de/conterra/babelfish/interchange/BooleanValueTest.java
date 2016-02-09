package de.conterra.babelfish.interchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link BooleanValue}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class BooleanValueTest
{
	/**
	 * the test object
	 * 
	 * @since 0.3.1
	 */
	private BooleanValue value = new BooleanValue(true);
	
	/**
	 * initialize the test object
	 * 
	 * @since 0.3.1
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()
	throws Exception
	{
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.BooleanValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty()
	{
		Assert.assertFalse(this.value.isEmpty());
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.BooleanValue#getValue()}.
	 */
	@Test
	public void testGetValue()
	{
		Assert.assertTrue(this.value.getValue());
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.BooleanValue#setValue(boolean)}.
	 */
	@Test
	public void testSetValue()
	{
		boolean oldValue = this.value.getValue();
		
		this.value.setValue( !oldValue);
		
		Assert.assertTrue(this.value.getValue() != oldValue);
	}
}