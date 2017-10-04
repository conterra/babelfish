package de.conterra.babelfish.util;

import java.nio.charset.Charset;

/**
 * defines an utility class with {@link String} functions
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.3.0
 */
public class StringUtils {
	/**
	 * complete empty {@link String}
	 *
	 * @since 0.4.0
	 */
	public static final String  EMPTY                  = "";
	/**
	 * default UTF-8 {@link Charset}
	 *
	 * @since 0.4.0
	 */
	public static final Charset UTF8                   = Charset.forName("UTF-8");
	/**
	 * the character, which is in the RegEx group 'word' and could use as delimiter
	 *
	 * @since 0.4.0
	 */
	public static final String  NEUTRAL_WORD_DELIMITER = "_";
	/**
	 * RegEx pattern group of all non-alphanumeric characters
	 *
	 * @since 0.4.0
	 */
	public static final String  NON_ALPHA_NUM_REGEX    = "[^\\p{L}\\d]";
	
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
		return content.replaceAll(StringUtils.NON_ALPHA_NUM_REGEX, replacement);
	}
}
