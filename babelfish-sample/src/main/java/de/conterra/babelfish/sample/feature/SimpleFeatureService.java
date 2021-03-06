package de.conterra.babelfish.sample.feature;

import de.conterra.babelfish.plugin.Plugin;
import de.conterra.babelfish.plugin.v10_02.feature.*;
import de.conterra.babelfish.plugin.v10_02.object.feature.FeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.sample.SimplePlugin;
import de.conterra.babelfish.util.StringUtils;

import java.awt.*;
import java.util.*;

/**
 * defines a very simple {@link FeatureService}
 *
 * @author ChrissW-R1
 * @version 0.1.1
 * @since 0.1.0
 */
public class SimpleFeatureService
		implements FeatureService {
	/**
	 * the only instance of a {@link SimpleFeatureService}<br>
	 * (singelton pattern)
	 *
	 * @since 0.1.0
	 */
	public static final  SimpleFeatureService                         INSTANCE     = new SimpleFeatureService();
	/**
	 * {@link Map} of all {@link Layer}s
	 *
	 * @since 0.1.0
	 */
	private static final Map<Integer, Layer<? extends FeatureObject>> layers       = new LinkedHashMap<>();
	/**
	 * an instance of {@link SimplePolygonLayer}
	 *
	 * @since 0.1.1
	 */
	private static final SimplePolygonLayer                           polygonLayer = new SimplePolygonLayer(2, "Polygons", StringUtils.EMPTY);
	/**
	 * an instance of {@link SimpleTable}
	 *
	 * @since 0.1.1
	 */
	private static final SimpleTable                                  table        = new SimpleTable(10, "Companies", StringUtils.EMPTY);
	
	static {
		Layer<? extends FeatureObject>[] layers = new Layer<?>[]
				{
						new SimplePointLayer(0, "Points", StringUtils.EMPTY),
						new SimpleLineLayer(1, "Lines", StringUtils.EMPTY),
						SimpleFeatureService.polygonLayer,
						SimpleFeatureService.table,
						};
		
		for (Layer<? extends FeatureObject> layer : layers) {
			SimpleFeatureService.layers.put(layer.getId(), layer);
		}
	}
	
	/**
	 * standard constructor
	 *
	 * @since 0.1.0
	 */
	private SimpleFeatureService() {
	}
	
	@Override
	public Image getIcon() {
		return null;
	}
	
	@Override
	public String getServiceDescription() {
		return "con terra GmbH and outside area";
	}
	
	@Override
	public Set<? extends Layer<? extends FeatureObject>> getLayers() {
		Set<Layer<? extends FeatureObject>> result = new LinkedHashSet<>();
		
		result.addAll(this.getFeatureLayers());
		result.addAll(this.getTables());
		
		return result;
	}
	
	@Override
	public Set<? extends FeatureLayer<?, ? extends GeometryFeatureObject<?>>> getFeatureLayers() {
		Set<FeatureLayer<?, ? extends GeometryFeatureObject<?>>> result = new LinkedHashSet<>();
		
		for (Layer<? extends FeatureObject> layer : SimpleFeatureService.layers.values()) {
			if (layer instanceof SimpleFeatureLayer<?, ?>) {
				@SuppressWarnings("unchecked")
				SimpleFeatureLayer<?, ? extends GeometryFeatureObject<?>> featureLayer = (SimpleFeatureLayer<?, ? extends GeometryFeatureObject<?>>) layer;
				result.add(featureLayer);
			}
		}
		
		return result;
	}
	
	@Override
	public Set<? extends Table<? extends FeatureObject>> getTables() {
		Set<Table<? extends FeatureObject>> result = new LinkedHashSet<>();
		
		for (Layer<? extends FeatureObject> layer : SimpleFeatureService.layers.values()) {
			if (layer instanceof Table) {
				result.add((Table<?>) layer);
			}
		}
		
		return result;
	}
	
	@Override
	public Set<? extends Relationship<? extends FeatureObject, ? extends FeatureObject>> getRelationships() {
		Set<Relationship<? extends FeatureObject, ? extends FeatureObject>> result = new HashSet<>();
		
		result.add(new SimpleRelationship(1, "Buildings_Companies", SimpleFeatureService.polygonLayer, SimpleFeatureService.table));
		
		return result;
	}
	
	@Override
	public Plugin getPlugin() {
		return SimplePlugin.getInstance();
	}
	
	@Override
	public String getId() {
		return "Simple Feature Service";
	}
}
