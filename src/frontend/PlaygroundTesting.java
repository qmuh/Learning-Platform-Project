package frontend;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.AssignSubItem;
import frontend.view.pages.items.StudentSubItem;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Assignment;
import shared.objects.Student;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class PlaygroundTesting extends JFrame implements ColourPalette
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(600, 600));

		JPanel background = new JPanel();
		background.setBackground(TERTIARY_COLOR);

		Assignment assignment = new Assignment(353, "Digital Circuits Lab 1", "SecretFolder",
				true, "Today");
		
		Student student1 = new Student(2001, "Jimmy", "Truong", "jimmy.truong@email.host", "222333");
		Student student2 = new Student(2002, "Trevoo", "Le", "trev@oo.le", "222333");

		Vector<Student> students = new Vector<Student>();
		students.add(student1);
		students.add(student2);
		
		Submission jimmy1 = new Submission(1, 353, 2001, "Somewhere", 0, "", "V2", "Now");

		Submission jimmy2 = new Submission(1, 353, 2001, "Somewhere", -1, "", "V2_2", "Now");
		

		Submission trevor1 = new Submission(1, 353, 2002, "Somewhere", -1, "", "v1.1", "Now");

		Submission trevor2 = new Submission(1, 353, 2002, "Somewhere", 0, "", "v0.9", "Now");
		
		
		AssignSubItem assignSubItem = new AssignSubItem(assignment, students);
		
		assignSubItem.addSubmission(jimmy1);

		assignSubItem.addSubmission(jimmy2);
		

		assignSubItem.addSubmission(trevor1);
		assignSubItem.addSubmission(trevor2);
		
		background.add(assignSubItem);
		frame.add(background);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// public static void main(String[] args)
	// {
	// PlaygroundTesting p = new PlaygroundTesting();
	// JPanel cardPanel = new JPanel();
	// CardLayout cardLayout = new CardLayout();
	// JPanel card1 = new JPanel();
	// JPanel card2 = new JPanel();
	// WButton first = new WButton("Press me");
	// first.addActionListener(new ActionListener()
	// {
	//
	// @Override
	// public void actionPerformed(ActionEvent e)
	// {
	// cardLayout.show(cardPanel, "Card 2");
	//
	// }
	// });
	// card1.add(first);
	// WButton second = new WButton("Don't Press me");
	// second.addActionListener(new ActionListener()
	// {
	//
	// @Override
	// public void actionPerformed(ActionEvent e)
	// {
	// cardLayout.show(cardPanel, "Card 1");
	// }
	// });
	// card2.add(second);
	// card1.setBackground(ColourPalette.ACCENT_COLOR);
	// card2.setBackground(ColourPalette.SECONDARY_COLOR);
	// cardPanel.setLayout(cardLayout);
	// cardPanel.add(card1, "Card 1");
	// cardPanel.add(card2, "Card 2");
	// cardLayout.show(cardPanel, "Card 1");
	//
	// p.add(cardPanel);
	// p.setSize(500, 500);
	// p.setVisible(true);
	// p.setDefaultCloseOperation(EXIT_ON_CLOSE);
	//
	// }

}
