package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * defines {@link HttpServlet}, which handles the initialization and shutdown
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
public abstract class BaseServlet
		extends HttpServlet {
	/**
	 * generated serial unique version identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 3L;
	
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		this.setDefaultHeaders(req, resp);
		this.doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		this.setDefaultHeaders(req, resp);
		this.doRequest(req, resp);
	}
	
	/**
	 * set all default headers to all {@link HttpServletResponse}s
	 *
	 * @param request  the {@link HttpServletRequest} from the client
	 * @param response the {@link HttpServletResponse}, which will be sent to the client
	 * @since 0.4.0
	 */
	public void setDefaultHeaders(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
	
	/**
	 * handles a request. Independent to the method (GET or POST).
	 *
	 * @param request  the {@link HttpServletRequest} from the client
	 * @param response the {@link HttpServletResponse}, which will be sent to the client
	 * @throws IOException if any in- or output {@link Exception} occurred
	 * @see BaseServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see BaseServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * @since 0.4.0
	 */
	protected abstract void doRequest(HttpServletRequest request, HttpServletResponse response)
	throws IOException;
}
