package de.conterra.babelfish.util;

/**
 * defines an utility class with {@link String} functions
 *
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.3.0
 */
public class StringUtils {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.3.0
	 */
	public StringUtils() {
	}
	
	/**
	 * replaces all non-alphanumeric characters<br>
	 * Does not replace national characters (like &auml;, &#293; or &#355;)!
	 *
	 * @param content     the {@link String} on which the characters would be removed
	 * @param replacement the replacement of the non-alphanumeric characters
	 * @return {@code content}, with replaced non-alphanumeric characters
	 *
	 * @since 0.3.0
	 */
	public static String replaceAllNonAlphaNum(String content, String replacement) {
		return content.replaceAll("[^\\p{L}0-9]", replacement);
	}
}