package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

/**
 * defines a labeling placement
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public abstract class LabelingPlacement {
	/**
	 * prefix of the id
	 *
	 * @since 0.4.0
	 */
	public static final String ID_PREFIX = "esriServer";
	
	@Override
	public String toString() {
		return this.getId();
	}
	
	/**
	 * gives the unique ESRI id
	 *
	 * @return the unique ESRI id
	 *
	 * @since 0.1.0
	 */
	public String getId() {
		return LabelingPlacement.ID_PREFIX;
	}
}
