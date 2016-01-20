package de.conterra.babelfish.plugin.v10_02.object.labeling;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * defines a {@link Set} of {@link LabelClass}es
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS
 *      REST API</a>
 */
public class LabelingInfo
{
	/**
	 * a {@link Set} of all {@link LabelClass}es
	 * 
	 * @since 0.1
	 */
	private final Set<LabelClass> classes = new LinkedHashSet<>();
	
	/**
	 * standard constructor
	 * 
	 * @since 0.1
	 */
	public LabelingInfo()
	{
	}
	
	/**
	 * constructor, with a given {@link Set} of {@link LabelClass}es
	 * 
	 * @since 0.1
	 * 
	 * @param classes a {@link Set} of {@link LabelClass}es, which will be added
	 */
	public LabelingInfo(Set<? extends LabelClass> classes)
	{
		this();
		this.classes.addAll(classes);
	}
	
	/**
	 * gives a {@link Set} of all {@link LabelClass}es
	 * 
	 * @since 0.1
	 * 
	 * @return a {@link Set} of all {@link LabelClass}es
	 */
	public Set<? extends LabelClass> getClasses()
	{
		return this.classes;
	}
	
	/**
	 * adds a {@link LabelClass}
	 * 
	 * @since 0.1
	 * 
	 * @param lblClass the {@link LabelClass} to add
	 * @return <code>true</code>, if the {@link LabelClass} was successfully
	 *         added
	 * @see Set#add(Object)
	 */
	public boolean addClass(LabelClass lblClass)
	{
		return this.classes.add(lblClass);
	}
	
	/**
	 * adds a {@link Set} of {@link LabelClass}es
	 * 
	 * @since 0.1
	 * 
	 * @param classes a {@link Set} of {@link LabelClass}es, which will be added
	 * @return <code>true</code>, if the {@link LabelClass}es was successfully
	 *         added
	 * @see Set#addAll(java.util.Collection)
	 */
	public boolean addClasses(Set<? extends LabelClass> classes)
	{
		return this.classes.addAll(classes);
	}
	
	/**
	 * removes a {@link LabelClass}
	 * 
	 * @since 0.1
	 * 
	 * @param lblClass the {@link LabelClass} to remove
	 * @return <code>true</code>, if this {@link LabelingInfo} had contained the
	 *         {@link LabelClass}
	 * @see Set#remove(Object)
	 */
	public boolean removeClass(LabelClass lblClass)
	{
		return this.classes.remove(lblClass);
	}
	
	/**
	 * removes all {@link LabelClass} from this {@link LabelingInfo}
	 * 
	 * @since 0.1
	 */
	public void clearClasses()
	{
		this.classes.clear();
	}
}