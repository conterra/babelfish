package de.conterra.babelfish.interchange;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests the class {@link ArrayValue}
 * 
 * @version 0.3.1
 * @author chwe
 * @since 0.3.1
 */
public class ArrayValueTest
{
	/**
	 * the test object
	 * 
	 * @since 0.3.1
	 */
	private ArrayValue	array;
	/**
	 * values which are stored in the test object
	 * 
	 * @since 0.3.1
	 */
	private Value[]		values	=
	{
			new StringValue("First value"),
			new BooleanValue(true),
			new NumberValue(Math.PI)
	};
	
	/**
	 * initialize the test object
	 * 
	 * @since 0.3.1
	 */
	@Before
	public void setUp()
	{
		this.array = new ArrayValue();
		
		for (Value value : this.values)
		{
			this.array.addValue(value);
		}
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty()
	{
		Assert.assertFalse(this.array.isEmpty());
		
		this.array.clear();
		Assert.assertTrue(this.array.isEmpty());
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#getValues()}.
	 */
	@Test
	public void testGetValues()
	{
		List<? extends Value> localValues = this.array.getValues();
		Assert.assertEquals(this.values.length, localValues.size());
		
		for (int i = 0; i < this.values.length; i++)
		{
			Assert.assertSame(localValues.get(i), this.values[i]);
		}
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#getValue(int)}.
	 */
	@Test
	public void testGetValue()
	{
		for (int i = 0; i < this.values.length; i++)
		{
			Assert.assertSame(this.values[i], this.array.getValue(i));
		}
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#addValue(de.conterra.babelfish.interchange.Value)}
	 * .
	 */
	@Test
	public void testAddValue()
	{
		this.array.clear();
		
		Value value = this.values[0];
		Assert.assertTrue(this.array.addValue(value));
		Assert.assertSame(value, this.array.getValue(0));
		
		NullValue nullValue = new NullValue();
		Assert.assertTrue(this.array.addValue(nullValue));
		Assert.assertSame(nullValue, this.array.getValue(this.array.getValues().size() - 1));
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#addValueNotNull(de.conterra.babelfish.interchange.Value)}
	 * .
	 */
	@Test
	public void testAddValueNotNull()
	{
		this.array.clear();
		
		NullValue nullValue = new NullValue();
		Assert.assertFalse(this.array.addValueNotNull(nullValue));
		Assert.assertEquals(0, this.array.getValues().size());
		
		StringValue nonNullValue = new StringValue("I'm not empty!");
		Assert.assertTrue(this.array.addValueNotNull(nonNullValue));
		Assert.assertSame(nonNullValue, this.array.getValue(0));
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#removeValue(de.conterra.babelfish.interchange.Value)}
	 * .
	 */
	@Test
	public void testRemoveValueValue()
	{
		Assert.assertTrue(this.array.removeValue(this.values[this.values.length - 1]));
		Assert.assertEquals(this.values.length - 1, this.array.getValues().size());
		
		Assert.assertFalse(this.array.removeValue(new NullValue()));
	}
	
	/**
	 * Test method for
	 * {@link de.conterra.babelfish.interchange.ArrayValue#removeValue(int)}.
	 */
	@Test
	public void testRemoveValueInt()
	{
		for (int i = this.values.length - 1; i >= 0; i--)
		{
			Assert.assertSame(this.values[i], this.array.removeValue(i));
		}
	}
}