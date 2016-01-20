package de.conterra.babelfish.plugin.v10_02.object.labeling.placement;

/**
 * defines a labeling placement
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/label.html">ArcGIS
 *      REST API</a>
 */
public abstract class LabelingPlacement
{
	@Override
	public String toString()
	{
		return this.getId();
	}
	
	/**
	 * gives the unique ESRI id
	 * 
	 * @since 0.1
	 * 
	 * @return the unique ESRI id
	 */
	public String getId()
	{
		return "esriServer";
	}
}