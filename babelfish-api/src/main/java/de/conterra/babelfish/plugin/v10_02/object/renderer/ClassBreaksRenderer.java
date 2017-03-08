package de.conterra.babelfish.plugin.v10_02.object.renderer;

import java.util.HashSet;
import java.util.Set;

/**
 * defines a Class Breaks Renderer
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class ClassBreaksRenderer
		extends RendererObject {
	/**
	 * the field
	 *
	 * @since 0.1.0
	 */
	private String field;
	/**
	 * the minimum value
	 *
	 * @since 0.1.0
	 */
	private double minValue;
	/**
	 * the {@link ClassBreak}s
	 *
	 * @since 0.1.0
	 */
	private final Set<ClassBreak> classBreaks = new HashSet<>();
	
	/**
	 * constructor, with given field and minimum value
	 *
	 * @param field    the field
	 * @param minValue the minimum value
	 * @since 0.1.0
	 */
	public ClassBreaksRenderer(String field, double minValue) {
		this.field = field;
		this.minValue = minValue;
	}
	
	@Override
	public String getType() {
		return "classBreaks";
	}
	
	/**
	 * gives the field
	 *
	 * @return the field
	 *
	 * @since 0.1.0
	 */
	public String getField() {
		return this.field;
	}
	
	/**
	 * sets the field
	 *
	 * @param field the field to set
	 * @since 0.1.0
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * gives the minimum value
	 *
	 * @return the minimum value
	 *
	 * @since 0.1.0
	 */
	public double getMinValue() {
		return this.minValue;
	}
	
	/**
	 * sets the minimum value
	 *
	 * @param minValue the minimum value to set
	 * @since 0.1.0
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * gives the {@link ClassBreak}s
	 *
	 * @return the {@link ClassBreak}s
	 *
	 * @since 0.1.0
	 */
	public Set<? extends ClassBreak> getClassBreaks() {
		return this.classBreaks;
	}
	
	/**
	 * adds a {@link ClassBreak}
	 *
	 * @param classBreak the {@link ClassBreak} to add
	 * @return {@code true}, if the {@link ClassBreak} was successfully added
	 *
	 * @see Set#add(Object)
	 * @since 0.1.0
	 */
	public boolean addClassBreak(ClassBreak classBreak) {
		return this.classBreaks.add(classBreak);
	}
	
	/**
	 * adds a {@link Set} of {@link ClassBreak}s
	 *
	 * @param classBreaks the {@link Set} of all {@link ClassBreak}s to add
	 * @return {@code true}, if the {@link ClassBreak}s were successfully added
	 *
	 * @see Set#addAll(java.util.Collection)
	 * @since 0.1.0
	 */
	public boolean addClassBreaks(Set<? extends ClassBreak> classBreaks) {
		return this.classBreaks.addAll(classBreaks);
	}
	
	/**
	 * removes a {@link ClassBreak}
	 *
	 * @param classBreak the {@link ClassBreak} to remove
	 * @return {@code true}, if this {@link ClassBreak} contains the {@link ClassBreak}
	 *
	 * @see Set#remove(Object)
	 * @since 0.1.0
	 */
	public boolean removeClassBreak(ClassBreak classBreak) {
		return this.classBreaks.remove(classBreak);
	}
}