package de.conterra.babelfish.plugin.v10_02.feature;

/**
 * defines a HTML popup
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface Popup
{
	/**
	 * gives the content type
	 * 
	 * @since 0.1
	 * 
	 * @return the type of content
	 */
	public PopupType getType();
	
	/**
	 * gives the content, which could be only an URL to a HTML document at
	 * {@link PopupType#URL} or the document itself at
	 * {@link PopupType#HtmlText}
	 * 
	 * @since 0.1
	 * 
	 * @return the popup content
	 */
	public String getContent();
}