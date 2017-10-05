package de.conterra.babelfish.sample.feature;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValue;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValueRenderer;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SimpleLineSymbol;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;
import de.conterra.babelfish.util.GeoUtils;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.iso.coordinate.LineStringImpl;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.awt.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * defines a {@link SimpleFeatureLayer} with {@link Polyline}s
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public class SimpleLineLayer
		extends SimpleFeatureLayer<Polyline, GeometryFeatureObject<Polyline>> {
	/**
	 * a {@link Set} of all {@link Polyline}s, which are in a {@link SimpleLineLayer}
	 *
	 * @since 0.1.0
	 */
	private static final Set<Feature<GeometryFeatureObject<Polyline>>> lines = new LinkedHashSet<>();
	
	static {
		CoordinateReferenceSystem crs;
		try {
			crs = GeoUtils.decodeCrs("4326");
		} catch (FactoryException e) {
			crs = null;
		}
		
		SimpleLineLayer.createLine(crs, new double[][]{
				{51.9341755, 7.6527110},
				{51.9345671, 7.6521095},
				{51.9349020, 7.6516035},
				{51.9349491, 7.6515135},
				{51.9349866, 7.6514252},
				{51.9350209, 7.6513814},
				{51.9350718, 7.6513469},
				{51.9351121, 7.6513277},
				{51.9351615, 7.6512599},
				{51.9352406, 7.6510872},
				{51.9353566, 7.6508319},
				{51.9355788, 7.6505251},
				{51.9359501, 7.6499854},
				{51.9364354, 7.6492437}
		}, "cycleway");
		SimpleLineLayer.createLine(crs, new double[][]{
				{51.9342948, 7.6529145},
				{51.9343728, 7.6527824},
				{51.9345116, 7.6525727},
				{51.9346852, 7.6523106},
				{51.9349937, 7.6518486}
		}, "street");
		SimpleLineLayer.createLine(crs, new double[][]{
				{51.9351211, 7.6530596},
				{51.9346852, 7.6523106},
				{51.9346670, 7.6522797},
				{51.9345671, 7.6521095},
				{51.9344798, 7.6519540}
		}, "street");
		SimpleLineLayer.createLine(crs, new double[][]{
				{51.9347301, 7.6536454},
				{51.9348100, 7.6535257},
				{51.9348609, 7.6534496},
				{51.9349558, 7.6536154},
				{51.9354725, 7.6528412},
				{51.9353776, 7.6526754},
				{51.9351211, 7.6530596},
				{51.9348609, 7.6534496}
		}, "street");
	}
	
	/**
	 * constructor, with all necessary attributes
	 *
	 * @param id   the unique identifier
	 * @param name the name shown to the user
	 * @param desc the layer description
	 * @since 0.1.0
	 */
	public SimpleLineLayer(int id, String name, String desc) {
		super(id, name, desc);
	}
	
	@Override
	public Class<Polyline> getGeometryType() {
		return Polyline.class;
	}
	
	@Override
	public RendererObject getRenderer() {
		UniqueValueRenderer result = new UniqueValueRenderer(new SimpleLineSymbol(SLSStyle.Solid, Color.ORANGE, 1), "Other " + this.getName());
		
		result.addField(SimpleField.TYPE_FIELD);
		
		result.addUniqueValue(new UniqueValue("cycleway", "cycleway", new SimpleLineSymbol(SLSStyle.Dash, Color.RED, 1)));
		
		return result;
	}
	
	@Override
	public Set<? extends Feature<GeometryFeatureObject<Polyline>>> getFeatures() {
		return new LinkedHashSet<>(SimpleLineLayer.lines);
	}
	
	/**
	 * creates a new {@link LineString} and adds it to the {@link Set}
	 *
	 * @param crs    the {@link CoordinateReferenceSystem}
	 * @param coords the coordinates
	 * @param type   the type {@link String}
	 * @since 0.4.0
	 */
	private static void createLine(CoordinateReferenceSystem crs, double[][] coords, String type) {
		List<Position> positions = new LinkedList<>();
		
		for (double[] coord : coords) {
			positions.add(new DirectPosition2D(crs, coord[0], coord[1]));
		}
		
		GeometryFeatureObject<Polyline> line = new GeometryFeatureObject<>(new Polyline(new LineStringImpl(positions)));
		line.addAttribute(SimpleField.TYPE_FIELD, type);
		SimpleLineLayer.lines.add(new SimpleFeature<>(line));
	}
}
