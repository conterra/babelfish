package de.conterra.babelfish.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * defines an utility class with data stream operations
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class DataUtils {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private DataUtils() {
	}
	
	/**
	 * encodes the data to a base64 encoded {@link String}
	 *
	 * @param data the data to encode
	 * @return the base64 encoded {@link String}
	 *
	 * @since 0.1.0
	 */
	public static String encodeBase64(byte[] data) {
		return new String(Base64.encodeBase64(data));
	}
	
	/**
	 * decodes a base64 encoded into the bytes
	 *
	 * @param data the encoded {@link String} to decode
	 * @return the original byte data
	 *
	 * @since 0.1.0
	 */
	public static byte[] decodeBase64(String data) {
		return Base64.decodeBase64(data.getBytes());
	}
	
	/**
	 * converts an {@link InputStream} to the byte data
	 *
	 * @param is the {@link InputStream} to get the byte data from
	 * @return an array, which contains the byte data
	 *
	 * @since 0.1.0
	 */
	public static byte[] toByteArray(InputStream is) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			IOUtils.copy(is, os);
		} catch (IOException e) {
			log.warn("An exception occurred while copy from input to output stream!", e);
		}
		
		byte[] res = os.toByteArray();
		
		DataUtils.closeStream(os);
		return res;
	}
	
	/**
	 * gives a byte array with the rendered data of an {@link Image} in the <a href="http://en.wikipedia.org/wiki/Portable_Network_Graphics" target="_blank">PNG</a> format
	 *
	 * @param img the {@link Image} to get the data from
	 * @return a byte array, which contains the {@link Image} data
	 *
	 * @since 0.1.0
	 */
	public static byte[] toByteArray(Image img) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(DataUtils.toBufferedImage(img), "png", os);
		} catch (IOException e) {
			log.warn("An exception occurred while writing the image to the output stream!", e);
		}
		
		byte[] result = os.toByteArray();
		
		DataUtils.closeStream(os);
		return result;
	}
	
	/**
	 * creates an {@link Image} of given raw data
	 *
	 * @param data the content data of the {@link Image}
	 * @return the {@link Image} representation of {@code data}<br>
	 * If the given data are no valid {@link Image} data, it returns a empty {@link Image}
	 *
	 * @since 0.1.0
	 */
	public static Image toImage(byte[] data) {
		BufferedImage result;
		
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			result = ImageIO.read(inputStream);
			
			DataUtils.closeStream(inputStream);
			
			log.debug("Image successfully created.");
		} catch (IllegalArgumentException | IOException e) {
			log.warn("Used empty image, because the input is not a valid one!", e);
			
			result = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		}
		
		return result;
	}
	
	/**
	 * converts an {@link Image} to a {@link BufferedImage}
	 *
	 * @param img the {@link Image} to convert
	 * @return the converted {@link Image}
	 *
	 * @since 0.1.0
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			log.debug("The given image is already a buffered image. Return the input.");
			
			return (BufferedImage) img;
		}
		
		log.debug("Create a buffered image with transparency.");
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		log.debug("Draw the image on to the buffered image.");
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		
		return bimage;
	}
	
	/**
	 * Closes streams safely
	 *
	 * @param stream the stream to close
	 * @return {@code true} if {@code stream} was closed correctly, {@code false} otherwise
	 *
	 * @since 0.4.0
	 */
	public static boolean closeStream(Closeable stream) {
		if (stream == null) {
			return true;
		}
		
		try {
			stream.close();
			return true;
		} catch (IOException e) {
			log.warn("Output stream couldn't closed right!", e);
		}
		
		return false;
	}
}
