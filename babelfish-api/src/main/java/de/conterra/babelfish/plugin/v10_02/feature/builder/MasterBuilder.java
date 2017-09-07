package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.DataValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.*;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.FeatureWrapper;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureBuilder;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.util.DataUtils;
import de.conterra.babelfish.util.GeoUtils;
import de.conterra.babelfish.util.JsonParser;
import de.conterra.babelfish.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.josql.QueryParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * the {@link ServiceBuilder} to handle any request on a {@link FeatureService}
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class MasterBuilder
		implements ServiceBuilder {
	@Override
	public ObjectValue build(RestService service, String[] path, Map<? extends String, ? extends String> parameters)
	throws ServiceNotAvailableException, WrongRequestException,
	       BuildingException {
		if (!(service instanceof FeatureService)) {
			throw new ServiceNotAvailableException("This is not a Feature Service, but it requested as such!");
		}
		
		FeatureService featureService = (FeatureService) service;
		
		CoordinateReferenceSystem outCrs;
		String                    parameter = parameters.get("outSR");
		if (parameter == null) {
			parameter = "102100";
		}
		try {
			outCrs = GeoUtils.decodeCrs(parameter);
		} catch (FactoryException e) {
			try {
				outCrs = JsonParser.parseCrs(new JSONObject(parameter));
			} catch (JSONException e2) {
				outCrs = null;
			}
		}
		if (outCrs == null) {
			log.warn("Wasn't able to decode outSR parameter " + parameter + " as a CRS!");
		} else {
			log.debug("Decoded given outSR parameter " + parameter + " as the following CRS: " + outCrs.toWKT());
		}
		
		CoordinateReferenceSystem inCrs;
		parameter = parameters.get("inSR");
		try {
			inCrs = GeoUtils.decodeCrs(parameter);
		} catch (FactoryException e) {
			try {
				inCrs = JsonParser.parseCrs(new JSONObject(parameter));
			} catch (JSONException e2) {
				log.warn("Wasn't able to decode inSR parameter " + parameter + " as a CRS!");
				inCrs = null;
			}
		} catch (NullPointerException e) {
			log.debug("Given parameter inSR was empty.");
			inCrs = null;
		}
		if (inCrs != null) {
			log.debug("Decoded given inSR parameter " + parameter + " as the following CRS: " + inCrs.toWKT());
		}
		
		parameter = parameters.get("geometry");
		GeometryObject geometry;
		try {
			geometry = GeoUtils.parseGeometry(parameter, inCrs);
			
			if (inCrs != null) {
				log.debug("Parse geometry: " + parameter + "\r\nwith CRS: " + inCrs.toWKT());
			}
		} catch (IllegalArgumentException e) {
			if (inCrs != null) {
				log.warn("Wasn't able to parse geometry: " + parameter + "\r\nwith CRS: " + inCrs.toWKT(), e);
			}
			
			geometry = null;
		}
		
		String whereClause = parameters.get("where");
		
		Set<Integer> objectIds = new LinkedHashSet<>();
		parameter = parameters.get("objectIds");
		if (parameter != null) {
			for (String objectId : parameters.get("objectIds").replaceAll("[^0-9,]", StringUtils.EMPTY).split(",")) {
				objectIds.add(Integer.parseInt(objectId));
			}
		}
		
		if (path.length <= 0) {
			log.debug("Create information site of the service.");
			
			return ServiceOverviewBuilder.build(featureService);
		} else {
			try {
				int layerId = Integer.parseInt(path[0]);
				
				Layer<? extends FeatureObject> layer = null;
				for (Layer<? extends FeatureObject> l : featureService.getLayers()) {
					if (l.getId() == layerId) {
						layer = l;
						break;
					}
				}
				
				if (layer != null) {
					if (path.length == 1) {
						log.debug("Create layer overview.");
						
						return LayerBuilder.build(layer, featureService, outCrs);
					} else {
						if (path[1].equalsIgnoreCase("images")) {
							if (path.length == 3) {
								byte[] data;
								
								try {
									data = DataUtils.toByteArray(layer.getImages().get(path[2]));
								} catch (NullPointerException e) {
									data = new byte[0];
								}
								
								return new DataValue(data);
							} else if (path.length == 2) {
								log.warn("There is no overview of images specified in the REST API!");
							} else {
								log.warn("The image path couldn't have a sub path!");
							}
							
							return null;
						} else if (path[1].equalsIgnoreCase("query")) {
							try {
								boolean idsOnly          = false;
								String  idsOnlyParameter = "returnIdsOnly";
								if (parameters.containsKey(idsOnlyParameter)) {
									idsOnly = Boolean.parseBoolean(parameters.get(idsOnlyParameter));
								}
								boolean countOnly          = false;
								String  countOnlyParameter = "returnCountOnly";
								if (parameters.containsKey(countOnlyParameter)) {
									countOnly = Boolean.parseBoolean(parameters.get(countOnlyParameter));
								}
								
								return QueryBuilder.build(layer, outCrs, geometry, whereClause, objectIds, idsOnly, countOnly);
							} catch (QueryParseException e) {
								String msg = "The query couldn't be executed!";
								log.error(msg, e);
								throw new BuildingException(msg, e);
							}
						} else if (path[1].equalsIgnoreCase("queryRelatedRecords")) {
							try {
								int relationshipId = Integer.parseInt(parameters.get("relationshipId"));
								
								Relationship<?, ?> relationship = null;
								for (Relationship<?, ?> rel : featureService.getRelationships()) {
									if (rel.getId() == relationshipId) {
										relationship = rel;
										
										break;
									}
								}
								
								if (relationship != null) {
									return RelatedFeaturesBuilder.build(relationship, outCrs, objectIds, whereClause);
								} else {
									String msg = "Couldn't found relationship with id: " + relationshipId;
									log.error(msg);
									throw new BuildingException(msg);
								}
							} catch (NumberFormatException e) {
								String msg = "The relationship identifier have to be a valid Number!";
								log.error(msg, e);
								throw new BuildingException(msg, e);
							}
						} else {
							try {
								if (layer instanceof FeatureLayer) {
									@SuppressWarnings(
											{
													"rawtypes",
													"unchecked"
											})
									Feature<? extends FeatureObject> feature = (new LayerWrapper(layer)).getFeature(Integer.parseInt(path[1]));
									
									if (feature != null) {
										FeatureWrapper<? extends FeatureObject> featureWrapper = new FeatureWrapper<>(feature);
										
										if (path.length == 2) {
											log.debug("Create feature result.");
											
											ObjectValue result        = new ObjectValue();
											Field       objectIdField = layer.getObjectIdField();
											if (objectIdField == null) {
												objectIdField = LayerWrapper.DEFAULT_OBJECT_ID_FIELD;
											}
											result.addContent("feature", FeatureBuilder.build(feature.getFeature(), outCrs, objectIdField));
											return result;
										} else {
											if (path[2].equalsIgnoreCase("attachments")) {
												if (path.length > 3) {
													try {
														Attachment attachment = featureWrapper.getAttachment(Integer.parseInt(path[3]));
														
														if (attachment == null) {
															String msg = "Attachment " + path[3] + " not found!";
															log.error(msg);
															throw new BuildingException(msg);
														}
														
														return new DataValue(attachment.getData());
													} catch (NumberFormatException e) {
														String msg = "The attachment id have to be a Number! Requested attachment: " + path[3];
														log.error(msg, e);
														throw new BuildingException(msg, e);
													}
												} else {
													return AttachmentBuilder.build(feature);
												}
											} else if (path[2].equalsIgnoreCase("htmlPopUp")) {
												return PopupBuilder.build(feature.getPopup());
											}
											
											return null;
										}
									} else {
										String msg = "The requested feature " + path[1] + " is not available!";
										log.error(msg);
										throw new BuildingException(msg);
									}
								} else {
									String msg = "Layer " + layer.getName() + " is not a Feature Layer!";
									log.warn(msg);
									throw new BuildingException(msg);
								}
							} catch (NumberFormatException e) {
								String msg = "The feature id have to be a Number! Requested feature: " + path[1];
								log.error(msg, e);
								throw new BuildingException(msg, e);
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				String msg = "The layer id have to be a Number! Requested layer: " + path[0];
				log.error(msg, e);
				throw new BuildingException(msg, e);
			}
		}
		
		return null;
	}
}
