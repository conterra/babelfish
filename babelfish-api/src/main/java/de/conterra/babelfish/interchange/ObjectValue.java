package de.conterra.babelfish.interchange;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * defines a {@link Value}, which could store a {@link Map} of other
 * {@link Value}s, referenced by ids
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ObjectValue
extends Value
{
	/**
	 * stores the body, which contains all ids and {@link Value}s
	 * 
	 * @since 0.1
	 */
	private LinkedHashMap<String, Value> body = new LinkedHashMap<>();
	
	/**
	 * constructor, with given {@link Map} of {@link Value}s
	 * 
	 * @since 0.1
	 * 
	 * @param body the {@link Map} of {@link Value}s to store
	 */
	public ObjectValue(Map<? extends String, ? extends Value> body)
	{
		this.body.putAll(body);
	}
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public ObjectValue()
	{
	}
	
	/**
	 * gives the body, which contains all ids and {@link Value}s<br>
	 * Note: The {@link Map} will be copied, so any changes doesn't matter.
	 * 
	 * @since 0.1
	 * 
	 * @return the body {@link Map}
	 */
	public Map<? extends String, ? extends Value> getBody()
	{
		return new LinkedHashMap<>(this.body);
	}
	
	/**
	 * gives a {@link Value} by its id
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the {@link Value} to get
	 * @return the {@link Value} on <code>id</code>
	 * @see Map#get(Object)
	 */
	public Value getValue(String id)
	{
		return this.body.get(id);
	}
	
	/**
	 * adds a {@link Value} to the body
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the {@link Value}
	 * @param value the {@link Value} to add
	 * @param nextId add the <code>value</code> in front of the entry with the
	 *        id <code>nextId</code> of <code>null</code>, to add it at the end
	 *        of the body
	 * @param emptyCheck <code>true</code> to check, if the given {@link Value}
	 *        is empty
	 * @return the previous {@link Value} on <code>id</code> or
	 *         <code>null</code>, if there was no {@link Value} on the
	 *         <code>id</code> before<br>
	 *         (<code>null</code> could also note, that the empty check failed.)
	 */
	public Value addContent(String id, Value value, String nextId, boolean emptyCheck)
	{
		if (emptyCheck)
		{
			if (value == null || value.isEmpty())
				return null;
		}
		
		int pos = -1;
		if (nextId != null)
			pos = new LinkedList<>(this.body.keySet()).indexOf(nextId);
		
		Value prevValue;
		
		if (pos < 0)
			prevValue = this.body.put(id, value);
		else
		{
			prevValue = this.body.remove(id);
			
			LinkedHashMap<String, Value> newBody = new LinkedHashMap<>();
			
			ArrayList<String> keys = new ArrayList<>(this.body.keySet());
			ArrayList<Value> values = new ArrayList<>(this.body.values());
			for (int i = 0; i < pos; i++)
				newBody.put(keys.get(i), values.get(i));
			
			newBody.put(id, value);
			
			for (int i = pos; i < this.body.size(); i++)
				newBody.put(keys.get(i), values.get(i));
			
			this.body = newBody;
		}
		
		return prevValue;
	}
	
	/**
	 * adds an {@link Value} to the end of the body
	 * 
	 * @since 0.1
	 * 
	 * @param id the id to add the {@link Value} to
	 * @param value the {@link Value} to add
	 * @return the previous {@link Value} on <code>id</code> or
	 *         <code>null</code> if there was no {@link Value} on the
	 *         <code>id</code> before
	 * @see ObjectValue#addContent(String, Value, String, boolean)
	 */
	public Value addContent(String id, Value value)
	{
		return this.addContent(id, value, null, false);
	}
	
	/**
	 * adds a {@link Value}, only if it is not empty
	 * 
	 * @since 0.1
	 * 
	 * @param id the id to add the {@link Value} to
	 * @param value the {@link Value} to add
	 * @return the previous {@link Value} on the <code>id</code> or
	 *         <code>null</code>, if there was no {@link Value} on the
	 *         <code>id</code> before or <code>value</code> was empty
	 * @see ObjectValue#addContent(String, Value, String, boolean)
	 */
	public Value addContentNotEmpty(String id, Value value)
	{
		return this.addContent(id, value, null, true);
	}
	
	/**
	 * removes a {@link Value} from the body
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the {@link Value}, which should removed
	 * @return the {@link Value} on <code>id</code>, which was removed
	 * @see Map#remove(Object)
	 */
	public Value removeContent(String id)
	{
		return this.body.remove(id);
	}
	
	@Override
	public boolean isEmpty()
	{
		if ( ! (this.body.isEmpty()))
		{
			for (Value value : this.body.values())
			{
				if ( ! (value.isEmpty()))
					return false;
			}
		}
		
		return true;
	}
}