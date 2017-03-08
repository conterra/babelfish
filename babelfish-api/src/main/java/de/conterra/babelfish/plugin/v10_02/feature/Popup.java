package de.conterra.babelfish.plugin.v10_02.feature;

/**
 * defines a HTML popup
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Popup {
	/**
	 * gives the content type
	 *
	 * @return the type of content
	 *
	 * @since 0.1.0
	 */
	public PopupType getType();
	
	/**
	 * gives the content, which could be only an URL to a HTML document at {@link PopupType#URL} or the document itself at {@link PopupType#HtmlText}
	 *
	 * @return the popup content
	 *
	 * @since 0.1.0
	 */
	public String getContent();
}