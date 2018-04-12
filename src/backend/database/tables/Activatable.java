package backend.database.tables;

/**
 * Provides an interface to denote whether a table has an activable method.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 7, 2018
 */
public interface Activatable
{
	public void setActive(int id, boolean isActive);
}
