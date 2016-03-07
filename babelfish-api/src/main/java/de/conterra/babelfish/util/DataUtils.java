package de.conterra.babelfish.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines an utility class with data stream operations
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class DataUtils
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(DataUtils.class);
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private DataUtils()
	{
	}
	
	/**
	 * encodes the data to a base64 encoded {@link String}
	 * 
	 * @since 0.1
	 * 		
	 * @param data the data to encode
	 * @return the base64 encoded {@link String}
	 */
	public static String encodeBase64(byte[] data)
	{
		return new String(Base64.encodeBase64(data));
	}
	
	/**
	 * decodes a base64 encoded into the bytes
	 * 
	 * @since 0.1
	 * 		
	 * @param data the encoded {@link String} to decode
	 * @return the original byte data
	 */
	public static byte[] decodeBase64(String data)
	{
		return Base64.decodeBase64(data.getBytes());
	}
	
	/**
	 * converts an {@link InputStream} to the byte data
	 * 
	 * @since 0.1
	 * 		
	 * @param is the {@link InputStream} to get the byte data from
	 * @return an array, which contains the byte data
	 */
	public static byte[] toByteArray(InputStream is)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try
		{
			IOUtils.copy(is, os);
		}
		catch (IOException e)
		{
			DataUtils.LOGGER.warn("An exception occurred while copy from input to output stream!", e);
		}
		
		return os.toByteArray();
	}
	
	/**
	 * gives a byte array with the rendered data of an {@link Image} in the
	 * <a href="http://en.wikipedia.org/wiki/Portable_Network_Graphics" target=
	 * "_blank">PNG</a> format
	 * 
	 * @since 0.1
	 * 		
	 * @param img the {@link Image} to get the data from
	 * @return a byte array, which contains the {@link Image} data
	 */
	public static byte[] toByteArray(Image img)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try
		{
			ImageIO.write(DataUtils.toBufferedImage(img), "png", os);
		}
		catch (IOException e)
		{
			DataUtils.LOGGER.warn("An exception occurred while writing the image to the output stream!", e);
		}
		
		byte[] result = os.toByteArray();
		
		try
		{
			os.close();
		}
		catch (IOException e)
		{
			DataUtils.LOGGER.warn("An exception occurred while close the output stream!", e);
		}
		
		return result;
	}
	
	/**
	 * creates an {@link Image} of given raw data
	 * 
	 * @since 0.1
	 * 		
	 * @param data the content data of the {@link Image}
	 * @return the {@link Image} representation of <code>data</code><br>
	 *         If the given data are no valid {@link Image} data, it returns a
	 *         empty {@link Image}
	 */
	public static Image toImage(byte[] data)
	{
		BufferedImage result;
		
		try
		{
			result = ImageIO.read(new ByteArrayInputStream(data));
			
			DataUtils.LOGGER.debug("Image successfully created.");
		}
		catch (IllegalArgumentException | IOException e)
		{
			DataUtils.LOGGER.warn("Used empty image, because the input is not a valid one!", e);
			
			result = new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
		}
		
		return result;
	}
	
	/**
	 * converts an {@link Image} to a {@link BufferedImage}
	 * 
	 * @since 0.1
	 * 		
	 * @param img the {@link Image} to convert
	 * @return the converted {@link Image}
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			DataUtils.LOGGER.debug("The given image is already a buffered image. Return the input.");
			
			return (BufferedImage)img;
		}
		
		DataUtils.LOGGER.debug("Create a buffered image with transparency.");
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		DataUtils.LOGGER.debug("Draw the image on to the buffered image.");
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		
		return bimage;
	}
}