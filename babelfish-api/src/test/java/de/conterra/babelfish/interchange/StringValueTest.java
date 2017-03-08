package de.conterra.babelfish.interchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link StringValue}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class StringValueTest {
	/**
	 * the test object
	 *
	 * @since 0.3.1
	 */
	private StringValue value;
	/**
	 * the default text of the test object
	 *
	 * @since 0.3.1
	 */
	private String text = "Hi, I'm a StringValue!";
	
	/**
	 * initialize the test object
	 *
	 * @since 0.3.1
	 */
	@Before
	public void setUp() {
		this.value = new StringValue(this.text);
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.StringValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(this.value.isEmpty());
		
		this.value.setValue("");
		Assert.assertTrue(this.value.isEmpty());
		
		this.value.setValue(new String());
		Assert.assertTrue(this.value.isEmpty());
		
		this.value.setValue(null);
		Assert.assertTrue(this.value.isEmpty());
		
		this.value.setValue(" ");
		Assert.assertFalse(this.value.isEmpty());
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.StringValue#getValue()}.
	 */
	@Test
	public void testGetValue() {
		Assert.assertEquals(this.text, this.value.getValue());
		Assert.assertNotEquals(this.text.substring(1), this.value.getValue());
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.StringValue#setValue(java.lang.String)}.
	 */
	@Test
	public void testSetValue() {
		String newText = "This is the new value text!";
		this.value.setValue(newText);
		Assert.assertEquals(newText, this.value.getValue());
	}
}