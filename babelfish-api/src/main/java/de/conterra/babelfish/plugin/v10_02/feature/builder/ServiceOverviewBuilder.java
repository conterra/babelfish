package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.ServiceWrapper;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureLayer;
import de.conterra.babelfish.plugin.v10_02.feature.FeatureService;
import de.conterra.babelfish.plugin.v10_02.feature.Table;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;

/**
 * defines a class to build the overview page of a {@link FeatureService}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class ServiceOverviewBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private ServiceOverviewBuilder() {
	}
	
	/**
	 * builds the overview page of a {@link FeatureService}
	 *
	 * @param service the {@link FeatureService} to build the overview of
	 * @return an {@link ObjectValue}, which contains the overview of {@code service}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(FeatureService service) {
		ObjectValue result = new ObjectValue();
		
		result.addContentNotEmpty("currentVersion", new NumberValue((new ServiceWrapper()).getVersion()));
		result.addContentNotEmpty("serviceDescription", new StringValue(service.getServiceDescription()));
		
		ArrayValue layers = new ArrayValue();
		for (FeatureLayer<? extends GeometryObject, ? extends GeometryFeatureObject<? extends GeometryObject>> layer : service.getFeatureLayers()) {
			ObjectValue layerValue = new ObjectValue();
			layerValue.addContentNotEmpty("id", new NumberValue(layer.getId()));
			layerValue.addContentNotEmpty("name", new StringValue(layer.getName()));
			layers.addValue(layerValue);
		}
		result.addContent("layers", layers);
		
		ArrayValue tables = new ArrayValue();
		for (Table<? extends FeatureObject> table : service.getTables()) {
			ObjectValue tableValue = new ObjectValue();
			tableValue.addContentNotEmpty("id", new NumberValue(table.getId()));
			tableValue.addContentNotEmpty("name", new StringValue(table.getName()));
			tables.addValue(tableValue);
		}
		result.addContent("tables", tables);
		
		return result;
	}
}