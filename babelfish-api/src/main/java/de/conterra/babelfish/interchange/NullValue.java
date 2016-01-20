package de.conterra.babelfish.interchange;

/**
 * defines an empty value
 * 
 * @version 0.1
 * @author chwe
 * @since 1.0
 */
public class NullValue
extends ObjectValue
{
	@Override
	public boolean isEmpty()
	{
		return true;
	}
}