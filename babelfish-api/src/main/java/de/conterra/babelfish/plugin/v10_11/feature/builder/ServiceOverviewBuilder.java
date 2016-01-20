package de.conterra.babelfish.plugin.v10_11.feature.builder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryBuilder;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.SpatialReference;
import de.conterra.babelfish.plugin.v10_11.ServiceWrapper;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_11.feature.FeatureService;
import de.conterra.babelfish.util.ContainerEnvelope;

/**
 * defines a class to build the overview page of a {@link FeatureService}
 * 
 * @version 0.2
 * @author chwe
 * @since 0.1
 */
public class ServiceOverviewBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private ServiceOverviewBuilder()
	{
	}
	
	/**
	 * builds the overview page of a {@link FeatureService}
	 * 
	 * @since 0.1
	 * 
	 * @param service the {@link FeatureService} to build the overview of
	 * @return an {@link ObjectValue}, which contains the overview of
	 *         <code>service</code>
	 */
	public static ObjectValue build(FeatureService service)
	{
		ObjectValue result = de.conterra.babelfish.plugin.v10_02.feature.builder.ServiceOverviewBuilder.build(service);
		
		result.addContent("currentVersion", new NumberValue( (new ServiceWrapper()).getVersion()), "serviceDescription", true);
		result.addContent("hasVersionedData", new BooleanValue(service.hasVersionedData()), "layers", true);
		result.addContent("supportsDisconnectedEditing", new BooleanValue(false), "layers", true);
		result.addContent("supportedQueryFormats", new StringValue("JSON"), "layers", true);
		result.addContent("maxRecordCount", new NumberValue(service.getMaxRecordCount()), "layers", true);
		ArrayValue capas = new ArrayValue();
		capas.addValue(new StringValue("Query"));
		result.addContent("capabilities", capas, "layers", true);
		result.addContent("description", new StringValue(service.getDescription()), "layers", true);
		result.addContent("copyrightText", new StringValue(service.getCopyrightText()), "layers", true);
		
		CoordinateReferenceSystem crs = null;
		Envelope initialExtent = service.getInitialExtent();
		Set<Envelope> extents = new HashSet<>();
		boolean allowGeometryUpdates = false;
		for (FeatureLayer<? extends GeometryObject, ? extends GeometryFeatureObject<? extends GeometryObject>> layer : service.getFeatureLayers())
		{
			if ( ( !allowGeometryUpdates) && layer.allowGeometryUpdates())
				allowGeometryUpdates = true;
			
			Envelope env = (new LayerWrapper<>(layer)).getEnvelope();
			
			if (crs == null)
				crs = env.getCoordinateReferenceSystem();
			
			extents.add(env);
		}
		if (crs == null)
			crs = DefaultGeographicCRS.WGS84;
		Envelope fullExtent = new ContainerEnvelope(crs, extents);
		if (initialExtent == null)
			initialExtent = fullExtent;
		result.addContent("spatialReference", GeometryBuilder.build(new SpatialReference(crs), crs), "layers", false);
		result.addContent("initialExtent", GeometryBuilder.build(new de.conterra.babelfish.plugin.v10_02.object.geometry.Envelope(initialExtent), crs), "layers", true);
		result.addContent("fullExtent", GeometryBuilder.build(new de.conterra.babelfish.plugin.v10_02.object.geometry.Envelope(fullExtent), crs), "layers", true);
		
		result.addContent("allowGeometryUpdates", new BooleanValue(allowGeometryUpdates), "layers", false);
		// TODO result.addContent("units", new StringValue(service.));
		ObjectValue docInfo = new ObjectValue();
		Map<? extends String, ? extends String> docMap = service.getDocumentInfo();
		for (String doc : docMap.keySet())
			docInfo.addContentNotEmpty(doc, new StringValue(docMap.get(doc)));
		result.addContent("documentInfo", docInfo, "layers", true);
		Double defaultZValue = service.defaultZValue();
		result.addContentNotEmpty("enableZDefaults", new BooleanValue(Double.isNaN(defaultZValue)));
		result.addContentNotEmpty("zDefault", new NumberValue(defaultZValue));
		
		return result;
	}
}