package de.conterra.babelfish.util;

import de.conterra.babelfish.plugin.v10_02.object.geometry.SpatialReference;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.iso.coordinate.EnvelopeImpl;
import org.geotools.geometry.iso.coordinate.LineStringImpl;
import org.geotools.geometry.iso.coordinate.PolygonImpl;
import org.geotools.geometry.iso.primitive.PointImpl;
import org.geotools.geometry.iso.primitive.SurfaceBoundaryImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Polygon;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Ring;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * defines a class, which could parse {@link JSONObject}s to babelfish objects
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class JsonParser {
	/**
	 * the {@link Logger} of this class
	 *
	 * @since 0.1.0
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);
	
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private JsonParser() {
	}
	
	/**
	 * helper method, to get a {@link CoordinateReferenceSystem} from a {@link JSONObject} or a call
	 *
	 * @param json the {@link JSONObject} to parse
	 * @param crs  the {@link CoordinateReferenceSystem} to use, if the {@link JSONObject} have no
	 * @return the {@link CoordinateReferenceSystem} to use
	 *
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	private static CoordinateReferenceSystem parseCrs(JSONObject json, CoordinateReferenceSystem crs)
			throws IllegalArgumentException {
		CoordinateReferenceSystem parsedCrs;
		
		try {
			parsedCrs = JsonParser.parseCrs(json);
			
			JsonParser.LOGGER.debug("Found a CRS in the given JSON object.");
		} catch (JSONException e) {
			parsedCrs = crs;
			
			JsonParser.LOGGER.debug("Use given CRS.");
		}
		
		if (parsedCrs == null) {
			String msg = "No valid CRS found or given!";
			JsonParser.LOGGER.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
		return parsedCrs;
	}
	
	/**
	 * creates a {@link SpatialReference} from a {@link JSONObject}
	 *
	 * @param json the {@link JSONObject} to parse
	 * @return the generated {@link SpatialReference}
	 *
	 * @throws JSONException if the {@link JSONObject} is not a valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	public static CoordinateReferenceSystem parseCrs(JSONObject json)
			throws JSONException {
		String decodeString;
		
		try {
			try {
				decodeString = json.getString("wkt");
				
				JsonParser.LOGGER.debug("Found CRS as WKT.");
			} catch (JSONException e) {
				try {
					decodeString = "" + json.getInt("wkid");
					
					JsonParser.LOGGER.debug("Found CRS as WKID.");
				} catch (JSONException e2) {
					JsonParser.LOGGER.debug("Check, if the JSON object is no CRS, but contains some.");
					
					return JsonParser.parseCrs(json.getJSONObject("spatialReference"));
				}
			}
			
			return GeoUtils.decodeCrs(decodeString);
		} catch (JSONException | FactoryException e) {
			String msg = "No valid CRS found!";
			JsonParser.LOGGER.error(msg, e);
			throw new JSONException(msg);
		}
	}
	
	/**
	 * creates a {@link Point} from a {@link JSONObject}
	 *
	 * @param json the {@link JSONObject} to parse
	 * @param crs  the used {@link CoordinateReferenceSystem}<br>
	 *             Only needed, if the {@link JSONObject} contains no {@link CoordinateReferenceSystem}
	 * @return the generated {@link Point}
	 *
	 * @throws JSONException            if the {@link JSONObject} is not a valid {@link Point}
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	public static Point parsePoint(JSONObject json, CoordinateReferenceSystem crs)
			throws JSONException, IllegalArgumentException {
		return new PointImpl(new DirectPosition2D(JsonParser.parseCrs(json, crs), json.getDouble("x"), json.getDouble("y")));
	}
	
	/**
	 * creates a {@link LineString} from a {@link JSONObject}
	 *
	 * @param json the {@link JSONObject} to parse
	 * @param crs  the used {@link CoordinateReferenceSystem}<br>
	 *             Only needed, if the {@link JSONObject} contains no {@link CoordinateReferenceSystem}
	 * @return the generated {@link LineString}
	 *
	 * @throws JSONException            if the {@link JSONObject} is not a valid {@link LineString}
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	public static Collection<? extends LineString> parseMultiLine(JSONObject json, CoordinateReferenceSystem crs)
			throws JSONException, IllegalArgumentException {
		JsonParser.LOGGER.debug("Extract CRS from JSON object.");
		CoordinateReferenceSystem parsedCrs = JsonParser.parseCrs(json, crs);
		List<LineString> result = new LinkedList<>();
		
		JsonParser.LOGGER.debug("Get paths from the JSON object.");
		JSONArray paths = json.getJSONArray("paths");
		for (int i = 0; i < paths.length(); i++) {
			JSONArray path = paths.getJSONArray(i);
			List<Position> points = new LinkedList<>();
			
			JsonParser.LOGGER.debug("Found " + path.length() + " positions on path with index " + i + ".");
			
			for (int k = 0; k < path.length(); k++) {
				JSONArray point = path.getJSONArray(k);
				
				JsonParser.LOGGER.debug("Create new point from position " + k + ".");
				points.add(new DirectPosition2D(parsedCrs, point.getDouble(0), point.getDouble(1)));
			}
			
			JsonParser.LOGGER.debug("Create new line.");
			result.add(new LineStringImpl(points));
		}
		
		return result;
	}
	
	/**
	 * creates a {@link Polygon} from a {@link JSONObject}
	 *
	 * @param json the {@link JSONObject} to parse
	 * @param crs  the used {@link CoordinateReferenceSystem}<br>
	 *             Only needed, if the {@link JSONObject} contains no {@link CoordinateReferenceSystem}
	 * @return the generated {@link Polygon}
	 *
	 * @throws JSONException            if the {@link JSONObject} is not a valid {@link Polygon}
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	public static Polygon parsePolygon(JSONObject json, CoordinateReferenceSystem crs)
			throws JSONException, IllegalArgumentException {
		JsonParser.LOGGER.debug("Extract CRS from JSON object.");
		CoordinateReferenceSystem parsedCrs = JsonParser.parseCrs(json, crs);
		List<Position> points = new LinkedList<>();
		
		JsonParser.LOGGER.debug("Get rings from the JSON object.");
		JSONArray rings = json.getJSONArray("rings");
		JSONArray ring = rings.getJSONArray(0);
		JsonParser.LOGGER.debug("Found " + ring.length() + " positions of the exterior ring.");
		for (int i = 0; i < ring.length(); i++) {
			JSONArray point = ring.getJSONArray(i);
			
			JsonParser.LOGGER.debug("Create new point from position " + i + " of the exterior ring.");
			points.add(new DirectPosition2D(parsedCrs, point.getDouble(0), point.getDouble(1)));
		}
		
		JsonParser.LOGGER.debug("Create exterior ring.");
		Ring exterior = GeoUtils.createRing(points.toArray(new Position[points.size()]));
		
		List<Ring> interior = new LinkedList<>();
		for (int i = 1; i < rings.length(); i++) {
			ring = rings.getJSONArray(i);
			points = new LinkedList<>();
			
			JsonParser.LOGGER.debug("Found " + ring.length() + " positions on interior ring with index " + i + ".");
			
			for (int k = 0; k < ring.length(); k++) {
				JSONArray point = ring.getJSONArray(k);
				
				JsonParser.LOGGER.debug("Create new point from position " + k + ".");
				points.add(new DirectPosition2D(parsedCrs, point.getDouble(0), point.getDouble(1)));
			}
			
			JsonParser.LOGGER.debug("Create new interior ring.");
			interior.add(GeoUtils.createRing(points.toArray(new Position[points.size()])));
		}
		
		JsonParser.LOGGER.debug("Create new polygon.");
		return new PolygonImpl(new SurfaceBoundaryImpl(parsedCrs, exterior, interior));
	}
	
	/**
	 * creates an {@link Envelope} from a {@link JSONObject}
	 *
	 * @param json the {@link JSONObject} to parse
	 * @param crs  the used {@link CoordinateReferenceSystem}<br>
	 *             Only needed, if the {@link JSONObject} contains no {@link CoordinateReferenceSystem}
	 * @return the generated {@link Envelope}
	 *
	 * @throws JSONException            if the {@link JSONObject} is not a valid {@link Envelope}
	 * @throws IllegalArgumentException if the {@link JSONObject} has no and the call gives no valid {@link CoordinateReferenceSystem}
	 * @since 0.1.0
	 */
	public static Envelope parseEnvelope(JSONObject json, CoordinateReferenceSystem crs)
			throws JSONException, IllegalArgumentException {
		JsonParser.LOGGER.debug("Extract CRS from JSON object.");
		CoordinateReferenceSystem parsedCrs = JsonParser.parseCrs(json, crs);
		
		JsonParser.LOGGER.debug("Create new envelope.");
		return new EnvelopeImpl(
				new DirectPosition2D(parsedCrs, json.getDouble("xmin"), json.getDouble("ymin")),
				new DirectPosition2D(parsedCrs, json.getDouble("xmax"), json.getDouble("ymax")));
	}
}