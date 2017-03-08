package de.conterra.babelfish.output;

import de.conterra.babelfish.interchange.ObjectValue;

import java.io.Writer;
import java.util.Map;

/**
 * describes a builder to format an output
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface OutlineFormatter {
	/**
	 * gives the content type of the output
	 *
	 * @return the MIME content type
	 *
	 * @since 0.1.0
	 */
	public String getContentType();
	
	/**
	 * gives the character encoding of the output
	 *
	 * @return the character encoding
	 *
	 * @since 0.1.0
	 */
	public String getCharacterEncoding();
	
	/**
	 * writes the output
	 *
	 * @param rootObject the object to print
	 * @param writer     the {@link Writer} to write to
	 * @param parameters extra parameter to control the output
	 * @since 0.1.0
	 */
	public void write(ObjectValue rootObject, Writer writer, Map<String, String[]> parameters);
}