package de.conterra.babelfish.plugin.v10_02.feature;

/**
 * defines a popup type of {@link Layer}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @see <a href="http://help.arcgis.com/en/arcgisserver/10.0/apis/rest/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public enum PopupType {
	/**
	 * the {@link Layer} doesn't support popups
	 *
	 * @since 0.1.0
	 */
	None("None"),
	/**
	 * the popups represented as links to the content
	 *
	 * @since 0.1.0
	 */
	URL("AsURL"),
	/**
	 * the popups represented as HTML text, which is the content
	 *
	 * @since 0.1.0
	 */
	HtmlText("AsHTMLText");
	
	/**
	 * the unique id
	 *
	 * @since 0.1.0
	 */
	private final String id;
	
	/**
	 * constructor, with given id
	 *
	 * @param id the unique id
	 * @since 0.1.0
	 */
	private PopupType(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "esriServerHTMLPopupType" + this.id;
	}
}