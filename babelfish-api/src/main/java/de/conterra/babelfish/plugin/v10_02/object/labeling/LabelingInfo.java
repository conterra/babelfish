package de.conterra.babelfish.plugin.v10_02.object.labeling;

import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * defines a {@link Set} of {@link LabelClass}es
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public class LabelingInfo {
	/**
	 * a {@link Set} of all {@link LabelClass}es
	 *
	 * @since 0.1.0
	 */
	@Getter
	private final Set<LabelClass> classes = new LinkedHashSet<>();
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	public LabelingInfo() {
	}
	
	/**
	 * constructor, with a given {@link Set} of {@link LabelClass}es
	 *
	 * @param classes a {@link Set} of {@link LabelClass}es, which will be added
	 * @since 0.1.0
	 */
	public LabelingInfo(Set<? extends LabelClass> classes) {
		this();
		this.classes.addAll(classes);
	}
	
	/**
	 * adds a {@link LabelClass}
	 *
	 * @param lblClass the {@link LabelClass} to add
	 * @return {@code true}, if the {@link LabelClass} was successfully added
	 *
	 * @see Set#add(Object)
	 * @since 0.1.0
	 */
	public boolean addClass(LabelClass lblClass) {
		return this.classes.add(lblClass);
	}
	
	/**
	 * adds a {@link Set} of {@link LabelClass}es
	 *
	 * @param classes a {@link Set} of {@link LabelClass}es, which will be added
	 * @return {@code true}, if the {@link LabelClass}es was successfully added
	 *
	 * @see Set#addAll(java.util.Collection)
	 * @since 0.1.0
	 */
	public boolean addClasses(Set<? extends LabelClass> classes) {
		return this.classes.addAll(classes);
	}
	
	/**
	 * removes a {@link LabelClass}
	 *
	 * @param lblClass the {@link LabelClass} to remove
	 * @return {@code true}, if this {@link LabelingInfo} had contained the {@link LabelClass}
	 *
	 * @see Set#remove(Object)
	 * @since 0.1.0
	 */
	public boolean removeClass(LabelClass lblClass) {
		return this.classes.remove(lblClass);
	}
	
	/**
	 * removes all {@link LabelClass} from this {@link LabelingInfo}
	 *
	 * @since 0.1.0
	 */
	public void clearClasses() {
		this.classes.clear();
	}
}
