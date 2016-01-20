package de.conterra.babelfish.plugin.v10_02.feature;

import java.awt.Image;
import java.io.File;

import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;

/**
 * defines an {@link Attachment} of a {@link GeometryFeatureObject}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public interface Attachment
{
	/**
	 * gives the unique identifier
	 * 
	 * @since 0.1
	 * 
	 * @return the unique id
	 */
	public int getId();
	
	/**
	 * gives the name, which will be shown to the user
	 * 
	 * @since 0.1
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * gives the data (like the content of a {@link File} or an {@link Image})
	 * 
	 * @since 0.1
	 * 
	 * @return the data, which will be sent to the client for download
	 */
	public byte[] getData();
}