package de.conterra.babelfish.output;

import de.conterra.babelfish.interchange.DataValue;
import de.conterra.babelfish.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * defines a class, wich handles the output of a {@link Byte} array to the client
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.2.3
 */
@Slf4j
public class ByteOutput {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.2.3
	 */
	private ByteOutput() {
	}
	
	/**
	 * handle the output of {@link Byte} data to the client
	 *
	 * @param dataValue the {@link DataValue} with the {@link Byte} data, which will be sent to the client
	 * @param response  the {@link HttpServletResponse} with link to the client
	 * @param filename  the name, to simulate a real {@link File}
	 * @throws IOException if any in- or output {@link Exception} occurred
	 * @since 0.2.3
	 */
	public static void output(DataValue dataValue, HttpServletResponse response, String filename)
	throws IOException {
		if (dataValue.isEmpty()) {
			String msg = "The requested ressource is an empty data set!";
			log.warn(msg);
			response.sendError(204, msg);
			return;
		}
		
		byte[] data = dataValue.getData();
		
		String mimeType;
		
		try {
			mimeType = Magic.getMagicMatch(data).getMimeType();
		} catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
			log.warn("Couldn't found MIME type for output data!", e);
			
			mimeType = "application/octet-stream";
		}
		
		log.debug("Send data ouput with MIME type " + mimeType + ".");
		
		response.setContentType(mimeType);
		response.setContentLength(data.length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(data);
		DataUtils.closeStream(outStream);
	}
}
