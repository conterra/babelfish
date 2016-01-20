package de.conterra.babelfish.sample.feature;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.iso.coordinate.LineStringImpl;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Polyline;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValue;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValueRenderer;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SimpleLineSymbol;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;
import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a {@link SimpleFeatureLayer} with {@link Polyline}s
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SimpleLineLayer
extends SimpleFeatureLayer<Polyline, GeometryFeatureObject<Polyline>>
{
	/**
	 * a {@link Set} of all {@link Polyline}s, which are in a
	 * {@link SimpleLineLayer}
	 * 
	 * @since 0.1
	 */
	private static final Set<Feature<GeometryFeatureObject<Polyline>>> lines = new LinkedHashSet<>();
	
	static
	{
		CoordinateReferenceSystem crs;
		try
		{
			crs = GeoUtils.decodeCrs("4326");
		}
		catch (FactoryException e)
		{
			crs = null;
		}
		
		List<Position> positions = new LinkedList<>();
		positions.add(new DirectPosition2D(crs, 51.9341755, 7.652711));
		positions.add(new DirectPosition2D(crs, 51.9345671, 7.6521095));
		positions.add(new DirectPosition2D(crs, 51.934902, 7.6516035));
		positions.add(new DirectPosition2D(crs, 51.9349491, 7.6515135));
		positions.add(new DirectPosition2D(crs, 51.9349866, 7.6514252));
		positions.add(new DirectPosition2D(crs, 51.9350209, 7.6513814));
		positions.add(new DirectPosition2D(crs, 51.9350718, 7.6513469));
		positions.add(new DirectPosition2D(crs, 51.9351121, 7.6513277));
		positions.add(new DirectPosition2D(crs, 51.9351615, 7.6512599));
		positions.add(new DirectPosition2D(crs, 51.9352406, 7.6510872));
		positions.add(new DirectPosition2D(crs, 51.9353566, 7.6508319));
		positions.add(new DirectPosition2D(crs, 51.9355788, 7.6505251));
		positions.add(new DirectPosition2D(crs, 51.9359501, 7.6499854));
		positions.add(new DirectPosition2D(crs, 51.9364354, 7.6492437));
		GeometryFeatureObject<Polyline> line = new GeometryFeatureObject<>(new Polyline(new LineStringImpl(positions)));
		line.addAttribute(SimpleField.TYPE_FIELD, "cycleway");
		SimpleLineLayer.lines.add(new SimpleFeature<>(line));
		
		positions = new LinkedList<>();
		positions.add(new DirectPosition2D(crs, 51.9342948, 7.6529145));
		positions.add(new DirectPosition2D(crs, 51.9343728, 7.6527824));
		positions.add(new DirectPosition2D(crs, 51.9345116, 7.6525727));
		positions.add(new DirectPosition2D(crs, 51.9346852, 7.6523106));
		positions.add(new DirectPosition2D(crs, 51.9349937, 7.6518486));
		line = new GeometryFeatureObject<>(new Polyline(new LineStringImpl(positions)));
		line.addAttribute(SimpleField.TYPE_FIELD, "street");
		SimpleLineLayer.lines.add(new SimpleFeature<>(line));
		
		positions = new LinkedList<>();
		positions.add(new DirectPosition2D(crs, 51.9351211, 7.6530596));
		positions.add(new DirectPosition2D(crs, 51.9346852, 7.6523106));
		positions.add(new DirectPosition2D(crs, 51.934667, 7.6522797));
		positions.add(new DirectPosition2D(crs, 51.9345671, 7.6521095));
		positions.add(new DirectPosition2D(crs, 51.9344798, 7.651954));
		line = new GeometryFeatureObject<>(new Polyline(new LineStringImpl(positions)));
		line.addAttribute(SimpleField.TYPE_FIELD, "street");
		SimpleLineLayer.lines.add(new SimpleFeature<>(line));
		
		positions = new LinkedList<>();
		positions.add(new DirectPosition2D(crs, 51.9347301, 7.6536454));
		positions.add(new DirectPosition2D(crs, 51.93481, 7.6535257));
		positions.add(new DirectPosition2D(crs, 51.9348609, 7.6534496));
		positions.add(new DirectPosition2D(crs, 51.9349558, 7.6536154));
		positions.add(new DirectPosition2D(crs, 51.9354725, 7.6528412));
		positions.add(new DirectPosition2D(crs, 51.9353776, 7.6526754));
		positions.add(new DirectPosition2D(crs, 51.9351211, 7.6530596));
		positions.add(new DirectPosition2D(crs, 51.9348609, 7.6534496));
		line = new GeometryFeatureObject<>(new Polyline(new LineStringImpl(positions)));
		line.addAttribute(SimpleField.TYPE_FIELD, "street");
		SimpleLineLayer.lines.add(new SimpleFeature<>(line));
	}
	
	/**
	 * constructor, with all necessary attributes
	 * 
	 * @since 0.1
	 * 
	 * @param id the unique identifier
	 * @param name the name shown to the user
	 * @param desc the layer description
	 */
	public SimpleLineLayer(int id, String name, String desc)
	{
		super(id, name, desc);
	}
	
	@Override
	public Class<Polyline> getGeometryType()
	{
		return Polyline.class;
	}
	
	@Override
	public RendererObject getRenderer()
	{
		UniqueValueRenderer result = new UniqueValueRenderer(new SimpleLineSymbol(SLSStyle.Solid, Color.ORANGE, 1), "Other " + this.getName());
		
		result.addField(SimpleField.TYPE_FIELD);
		
		result.addUniqueValue(new UniqueValue("cycleway", "cycleway", new SimpleLineSymbol(SLSStyle.Dash, Color.RED, 1)));
		
		return result;
	}
	
	@Override
	public Set<? extends Feature<GeometryFeatureObject<Polyline>>> getFeatures()
	{
		return new LinkedHashSet<>(SimpleLineLayer.lines);
	}
}