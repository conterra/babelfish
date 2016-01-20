package de.conterra.babelfish.util;

/**
 * defines a utility class with {@link String} functions
 * 
 * @version 0.3.0
 * @author chwe
 * @since 0.3.0
 */
public class StringUtils
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.3.0
	 */
	public StringUtils()
	{
	}
	
	/**
	 * replaces all non-alphanumeric characters<br>
	 * Does not replace national characters (like &auml;, &#293; or &#355;)!
	 * 
	 * @since 0.3.0
	 * 
	 * @param content the {@link String} on which the characters would be
	 *        removed
	 * @param replacement the replacement of the non-alphanumeric characters
	 * @return <code>content</code>, with replaced non-alphanumeric characters
	 */
	public static String replaceAllNonAlphaNum(String content, String replacement)
	{
		return content.replaceAll("[^\\p{L}0-9]", replacement);
	}
}