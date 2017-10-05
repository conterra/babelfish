package de.conterra.babelfish.plugin.v10_02.object.domain;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * defines a Coded Value Domain
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class CodedValueDomain
		extends DomainObject {
	/**
	 * the name
	 *
	 * @since 0.1.0
	 */
	@Getter
	private final String name;
	/**
	 * the coded values
	 *
	 * @since 0.1.0
	 */
	private LinkedHashMap<String, Object> values = new LinkedHashMap<>();
	
	/**
	 * constructor, with given name
	 *
	 * @param name the name
	 * @since 0.1.0
	 */
	public CodedValueDomain(String name) {
		this.name = name;
	}
	
	/**
	 * constructor, with given name and coded values
	 *
	 * @param name        the name
	 * @param codedValues the coded values to store
	 * @since 0.1.0
	 */
	public CodedValueDomain(String name, Map<? extends String, ? extends String> codedValues) {
		this(name);
		
		this.values.putAll(codedValues);
	}
	
	@Override
	public String getType() {
		return "codedValue";
	}
	
	/**
	 * gives a {@link Map} of all coded values
	 *
	 * @return a {@link Map} of all coded values
	 *
	 * @since 0.1.0
	 */
	public Map<? extends String, ?> getCodedValues() {
		return new LinkedHashMap<>(this.values);
	}
	
	/**
	 * gives a code by its name
	 *
	 * @param name the name
	 * @return the code on {@code name}
	 *
	 * @see Map#get(Object)
	 * @since 0.1.0
	 */
	public Object getCode(String name) {
		return this.values.get(name);
	}
	
	/**
	 * adds a coded value
	 *
	 * @param name the name of {@code code}
	 * @param code the code to add (could only be a {@link String} or {@link Number})
	 * @return the previous stored code on {@code name} or {@code null} if no code was stored on {@code name}
	 *
	 * @throws IllegalArgumentException if {@code code} is not valid type
	 * @see Map#put(Object, Object)
	 * @since 0.1.0
	 */
	public Object addCodedValue(String name, Object code)
	throws IllegalArgumentException {
		if (!(code instanceof String)
		    && !(code instanceof Number)) {
			throw new IllegalArgumentException("Only String or Number codes are supported!");
		}
		
		return this.values.put(name, code);
	}
	
	/**
	 * removes a coded value
	 *
	 * @param name the name of the code to remove
	 * @return the code, which was removed or {@code null} if no code was stored on {@code name}
	 *
	 * @see Map#remove(Object)
	 * @since 0.1.0
	 */
	public Object removeCodedValue(String name) {
		return this.values.remove(name);
	}
	
	/**
	 * removes all stored coded values
	 *
	 * @see Map#clear()
	 * @since 0.1.0
	 */
	public void clear() {
		this.values.clear();
	}
}
