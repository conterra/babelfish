package de.conterra.babelfish.plugin.v10_02.feature.builder;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.DateValue;
import de.conterra.babelfish.interchange.NullValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.ServiceWrapper;
import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_02.feature.Field;
import de.conterra.babelfish.plugin.v10_02.feature.Layer;
import de.conterra.babelfish.plugin.v10_02.feature.Relationship;
import de.conterra.babelfish.plugin.v10_02.feature.Table;
import de.conterra.babelfish.plugin.v10_02.feature.Template;
import de.conterra.babelfish.plugin.v10_02.feature.TimeLayer;
import de.conterra.babelfish.plugin.v10_02.feature.Type;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryBuilder;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.labeling.LabelBuilder;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererBuilder;

/**
 * a class to build the overview page of a {@link Layer}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class LayerBuilder
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(LayerBuilder.class);
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private LayerBuilder()
	{
	}
	
	/**
	 * builds the overview of a {@link Layer}
	 * 
	 * @since 0.1
	 * 
	 * @param <T> the {@link FeatureObject} type
	 * @param layer the {@link Layer} to build the overview of
	 * @param service the {@link FeatureService}, which contains
	 *        <code>layer</code>
	 * @param crs the target {@link CoordinateReferenceSystem} or
	 *        <code>null</code>, if the {@link CoordinateReferenceSystem} of the
	 *        {@link Feature}s should be used
	 * @return an {@link ObjectValue}, which contains the overview of
	 *         <code>layer</code>
	 */
	public static <T extends FeatureObject> ObjectValue build(Layer<T> layer, FeatureService service, CoordinateReferenceSystem crs)
	{
		ObjectValue result = new ObjectValue();
		
		Set<Feature<? extends FeatureObject>> allFeatures = new LinkedHashSet<>();
		LayerWrapper<T> wrapper = new LayerWrapper<>(layer);
		String name = layer.getName();
		
		result.addContentNotEmpty("currentVersion", new NumberValue( (new ServiceWrapper()).getVersion()));
		result.addContentNotEmpty("id", new NumberValue(layer.getId()));
		result.addContentNotEmpty("name", new StringValue(name));
		
		String type;
		if (layer instanceof Table)
			type = "Table";
		else
			type = "Feature Layer";
		result.addContentNotEmpty("type", new StringValue(type));
		
		result.addContentNotEmpty("description", new StringValue(layer.getDescription()));
		result.addContentNotEmpty("copyrightText", new StringValue(layer.getCopyrightText()));
		
		ArrayValue rels = new ArrayValue();
		for (Relationship<? extends FeatureObject, ? extends FeatureObject> rel : service.getRelationships())
		{
			boolean origin = false;
			boolean destination = false;
			
			Layer<? extends FeatureObject> originLayer = rel.getOriginLayer();
			Layer<? extends FeatureObject> destLayer = rel.getDestinationLayer();
			
			if (originLayer.equals(layer))
				origin = true;
			if (destLayer.equals(layer))
				destination = true;
			
			if (origin != destination)
			{
				LayerBuilder.LOGGER.debug("Found relationship " + originLayer.getName() + " --> " + destLayer.getName() + ".");
				
				ObjectValue relValue = new ObjectValue();
				
				relValue.addContentNotEmpty("id", new NumberValue(rel.getId()));
				relValue.addContentNotEmpty("name", new StringValue(rel.getName()));
				
				int relatedTableId;
				if (origin)
					relatedTableId = destLayer.getId();
				else
					relatedTableId = originLayer.getId();
				relValue.addContentNotEmpty("relatedTableId", new NumberValue(relatedTableId));
				
				rels.addValue(relValue);
			}
		}
		result.addContentNotEmpty("relationships", rels);
		
		if (layer instanceof FeatureLayer<?, ?>)
		{
			LayerBuilder.LOGGER.debug("Layer " + name + " is a feature layer.");
			
			FeatureLayer<? extends GeometryObject, ? extends GeometryFeatureObject<? extends GeometryObject>> featureLayer = (FeatureLayer<?, ?>)layer;
			
			allFeatures.addAll(featureLayer.getFeatures());
			
			result.addContentNotEmpty("geometryType", new StringValue(GeometryObject.getType(featureLayer.getGeometryType())));
			result.addContentNotEmpty("minScale", new NumberValue(featureLayer.getMinScale()));
			result.addContentNotEmpty("maxScale", new NumberValue(featureLayer.getMaxScale()));
			
			Envelope extent = wrapper.getEnvelope();
			if (extent != null)
				result.addContentNotEmpty("extent", GeometryBuilder.build(new de.conterra.babelfish.plugin.v10_02.object.geometry.Envelope(extent), crs));
			
			ObjectValue drawingInfo = new ObjectValue();
			drawingInfo.addContentNotEmpty("renderer", RendererBuilder.build(featureLayer.getRenderer()));
			drawingInfo.addContentNotEmpty("transparency", new NumberValue(featureLayer.getTranparency()));
			drawingInfo.addContentNotEmpty("labelingInfo", LabelBuilder.build(featureLayer.getLabelingInfo()));
			result.addContentNotEmpty("drawingInfo", drawingInfo);
		}
		
		if (layer instanceof TimeLayer<?>)
		{
			LayerBuilder.LOGGER.debug("Layer " + name + " have time versioned data.");
			
			TimeLayer<T> timeLayer = (TimeLayer<T>)layer;
			
			allFeatures.addAll(timeLayer.getTimeFeatures().keySet());
			
			ObjectValue timeInfo = new ObjectValue();
			timeInfo.addContentNotEmpty("startTimeField", new StringValue(timeLayer.getStartTimeField()));
			timeInfo.addContentNotEmpty("endTimeField", new StringValue(timeLayer.getEndTimeField()));
			timeInfo.addContentNotEmpty("trackIdField", new StringValue(timeLayer.getTrackIdField()));
			
			ArrayValue timeExtent = new ArrayValue();
			timeExtent.addValue(new DateValue(timeLayer.getStartTime()));
			timeExtent.addValue(new DateValue(timeLayer.getEndTime()));
			timeInfo.addContent("timeExtent", timeExtent);
			
			ObjectValue timeReference = new ObjectValue();
			timeReference.addContent("timeZone", new StringValue(DateTimeZone.UTC.getID()));
			timeReference.addContent("respectsDaylightSaving", new BooleanValue(false));
			timeInfo.addContent("timeReference", timeReference);
			
			long interval = Long.MAX_VALUE;
			Map<? extends Feature<? extends T>, ? extends DateTime> timeFeatures = timeLayer.getTimeFeatures();
			for (Feature<? extends T> feature : timeFeatures.keySet())
			{
				DateTime time = timeFeatures.get(feature);
				
				for (Feature<? extends T> innerFeature : timeFeatures.keySet())
				{
					DateTime innerTime = timeFeatures.get(innerFeature);
					long innerInterval = Math.abs(time.getMillis() - innerTime.getMillis());
					
					if (innerInterval < interval)
						interval = innerInterval;
				}
			}
			timeInfo.addContent("timeInterval", new NumberValue(interval));
			timeInfo.addContent("timeIntervalUnits", new StringValue("ms"));
			
			result.addContentNotEmpty("timeInfo", timeInfo);
		}
		
		boolean att = false;
		for (Feature<? extends FeatureObject> feature : allFeatures)
		{
			if ( ! (feature.getAttachments().isEmpty()))
			{
				att = true;
				break;
			}
		}
		result.addContent("hasAttachments", new BooleanValue(att));
		
		result.addContent("htmlPopupType", new StringValue(layer.getPopupType().toString()));
		Field objectIdField;
		if (layer.getObjectIdField() != null)
			objectIdField = layer.getObjectIdField();
		else
			objectIdField = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
		result.addContent("objectIdField", new StringValue(objectIdField.getName()));
		
		String globalIdFieldName;
		Field globalIdField = layer.getGlobalIdField();
		if (globalIdField != null)
			globalIdFieldName = globalIdField.getName();
		else
			globalIdFieldName = "";
		result.addContent("globalIdField", new StringValue(globalIdFieldName));
		
		Field displayField = layer.getDisplayField();
		if (displayField != null)
			result.addContent("displayField", new StringValue(displayField.getName()));
		else
			result.addContent("displayField", new NullValue());
		
		Field typeIdField = layer.getDisplayField();
		if (typeIdField != null)
			result.addContent("typeIdField", new StringValue(typeIdField.getName()));
		else
			result.addContent("typeIdField", new NullValue());
		
		ArrayValue fieldsValue = new ArrayValue();
		for (Field field : wrapper.getFields())
			fieldsValue.addValue(FieldBuilder.build(field));
		result.addContent("fields", fieldsValue);
		
		ArrayValue types = new ArrayValue();
		for (Type<? extends T> subType : layer.getSubTypes())
			types.addValue(TypeBuilder.build(subType, crs, objectIdField));
		result.addContent("types", types);
		
		ArrayValue templates = new ArrayValue();
		for (Template<? extends T> template : layer.getTemplates())
			templates.addValue(TemplateBuilder.build(template, crs, objectIdField));
		result.addContent("templates", templates);
		
		// TODO add dynamic capabilities (if the layer provides Editing, etc.)
		result.addContent("capabilities", new StringValue("Query"));
		
		return result;
	}
}