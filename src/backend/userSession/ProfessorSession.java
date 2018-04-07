package backend.userSession;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import shared.objects.Assignment;
import shared.objects.Course;
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
		String interpreter[] = command.getCommand().split(" ");
		String commandType = interpreter[0];

		if (commandType.equals(CMD_INSERT))
		{
			handleInsert(interpreter[1], command.getmessageObject());

		} else if (commandType.equals(CMD_REMOVE))
		{
			System.err.println("!---------------------------------------!");
			System.err.println(
					"The command: " + command + " has not been implemented");
			System.err.println("\t\t - " + commandType);
			System.err.println("!---------------------------------------!");

		} else if (commandType.equals(CMD_RECEIVE))
		{
			handleRecieve(interpreter, command.getmessageObject());

		} else if (commandType.equals(CMD_MODIFY))
		{
			handleModify(interpreter, command.getmessageObject());

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

	private void handleModify(String[] interpreter, Object getmessageObject)
	{
		if (interpreter[1].equals("COURSEACTIVE"))
		{
			database.getCourseTable()
					.setActive(((Course) getmessageObject).getId(), true);
		}

		if (interpreter[1].equals("COURSEINACTIVE"))
		{
			database.getCourseTable()
					.setActive(((Course) getmessageObject).getId(), false);
		}

		if (interpreter[1].equals("ASSIGNACTIVE"))
		{
			database.getAssignmentTable()
					.setActive(((Assignment) getmessageObject).getId(), true);
		}

		if (interpreter[1].equals("ASSIGNINACTIVE"))
		{
			database.getAssignmentTable()
					.setActive(((Assignment) getmessageObject).getId(), false);
		}

	}

	private void handleRecieve(String[] interpreter, Object getMessage)
	{
		try
		{
			if (interpreter[1].equals(RETRIEVE_COURSES))
			{
				System.out.println("I recieve the correct message my id is "
						+ professor.getId());
				Vector<Course> myCourses = database.getCourseTable()
						.searchByProfId(professor.getId());
				sendObject(myCourses);
			}

			if (interpreter[1].equals(RETRIEVE_STUDENT_BY_ID))
			{
				Student myEnrolledStudents = (Student) database.getUserTable()
						.getUserByID(((int) getMessage));
				sendObject(myEnrolledStudents);
			}

			if (interpreter[1].equals(RETRIEVE_STUDENT_BY_LASTNAME))
			{
				Vector<Student> myEnrolledStudents = database.getUserTable()
						.searchLastName(((String) getMessage));
				sendObject(myEnrolledStudents);
			}

			if (interpreter[1].equals(RETRIEVE_ALL_STUDENTS))
			{
				Vector<Student> allStudents = database.getUserTable()
						.allStudents();
				sendObject(allStudents);
			}

			if (interpreter[1].equals(RETRIEVE_ALL_ENROLLED_STUDENTS))
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
			}

			if (interpreter[1].equals("ALLASSIGNMENTS"))
			{
				Vector<Assignment> allStudents = database.getAssignmentTable()
						.getAllAssignments(((Course) getMessage).getId());
				objectOut.writeObject(allStudents);
				objectOut.flush();
			}

		} catch (IOException e)
		{
			System.out.println("Error");
			e.printStackTrace();
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
		}

		if (type.equals(INSERT_ENROLLMENT))
		{
			database.getStudentEnrollmentTable()
					.add((StudentEnrollment) getmessageObject);
		}

		if (type.equals(INSERT_UNENROLLMENT))
		{
			database.getStudentEnrollmentTable()
					.remove((StudentEnrollment) getmessageObject);
		}
		if (type.equals(INSERT_ASSIGNMENT))
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
		}
	}

}
