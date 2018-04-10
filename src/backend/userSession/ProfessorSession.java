package backend.userSession;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import shared.interfaces.ProfessorCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.EmailInfo;
import shared.objects.LoginInfo;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.StudentEnrollment;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.01
 * @since April 6, 2018
 */
public class ProfessorSession extends ClientSession implements ProfessorCommands
{

	private Professor professor;

	public ProfessorSession(Socket socket)
	{
		super(socket);
	}

	public void setProfessor(Professor professor)
	{
		this.professor = professor;
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

	private void handleModify(String modify, Object getmessageObject)
	{
		if (modify.equals(MODIFY_COURSE_ACTIVE))
		{
			database.getCourseTable()
					.setActive(((Course) getmessageObject).getId(), true);

		} else if (modify.equals(MODIFY_COURSE_INACTIVE))
		{
			database.getCourseTable()
					.setActive(((Course) getmessageObject).getId(), false);

		} else if (modify.equals(MODIFY_ASSIGNMENT_ACTIVE))
		{
			database.getAssignmentTable()
					.setActive(((Assignment) getmessageObject).getId(), true);

		} else if (modify.equals(MODIFY_ASSIGNMENT_INACTIVE))
		{
			database.getAssignmentTable()
					.setActive(((Assignment) getmessageObject).getId(), false);

		} else
		{
			System.err.println("!---------------------------------------!");
			System.err.println("An unknown modify was received. It was: ");
			System.err.println("\t\t - " + modify);
			System.err.println("!---------------------------------------!");
		}
	}

	private void handleRecieve(String type, Object getMessage)
	{
		if (type.equals(RECEIVE_COURSES))
		{
			Vector<Course> myCourses = database.getCourseTable()
					.searchByProfId(professor.getId());
			sendObject(myCourses);

		} else if (type.equals(RECEIVE_STUDENT_BY_ID))
		{
			Student myEnrolledStudents = (Student) database.getUserTable()
					.getUserByID(((int) getMessage));
			sendObject(myEnrolledStudents);

		} else if (type.equals(RECEIVE_STUDENT_BY_LASTNAME))
		{
			Vector<Student> myEnrolledStudents = database.getUserTable()
					.searchLastName(((String) getMessage));
			sendObject(myEnrolledStudents);

		} else if (type.equals(RECEIVE_ALL_STUDENTS))
		{
			Vector<Student> allStudents = database.getUserTable().allStudents();
			sendObject(allStudents);

		} else if (type.equals(RECEIVE_ALL_ENROLLED_STUDENTS))
		{
			Vector<Integer> enrolled = database.getStudentEnrollmentTable()
					.getAllEnrolledStudent(((Course) getMessage).getId());
			Vector<Student> enrolledStudent = new Vector<Student>();
			for (int i = 0; i < enrolled.size(); i++)
			{
				enrolledStudent.add((Student) database.getUserTable()
						.getUserByID(enrolled.get(i)));
			}
			sendObject(enrolledStudent);

		} else if (type.equals(RECEIVE_ALL_ASSIGNMENTS))
		{
			Vector<Assignment> allAssignments = database.getAssignmentTable()
					.getAllAssignments(((Course) getMessage).getId());
			sendObject(allAssignments);

		} else if (type.equals(RECEIVE_STUDENT_IS_ENROLLED))
		{

			int studentID = ((StudentEnrollment) getMessage).getStudent_id();
			int courseID = ((StudentEnrollment) getMessage).getCourse_id();

			Boolean isEnrolled = database.getStudentEnrollmentTable()
					.isStudentEnrolled(studentID, courseID);
			sendObject(isEnrolled);
		} else
		{

			System.err.println("!---------------------------------------!");
			System.err.println("An unknown type was received. It was: ");
			System.err.println("\t\t - " + type);
			System.err.println("!---------------------------------------!");
		}
	}

	private void handleInsert(String type, Object getmessageObject)
	{
		if (type.equals(INSERT_COURSE))
		{
			database.getCourseTable().add((Course) getmessageObject);
			System.out.println(
					"Adding course " + ((Course) getmessageObject).getName()
							+ " for Prof: " + professor.getFirstName());

		} else if (type.equals(INSERT_ENROLLMENT))
		{
			database.getStudentEnrollmentTable()
					.add((StudentEnrollment) getmessageObject);

		} else if (type.equals(INSERT_UNENROLLMENT))
		{
			database.getStudentEnrollmentTable()
					.remove((StudentEnrollment) getmessageObject);

		} else if (type.equals(INSERT_ASSIGNMENT))
		{
			database.getAssignmentTable().add(((Assignment) getmessageObject));
			byte[] file;
			try
			{
				file = (byte[]) objectIn.readObject();
				fileHelper.storeFile(file, ((Assignment) getmessageObject));
			} catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}

		} else
		{
			System.err.println("!---------------------------------------!");
			System.err.println("An unknown type was received. It was: ");
			System.err.println("\t\t - " + type);
			System.err.println("!---------------------------------------!");

		}
	}
}
