package de.conterra.babelfish.output;

/**
 * defines a class to create a HTML header
 *
 * @author ChrissW-R1
 * @version 0.2.3
 * @since 0.2.3
 */
public class Header {
	/**
	 * private standard constructor, to prevent initialization
	 *
	 * @since 0.2.3
	 */
	private Header() {
	}
	
	/**
	 * gives the static HTML header
	 *
	 * @param rootPath the root URL of the web application
	 * @return the header to show on any HTML site
	 *
	 * @since 0.2.3
	 */
	public static String getHeader(String rootPath) {
		String res = "<table width=\"100%\" height=\"50px\" border=\"0\">";
		res += "<tr><td align=\"left\" valign=\"center\">";
		res += "<img src=\"" + rootPath + "/res/logoLettering\" width=\"288\" height=\"50\" />";
		res += "</td><td align=\"right\" valign=\"center\">";
		res += "<img src=\"" + rootPath + "/res/conterraLogo\" width=\"284\" height=\"40\" />";
		res += "</td></tr></table>";
		
		return res;
	}
}