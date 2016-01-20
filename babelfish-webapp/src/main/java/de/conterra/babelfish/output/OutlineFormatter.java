package de.conterra.babelfish.output;

import java.io.Writer;
import java.util.Map;

import de.conterra.babelfish.interchange.ObjectValue;

/**
 * describes a builder to format an output
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface OutlineFormatter
{
	/**
	 * gives the content type of the output
	 * 
	 * @since 0.1
	 * 
	 * @return the MIME content type
	 */
	public String getContentType();
	
	/**
	 * gives the character encoding of the output
	 * 
	 * @since 0.1
	 * 
	 * @return the character encoding
	 */
	public String getCharacterEncoding();
	
	/**
	 * writes the output
	 * 
	 * @since 0.1
	 * 
	 * @param rootObject the object to print
	 * @param writer the {@link Writer} to write to
	 * @param parameters extra parameter to control the output
	 */
	public void write(ObjectValue rootObject, Writer writer, Map<String, String[]> parameters);
}