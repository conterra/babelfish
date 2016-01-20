package de.conterra.babelfish.plugin.v10_02.feature;

/**
 * defines a popup type of {@link Layer}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 * 
 * @see <a
 *      href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS
 *      REST API</a>
 */
public enum PopupType
{
	/**
	 * the {@link Layer} doesn't support popups
	 * 
	 * @since 0.1
	 */
	None("None"),
	/**
	 * the popups represented as links to the content
	 * 
	 * @since 0.1
	 */
	URL("AsURL"),
	/**
	 * the popups represented as HTML text, which is the content
	 * 
	 * @since 0.1
	 */
	HtmlText("AsHTMLText");
	
	/**
	 * the unique id
	 * 
	 * @since 0.1
	 */
	private final String id;
	
	/**
	 * constructor, with given id
	 * 
	 * @author chwe
	 * @since 0.1
	 * 
	 * @param id the unique id
	 */
	private PopupType(String id)
	{
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "esriServerHTMLPopupType" + this.id;
	}
}