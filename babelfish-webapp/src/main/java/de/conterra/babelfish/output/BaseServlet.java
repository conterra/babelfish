package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * defines {@link HttpServlet}, which handles the initialization and shutdown
 *
 * @author ChrissW-R1
 * @version 0.1.1
 * @since 0.1.0
 */
public abstract class BaseServlet
		extends HttpServlet {
	/**
	 * generated serial unique version identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 1387929037760889253L;
	
	@Override
	public void init()
			throws ServletException {
		super.init();
		
		Initializer.init(this, false);
	}
	
	@Override
	public void destroy() {
		Initializer.SHUTDOWN_TIMER.reschedule(0);
		
		super.destroy();
	}
}