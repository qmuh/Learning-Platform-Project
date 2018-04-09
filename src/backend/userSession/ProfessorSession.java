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
 * @version 1.0
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
					.setActive(((Course) getmessageObject).getId());
		}

		if (interpreter[1].equals("COURSEINACTIVE"))
		{
			database.getCourseTable()
					.setInactive(((Course) getmessageObject).getId());
		}

		if (interpreter[1].equals("ASSIGNACTIVE"))
		{
			database.getAssignmentTable()
					.setActive(((Assignment) getmessageObject).getId());
		}

		if (interpreter[1].equals("ASSIGNINACTIVE"))
		{
			database.getAssignmentTable()
					.setInactive(((Assignment) getmessageObject).getId());
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

				objectOutputStream.writeObject(myCourses);
				objectOutputStream.flush();
			}

			if (interpreter[1].equals("STUDENTBYID"))
			{
				Student myEnrolledStudents = (Student) database.getUserTable()
						.getUserByID(((int) getMessage));
				objectOutputStream.writeObject(myEnrolledStudents);
				objectOutputStream.flush();
			}

			if (interpreter[1].equals("STUDENTBYLAST"))
			{
				Vector<Student> myEnrolledStudents = database.getUserTable()
						.searchLastName(((String) getMessage));
				objectOutputStream.writeObject(myEnrolledStudents);
				objectOutputStream.flush();
			}

			if (interpreter[1].equals("ALLSTUDENTS"))
			{
				Vector<Student> allStudents = database.getUserTable()
						.allStudents();
				objectOutputStream.writeObject(allStudents);
				objectOutputStream.flush();
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
				objectOutputStream.writeObject(enrolledStudent);
				objectOutputStream.flush();
			}

			if (interpreter[1].equals("ALLASSIGNMENTS"))
			{
				Vector<Assignment> allStudents = database.getAssignmentTable()
						.getAllAssignments(((Course) getMessage).getId());
				objectOutputStream.writeObject(allStudents);
				objectOutputStream.flush();
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
				file = (byte[]) objectInputStream.readObject();
				fileHelper.storeFile(file, ((Assignment) getmessageObject));
			} catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
