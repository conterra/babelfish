package de.conterra.babelfish.plugin.v10_11.feature.builder;

import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.plugin.*;
import de.conterra.babelfish.plugin.v10_02.feature.Relationship;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_11.feature.Layer;
import de.conterra.babelfish.util.GeoUtils;
import de.conterra.babelfish.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.josql.QueryParseException;
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
		
		CoordinateReferenceSystem crs       = null;
		String                    crsString = parameters.get("outSR");
		if (crsString != null) {
			try {
				crs = GeoUtils.decodeCrs(crsString);
			} catch (FactoryException e) {
				log.debug("Couldn't decode CRS from parameter map.", e);
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
					Set<Integer> objectIds = new LinkedHashSet<>();
					String       parameter = parameters.get("objectIds");
					if (parameter != null) {
						for (String objectId : parameters.get("objectIds").replaceAll("[^0-9,]", StringUtils.EMPTY).split(",")) {
							objectIds.add(Integer.parseInt(objectId));
						}
					}
					
					String whereClause = parameters.get("where");
					
					if (path.length == 1) {
						return LayerBuilder.build(layer, featureService, crs);
					} else if ("query".equalsIgnoreCase(path[1])) {
						try {
							CoordinateReferenceSystem inCrs;
							parameter = parameters.get("inSR");
							try {
								inCrs = GeoUtils.decodeCrs(parameter);
							} catch (NullPointerException | FactoryException e) {
								inCrs = null;
							}
							
							parameter = parameters.get("geometry");
							GeometryObject geometry;
							try {
								geometry = GeoUtils.parseGeometry(parameter, inCrs);
							} catch (IllegalArgumentException e) {
								log.info("Unable to parse geometry\r\n" + parameter + "\r\nwith CRS: " + inCrs, e);
								geometry = null;
							}
							
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
							
							return QueryBuilder.build(layer, crs, geometry, whereClause, objectIds, idsOnly, countOnly);
						} catch (QueryParseException e) {
							String msg = "The query couldn't be executed!";
							log.error(msg, e);
							throw new BuildingException(msg, e);
						}
					} else if ("queryRelatedRecords".equalsIgnoreCase(path[1])) {
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
								return RelatedFeaturesBuilder.build(relationship, crs, objectIds, whereClause);
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
					}
				}
			} catch (NumberFormatException e) {
				String msg = "The layer id must be a number! Requested id: " + path[0];
				log.error(msg, e);
				throw new BuildingException(msg, e);
			}
		}
		
		return (new de.conterra.babelfish.plugin.v10_02.feature.builder.MasterBuilder()).build(featureService, path, parameters);
	}
}
