package de.conterra.babelfish.plugin.v10_21.feature;

/**
 * defines all possible synchronization capabilities
 *
 * @author ChrissW-R1
 * @version 0.3.1
 * @since 0.3.1
 */
public enum SyncCapability {
	/**
	 * supports asynchronous synchronization
	 *
	 * @since 0.3.1
	 */
	ASync,
	/**
	 * supports overriding of existing features
	 *
	 * @since 0.3.1
	 */
	RegisteringExistingData,
	/**
	 * supports the control of the synchronization direction
	 *
	 * @since 0.3.1
	 */
	SyncDirectionControl,
	/**
	 * supports the synchronization layer by layer
	 *
	 * @since 0.3.1
	 */
	PerLayerSync,
	/**
	 * supports the synchronization by replications
	 *
	 * @since 0.3.1
	 */
	PerReplicaSync,
	/**
	 * supports a rollback on failure
	 *
	 * @since 0.3.1
	 */
	RollbackOnFailure;
}