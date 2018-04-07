package backend.userSession;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import sharedobjects.*;

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
						.getUserByID(((CourseMessage) getMessage).getUserId());
				objectOutputStream.writeObject(myEnrolledStudents);
				objectOutputStream.flush();

			}

			if (interpreter[1].equals("COURSEBYLAST"))
			{
				Vector<Student> myEnrolledStudents = database.getUserTable()
						.searchLastName(((CourseMessage) getMessage).getName());
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
