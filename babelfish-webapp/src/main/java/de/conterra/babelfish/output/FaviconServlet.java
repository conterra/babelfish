package de.conterra.babelfish.output;

import de.conterra.babelfish.Initializer;
import de.conterra.babelfish.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.image4j.codec.ico.ICOEncoder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * {@link Servlet}, which provides a Favicon
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
@WebServlet(description = "Servlet, which provides a Favicon", urlPatterns =
		{
				"/favicon.ico"
		})
public class FaviconServlet
		extends BaseServlet {
	/**
	 * generated serial version unique identifier
	 *
	 * @since 0.1.0
	 */
	private static final long serialVersionUID = 3L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}
	
	/**
	 * handles the {@link HttpServletRequest}, same on GET and POST
	 *
	 * @param request  the {@link HttpServletRequest}
	 * @param response the {@link HttpServletResponse}
	 * @throws IOException if any in- or output {@link Exception} occurred
	 * @see WrongHomeServlet#doGet(HttpServletRequest, HttpServletResponse)
	 * @see WrongHomeServlet#doPost(HttpServletRequest, HttpServletResponse)
	 * @since 0.1.0
	 */
	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Initializer.init(this, false);
		
		int picSize = 32;
		
		ByteArrayOutputStream outByte = new ByteArrayOutputStream();
		log.debug("Transform the icon into a byte array of ICO format.");
		ICOEncoder.write(DataUtils.toBufferedImage(Initializer.getDefaultIcon().getScaledInstance(picSize, picSize, Image.SCALE_SMOOTH)), outByte);
		byte[] data = outByte.toByteArray();
		outByte.close();
		
		log.debug("Set header information.");
		response.setContentType("image/vnd.microsoft.icon");
		response.setContentLength(data.length);
		
		OutputStream outStream = response.getOutputStream();
		log.debug("Write image byte data to output stream.");
		outStream.write(data);
		outStream.close();
	}
}