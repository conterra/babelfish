package de.conterra.babelfish.interchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link NumberValue}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class NumberValueTest {
	private NumberValue value;
	
	/**
	 * initialize the test object
	 *
	 * @since 0.3.1
	 */
	@Before
	public void setUp() {
		this.value = new NumberValue(1);
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.NumberValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(this.value.isEmpty());
		
		this.value.setValue(null);
		Assert.assertTrue(this.value.isEmpty());
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.NumberValue#getValue()}.
	 */
	@Test
	public void testGetValue() {
		Assert.assertEquals(1, this.value.getValue());
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.NumberValue#setValue(java.lang.Number)}.
	 */
	@Test
	public void testSetValue() {
		this.value.setValue(2.5);
		Assert.assertEquals(2.5, this.value.getValue());
	}
}