package de.conterra.babelfish.plugin.v10_11;

import de.conterra.babelfish.plugin.v10_02.feature.Layer;

/**
 * describes the {@link Cardinality} i.e. between two {@link Layer}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS
 *      REST API</a>
 */
public enum Cardinality
{
	/**
	 * a 1:1 relation
	 * 
	 * @since 0.1
	 */
	OneToOne("OneToOne"),
	/**
	 * a 1:n relation
	 * 
	 * @since 0.1
	 */
	OneToMany("OneToMany"),
	/**
	 * a m:n relation
	 * 
	 * @since 0.1
	 */
	ManyToMany("ManyToMany");
	
	/**
	 * the unique identifier to the {@link Cardinality}
	 * 
	 * @since 0.1
	 */
	private final String id;
	
	/**
	 * constructor, with given id
	 * 
	 * @since 0.1
	 * 
	 * @param id the id of the {@link Cardinality}
	 */
	private Cardinality(String id)
	{
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "esriRelCardinality" + this.id;
	}
}