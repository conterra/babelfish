package de.conterra.babelfish.interchange;

import java.awt.*;
import java.io.File;

/**
 * defines an {@link ObjectValue}, which supports any kind of data, to be sent to the client<br>
 * For example, to send {@link Image}s or {@link File}s
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class DataValue
		extends ObjectValue {
	/**
	 * the data, which will be sent to the client
	 *
	 * @since 0.1.0
	 */
	private final byte[] data;
	
	/**
	 * constructor, with given data
	 *
	 * @param data the data, which will be sent to the client
	 * @since 0.1.0
	 */
	public DataValue(byte[] data) {
		if (data != null)
			this.data = data;
		else
			this.data = new byte[0];
	}
	
	@Override
	public boolean isEmpty() {
		return this.data.length <= 0;
	}
	
	/**
	 * gives the data
	 *
	 * @return the data, which will be sent to the client
	 *
	 * @since 0.1.0
	 */
	public byte[] getData() {
		return this.data;
	}
}