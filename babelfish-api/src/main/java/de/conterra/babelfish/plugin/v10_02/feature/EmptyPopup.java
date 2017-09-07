package de.conterra.babelfish.plugin.v10_02.feature;

import de.conterra.babelfish.util.StringUtils;

/**
 * defines an empty {@link Popup}, which implements the type {@link PopupType#None}
 *
 * @author ChrissW-R1
 * @version 0.1.0
 * @since 0.1.0
 */
public class EmptyPopup
		implements Popup {
	@Override
	public PopupType getType() {
		return PopupType.None;
	}
	
	@Override
	public String getContent() {
		return StringUtils.EMPTY;
	}
}
