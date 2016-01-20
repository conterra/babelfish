package de.conterra.babelfish.plugin.v10_02.object.renderer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

/**
 * defines a Unique Value Renderer
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class UniqueValueRenderer
extends RendererObject
{
	/**
	 * the fields
	 * 
	 * @since 0.1
	 */
	private final List<Field> fields = new LinkedList<>();
	/**
	 * the field delimiter
	 * 
	 * @since 0.1
	 */
	private String fieldDelimiter = ", ";
	/**
	 * the default {@link SymbolObject}
	 * 
	 * @since 0.1
	 */
	private SymbolObject defaultSymbol;
	/**
	 * the default label
	 * 
	 * @since 0.1
	 */
	private String defaultLabel;
	/**
	 * the {@link UniqueValue}s
	 * 
	 * @since 0.1
	 */
	private final Set<UniqueValue> uniqueValues = new LinkedHashSet<>();
	
	/**
	 * constructor, with given default label and {@link SymbolObject}
	 * 
	 * @since 0.1
	 * 
	 * @param symbol the default {@link SymbolObject}
	 * @param label the default label
	 */
	public UniqueValueRenderer(SymbolObject symbol, String label)
	{
		this.defaultSymbol = symbol;
		this.defaultLabel = label;
	}
	
	@Override
	public String getType()
	{
		return "uniqueValue";
	}
	
	/**
	 * gives a {@link Collection} of all fields
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Collection} of all fields
	 */
	public Collection<? extends Field> getFields()
	{
		return this.fields;
	}
	
	/**
	 * adds a field
	 * 
	 * @since 0.1
	 * 
	 * @param field the field to add
	 * @return <code>true</code>, if the field was successfully added
	 * @see Collection#add(Object)
	 */
	public boolean addField(Field field)
	{
		return this.fields.add(field);
	}
	
	/**
	 * adds a {@link Collection} of fields
	 * 
	 * @since 0.1
	 * 
	 * @param fields a {@link Collection} of all fields to add
	 * @return <code>true</code>, if the fields were successfully added
	 * @see Collection#addAll(Collection)
	 */
	public boolean addFields(Collection<? extends Field> fields)
	{
		return this.fields.addAll(fields);
	}
	
	/**
	 * removes a field
	 * 
	 * @since 0.1
	 * 
	 * @param field the field to remove
	 * @return <code>true</code>, if this {@link UniqueValueRenderer} contains
	 *         the field
	 * @see List#remove(Object)
	 */
	public boolean removeField(Field field)
	{
		return this.fields.remove(field);
	}
	
	/**
	 * gives the field delimiter
	 * 
	 * @since 0.1
	 * 
	 * @return the field delimiter
	 */
	public String getFieldDelimiter()
	{
		return this.fieldDelimiter;
	}
	
	/**
	 * sets the field delimiter
	 * 
	 * @since 0.1
	 * 
	 * @param fieldDelimitier the field delimiter to set
	 */
	public void setFieldDelimitier(String fieldDelimitier)
	{
		this.fieldDelimiter = fieldDelimitier;
	}
	
	/**
	 * gives the default {@link SymbolObject}
	 * 
	 * @since 0.1
	 * 
	 * @return the default {@link SymbolObject}
	 */
	public SymbolObject getDefaultSymbol()
	{
		return this.defaultSymbol;
	}
	
	/**
	 * sets the default {@link SymbolObject}
	 * 
	 * @since 0.1
	 * 
	 * @param defaultSymbol the default {@link SymbolObject} to set
	 */
	public void setDefaultSymbol(SymbolObject defaultSymbol)
	{
		this.defaultSymbol = defaultSymbol;
	}
	
	/**
	 * gives the default label
	 * 
	 * @since 0.1
	 * 
	 * @return the default label
	 */
	public String getDefaultLabel()
	{
		return this.defaultLabel;
	}
	
	/**
	 * sets the default label
	 * 
	 * @since 0.1
	 * 
	 * @param defaultLabel the default label to set
	 */
	public void setDefaultLabel(String defaultLabel)
	{
		this.defaultLabel = defaultLabel;
	}
	
	/**
	 * gives the {@link UniqueValue}s
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link UniqueValue}s
	 */
	public Set<? extends UniqueValue> getUniqueValues()
	{
		return this.uniqueValues;
	}
	
	/**
	 * adds an {@link UniqueValue}
	 * 
	 * @since 0.1
	 * 
	 * @param uniqueValue the {@link UniqueValue} to add
	 * @return <code>true</code>, if the {@link UniqueValue} was successfully
	 *         added
	 * @see Set#add(Object)
	 */
	public boolean addUniqueValue(UniqueValue uniqueValue)
	{
		return this.uniqueValues.add(uniqueValue);
	}
	
	/**
	 * adds a {@link Set} of {@link UniqueValue}s
	 * 
	 * @since 0.1
	 * 
	 * @param uniqueValues a {@link Set} of all {@link UniqueValue}s to add
	 * @return <code>true</code>, if the {@link UniqueValue}s were successfully
	 *         added
	 * @see Set#addAll(java.util.Collection)
	 */
	public boolean addUniqueValues(Set<? extends UniqueValue> uniqueValues)
	{
		return this.uniqueValues.addAll(uniqueValues);
	}
	
	/**
	 * removes an {@link UniqueValue}
	 * 
	 * @since 0.1
	 * 
	 * @param uniqueValue the {@link UniqueValue} to remove
	 * @return <code>true</code>, if this {@link UniqueValueRenderer} contains
	 *         the {@link UniqueValue}
	 */
	public boolean removeUniqueValue(UniqueValue uniqueValue)
	{
		return this.uniqueValues.remove(uniqueValue);
	}
}