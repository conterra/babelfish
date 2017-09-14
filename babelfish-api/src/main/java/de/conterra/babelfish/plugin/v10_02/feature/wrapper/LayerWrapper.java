package de.conterra.babelfish.plugin.v10_02.feature.wrapper;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.FieldType;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.object.domain.DomainObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.util.ContainerEnvelope;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * defines a class, which provides some additional functions to a {@link Layer}
 *
 * @param <T> the {@link FeatureObject} type
 * @author ChrissW-R1
 * @version 0.3.0
 * @since 0.1.0
 */
public class LayerWrapper<T extends FeatureObject> {
	/**
	 * the default field of object identifiers<br>
	 * Used, if the user have no object id field specified
	 *
	 * @since 0.1.0
	 */
	public static final Field DEFAULT_OBJECT_ID_FIELD = new Field() {
		@Override
		public boolean isEditable() {
			return false;
		}
		
		@Override
		public FieldType getType() {
			return FieldType.OID;
		}
		
		@Override
		public String getName() {
			return "objectid";
		}
		
		@Override
		public int getLength() {
			return -1;
		}
		
		@Override
		public DomainObject getDomain() {
			return null;
		}
		
		@Override
		public String getAlias() {
			return "Object ID";
		}
	};
	/**
	 * the default {@link Field} of global identifier
	 *
	 * @since 0.3.0
	 */
	public static final Field DEFAULT_GLOBAL_ID_FIELD = new Field() {
		@Override
		public boolean isEditable() {
			return false;
		}
		
		@Override
		public FieldType getType() {
			return FieldType.GlobalID;
		}
		
		@Override
		public String getName() {
			return "globalid";
		}
		
		@Override
		public int getLength() {
			return LayerWrapper.DEFAULT_OBJECT_ID_FIELD.getLength();
		}
		
		@Override
		public DomainObject getDomain() {
			return LayerWrapper.DEFAULT_OBJECT_ID_FIELD.getDomain();
		}
		
		@Override
		public String getAlias() {
			return "Global ID";
		}
	};
	
	/**
	 * the {@link Layer} to get provide the functions to
	 *
	 * @since 0.1.0
	 */
	private final Layer<T> layer;
	
	/**
	 * standard constructor
	 *
	 * @param layer the {@link Layer} to get the information from
	 * @since 0.1.0
	 */
	public LayerWrapper(Layer<T> layer) {
		this.layer = layer;
	}
	
	/**
	 * gives a {@link Feature} by id
	 *
	 * @param id the id of the feature
	 * @return the {@link Feature} on id {@code id}, or {@code null} if the {@link Layer} have no {@link Feature} with the given id
	 *
	 * @since 0.1.0
	 */
	public Feature<T> getFeature(long id) {
		Field objectIdField = this.layer.getObjectIdField();
		if (objectIdField == null) {
			objectIdField = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
		}
		
		for (Feature<T> feature : this.layer.getFeatures()) {
			Object value = feature.getFeature().getAttribute(objectIdField);
			if (value == null) {
				value = feature.getFeature().hashCode();
			}
			
			Number number = null;
			if (value instanceof Number) {
				number = (Number) value;
			} else if (value instanceof String) {
				number = Long.parseLong((String) value);
			}
			
			if (number != null && number.longValue() == id) {
				return feature;
			}
		}
		
		return null;
	}
	
	/**
	 * gives all {@link Field}s, which are used by {@link Feature}s in the {@link Layer}
	 *
	 * @return a {@link Set} of all {@link Field}s used in the {@link Layer}
	 *
	 * @since 0.1.0
	 */
	public Set<? extends Field> getFields() {
		Set<Field> result = new FieldSet();
		
		Field field = this.layer.getObjectIdField();
		if (field == null) {
			field = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
		}
		result.add(field);
		
		field = this.layer.getGlobalIdField();
		if (field != null) {
			result.add(field);
		}
		
		field = this.layer.getDisplayField();
		if (field != null) {
			result.add(field);
		}
		
		field = this.layer.getTypeIdField();
		if (field != null) {
			result.add(field);
		}
		
		for (Feature<? extends T> feature : this.layer.getFeatures()) {
			result.addAll(feature.getFeature().getAttributes().keySet());
		}
		
		return result;
	}
	
	/**
	 * gives the extent
	 *
	 * @return the extent or {@code null}, if no {@link CoordinateReferenceSystem} could exported from the features
	 *
	 * @since 0.1.0
	 */
	public Envelope getEnvelope() {
		CoordinateReferenceSystem crs       = null;
		List<Envelope>            envelopes = new LinkedList<>();
		for (Feature<? extends T> f : this.layer.getFeatures()) {
			FeatureObject fObj = f.getFeature();
			
			if (fObj instanceof GeometryFeatureObject) {
				Envelope env = ((GeometryFeatureObject<?>) fObj).getGeometry().getEnvelope();
				
				envelopes.add(env);
				
				if (crs == null) {
					crs = env.getCoordinateReferenceSystem();
				}
			}
		}
		if (crs != null) {
			return new ContainerEnvelope(crs, envelopes);
		}
		
		return null;
	}
}
