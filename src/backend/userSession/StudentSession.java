package backend.userSession;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.util.regex.Pattern;

import backend.database.DatabaseProperties;
import frontend.interfaces.WondrisDirectories;
import shared.interfaces.StudentCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.Grade;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.Submission;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class StudentSession extends ClientSession
		implements StudentCommands, WondrisDirectories
{

	/**
	 * The student associated with this session
	 */
	private Student student;

	/**
	 * Initializes this session with a socket and calls super
	 *
	 * @param socket
	 *            The socket given to the super
	 */
	public StudentSession(Socket socket)
	{
		super(socket);
	}

	@Override
	public void write()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean interpretMessage(SendMessage<?> command)
	{
		String interpreter[] = command.getCommand().split(";");
		String commandType = interpreter[0] + ";";

		if (commandType.equals(CMD_INSERT))
		{
			handleInsert(interpreter[1], command.getContents());

		} else if (commandType.equals(CMD_REMOVE))
		{
			System.err.println("!---------------------------------------!");
			System.err.println(
					"The command: " + command + " has not been implemented");
			System.err.println("\t\t - " + commandType);
			System.err.println("!---------------------------------------!");

		} else if (commandType.equals(CMD_RECEIVE))
		{
			handleRecieve(interpreter[1], command.getContents());

		} else if (commandType.equals(CMD_MODIFY))
		{
			handleModify(interpreter[1], command.getContents());

		} else if (commandType.equals(CMD_EMAIL))
		{

			super.handleEmail((EmailInfo) command.getContents());

		} else if (commandType.equals(CMD_LOGOUT))
		{
			return false;

		} else
		{
			System.err.println("!---------------------------------------!");
			System.err.println("An unknown command was received. It was: ");
			System.err.println("\t\t - " + commandType);
			System.err.println("!---------------------------------------!");
			try
			{
				throw new Exception();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return true;

	}

	/**
	 * Handles client commands having to do with them modifying data
	 *
	 * @param interpreter
	 *            The specific command from an interface
	 * @param getmessageObject
	 *            The object that is used to help modify
	 */
	private void handleModify(String interpreter, Object getmessageObject)
	{

	}

	/**
	 * Handles client commands having to do with them receiving data
	 *
	 * @param interpreter
	 *            The specific command from an interface
	 * @param getmessageObject
	 *            The object that is used for some commands
	 */
	private void handleRecieve(String interpreter, Object getmessageObject)
	{
		// Returns the students courses
		if (interpreter.equals(RECEIVE_COURSES))
		{
			Vector<Integer> studentCoursesIds = database
					.getStudentEnrollmentTable().getCourseIDs(student.getId());
			Vector<Course> studentCourses = new Vector<Course>();

			for (int i = 0; i < studentCoursesIds.size(); i++)
			{
				Course myCourse = database.getCourseTable()
						.searchActiveCourseID(studentCoursesIds.get(i));
				if (myCourse != null)
				{
					studentCourses.add(myCourse);
				}
			}
			sendObject(studentCourses);
		}
		// Returns the students assignments for each course
		else if (interpreter.equals(RECEIVE_ASSIGNMENTS))
		{
			Vector<Assignment> courseAssignments = database.getAssignmentTable()
					.getAllStudentAssignments(
							((Course) getmessageObject).getId());

			sendObject(courseAssignments);
		}

		// Returns the students grades
		else if (interpreter.equals(RECEIVE_GRADES))
		{
			Vector<Grade> myGrades = database.getGradeTable()
					.studentGradesForCourse(((Course) getmessageObject).getId(),
							student.getId());

			System.out.println("THESE MANY GRADES " + myGrades.size());

			for (int i = 0; i < myGrades.size() - 1; i++)
			{
				for (int j = i + 1; j < myGrades.size(); j++)
				{
					if (myGrades.get(i).getAssignID() == myGrades.get(j)
							.getAssignID())
					{
						System.out.println("SAME ASSIGN DETECTED ");
						if(myGrades.get(i).getGrade() > myGrades.get(j).getGrade())
						{
							myGrades.remove(j);
						}
						else 
						{
							myGrades.remove(i);
						}
						j--;

					}
				}
			}

			sendObject(myGrades);

		} else if (interpreter.equals(RECEIVE_PROFESSOR))
		{
			Professor myProf = (Professor) database.getUserTable()
					.getUserByID(((Course) getmessageObject).getProf_id());
			sendObject(myProf);

		} else if (interpreter.equals(RECEIVE_SUBMISSIONS))
		{
			Vector<Submission> mySubmissions = database.getSubmissionTable()
					.searchByStudentID(student.getId());

			System.out.println(
					"My submissions have a size of: " + mySubmissions.size());
			sendObject(mySubmissions);
		} else if (interpreter.equals(RECEIVE_ASSIGNMENT))
		{
			super.sendBackFile(((Assignment) getmessageObject).getPath());

		}

	}

	/**
	 * Handles client commands having to do with keeping database updated
	 *
	 * @param interpreter
	 *            The specific command from an interface
	 * @param getmessageObject
	 *            The object that is being inserted
	 */
	private void handleInsert(String interpreter, Object getmessageObject)
	{
		if (interpreter.equals(INSERT_SUBMISSION))
		{
			Submission mySubmission = (Submission) getmessageObject;

			Assignment toGet = database.getAssignmentTable()
					.searchByAssignID(mySubmission.getAssign_id());

			mySubmission.setPath(DATABASE_STORAGE + toGet.getTitle()
					+ File.separator + mySubmission.getTitle());
			
			System.out.println(
					"Inserting submission at " + mySubmission.getPath());
			System.out.println(
					"My submission title is" + mySubmission.getTitle());
			database.getSubmissionTable().add(mySubmission);
			byte[] file;
			try
			{
				file = (byte[]) objectIn.readObject();
				fileHelper.storeFile(file, mySubmission);
			} catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setUser(User user)
	{
		this.student = (Student) user;
	}
}
