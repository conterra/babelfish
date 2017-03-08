package de.conterra.babelfish.plugin.v10_02.object.renderer;

import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SymbolObject;

import java.util.*;

/**
 * defines a Unique Value Renderer
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class UniqueValueRenderer
		extends RendererObject {
	/**
	 * the fields
	 *
	 * @since 0.1.0
	 */
	private final List<Field> fields = new LinkedList<>();
	/**
	 * the field delimiter
	 *
	 * @since 0.1.0
	 */
	private String fieldDelimiter = ", ";
	/**
	 * the default {@link SymbolObject}
	 *
	 * @since 0.1.0
	 */
	private SymbolObject defaultSymbol;
	/**
	 * the default label
	 *
	 * @since 0.1.0
	 */
	private String defaultLabel;
	/**
	 * the {@link UniqueValue}s
	 *
	 * @since 0.1.0
	 */
	private final Set<UniqueValue> uniqueValues = new LinkedHashSet<>();
	
	/**
	 * constructor, with given default label and {@link SymbolObject}
	 *
	 * @param symbol the default {@link SymbolObject}
	 * @param label  the default label
	 * @since 0.1.0
	 */
	public UniqueValueRenderer(SymbolObject symbol, String label) {
		this.defaultSymbol = symbol;
		this.defaultLabel = label;
	}
	
	@Override
	public String getType() {
		return "uniqueValue";
	}
	
	/**
	 * gives a {@link Collection} of all fields
	 *
	 * @return a {@link Collection} of all fields
	 *
	 * @since 0.1.0
	 */
	public Collection<? extends Field> getFields() {
		return this.fields;
	}
	
	/**
	 * adds a field
	 *
	 * @param field the field to add
	 * @return {@code true}, if the field was successfully added
	 *
	 * @see Collection#add(Object)
	 * @since 0.1.0
	 */
	public boolean addField(Field field) {
		return this.fields.add(field);
	}
	
	/**
	 * adds a {@link Collection} of fields
	 *
	 * @param fields a {@link Collection} of all fields to add
	 * @return {@code true}, if the fields were successfully added
	 *
	 * @see Collection#addAll(Collection)
	 * @since 0.1.0
	 */
	public boolean addFields(Collection<? extends Field> fields) {
		return this.fields.addAll(fields);
	}
	
	/**
	 * removes a field
	 *
	 * @param field the field to remove
	 * @return {@code true}, if this {@link UniqueValueRenderer} contains the field
	 *
	 * @see List#remove(Object)
	 * @since 0.1.0
	 */
	public boolean removeField(Field field) {
		return this.fields.remove(field);
	}
	
	/**
	 * gives the field delimiter
	 *
	 * @return the field delimiter
	 *
	 * @since 0.1.0
	 */
	public String getFieldDelimiter() {
		return this.fieldDelimiter;
	}
	
	/**
	 * sets the field delimiter
	 *
	 * @param fieldDelimitier the field delimiter to set
	 * @since 0.1.0
	 */
	public void setFieldDelimitier(String fieldDelimitier) {
		this.fieldDelimiter = fieldDelimitier;
	}
	
	/**
	 * gives the default {@link SymbolObject}
	 *
	 * @return the default {@link SymbolObject}
	 *
	 * @since 0.1.0
	 */
	public SymbolObject getDefaultSymbol() {
		return this.defaultSymbol;
	}
	
	/**
	 * sets the default {@link SymbolObject}
	 *
	 * @param defaultSymbol the default {@link SymbolObject} to set
	 * @since 0.1.0
	 */
	public void setDefaultSymbol(SymbolObject defaultSymbol) {
		this.defaultSymbol = defaultSymbol;
	}
	
	/**
	 * gives the default label
	 *
	 * @return the default label
	 *
	 * @since 0.1.0
	 */
	public String getDefaultLabel() {
		return this.defaultLabel;
	}
	
	/**
	 * sets the default label
	 *
	 * @param defaultLabel the default label to set
	 * @since 0.1.0
	 */
	public void setDefaultLabel(String defaultLabel) {
		this.defaultLabel = defaultLabel;
	}
	
	/**
	 * gives the {@link UniqueValue}s
	 *
	 * @return the {@link UniqueValue}s
	 *
	 * @since 0.1.0
	 */
	public Set<? extends UniqueValue> getUniqueValues() {
		return this.uniqueValues;
	}
	
	/**
	 * adds an {@link UniqueValue}
	 *
	 * @param uniqueValue the {@link UniqueValue} to add
	 * @return {@code true}, if the {@link UniqueValue} was successfully added
	 *
	 * @see Set#add(Object)
	 * @since 0.1.0
	 */
	public boolean addUniqueValue(UniqueValue uniqueValue) {
		return this.uniqueValues.add(uniqueValue);
	}
	
	/**
	 * adds a {@link Set} of {@link UniqueValue}s
	 *
	 * @param uniqueValues a {@link Set} of all {@link UniqueValue}s to add
	 * @return {@code true}, if the {@link UniqueValue}s were successfully added
	 *
	 * @see Set#addAll(java.util.Collection)
	 * @since 0.1.0
	 */
	public boolean addUniqueValues(Set<? extends UniqueValue> uniqueValues) {
		return this.uniqueValues.addAll(uniqueValues);
	}
	
	/**
	 * removes an {@link UniqueValue}
	 *
	 * @param uniqueValue the {@link UniqueValue} to remove
	 * @return {@code true}, if this {@link UniqueValueRenderer} contains the {@link UniqueValue}
	 *
	 * @since 0.1.0
	 */
	public boolean removeUniqueValue(UniqueValue uniqueValue) {
		return this.uniqueValues.remove(uniqueValue);
	}
}