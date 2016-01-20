package de.conterra.babelfish.plugin.v10_02.feature;

/**
 * defines an empty {@link Popup}, which implements the type
 * {@link PopupType#None}
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class EmptyPopup
implements Popup
{
	@Override
	public PopupType getType()
	{
		return PopupType.None;
	}
	
	@Override
	public String getContent()
	{
		return "";
	}
}