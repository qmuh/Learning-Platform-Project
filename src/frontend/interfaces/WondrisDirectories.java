package frontend.interfaces;

import java.io.File;

/**
 * Used for setting the directory for dropboxes
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public interface WondrisDirectories
{
	
	/**
	 * The chosen directory
	 */
	public static final String DIR_GRAPHICS = System.getProperty("user.dir")
			+ File.separator + "graphics" + File.separator;
}
