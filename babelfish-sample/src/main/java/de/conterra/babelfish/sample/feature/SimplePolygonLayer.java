package de.conterra.babelfish.sample.feature;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.iso.coordinate.PolygonImpl;
import org.geotools.geometry.iso.primitive.SurfaceBoundaryImpl;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Ring;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import de.conterra.babelfish.plugin.v10_02.feature.Feature;
import de.conterra.babelfish.plugin.v10_02.feature.wrapper.LayerWrapper;
import de.conterra.babelfish.plugin.v10_02.object.feature.GeometryFeatureObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Point;
import de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon;
import de.conterra.babelfish.plugin.v10_02.object.renderer.RendererObject;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValue;
import de.conterra.babelfish.plugin.v10_02.object.renderer.UniqueValueRenderer;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SimpleFillSymbol;
import de.conterra.babelfish.plugin.v10_02.object.symbol.SimpleLineSymbol;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SFSStyle;
import de.conterra.babelfish.plugin.v10_02.object.symbol.style.SLSStyle;
import de.conterra.babelfish.util.GeoUtils;

/**
 * defines a {@link SimpleFeatureLayer} with {@link Polygon}s
 * 
 * @version 0.1.1
 * @author chwe
 * @since 0.1
 */
public class SimplePolygonLayer
extends SimpleFeatureLayer<Polygon, GeometryFeatureObject<Polygon>>
{
	/**
	 * a {@link Set} of all {@link Point}s, which are in a
	 * {@link SimplePointLayer}
	 * 
	 * @since 0.1
	 */
	private static final Set<Feature<GeometryFeatureObject<Polygon>>> polygons = new LinkedHashSet<>();
	/**
	 * the Deilmann Park<br>
	 * (home of the con terra GmbH)
	 * 
	 * @since 0.1.1
	 */
	public static final SimpleFeature<GeometryFeatureObject<Polygon>> DEILMANN_PARK;
	/**
	 * the Agentur f&uuml;r Arbeit M&uuml;nster
	 * 
	 * @since 0.1.1
	 */
	public static final SimpleFeature<GeometryFeatureObject<Polygon>> AGENTUR_FUER_ARBEIT;
	
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
		
		Position[] exterior = new Position[]
		{
				new DirectPosition2D(crs, 51.9353792, 7.6524991),
				new DirectPosition2D(crs, 51.9350517, 7.6519358),
				new DirectPosition2D(crs, 51.9349669, 7.6520647),
				new DirectPosition2D(crs, 51.9350406, 7.6521918),
				new DirectPosition2D(crs, 51.9350053, 7.6522454),
				new DirectPosition2D(crs, 51.9349904, 7.6522198),
				new DirectPosition2D(crs, 51.9349725, 7.6522471),
				new DirectPosition2D(crs, 51.9349873, 7.6522725),
				new DirectPosition2D(crs, 51.9349519, 7.6523262),
				new DirectPosition2D(crs, 51.9348782, 7.6521995),
				new DirectPosition2D(crs, 51.9347933, 7.6523287),
				new DirectPosition2D(crs, 51.9351209, 7.6528919),
				new DirectPosition2D(crs, 51.9352058, 7.652763),
				new DirectPosition2D(crs, 51.9351315, 7.6526348),
				new DirectPosition2D(crs, 51.9351501, 7.6526064),
				new DirectPosition2D(crs, 51.9351351, 7.6525807),
				new DirectPosition2D(crs, 51.9351517, 7.6525554),
				new DirectPosition2D(crs, 51.9351822, 7.6526077),
				new DirectPosition2D(crs, 51.9352001, 7.6525804),
				new DirectPosition2D(crs, 51.9351696, 7.6525282),
				new DirectPosition2D(crs, 51.9351863, 7.6525031),
				new DirectPosition2D(crs, 51.9352012, 7.6525288),
				new DirectPosition2D(crs, 51.9352201, 7.6525002),
				new DirectPosition2D(crs, 51.9352943, 7.652628),
		};
		Position[][] interior = new Position[][]
		{
				new DirectPosition[]
				{
						new DirectPosition2D(crs, 51.9351541, 7.6523868),
						new DirectPosition2D(crs, 51.9350591, 7.6522237),
						new DirectPosition2D(crs, 51.9350236, 7.6522769),
						new DirectPosition2D(crs, 51.9350384, 7.6523024),
						new DirectPosition2D(crs, 51.9350205, 7.6523297),
						new DirectPosition2D(crs, 51.9350057, 7.6523041),
						new DirectPosition2D(crs, 51.9349703, 7.652358),
						new DirectPosition2D(crs, 51.9350655, 7.6525213),
				},
		};
		List<Ring> interiorRings = new LinkedList<>(SimplePolygonLayer.createRings(interior));
		GeometryFeatureObject<Polygon> polygon = new GeometryFeatureObject<Polygon>(new Polygon(new PolygonImpl(new SurfaceBoundaryImpl(crs, GeoUtils.createRing(exterior), interiorRings))));
		polygon.addAttribute(LayerWrapper.DEFAULT_OBJECT_ID_FIELD, 11);
		polygon.addAttribute(SimpleField.TYPE_FIELD, "building");
		polygon.addAttribute(SimpleField.NAME_FIELD, "Deilmannpark");
		DEILMANN_PARK = new SimpleFeature<>(polygon);
		SimplePolygonLayer.polygons.add(SimplePolygonLayer.DEILMANN_PARK);
		
		exterior = new Position[]
		{
				new DirectPosition2D(crs, 51.9350221, 7.6530431),
				new DirectPosition2D(crs, 51.9346944, 7.6524792),
				new DirectPosition2D(crs, 51.9346095, 7.6526084),
				new DirectPosition2D(crs, 51.9346829, 7.652735),
				new DirectPosition2D(crs, 51.9346611, 7.6527679),
				new DirectPosition2D(crs, 51.9346487, 7.6527463),
				new DirectPosition2D(crs, 51.9346352, 7.6527667),
				new DirectPosition2D(crs, 51.9346332, 7.6527635),
				new DirectPosition2D(crs, 51.9346152, 7.6527909),
				new DirectPosition2D(crs, 51.9346298, 7.6528159),
				new DirectPosition2D(crs, 51.9345944, 7.6528695),
				new DirectPosition2D(crs, 51.934521, 7.6527431),
				new DirectPosition2D(crs, 51.934436, 7.652872),
				new DirectPosition2D(crs, 51.9347635, 7.6534357),
				new DirectPosition2D(crs, 51.9348484, 7.6533068),
				new DirectPosition2D(crs, 51.9347747, 7.6531799),
				new DirectPosition2D(crs, 51.9348101, 7.6531262),
				new DirectPosition2D(crs, 51.9348248, 7.6531515),
				new DirectPosition2D(crs, 51.9348427, 7.6531243),
				new DirectPosition2D(crs, 51.9348278, 7.6530985),
				new DirectPosition2D(crs, 51.9348632, 7.6530453),
				new DirectPosition2D(crs, 51.9349371, 7.6531722),
		};
		interior = new Position[][]
		{
				new DirectPosition[]
				{
						new DirectPosition2D(crs, 51.9348448, 7.6530137),
						new DirectPosition2D(crs, 51.9348095, 7.6530671),
						new DirectPosition2D(crs, 51.9347949, 7.6530417),
						new DirectPosition2D(crs, 51.934777, 7.653069),
						new DirectPosition2D(crs, 51.9347917, 7.6530945),
						new DirectPosition2D(crs, 51.9347563, 7.6531482),
						new DirectPosition2D(crs, 51.9346611, 7.6529844),
						new DirectPosition2D(crs, 51.9346832, 7.6529507),
						new DirectPosition2D(crs, 51.934692, 7.6529657),
						new DirectPosition2D(crs, 51.9346932, 7.652964),
						new DirectPosition2D(crs, 51.9346944, 7.6529659),
						new DirectPosition2D(crs, 51.9347362, 7.6529021),
						new DirectPosition2D(crs, 51.9347352, 7.6528999),
						new DirectPosition2D(crs, 51.9347364, 7.6528981),
						new DirectPosition2D(crs, 51.9347275, 7.6528829),
						new DirectPosition2D(crs, 51.9347494, 7.6528494),
				},
		};
		interiorRings = new LinkedList<>(SimplePolygonLayer.createRings(interior));
		polygon = new GeometryFeatureObject<Polygon>(new Polygon(new PolygonImpl(new SurfaceBoundaryImpl(crs, GeoUtils.createRing(exterior), interiorRings))));
		polygon.addAttribute(LayerWrapper.DEFAULT_OBJECT_ID_FIELD, 12);
		polygon.addAttribute(SimpleField.TYPE_FIELD, "building");
		polygon.addAttribute(SimpleField.NAME_FIELD, "Agentur für Arbeit");
		AGENTUR_FUER_ARBEIT = new SimpleFeature<>(polygon);
		SimplePolygonLayer.polygons.add(SimplePolygonLayer.AGENTUR_FUER_ARBEIT);
		
		exterior = new Position[]
		{
				new DirectPosition2D(crs, 51.9343282, 7.6527058),
				new DirectPosition2D(crs, 51.9349452, 7.6517654),
				new DirectPosition2D(crs, 51.9349937, 7.6518486),
				new DirectPosition2D(crs, 51.9350363, 7.6519217),
				new DirectPosition2D(crs, 51.9347514, 7.6523559),
				new DirectPosition2D(crs, 51.9351028, 7.6529594),
				new DirectPosition2D(crs, 51.9353899, 7.6525219),
				new DirectPosition2D(crs, 51.9354284, 7.6525879),
				new DirectPosition2D(crs, 51.9355491, 7.6527953),
				new DirectPosition2D(crs, 51.9349321, 7.6537358),
				new DirectPosition2D(crs, 51.93481, 7.6535257),
				new DirectPosition2D(crs, 51.9347758, 7.6534673),
				new DirectPosition2D(crs, 51.9350425, 7.6530608),
				new DirectPosition2D(crs, 51.9346918, 7.6524587),
				new DirectPosition2D(crs, 51.934423, 7.6528685),
				new DirectPosition2D(crs, 51.9343728, 7.6527824),
		};
		interior = new Position[][]
		{
		};
		interiorRings = new LinkedList<>(SimplePolygonLayer.createRings(interior));
		polygon = new GeometryFeatureObject<Polygon>(new Polygon(new PolygonImpl(new SurfaceBoundaryImpl(crs, GeoUtils.createRing(exterior), interiorRings))));
		polygon.addAttribute(LayerWrapper.DEFAULT_OBJECT_ID_FIELD, 21);
		polygon.addAttribute(SimpleField.TYPE_FIELD, "parking");
		SimplePolygonLayer.polygons.add(new SimpleFeature<>(polygon));
		
		exterior = new Position[]
		{
				new DirectPosition2D(crs, 51.9359382, 7.6513085),
				new DirectPosition2D(crs, 51.9360430, 7.6515693),
				new DirectPosition2D(crs, 51.9362547, 7.6517828),
				new DirectPosition2D(crs, 51.9363313, 7.6519439),
				new DirectPosition2D(crs, 51.9363427, 7.6521155),
				new DirectPosition2D(crs, 51.9363173, 7.6523200),
				new DirectPosition2D(crs, 51.9362809, 7.6524671),
				new DirectPosition2D(crs, 51.9362918, 7.6525871),
				new DirectPosition2D(crs, 51.9363192, 7.6526924),
				new DirectPosition2D(crs, 51.9363709, 7.6527872),
				new DirectPosition2D(crs, 51.9364571, 7.6528816),
				new DirectPosition2D(crs, 51.9366072, 7.6529448),
				new DirectPosition2D(crs, 51.9367551, 7.6529293),
				new DirectPosition2D(crs, 51.9368879, 7.6529176),
				new DirectPosition2D(crs, 51.9370215, 7.6530323),
				new DirectPosition2D(crs, 51.9370811, 7.6532516),
				new DirectPosition2D(crs, 51.9371534, 7.6533074),
				new DirectPosition2D(crs, 51.9372051, 7.6534278),
				new DirectPosition2D(crs, 51.9372495, 7.6536632),
				new DirectPosition2D(crs, 51.9373297, 7.6537690),
				new DirectPosition2D(crs, 51.9373642, 7.6538524),
				new DirectPosition2D(crs, 51.9373750, 7.6539026),
				new DirectPosition2D(crs, 51.9373118, 7.6538840),
				new DirectPosition2D(crs, 51.9372620, 7.6539432),
				new DirectPosition2D(crs, 51.9371880, 7.6539838),
				new DirectPosition2D(crs, 51.9371211, 7.6539779),
				new DirectPosition2D(crs, 51.9370567, 7.6540338),
				new DirectPosition2D(crs, 51.9368287, 7.6539184),
				new DirectPosition2D(crs, 51.9366532, 7.6539303),
				new DirectPosition2D(crs, 51.9365362, 7.6538504),
				new DirectPosition2D(crs, 51.9364752, 7.6536706),
				new DirectPosition2D(crs, 51.9363843, 7.6534975),
				new DirectPosition2D(crs, 51.9362246, 7.6532967),
				new DirectPosition2D(crs, 51.9360816, 7.6531093),
				new DirectPosition2D(crs, 51.9359878, 7.6527817),
				new DirectPosition2D(crs, 51.9359493, 7.6525448),
				new DirectPosition2D(crs, 51.9358799, 7.6524344),
				new DirectPosition2D(crs, 51.9356416, 7.6522916),
				new DirectPosition2D(crs, 51.9355569, 7.6521494),
				new DirectPosition2D(crs, 51.9355357, 7.6520591),
				new DirectPosition2D(crs, 51.9355319, 7.6516789),
				new DirectPosition2D(crs, 51.9354194, 7.6513153),
				new DirectPosition2D(crs, 51.9354294, 7.6511335),
				new DirectPosition2D(crs, 51.9355222, 7.6509656),
				new DirectPosition2D(crs, 51.9356649, 7.6508759),
				new DirectPosition2D(crs, 51.9357380, 7.6508973),
				new DirectPosition2D(crs, 51.9358275, 7.6510251),
				new DirectPosition2D(crs, 51.9358704, 7.6511354),
		};
		interior = new Position[][]
		{
		};
		interiorRings = new LinkedList<>(SimplePolygonLayer.createRings(interior));
		polygon = new GeometryFeatureObject<Polygon>(new Polygon(new PolygonImpl(new SurfaceBoundaryImpl(crs, GeoUtils.createRing(exterior), interiorRings))));
		polygon.addAttribute(LayerWrapper.DEFAULT_OBJECT_ID_FIELD, 31);
		polygon.addAttribute(SimpleField.TYPE_FIELD, "water");
		polygon.addAttribute(SimpleField.NAME_FIELD, "Friedenssee");
		SimplePolygonLayer.polygons.add(new SimpleFeature<>(polygon));
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
	public SimplePolygonLayer(int id, String name, String desc)
	{
		super(id, name, desc);
	}
	
	@Override
	public Class<Polygon> getGeometryType()
	{
		return Polygon.class;
	}
	
	@Override
	public RendererObject getRenderer()
	{
		UniqueValueRenderer result = new UniqueValueRenderer(new SimpleFillSymbol(SFSStyle.Solid, Color.GREEN, new SimpleLineSymbol(SLSStyle.Solid, Color.YELLOW, 1)), "Other " + this.getName());
		
		result.addField(SimpleField.TYPE_FIELD);
		
		result.addUniqueValue(new UniqueValue("water", "water", new SimpleFillSymbol(SFSStyle.Solid, Color.BLUE, new SimpleLineSymbol(SLSStyle.Solid, Color.YELLOW, 1))));
		result.addUniqueValue(new UniqueValue("building", "building", new SimpleFillSymbol(SFSStyle.Solid, new Color(151, 105, 63), new SimpleLineSymbol(SLSStyle.Solid, Color.BLACK, 1))));
		
		return result;
	}
	
	@Override
	public Set<? extends Feature<GeometryFeatureObject<Polygon>>> getFeatures()
	{
		return new LinkedHashSet<>(SimplePolygonLayer.polygons);
	}
	
	/**
	 * creates a {@link Collection} of {@link Ring}s from an array of linked
	 * {@link Position}s
	 * 
	 * @since 0.1
	 * 
	 * @param positions the array of linked {@link Position}s
	 * @return the {@link Collection}s of {@link Ring}s
	 */
	private static Collection<? extends Ring> createRings(Position[][] positions)
	{
		List<Ring> result = new LinkedList<>();
		
		for (Position[] pos : positions)
			result.add(GeoUtils.createRing(pos));
		
		return result;
	}
}