package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a feature service layer
 * 
 * @version 0.2
 * @author chwe
 * @since 0.1
 * 
 * @param <T> the {@link FeatureObject} type
 * @see <a
 *      href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS
 *      REST API</a>
 */
public abstract interface Layer<T extends FeatureObject>
extends de.conterra.babelfish.plugin.v10_02.feature.Layer<T>
{
	/**
	 * is this {@link Layer} visible by default?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link Layer} is visible by default
	 */
	public boolean isDefaultVisible();
	
	/**
	 * gives the field name with the creation date
	 * 
	 * @since 0.1
	 * 
	 * @return the field name with the creation date
	 */
	public String getCreationDateField();
	
	/**
	 * gives the field name with the creator name or id
	 * 
	 * @since 0.1
	 * 
	 * @return the field name with the creator name or id
	 */
	public String getCreatorField();
	
	/**
	 * gives the field name with the last edit date
	 * 
	 * @since 0.1
	 * 
	 * @return the field name with the date of last edit
	 */
	public String getEditDateField();
	
	/**
	 * gives the field name with the name or id of the last editor
	 * 
	 * @since 0.1
	 * 
	 * @return the field name with the name or id of the last editor
	 */
	public String getEditorField();
	
	/**
	 * gives the realm name
	 * 
	 * @since 0.1
	 * 
	 * @return the realm name
	 */
	public String getRealm();
	
	/**
	 * allows this {@link Layer} others to update data?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this layer allows others to update data
	 */
	public boolean allowOthersToUpdate();
	
	/**
	 * allows this {@link Layer} others to delete data?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this layer allows others to delete data
	 */
	public boolean allowOthersToDelete();
	
	/**
	 * allows this {@link Layer} others to execute queries?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this layer allows others to execute queries
	 */
	public boolean allowOthersToQuery();
	
	/**
	 * could synchronizations return changes?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if synchronizations could return changes
	 */
	public boolean syncCanReturnChanges();
	
	/**
	 * are the data in this {@link Layer} versioned?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if the {@link Layer} supports versioned data
	 * @see FeatureService#hasVersionedData()
	 */
	public boolean isDataVersioned();
	
	/**
	 * supports this {@link Layer} a rollback on failure?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link Layer} supports a rollback on
	 *         failure
	 */
	public boolean supportsRollbackOnFailureParameter();
	
	/**
	 * supports this {@link Layer} statistics?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link Layer} supports statistics
	 */
	public boolean supportsStatistics();
	
	/**
	 * supports this {@link Layer} advanced queries?
	 * 
	 * @since 0.1
	 * 
	 * @return <code>true</code>, if this {@link Layer} supports advanced
	 *         queries
	 */
	public boolean supportsAdvancedQueries();
	
	/**
	 * gives the maximum count of records
	 * 
	 * @since 0.2
	 * 
	 * @return the maximum count of records or a negative {@link Integer} to use
	 *         the same count as the {@link FeatureService}
	 */
	public int getMaxRecordCount();
}