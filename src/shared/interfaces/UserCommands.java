package shared.interfaces;

import java.io.File;

/**
 * An interface that stores all general user commands.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 7, 2018
 */
public interface UserCommands
{
	public static final String CMD_LOGOUT = "LOGOUT;";

	public static final String CMD_INSERT = "INSERT;";

	public static final String CMD_REMOVE = "REMOVE;";

	public static final String CMD_RECEIVE = "RECEIVE;";

	public static final String CMD_MODIFY = "MODIFY;";

	public static final String CMD_EMAIL = "EMAIL;";

	/**
	 * The directory that stores database files. Located in user.dir/dropbox/
	 */
	public static final String DATABASE_STORAGE = System.getProperty("user.dir")
			+ File.separator + "dropbox" + File.separator;

	public static final String RECEIVE_ASSIGNMENT = "RECEIVEASSIGNMENT";

	public static final String RECEIVE_COURSES = "COURSES";
}
