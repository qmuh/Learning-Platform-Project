package frontend;

import sharedobjects.Assignment;

public abstract class Table
{

	/** Implementation needed for all tables
	 * @param toAdd Object being added to the table
	 */
	abstract public void add(Object toAdd);
	
	/**
	 * Creates a table which is then later used by the table
	 */
	abstract public void createTable();

}
