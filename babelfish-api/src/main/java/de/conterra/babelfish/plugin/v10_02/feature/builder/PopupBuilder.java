package de.conterra.babelfish.plugin.v10_02.feature.builder;

import de.conterra.babelfish.interchange.ObjectValue;
import de.conterra.babelfish.interchange.StringValue;
import de.conterra.babelfish.plugin.v10_02.feature.Popup;

/**
 * defines a builder, which creates an {@link ObjectValue} from a {@link Popup}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class PopupBuilder {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.1.0
	 */
	private PopupBuilder() {
	}
	
	/**
	 * creates an {@link ObjectValue}, with information of a {@link Popup}
	 *
	 * @param popup the {@link Popup} to build
	 * @return the created {@link ObjectValue}
	 *
	 * @since 0.1.0
	 */
	public static ObjectValue build(Popup popup) {
		ObjectValue result = new ObjectValue();
		
		result.addContent("htmlPopupType", new StringValue(popup.getType().toString()));
		result.addContent("content", new StringValue(popup.getContent()));
		
		return result;
	}
}