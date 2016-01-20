package de.conterra.babelfish.interchange;

import java.awt.Image;
import java.io.File;

/**
 * defines an {@link ObjectValue}, which supports any kind of data, to be sent
 * to the client<br>
 * For example, to send {@link Image}s or {@link File}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class DataValue
extends ObjectValue
{
	/**
	 * the data, which will be sent to the client
	 * 
	 * @since 0.1
	 */
	private final byte[] data;
	
	/**
	 * constructor, with given data
	 * 
	 * @since 0.1
	 * 
	 * @param data the data, which will be sent to the client
	 */
	public DataValue(byte[] data)
	{
		if (data != null)
			this.data = data;
		else
			this.data = new byte[0];
	}
	
	@Override
	public boolean isEmpty()
	{
		return this.data.length <= 0;
	}
	
	/**
	 * gives the data
	 * 
	 * @since 0.1
	 * 
	 * @return the data, which will be sent to the client
	 */
	public byte[] getData()
	{
		return this.data;
	}
}