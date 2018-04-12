package backend.database;

/**
 * An interface that stores all the database properties.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public interface DatabaseProperties
{
	/**
	 * The name of the database
	 */
	public static final String DATABASE_NAME = "PlatformDB";

	/**
	 * The username for the database
	 */
	public static final String USERNAME = "root";

	/**
	 * The database password
	 */
	public static final String PASSWORD = "qazxsw123";

	/**
	 * Connection for the jdbc connection
	 */
	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/PlatformDB";
}
