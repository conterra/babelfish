package de.conterra.babelfish.plugin.v10_02.object.renderer;

import java.util.HashSet;
import java.util.Set;

/**
 * defines a Class Breaks Renderer
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class ClassBreaksRenderer
extends RendererObject
{
	/**
	 * the field
	 * 
	 * @since 0.1
	 */
	private String field;
	/**
	 * the minimum value
	 * 
	 * @since 0.1
	 */
	private double minValue;
	/**
	 * the {@link ClassBreak}s
	 * 
	 * @since 0.1
	 */
	private final Set<ClassBreak> classBreaks = new HashSet<>();
	
	/**
	 * constructor, with given field and minimum value
	 * 
	 * @since 0.1
	 * 
	 * @param field the field
	 * @param minValue the minimum value
	 */
	public ClassBreaksRenderer(String field, double minValue)
	{
		this.field = field;
		this.minValue = minValue;
	}
	
	@Override
	public String getType()
	{
		return "classBreaks";
	}
	
	/**
	 * gives the field
	 * 
	 * @since 0.1
	 * 
	 * @return the field
	 */
	public String getField()
	{
		return this.field;
	}
	
	/**
	 * sets the field
	 * 
	 * @since 0.1
	 * 
	 * @param field the field to set
	 */
	public void setField(String field)
	{
		this.field = field;
	}
	
	/**
	 * gives the minimum value
	 * 
	 * @since 0.1
	 * 
	 * @return the minimum value
	 */
	public double getMinValue()
	{
		return this.minValue;
	}
	
	/**
	 * sets the minimum value
	 * 
	 * @since 0.1
	 * 
	 * @param minValue the minimum value to set
	 */
	public void setMinValue(double minValue)
	{
		this.minValue = minValue;
	}
	
	/**
	 * gives the {@link ClassBreak}s
	 * 
	 * @since 0.1
	 * 
	 * @return the {@link ClassBreak}s
	 */
	public Set<? extends ClassBreak> getClassBreaks()
	{
		return this.classBreaks;
	}
	
	/**
	 * adds a {@link ClassBreak}
	 * 
	 * @since 0.1
	 * 
	 * @param classBreak the {@link ClassBreak} to add
	 * @return <code>true</code>, if the {@link ClassBreak} was successfully
	 *         added
	 * @see Set#add(Object)
	 */
	public boolean addClassBreak(ClassBreak classBreak)
	{
		return this.classBreaks.add(classBreak);
	}
	
	/**
	 * adds a {@link Set} of {@link ClassBreak}s
	 * 
	 * @since 0.1
	 * 
	 * @param classBreaks the {@link Set} of all {@link ClassBreak}s to add
	 * @return <code>true</code>, if the {@link ClassBreak}s were successfully
	 *         added
	 * @see Set#addAll(java.util.Collection)
	 */
	public boolean addClassBreaks(Set<? extends ClassBreak> classBreaks)
	{
		return this.classBreaks.addAll(classBreaks);
	}
	
	/**
	 * removes a {@link ClassBreak}
	 * 
	 * @since 0.1
	 * 
	 * @param classBreak the {@link ClassBreak} to remove
	 * @return <code>true</code>, if this {@link ClassBreak} contains the
	 *         {@link ClassBreak}
	 * @see Set#remove(Object)
	 */
	public boolean removeClassBreak(ClassBreak classBreak)
	{
		return this.classBreaks.remove(classBreak);
	}
}