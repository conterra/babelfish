package de.conterra.babelfish.plugin.v10_02.feature.wrapper;

import de.conterra.babelfish.plugin.v10_02.feature.Field;

import java.util.*;

/**
 * defines a {@link Set} of {@link Field}s, which have every name only one time
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class FieldSet
		implements Set<Field> {
	/**
	 * a {@link Set} of all {@link Field}s
	 *
	 * @since 0.1.0
	 */
	private final Set<Field> fields = new LinkedHashSet<>();
	/**
	 * a {@link Set} of all stored field names
	 *
	 * @since 0.1.0
	 */
	private final Set<String> names = new HashSet<>();
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public FieldSet() {
	}
	
	/**
	 * constructor, with given {@link Collection} of {@link Field}s to add
	 *
	 * @param fields a {@link Collection} of {@link Field}s to add
	 * @since 0.1.0
	 */
	public FieldSet(Collection<? extends Field> fields) {
		this();
		
		this.addAll(fields);
	}
	
	@Override
	public boolean add(Field e) {
		String name = e.getName();
		
		if (this.contains(name))
			return false;
		
		return this.names.add(name) && this.fields.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends Field> c) {
		boolean result = false;
		
		for (Field field : c) {
			if (this.add(field))
				result = true;
		}
		
		return result;
	}
	
	@Override
	public void clear() {
		this.names.clear();
		this.fields.clear();
	}
	
	@Override
	public boolean contains(Object o) {
		if (o instanceof String)
			return this.names.contains(o);
		
		return this.fields.contains(o);
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!(this.contains(o)))
				return false;
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof FieldSet))
			return false;
		
		return this.fields.equals(((FieldSet) o).fields);
	}
	
	@Override
	public int hashCode() {
		return this.fields.hashCode();
	}
	
	@Override
	public boolean isEmpty() {
		return this.fields.isEmpty();
	}
	
	@Override
	public Iterator<Field> iterator() {
		return this.fields.iterator();
	}
	
	@Override
	public boolean remove(Object o) {
		if (o instanceof String) {
			if (this.names.remove(o)) {
				for (Field field : this.fields) {
					if (field.getName().equalsIgnoreCase((String) o))
						return true;
				}
			}
			
			return false;
		}
		
		if (o instanceof Field) {
			Field field = (Field) o;
			
			if (this.fields.remove(field))
				return this.names.remove(field.getName());
			
			return false;
		}
		
		return false;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		
		for (Object o : c) {
			if (this.remove(o))
				result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		
		for (Field field : this.fields) {
			if (!(c.contains(field))) {
				if (this.remove(field))
					result = true;
			}
		}
		
		return result;
	}
	
	@Override
	public int size() {
		return this.fields.size();
	}
	
	@Override
	public Object[] toArray() {
		return this.fields.toArray();
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		return this.fields.toArray(a);
	}
	
	/**
	 * gives the {@link Field} of the given name
	 *
	 * @param name the name of the {@link Field} to search
	 * @return the {@link Field} with name {@code name} or {@code null}, if this {@link Set} contains no {@link Field} with the given name
	 *
	 * @since 0.1.0
	 */
	public Field get(String name) {
		for (Field field : this.fields) {
			if (field.getName().equalsIgnoreCase(name))
				return field;
		}
		
		return null;
	}
}