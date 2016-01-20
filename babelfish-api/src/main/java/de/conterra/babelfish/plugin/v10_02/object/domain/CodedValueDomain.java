package de.conterra.babelfish.plugin.v10_02.object.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * defines a Coded Value Domain
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class CodedValueDomain
extends DomainObject
{
	/**
	 * the name
	 * 
	 * @since 0.1
	 */
	private final String name;
	/**
	 * the coded values
	 * 
	 * @since 0.1
	 */
	private LinkedHashMap<String, Object> values = new LinkedHashMap<>();
	
	/**
	 * constructor, with given name
	 * 
	 * @since 0.1
	 * 
	 * @param name the name
	 */
	public CodedValueDomain(String name)
	{
		this.name = name;
	}
	
	/**
	 * constructor, with given name and coded values
	 * 
	 * @since 0.1
	 * 
	 * @param name the name
	 * @param codedValues the coded values to store
	 */
	public CodedValueDomain(String name, Map<? extends String, ? extends String> codedValues)
	{
		this(name);
		
		this.values.putAll(codedValues);
	}
	
	@Override
	public String getType()
	{
		return "codedValue";
	}
	
	/**
	 * gives the name
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * gives a {@link Map} of all coded values
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Map} of all coded values
	 */
	public Map<? extends String, ? extends Object> getCodedValues()
	{
		return new LinkedHashMap<>(this.values);
	}
	
	/**
	 * gives a code by its name
	 * 
	 * @since 0.1
	 * 
	 * @param name the name
	 * @return the code on <code>name</code>
	 * @see Map#get(Object)
	 */
	public Object getCode(String name)
	{
		return this.values.get(name);
	}
	
	/**
	 * adds a coded value
	 * 
	 * @since 0.1
	 * 
	 * @param name the name of <code>code</code>
	 * @param code the code to add (could only be a {@link String} or
	 *        {@link Number})
	 * @return the previous stored code on <code>name</code> or
	 *         <code>null</code> if no code was stored on <code>name</code>
	 * @throws IllegalArgumentException if <code>code</code> is not valid type
	 * @see Map#put(Object, Object)
	 */
	public Object addCodedValue(String name, Object code)
	throws IllegalArgumentException
	{
		if ( ! (code instanceof String)
		&& ! (code instanceof Number))
			throw new IllegalArgumentException("Only String or Number codes are supported!");
		
		return this.values.put(name, code);
	}
	
	/**
	 * removes a coded value
	 * 
	 * @since 0.1
	 * 
	 * @param name the name of the code to remove
	 * @return the code, which was removed or <code>null</code> if no code was
	 *         stored on <code>name</code>
	 * @see Map#remove(Object)
	 */
	public Object removeCodedValue(String name)
	{
		return this.values.remove(name);
	}
	
	/**
	 * removes all stored coded values
	 * 
	 * @since 0.1
	 * 
	 * @see Map#clear()
	 */
	public void clear()
	{
		this.values.clear();
	}
}