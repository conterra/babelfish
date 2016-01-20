package de.conterra.babelfish.output;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import de.conterra.babelfish.Initializer;

/**
 * defines {@link HttpServlet}, which handles the initialization and shutdown
 * 
 * @version 0.1.1
 * @author chwe
 * @since 0.1
 */
public abstract class BaseServlet
extends HttpServlet
{
	/**
	 * generated serial unique version identifier
	 * 
	 * @since 0.1
	 */
	private static final long serialVersionUID = 1387929037760889253L;
	
	@Override
	public void init()
	throws ServletException
	{
		super.init();
		
		Initializer.init(this, false);
	}
	
	@Override
	public void destroy()
	{
		Initializer.SHUTDOWN_TIMER.reschedule(0);
		
		super.destroy();
	}
}