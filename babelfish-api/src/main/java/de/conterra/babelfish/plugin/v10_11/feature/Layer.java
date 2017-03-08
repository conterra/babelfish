package de.conterra.babelfish.plugin.v10_11.feature;

import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;

/**
 * describes a feature service layer
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.2.0
 * @see <a href="http://resources.arcgis.com/en/help/rest/apiref/fslayer.html">ArcGIS REST API</a>
 * @since 0.1.0
 */
public abstract interface Layer<T extends FeatureObject>
		extends de.conterra.babelfish.plugin.v10_02.feature.Layer<T> {
	/**
	 * is this {@link Layer} visible by default?
	 *
	 * @return {@code true}, if this {@link Layer} is visible by default
	 *
	 * @since 0.1.0
	 */
	public boolean isDefaultVisible();
	
	/**
	 * gives the field name with the creation date
	 *
	 * @return the field name with the creation date
	 *
	 * @since 0.1.0
	 */
	public String getCreationDateField();
	
	/**
	 * gives the field name with the creator name or id
	 *
	 * @return the field name with the creator name or id
	 *
	 * @since 0.1.0
	 */
	public String getCreatorField();
	
	/**
	 * gives the field name with the last edit date
	 *
	 * @return the field name with the date of last edit
	 *
	 * @since 0.1.0
	 */
	public String getEditDateField();
	
	/**
	 * gives the field name with the name or id of the last editor
	 *
	 * @return the field name with the name or id of the last editor
	 *
	 * @since 0.1.0
	 */
	public String getEditorField();
	
	/**
	 * gives the realm name
	 *
	 * @return the realm name
	 *
	 * @since 0.1.0
	 */
	public String getRealm();
	
	/**
	 * allows this {@link Layer} others to update data?
	 *
	 * @return {@code true}, if this layer allows others to update data
	 *
	 * @since 0.1.0
	 */
	public boolean allowOthersToUpdate();
	
	/**
	 * allows this {@link Layer} others to delete data?
	 *
	 * @return {@code true}, if this layer allows others to delete data
	 *
	 * @since 0.1.0
	 */
	public boolean allowOthersToDelete();
	
	/**
	 * allows this {@link Layer} others to execute queries?
	 *
	 * @return {@code true}, if this layer allows others to execute queries
	 *
	 * @since 0.1.0
	 */
	public boolean allowOthersToQuery();
	
	/**
	 * could synchronizations return changes?
	 *
	 * @return {@code true}, if synchronizations could return changes
	 *
	 * @since 0.1.0
	 */
	public boolean syncCanReturnChanges();
	
	/**
	 * are the data in this {@link Layer} versioned?
	 *
	 * @return {@code true}, if the {@link Layer} supports versioned data
	 *
	 * @see FeatureService#hasVersionedData()
	 * @since 0.1.0
	 */
	public boolean isDataVersioned();
	
	/**
	 * supports this {@link Layer} a rollback on failure?
	 *
	 * @return {@code true}, if this {@link Layer} supports a rollback on failure
	 *
	 * @since 0.1.0
	 */
	public boolean supportsRollbackOnFailureParameter();
	
	/**
	 * supports this {@link Layer} statistics?
	 *
	 * @return {@code true}, if this {@link Layer} supports statistics
	 *
	 * @since 0.1.0
	 */
	public boolean supportsStatistics();
	
	/**
	 * supports this {@link Layer} advanced queries?
	 *
	 * @return {@code true}, if this {@link Layer} supports advanced queries
	 *
	 * @since 0.1.0
	 */
	public boolean supportsAdvancedQueries();
	
	/**
	 * gives the maximum count of records
	 *
	 * @return the maximum count of records or a negative {@link Integer} to use the same count as the {@link FeatureService}
	 *
	 * @since 0.2.0
	 */
	public int getMaxRecordCount();
}