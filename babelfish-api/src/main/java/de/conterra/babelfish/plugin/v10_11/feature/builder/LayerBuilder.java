package de.conterra.babelfish.plugin.v10_11.feature.builder;

import de.conterra.babelfish.interchange.*;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_11.feature.Layer;
import de.conterra.babelfish.plugin.v10_11.feature.Relationship;
import de.conterra.babelfish.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * a class to build the overview page of a {@link Layer}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class LayerBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private LayerBuilder() {
	}
	
	/**
	 * builds the overview of a {@link Layer}
	 *
	 * @param <T>     the {@link FeatureObject} type
	 * @param layer   the {@link Layer} to build the overview of
	 * @param service the {@link FeatureService}, which contains {@code layer}
	 * @param crs     the target {@link CoordinateReferenceSystem} or {@code null}, if the {@link CoordinateReferenceSystem} of the {@link Feature}s should be used
	 * @return an {@link ObjectValue}, which contains the overview of {@code layer}
	 *
	 * @since 0.1.0
	 */
	public static <T extends FeatureObject> ObjectValue build(Layer<T> layer, FeatureService service, CoordinateReferenceSystem crs) {
		ObjectValue result = de.conterra.babelfish.plugin.v10_02.feature.builder.LayerBuilder.build(layer, service, crs);
		
		result.addContent("defaultVisibility", new BooleanValue(layer.isDefaultVisible()), "relationships", true);
		ObjectValue editFieldsInfo = new ObjectValue();
		editFieldsInfo.addContentNotEmpty("creationDateField", new StringValue(layer.getCreationDateField()));
		editFieldsInfo.addContentNotEmpty("creatorField", new StringValue(layer.getCreatorField()));
		editFieldsInfo.addContentNotEmpty("editDateField", new StringValue(layer.getEditDateField()));
		editFieldsInfo.addContentNotEmpty("editorField", new StringValue(layer.getEditorField()));
		editFieldsInfo.addContentNotEmpty("realm", new StringValue(layer.getRealm()));
		result.addContent("editFieldsInfo", editFieldsInfo, "relationships", true);
		ObjectValue ownershipAccessControl = new ObjectValue();
		ownershipAccessControl.addContentNotEmpty("allowOthersToUpdate", new BooleanValue(layer.allowOthersToUpdate()));
		ownershipAccessControl.addContentNotEmpty("allowOthersToDelete", new BooleanValue(layer.allowOthersToDelete()));
		ownershipAccessControl.addContentNotEmpty("allowOthersToQuery", new BooleanValue(layer.allowOthersToQuery()));
		result.addContent("ownershipBasedAccessControlForFeatures", ownershipAccessControl, "relationships", true);
		result.addContent("syncCanReturnChanges", new BooleanValue(layer.syncCanReturnChanges()), "relationships", true);
		
		ArrayValue rels = (ArrayValue) (result.getValue("relationships"));
		if (rels != null) {
			for (Value relValue : rels.getValues()) {
				ObjectValue relObject = (ObjectValue) relValue;
				int         relId     = ((NumberValue) (relObject.getValue(StringUtils.EMPTY))).getValue().intValue();
				
				for (Relationship<? extends FeatureObject, ? extends FeatureObject> rel : service.getRelationships()) {
					if (rel.getId() == relId) {
						log.debug("Extends relation " + rel.getOriginLayer().getName() + " --> " + rel.getDestinationLayer().getName() + ".");
						
						if (rel.getCardinality() != null) {
							relObject.addContent("cardinality", new StringValue(rel.getCardinality().toString()));
						}
						
						String role = "esriRelRole";
						if (rel.getOriginLayer().equals(layer)) {
							role += "Origin";
						} else {
							role += "Destination";
						}
						relObject.addContent("role", new StringValue(role));
						
						relObject.addContentNotEmpty("keyField", new StringValue(rel.getKeyField()));
						relObject.addContentNotEmpty("composite", new BooleanValue(rel.isComposite()));
						
						break;
					}
				}
			}
			result.addContent("relationships", rels, "relationships", false);
		}
		
		// ToDo add directly AFTER relationships
		result.addContent("isDataVersioned", new BooleanValue(layer.isDataVersioned()), "relationships", true);
		result.addContent("supportsRollbackOnFailureParameter", new BooleanValue(layer.supportsRollbackOnFailureParameter()), "relationships", true);
		result.addContent("supportsStatistics", new BooleanValue(layer.supportsStatistics()), "relationships", true);
		result.addContent("supportsAdvancedQueries", new BooleanValue(layer.supportsAdvancedQueries()), "relationships", true);
		
		if (layer instanceof FeatureLayer<?, ?>) {
			log.debug("Layer " + layer.getName() + " is a feature layer.");
			
			FeatureLayer<? extends GeometryObject, ? extends GeometryFeatureObject<? extends GeometryObject>> featureLayer = (FeatureLayer<?, ?>) layer;
			
			result.addContent("effectiveMinScale", new NumberValue(featureLayer.getEffectiveMinScale()), "extent", true);
			result.addContent("effectiveMaxScale", new NumberValue(featureLayer.getEffectiveMaxScale()), "extent", true);
			result.addContent("hasM", new BooleanValue(false), "hasAttachments", false);
			result.addContent("hasZ", new BooleanValue((new LayerWrapper<>(featureLayer)).getEnvelope().getDimension() >= 3), "hasAttachments", false);
			
			Double defaultZValue = featureLayer.defaultZValue();
			if (defaultZValue == null) {
				defaultZValue = service.defaultZValue();
			}
			result.addContent("enableZDefaults", new BooleanValue(Double.isNaN(defaultZValue)), "hasAttachments", false);
			result.addContent("zDefault", new NumberValue(defaultZValue), "hasAttachments", true);
		}
		
		int maxRecordCount = layer.getMaxRecordCount();
		if (maxRecordCount < 0) {
			maxRecordCount = service.getMaxRecordCount();
		}
		result.addContent("maxRecordCount", new NumberValue(maxRecordCount), "capabilities", true);
		result.addContent("supportedQueryFormats", new StringValue("JSON"), "capabilities", true);
		
		return result;
	}
}
