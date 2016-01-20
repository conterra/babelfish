package de.conterra.babelfish.interchange;

import java.util.LinkedList;
import java.util.List;

/**
 * defines a {@link Value}, which stores an array of {@link Value}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ArrayValue
extends Value
{
	/**
	 * list of stored {@link Value}s
	 * 
	 * @since 0.1
	 */
	private LinkedList<Value> values = new LinkedList<>();
	
	/**
	 * constructor, with given {@link Value}s
	 * 
	 * @since 0.1
	 * 
	 * @param values {@link List} of {@link Value}s to store
	 */
	public ArrayValue(List<? extends Value> values)
	{
		this.values.addAll(values);
	}
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public ArrayValue()
	{
	}
	
	/**
	 * gives the {@link List} of stored {@link Value}s<br>
	 * Note: The {@link List} will be copied, so any changes doesn't matter.
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link List} of stored {@link Value}s
	 */
	public List<? extends Value> getValues()
	{
		return new LinkedList<>(this.values);
	}
	
	/**
	 * gives a stored {@link Value}
	 * 
	 * @since 0.1
	 * 
	 * @param index the store index of the {@link Value}
	 * @return the {@link Value} at <code>index</code>
	 * @see List#get(int)
	 */
	public Value getValue(int index)
	{
		return this.values.get(index);
	}
	
	/**
	 * adds a {@link Value}
	 * 
	 * @since 0.1
	 * 
	 * @param value the {@link Value} to add
	 * @return <code>true</code>, if the {@link Value} was added
	 * @see List#add(Object)
	 */
	public boolean addValue(Value value)
	{
		return this.values.add(value);
	}
	
	/**
	 * adds a {@link Value}, only if it is not empty
	 * 
	 * @since 0.1
	 * 
	 * @param value the {@link Value} to add
	 * @return <code>true</code>, if the {@link Value} was added
	 * @see ArrayValue#addValue(Value)
	 */
	public boolean addValueNotNull(Value value)
	{
		if (value == null || value.isEmpty())
			return false;
		
		return this.addValue(value);
	}
	
	/**
	 * removes a {@link Value}
	 * 
	 * @since 0.1
	 * 
	 * @param value the {@link Value} to remove
	 * @return <code>true</code>, if the {@link Value} was contained
	 * @see List#remove(Object)
	 */
	public boolean removeValue(Value value)
	{
		return this.values.remove(value);
	}
	
	/**
	 * removes a {@link Value} by its index
	 * 
	 * @since 0.1
	 * 
	 * @param index the index of the {@link Value} to remove
	 * @return the {@link Value}, which stored at the <code>index</code>
	 */
	public Value removeValue(int index)
	{
		return this.values.remove(index);
	}
	
	@Override
	public boolean isEmpty()
	{
		if ( ! (this.values.isEmpty()))
		{
			for (Value value : this.values)
			{
				if ( ! (value.isEmpty()))
					return false;
			}
		}
		
		return true;
	}
}