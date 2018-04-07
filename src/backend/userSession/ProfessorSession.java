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
public class ProfessorSession extends ClientSession
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

		if (interpreter[0].equals("INSERT"))
		{
			handleInsert(interpreter, command.getmessageObject());
		}

		else if (interpreter[0].equals("REMOVE"))
		{

		}

		else if (interpreter[0].equals("RECEIVE"))
		{
			handleRecieve(interpreter, command.getmessageObject());
		}

		else if (interpreter[0].equals("MODIFY"))
		{
			handleModify(interpreter, command.getmessageObject());
		} else if (interpreter[0].equals("LOGOUT"))
		{
			return false;
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
			if (interpreter[1].equals("COURSES"))
			{
				System.out.println("I recieve the correct message my id is "
						+ professor.getId());
				Vector<Course> myCourses = database.getCourseTable()
						.searchByProfId(professor.getId());

				objectOut.writeObject(myCourses);
				objectOut.flush();
			}

			if (interpreter[1].equals("STUDENTBYID"))
			{
				Student myEnrolledStudents = (Student) database.getUserTable()
						.getUserByID(((int) getMessage));
				objectOut.writeObject(myEnrolledStudents);
				objectOut.flush();
			}

			if (interpreter[1].equals("STUDENTBYLAST"))
			{
				Vector<Student> myEnrolledStudents = database.getUserTable()
						.searchLastName(((String) getMessage));
				objectOut.writeObject(myEnrolledStudents);
				objectOut.flush();
			}

			if (interpreter[1].equals("ALLSTUDENTS"))
			{
				Vector<Student> allStudents = database.getUserTable()
						.allStudents();
				objectOut.writeObject(allStudents);
				objectOut.flush();
			}

			if (interpreter[1].equals("ALLENROLLED"))
			{
				Vector<Integer> enrolled = database.getStudentEnrollmentTable()
						.getAllEnrolledStudent(((Course) getMessage).getId());
				Vector<Student> enrolledStudent = new Vector<Student>();
				for (int i = 0; i < enrolled.size(); i++)
				{
					enrolledStudent.add((Student) database.getUserTable()
							.getUserByID(enrolled.get(i)));
				}
				objectOut.writeObject(enrolledStudent);
				objectOut.flush();
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

	private void handleInsert(String[] interpreter, Object getmessageObject)
	{
		if (interpreter[1].equals("COURSE"))
		{
			database.getCourseTable().add((Course) getmessageObject);
			System.out.println(
					"Adding course " + ((Course) getmessageObject).getName()
							+ " for Prof: " + professor.getFirstName());
		}

		if (interpreter[1].equals("ENROLL"))
		{
			database.getStudentEnrollmentTable()
					.add((StudentEnrollment) getmessageObject);
		}

		if (interpreter[1].equals("UNENROLL"))
		{
			database.getStudentEnrollmentTable()
					.remove((StudentEnrollment) getmessageObject);
		}

		if (interpreter[1].equals("ASSIGNMENT"))
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
