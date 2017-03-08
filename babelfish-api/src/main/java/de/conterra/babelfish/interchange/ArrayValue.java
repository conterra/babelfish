package de.conterra.babelfish.interchange;

import java.util.LinkedList;
import java.util.List;

/**
 * defines a {@link Value}, which stores an array of {@link Value}s
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.1.0
 */
public class ArrayValue
		extends Value {
	/**
	 * list of stored {@link Value}s
	 *
	 * @since 0.1.0
	 */
	private LinkedList<Value> values = new LinkedList<>();
	
	/**
	 * constructor, with given {@link Value}s
	 *
	 * @param values {@link List} of {@link Value}s to store
	 * @since 0.1.0
	 */
	public ArrayValue(List<? extends Value> values) {
		this.values.addAll(values);
	}
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public ArrayValue() {
	}
	
	/**
	 * gives the {@link List} of stored {@link Value}s<br>
	 * Note: The {@link List} will be copied, so any changes doesn't matter.
	 *
	 * @return the {@link List} of stored {@link Value}s
	 *
	 * @since 0.1.0
	 */
	public List<? extends Value> getValues() {
		return new LinkedList<>(this.values);
	}
	
	/**
	 * gives a stored {@link Value}
	 *
	 * @param index the store index of the {@link Value}
	 * @return the {@link Value} at {@code index}
	 *
	 * @see List#get(int)
	 * @since 0.1.0
	 */
	public Value getValue(int index) {
		return this.values.get(index);
	}
	
	/**
	 * adds a {@link Value}
	 *
	 * @param value the {@link Value} to add
	 * @return {@code true}, if the {@link Value} was added
	 *
	 * @see List#add(Object)
	 * @since 0.1.0
	 */
	public boolean addValue(Value value) {
		return this.values.add(value);
	}
	
	/**
	 * adds a {@link Value}, only if it is not empty
	 *
	 * @param value the {@link Value} to add
	 * @return {@code true}, if the {@link Value} was added
	 *
	 * @see ArrayValue#addValue(Value)
	 * @since 0.1.0
	 */
	public boolean addValueNotNull(Value value) {
		if (value == null || value.isEmpty())
			return false;
		
		return this.addValue(value);
	}
	
	/**
	 * removes a {@link Value}
	 *
	 * @param value the {@link Value} to remove
	 * @return {@code true}, if the {@link Value} was contained
	 *
	 * @see List#remove(Object)
	 * @since 0.1.0
	 */
	public boolean removeValue(Value value) {
		return this.values.remove(value);
	}
	
	/**
	 * removes a {@link Value} by its index
	 *
	 * @param index the index of the {@link Value} to remove
	 * @return the {@link Value}, which stored at the {@code index}
	 *
	 * @since 0.1.0
	 */
	public Value removeValue(int index) {
		return this.values.remove(index);
	}
	
	/**
	 * removes all {@link Value}s stored in this {@link ArrayValue}
	 *
	 * @since 0.3.1
	 */
	public void clear() {
		this.values.clear();
	}
	
	@Override
	public boolean isEmpty() {
		if (!(this.values.isEmpty())) {
			for (Value value : this.values) {
				if (!(value.isEmpty()))
					return false;
			}
		}
		
		return true;
	}
}