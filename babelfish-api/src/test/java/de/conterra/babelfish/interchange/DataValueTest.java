package de.conterra.babelfish.interchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link DataValue}
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public class DataValueTest {
	/**
	 * the test object
	 *
	 * @since 0.3.1
	 */
	private DataValue value;
	/**
	 * the default data of the test object
	 *
	 * @since 0.3.1
	 */
	private byte[] data =
			{
					0,
					1,
					2,
					3,
					4,
					5,
					6,
					7
			};
	
	/**
	 * what does this method do?
	 *
	 * @since 0.3.1
	 */
	@Before
	public void setUp() {
		this.value = new DataValue(this.data);
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.DataValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(this.value.isEmpty());
		
		this.value = new DataValue(null);
		Assert.assertTrue(this.value.isEmpty());
		
		this.value = new DataValue(new byte[]{});
		Assert.assertTrue(this.value.isEmpty());
	}
	
	/**
	 * Test method for {@link de.conterra.babelfish.interchange.DataValue#getData()}.
	 */
	@Test
	public void testGetData() {
		Assert.assertSame(this.data, this.value.getData());
	}
}