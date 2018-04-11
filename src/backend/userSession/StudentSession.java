package backend.userSession;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import shared.interfaces.StudentCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.Grade;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class StudentSession extends ClientSession implements StudentCommands
{

	/**
	 * The student associated with this session
	 */
	private Student user;

	/** Initializes this session with a socket and calls super
	 * @param socket The socket given to the super
	 */
	public StudentSession(Socket socket)
	{
		super(socket);
	}

	/** Sets the student for this class
	 * @param toSet
	 */
	public void setStudent(Student toSet)
	{
		user = toSet;
	}

	@Override
	public void write()
	{
		// TODO Auto-generated method stub

	}

	@Override
	boolean interpretMessage(SendMessage<?> command)
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

		} else if(commandType.equals(CMD_EMAIL)) { 
		
			super.handleEmail((EmailInfo)command.getContents());
		
		}else if (commandType.equals(CMD_LOGOUT))
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
	
	/** Handles client commands having to do with them modifying data
	 * @param interpreter The specific command from an interface
	 * @param getmessageObject The object that is used to help modify
	 */
	private void handleModify(String interpreter, Object getmessageObject)
	{
	
	}

	/** Handles client commands having to do with them receiving data
	 * @param interpreter The specific command from an interface
	 * @param getmessageObject The object that is used for some commands
	 */
	private void handleRecieve(String interpreter, Object getmessageObject)
	{
		//Returns the students courses
		if(interpreter.equals(RECEIVE_COURSES));
		{
			Vector<Integer> studentCoursesIds = database.getStudentEnrollmentTable().getCourseIDs(user.getId());
			Vector<Course> studentCourses = new Vector<Course>();
			
			for (int i = 0; i < studentCoursesIds.size(); i++)
			{	Course myCourse = database.getCourseTable().searchActiveCourseID(studentCoursesIds.get(i));
				if(myCourse != null)
				{
					studentCourses.add(myCourse);
				}
			}
			sendObject(studentCourses);
		} 
		// Returns the students assignments for each course
		if (interpreter.equals(RECEIVE_ASSIGNMENTS))
		{
			Vector<Assignment> courseAssignments = database.getAssignmentTable().
					getAllStudentAssignments(((Course)getmessageObject).getId());
			
			sendObject(courseAssignments);
		}
		// Returns the students grades
		if(interpreter.equals(RECEIVE_GRADES))
		{
			Vector<Grade> myGrades = database.getGradeTable().
					studentGradesForCourse(((Course)getmessageObject).getId(),user.getId());
			System.out.println("I have recieved the correct request");
			sendObject(myGrades);
			
		}
	
	}

	/** Handles client commands having to do with keeping database updated
	 * @param interpreter The specific command from an interface
	 * @param getmessageObject The object that is being inserted
	 */
	private void handleInsert(String interpreter, Object getmessageObject)
	{
		if(interpreter.equals(INSERT_SUBMISSION))
		{
			Submission mySubmission = (Submission) getmessageObject;
			String toSplit[] = mySubmission.getPath().split("/");
			mySubmission.setPath("/Users/qasimmuhammad/Desktop/Database" + "/"
					+ (toSplit[toSplit.length - 1]));
			
			database.getSubmissionTable().add(mySubmission);
			byte[] file;
			try
			{
				file = (byte[]) objectIn.readObject();
				fileHelper.storeFile(file, ((Submission) getmessageObject));
			} catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}
