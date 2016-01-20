package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.Popup;

/**
 * defines a builder, which creates an {@link ObjectValue} from a {@link Popup}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class PopupBuilder
{
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private PopupBuilder()
	{
	}
	
	/**
	 * creates an {@link ObjectValue}, with information of a {@link Popup}
	 * 
	 * @since 0.1
	 * 
	 * @param popup the {@link Popup} to build
	 * @return the created {@link ObjectValue}
	 */
	public static ObjectValue build(Popup popup)
	{
		ObjectValue result = new ObjectValue();
		
		result.addContent("htmlPopupType", new StringValue(popup.getType().toString()));
		result.addContent("content", new StringValue(popup.getContent()));
		
		return result;
	}
}