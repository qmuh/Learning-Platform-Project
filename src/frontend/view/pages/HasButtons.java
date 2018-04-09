package frontend.view.pages;

import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;

/**
 * An interface that declares that a component has buttons. Used to force button
 * registration functionality.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public interface HasButtons
{

	/**
	 * Components declared to have buttons should be registered to a provided
	 * hash table.
	 * 
	 * @param buttons
	 */
	public void registerButtons(Hashtable<Integer, JButton> buttons);

	/**
	 * Add
	 * 
	 * @param id
	 * @param listener
	 */
	public void addListener(Integer id, ActionListener listener);
}
