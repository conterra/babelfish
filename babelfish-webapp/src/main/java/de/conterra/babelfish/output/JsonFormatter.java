package de.conterra.babelfish.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.conterra.babelfish.interchange.ArrayValue;
import de.conterra.babelfish.interchange.BooleanValue;
import de.conterra.babelfish.interchange.NullValue;
import de.conterra.babelfish.interchange.NumberValue;
import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.interchange.Value;

/**
 * defines an {@link OutlineFormatter} to produce a JSON {@link String}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class JsonFormatter
implements OutlineFormatter
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(JsonFormatter.class);
	
	@Override
	public String getContentType()
	{
		return "application/json";
	}
	
	@Override
	public String getCharacterEncoding()
	{
		return "UTF-8";
	}
	
	@Override
	public void write(ObjectValue rootObject, Writer writer, Map<String, String[]> parameters)
	{
		boolean pretty = false;
		
		try
		{
			if (parameters.get("f")[0].equalsIgnoreCase("pJSON"))
				pretty = true;
		}
		catch (NullPointerException | ArrayIndexOutOfBoundsException e)
		{
		}
		
		if ( !pretty)
		{
			try
			{
				if (parameters.get("pretty")[0].equalsIgnoreCase("true"))
					pretty = true;
			}
			catch (NullPointerException | ArrayIndexOutOfBoundsException e)
			{
			}
		}
		
		JsonFormatter.LOGGER.debug("Request pretty format: " + pretty);
		
		String callback = "";
		if (parameters.containsKey("callback"))
			callback = parameters.get("callback")[0];
		
		if ( ! (callback.isEmpty()))
		{
			JsonFormatter.LOGGER.debug("Write callback information from request.");
			
			try
			{
				writer.write(callback + "(");
			}
			catch (IOException e)
			{
				JsonFormatter.LOGGER.error("Error on write callback information!", e);
			}
		}
		
		try
		{
			JSONObject jsonObject = JsonFormatter.convertObject(rootObject);
			
			if (pretty)
				writer.write(jsonObject.toString(2));
			else
				jsonObject.write(writer);
		}
		catch (JSONException | IOException e)
		{
			JsonFormatter.LOGGER.error("Error on write JSON content!", e);
		}
		
		if ( ! (callback.isEmpty()))
		{
			try
			{
				writer.write(");");
			}
			catch (IOException e)
			{
				JsonFormatter.LOGGER.error("Error on write callback close pattern!", e);
			}
		}
	}
	
	/**
	 * converts any kind of {@link Value} to an {@link Object} of the
	 * JSON-Java-API
	 * 
	 * @since 0.1
	 * 
	 * @param value the {@link Value} to convert
	 * @return the JSON-Java-API-{@link Object} represents the
	 *         <code>value</code>
	 */
	public static Object convertValue(Value value)
	{
		Object result = JSONObject.NULL;
		
		if (value != null)
		{
			if (value instanceof NullValue)
				result = JSONObject.NULL;
			else if (value instanceof ObjectValue)
				result = JsonFormatter.convertObject((ObjectValue)value);
			else if (value instanceof ArrayValue)
				result = JsonFormatter.convertArray((ArrayValue)value);
			else if ( ! (value.isEmpty()))
			{
				if (value instanceof StringValue)
					result = ((StringValue)value).getValue();
				else if (value instanceof BooleanValue)
					result = ((BooleanValue)value).getValue();
				else if (value instanceof NumberValue)
					result = ((NumberValue)value).getValue();
			}
		}
		
		return result;
	}
	
	/**
	 * converts an {@link ObjectValue} to a {@link JSONObject}
	 * 
	 * @since 0.1
	 * 
	 * @param object the {@link ObjectValue} to convert
	 * @return the {@link JSONObject}, which represents the <code>object</code>
	 */
	public static JSONObject convertObject(ObjectValue object)
	{
		JSONObject jsonObject = new JSONObject();
		
		for (String key : object.getBody().keySet())
		{
			Object addValue = JsonFormatter.convertValue(object.getValue(key));
			
			if (addValue != null)
				jsonObject.put(key, addValue);
		}
		
		return jsonObject;
	}
	
	/**
	 * converts an {@link ArrayValue} to a {@link JSONArray}
	 * 
	 * @since 0.1
	 * 
	 * @param array the {@link ArrayValue} to convert
	 * @return the {@link JSONArray}, which represents the <code>array</code>
	 */
	public static JSONArray convertArray(ArrayValue array)
	{
		JSONArray jsonArray = new JSONArray();
		
		for (Value value : array.getValues())
		{
			Object addValue = JsonFormatter.convertValue(value);
			
			if (addValue != null)
				jsonArray.put(addValue);
		}
		
		return jsonArray;
	}
}