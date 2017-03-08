package de.conterra.babelfish.util;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines an utility class, which provides some tools of MIME types
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class MimeUtils {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MimeUtils.class);
	
	/**
	 * the default type of data, which couldn't classified it more accurately
	 *
	 * @since 0.1.0
	 */
	public static final String DEFAULT_TYPE = "application/octet-stream";
	/**
	 * the type of JSON documents
	 *
	 * @since 0.1.0
	 */
	public static final String JSON_TYPE = "application/json";
	
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private MimeUtils() {
	}
	
	/**
	 * gives the MIME type of a data set
	 *
	 * @param data the data set to check
	 * @return the MIME type or {@code application/octet-stream} if {@code data} hasn't a known <a href="http://en.wikipedia.org/wiki/MIME" target="_blank">MIME</a> type
	 *
	 * @since 0.1.0
	 */
	public static String getType(byte[] data) {
		String result;
		
		try {
			MimeUtils.LOGGER.debug("Try find MIME type with Java Mime Magic Library.");
			result = Magic.getMagicMatch(data).getMimeType();
			
			if (result.equalsIgnoreCase("text/plain")) {
				MimeUtils.LOGGER.debug("MIME type is plain text. Try to classify it more accurately.");
				
				try {
					new JSONObject(new String(data));
					
					MimeUtils.LOGGER.debug("Found valid JSON document");
					
					result = MimeUtils.JSON_TYPE;
				} catch (JSONException e) {
				}
			}
		} catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
			MimeUtils.LOGGER.debug("No known MIME type found. Use default type " + MimeUtils.DEFAULT_TYPE);
			
			result = MimeUtils.DEFAULT_TYPE;
		}
		
		return result;
	}
}