package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;

import java.awt.*;
import java.io.File;

/**
 * defines an {@link Attachment} of a {@link GeometryFeatureObject}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Attachment {
	/**
	 * gives the unique identifier
	 *
	 * @return the unique id
	 *
	 * @since 0.1.0
	 */
	public int getId();
	
	/**
	 * gives the name, which will be shown to the user
	 *
	 * @return the name
	 *
	 * @since 0.1.0
	 */
	public String getName();
	
	/**
	 * gives the data (like the content of a {@link File} or an {@link Image})
	 *
	 * @return the data, which will be sent to the client for download
	 *
	 * @since 0.1.0
	 */
	public byte[] getData();
}