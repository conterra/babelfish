package de.conterra.babelfish.plugin.v10_11;

import de.conterra.babelfish.plugin.v10_02.feature.Layer;

/**
 * describes the {@link Cardinality} i.e. between two {@link Layer}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum Cardinality {
	/**
	 * a 1:1 relation
	 *
	 * @since 0.1.0
	 */
	OneToOne("OneToOne"),
	/**
	 * a 1:n relation
	 *
	 * @since 0.1.0
	 */
	OneToMany("OneToMany"),
	/**
	 * a m:n relation
	 *
	 * @since 0.1.0
	 */
	ManyToMany("ManyToMany");
	
	/**
	 * the unique identifier to the {@link Cardinality}
	 *
	 * @since 0.1.0
	 */
	private final String id;
	
	/**
	 * constructor, with given id
	 *
	 * @param id the id of the {@link Cardinality}
	 * @since 0.1.0
	 */
	private Cardinality(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "esriRelCardinality" + this.id;
	}
}