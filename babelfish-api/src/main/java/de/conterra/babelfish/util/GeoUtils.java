package de.conterra.babelfish.util;

import com.vividsolutions.jts.geom.Coordinate;
import de.conterra.babelfish.plugin.v10_02.object.geometry.GeometryObject;
import de.conterra.babelfish.plugin.v10_02.object.geometry.MultiLine;
import de.conterra.babelfish.plugin.v10_02.object.geometry.SpatialReference;
import lombok.extern.slf4j.Slf4j;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.geometry.iso.coordinate.EnvelopeImpl;
import org.geotools.geometry.iso.coordinate.LineSegmentImpl;
import org.geotools.geometry.iso.primitive.CurveImpl;
import org.geotools.geometry.iso.primitive.PointImpl;
import org.geotools.geometry.iso.primitive.RingImpl;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.json.JSONException;
import org.json.JSONObject;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Ring;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * defines an utility class, which extends the functions of the <a href="http://www.geotools.org/">GeoTools</a> library
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class GeoUtils {
	/**
	 * a {@link Map} of all ESRI specific reference identifiers, mapped to the real EPSG codes
	 *
	 * @since 0.1.0
	 */
	public static final Map<Integer, Integer> ESRI_IDS            = new HashMap<>();
	/**
	 * an {@link Envelope}, which contains the whole world
	 *
	 * @since 0.4.0
	 */
	public static final Envelope              FULL_WORLD_ENVELOPE = new Envelope2D(DefaultGeographicCRS.WGS84, -180, -90, 360, 180);
	
	static {
		GeoUtils.ESRI_IDS.put(102100, 3857);
	}
	
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private GeoUtils() {
	}
	
	/**
	 * transform a {@link DirectPosition} into another {@link CoordinateReferenceSystem}
	 *
	 * @param srcPos  the {@link DirectPosition} to transform
	 * @param destCrs the {@link CoordinateReferenceSystem} to transform the {@code srcPos} into
	 * @return the transformed {@link DirectPosition} or {@code srcPos} if it is already referenced in {@code destCrs}
	 *
	 * @throws TransformException if the {@link DirectPosition} could not transformed into {@code destCrs}
	 * @since 0.1.0
	 */
	public static DirectPosition transform(DirectPosition srcPos, CoordinateReferenceSystem destCrs)
	throws TransformException {
		try {
			log.debug("Get source CRS.");
			CoordinateReferenceSystem srcCrs = srcPos.getCoordinateReferenceSystem();
			
			if (srcCrs == destCrs || srcPos.getDimension() < 2) {
				log.debug("Source and target CRS are the same. Return source position.");
				
				return srcPos;
			}
			
			DirectPosition2D pos2D = new DirectPosition2D(srcCrs, srcPos.getOrdinate(0), srcPos.getOrdinate(1));
			
			log.debug("Search for transformation model.");
			MathTransform trans = CRS.findMathTransform(srcCrs, destCrs);
			log.debug("Transform the source position to the target CRS.");
			DirectPosition destPos = trans.transform(pos2D, null);
			
			log.debug("Add ordinates of third and higher dimension.");
			for (int i = 2; i < srcPos.getDimension(); i++) {
				destPos.setOrdinate(i, srcPos.getOrdinate(i));
			}
			
			return destPos;
		} catch (NullPointerException | MismatchedDimensionException | FactoryException e) {
			String msg = e.getMessage();
			log.error(msg, e);
			throw new TransformException(msg, e);
		}
	}
	
	/**
	 * transforms multiple {@link DirectPosition}s to another {@link CoordinateReferenceSystem}
	 *
	 * @param srcPos  an array of all {@link DirectPosition}s to transform
	 * @param destCrs the destination {@link CoordinateReferenceSystem} to transform the {@code srcPos} into
	 * @return a new array with all transformed {@link DirectPosition}s
	 *
	 * @throws TransformException if any {@link DirectPosition} in {@code srcPos} couldn't transformed into {@code destCrs}
	 * @see GeoUtils#transform(DirectPosition, CoordinateReferenceSystem)
	 * @since 0.4.0
	 */
	public static DirectPosition[] transformAll(DirectPosition[] srcPos, CoordinateReferenceSystem destCrs)
	throws TransformException {
		DirectPosition[] destPos = new DirectPosition[srcPos.length];
		
		for (int i = 0; i < srcPos.length; i++) {
			destPos[i] = GeoUtils.transform(srcPos[i], destCrs);
		}
		
		return destPos;
	}
	
	/**
	 * decodes a {@link String} to a {@link CoordinateReferenceSystem}
	 *
	 * @param crsString the {@link String} to decode (could be a WKT or a WKID)
	 * @return the {@link CoordinateReferenceSystem}, represented by {@code crsString}
	 *
	 * @throws FactoryException if the {@link String} didn't represent a valid {@link CoordinateReferenceSystem}
	 * @see CRS#decode(String)
	 * @since 0.1.0
	 */
	public static CoordinateReferenceSystem decodeCrs(String crsString)
	throws FactoryException {
		String resString;
		
		try {
			int epsg = Integer.parseInt(crsString);
			
			log.debug("Given text of CRS is only a WKID.");
			
			if (GeoUtils.ESRI_IDS.containsKey(epsg)) {
				int realEpsg = GeoUtils.ESRI_IDS.get(epsg);
				
				log.debug("Changed ESRI-WKID " + epsg + " to real EPSG code " + realEpsg + ".");
				
				epsg = realEpsg;
			}
			
			resString = "EPSG:" + epsg;
		} catch (NumberFormatException e) {
			log.debug("Couldn't parse text as WKID. Try to parse it as WKT.", e);
			
			resString = crsString;
		}
		
		return CRS.decode(resString);
	}
	
	/**
	 * gives the EPSG code of the root element of a WKT
	 *
	 * @param wkt the well-known-text (WKT) to parse
	 * @return the EPSG code or a negative value, if no code was found
	 *
	 * @since 0.1.0
	 */
	public static int decodeEpsg(String wkt) {
		String str = wkt.substring(wkt.indexOf("[") + 1, wkt.lastIndexOf("]"));
		
		int     count = 0;
		boolean run   = true;
		for (int i = 0; run && i < str.length(); i++) {
			switch (str.substring(i, i + 1)) {
				case "[":
					count++;
					break;
				case "]":
					count--;
					break;
				default:
					String checkStr = str.substring(i);
					if (count <= 0 && checkStr.startsWith("AUTHORITY")) {
						log.debug("Found authority of the root WKT element.");
						
						String authStr = checkStr.substring(checkStr.indexOf("[") + 1, checkStr.indexOf("]"));
						authStr = authStr.replaceAll("[ \"]", StringUtils.EMPTY);
						String[] strs = authStr.split(",");
						
						if (strs.length >= 2 && "EPSG".equalsIgnoreCase(strs[0])) {
							try {
								return Integer.parseInt(strs[1]);
							} catch (NumberFormatException e) {
								log.error("The authority EPSG code couldn't parsed to a number.", e);
							}
						}
						
						run = false;
					}
			}
		}
		
		log.error("Couldn't found a valid authority of the root element!");
		return -1;
	}
	
	/**
	 * gives the EPSG code of a {@link CoordinateReferenceSystem}
	 *
	 * @param crs the {@link CoordinateReferenceSystem}
	 * @return the EPSG code of {@code crs} or a negative value, if no code was found
	 *
	 * @since 0.1.0
	 */
	public static int decodeEpsg(CoordinateReferenceSystem crs) {
		return GeoUtils.decodeEpsg(crs.toWKT());
	}
	
	/**
	 * creates a {@link GeometryObject} from a given {@link String}, which could
	 * be a {@link JSONObject} or a simple representation
	 *
	 * @param string the {@link String} representation of a {@link JSONObject}, a {@link Point} (format: {@code doubleX,doubleY}) or an {@link Envelope} (format: {@code doubleXmin,doubleYmin,doubleXmax,doubleYmin})
	 * @param crs    the used {@link CoordinateReferenceSystem}<br>
	 *               Only needed, if the given {@link String} contains no {@link CoordinateReferenceSystem}
	 * @return the generated {@link GeometryObject}
	 *
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem} of the given {@link String} has an unknown syntax
	 * @since 0.1.0
	 */
	public static GeometryObject parseGeometry(String string, CoordinateReferenceSystem crs)
	throws IllegalArgumentException {
		if (string != null && !(string.isEmpty())) {
			try {
				JSONObject json = new JSONObject(string);
				
				try {
					log.debug("Try parsing as polygon.");
					
					return new de.conterra.babelfish.plugin.v10_02.object.geometry.Polygon(JsonParser.parsePolygon(json, crs));
				} catch (JSONException e) {
					log.debug("The JSON object is not a valid polyon.", e);
				}
				
				try {
					log.debug("Try parsing as envelope.");
					
					return new de.conterra.babelfish.plugin.v10_02.object.geometry.Envelope(JsonParser.parseEnvelope(json, crs));
				} catch (JSONException e) {
					log.debug("The JSON object is not a valid envelope.", e);
				}
				
				try {
					log.debug("Try parsing as multi line.");
					
					return new MultiLine(JsonParser.parseMultiLine(json, crs));
				} catch (JSONException e) {
					log.debug("The JSON object is not a valid multi line.", e);
				}
				
				try {
					log.debug("Try parsing as point.");
					
					return new de.conterra.babelfish.plugin.v10_02.object.geometry.Point(JsonParser.parsePoint(json, crs));
				} catch (JSONException e) {
					log.debug("The JSON object is not a valid point.", e);
				}
				
				return new SpatialReference(JsonParser.parseCrs(json));
			} catch (JSONException | IllegalArgumentException e) {
				log.debug("The given string is not a valid JSON object.", e);
			}
			
			if (crs == null) {
				String msg = "No valid CRS found or given!";
				log.error(msg);
				throw new IllegalArgumentException(msg);
			}
			
			String[] coords = string.split(",");
			
			try {
				if (coords.length != 2) {
					throw new IndexOutOfBoundsException("The string has not exact two doubles!");
				}
				
				log.debug("Try parsing as raw point (no JSON).");
				
				return new de.conterra.babelfish.plugin.v10_02.object.geometry.Point(new PointImpl(new DirectPosition2D(crs, Double.parseDouble(coords[0]), Double.parseDouble(coords[1]))));
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				log.debug("The content is not a valid raw point.", e);
			}
			
			try {
				if (coords.length != 4) {
					throw new IndexOutOfBoundsException("The String has not exact four doubles!");
				}
				
				log.debug("Try parsing as raw envelope (no JSON).");
				
				return new de.conterra.babelfish.plugin.v10_02.object.geometry.Envelope(new EnvelopeImpl(
						new DirectPosition2D(crs, Double.parseDouble(coords[0]), Double.parseDouble(coords[1])),
						new DirectPosition2D(crs, Double.parseDouble(coords[2]), Double.parseDouble(coords[3]))
				));
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				log.debug("The content is not a valid raw envelope.", e);
			}
		}
		
		String msg = "The given String has no known syntax!\r\nGiven String: " + string;
		log.error(msg);
		throw new IllegalArgumentException(msg);
	}
	
	/**
	 * converts a {@link Position} to a JTS {@link Coordinate}
	 *
	 * @param position the {@link Position} to convert
	 * @return the {@link Coordinate} representation of {@code position}
	 *
	 * @since 0.1.0
	 */
	public static Coordinate getJtsCoordinate(Position position) {
		DirectPosition pos = position.getDirectPosition();
		
		if (pos.getDimension() >= 3) {
			log.debug("Create new 3D JTS coordinate.");
			
			return new Coordinate(pos.getOrdinate(0), pos.getOrdinate(1), pos.getOrdinate(2));
		} else {
			log.debug("Create new 2D JTS coordinate.");
			
			return new Coordinate(pos.getOrdinate(0), pos.getOrdinate(1));
		}
	}
	
	/**
	 * creates a {@link Ring} from an array of linked {@link Position}s
	 *
	 * @param positions the array of linked {@link Position}s
	 * @return the {@link Ring}
	 *
	 * @since 0.1.0
	 */
	public static Ring createRing(Position[] positions) {
		List<OrientableCurve> curves = new LinkedList<>();
		
		log.debug("Adds lines between all points to the ring.");
		for (int i = 0; i < positions.length - 1; i++) {
			curves.add(new CurveImpl(new LineSegmentImpl(positions[i].getDirectPosition(), positions[i + 1].getDirectPosition(), 0)));
		}
		if (positions.length >= 2 && !(positions[0].equals(positions[positions.length - 1]))) {
			log.debug("Adds a line from the last to the first point to the ring.");
			
			curves.add(new CurveImpl(new LineSegmentImpl(positions[positions.length - 1].getDirectPosition(), positions[0].getDirectPosition(), 0)));
		}
		
		return new RingImpl(curves);
	}
}
