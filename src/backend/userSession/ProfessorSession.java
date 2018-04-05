package backend.userSession;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import sharedobjects.*;


public class ProfessorSession extends ClientSession
{

	Professor user;
	public ProfessorSession(Socket socket)
	{
		super(socket);
	}

	public void setProfessor(Professor thisUser)
	{
		user = thisUser;
	}
	
	
	
	@Override
	public void run()
	{
		boolean isRunning = true;

		while (isRunning)
		{
			try
			{
				SendMessage newMessage = (SendMessage)inputStream.readObject();
				interpretMessage(newMessage);
				

			} catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}


		
	}

	@Override
	public void write()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	void interpretMessage(SendMessage command)
	{
		String interpreter[] = command.getCommand().split(" ");
		
		if(interpreter[0].equals("INSERT"))
		{
			handleInsert(interpreter,command.getmessageObject());
		}
		
		else if(interpreter[0].equals("REMOVE"))
		{
			
		}
		
		else if(interpreter[0].equals("RECIEVE"))
		{
			handleRecieve(interpreter, command.getmessageObject());
		}
		
		else if(interpreter[0].equals("MODIFY"))
		{
			handleModify(interpreter, command.getmessageObject());
		}
		
		
	}

	
	private void handleModify(String[] interpreter, Object getmessageObject)
	{
		if(interpreter[1].equals("COURSEACTIVE"))
		{
			myDatabase.getCourseTable().setActive( ((Course)getmessageObject).getId());
		}
		
		if(interpreter[1].equals("COURSEINACTIVE"))
		{
			myDatabase.getCourseTable().setActive(((Course)getmessageObject).getId() );

		}
	}

	private void handleRecieve(String[] interpreter, Object getMessage)
	{
		try 
		{
		
			if(interpreter[1].equals("COURSES"))
			{
				Vector<Course> myCourses = myDatabase.getCourseTable().searchByProfId(user.getId());
				outputStream.writeObject(myCourses);
				outputStream.flush();
			}
			
			if(interpreter[1].equals("COURSEBYID"))
			{
				// Here the sent prof id is actually the student
				Vector<Integer> myStudents = myDatabase.getStudentEnrollmentTable().getStudentsbyID(((CourseMessage)getMessage).getCourseId() ,((CourseMessage)getMessage).getUserId());
				Vector<Student> myEnrolledStudents = myDatabase.getUserTable().getStudents(myStudents);
				outputStream.writeObject(myEnrolledStudents);
				outputStream.flush();
				
			}
			
			if(interpreter[1].equals("COURSEBYLAST")) 
			{
				// Here the name is the last name
				Vector<Integer> lastNameList = myDatabase.getUserTable().searchLastName(((CourseMessage)getMessage).getName());
				Vector<Integer> myStudents = new Vector<Integer>();
				for (int i = 0; i < lastNameList.size(); i++)
				{
					Vector<Integer> tempStudents = myDatabase.getStudentEnrollmentTable().getStudentsbyID(((CourseMessage)getMessage).getCourseId(),lastNameList.get(i));
					
					myStudents.addAll(tempStudents);
				}
				Vector<Student> myEnrolledStudents = myDatabase.getUserTable().getStudents(myStudents);
				outputStream.writeObject(myEnrolledStudents);
				outputStream.flush();
			}
			
		}catch (IOException e) {
			System.out.println("Error");
		}
		
	}

	private void handleInsert(String[] interpreter, Object getmessageObject)
	{
		if(interpreter[1].equals("COURSE"))
		{
			myDatabase.getCourseTable().add( (Course) getmessageObject);
		}
		
		if(interpreter[1].equals("ENROLL"))
		{
			
			myDatabase.getStudentEnrollmentTable().add((StudentEnrollment)getmessageObject);
		}
		
		if(interpreter[1].equals("UNENROLL"))
		{
			myDatabase.getStudentEnrollmentTable().remove((StudentEnrollment)getmessageObject);
		}
		
	}

	
	
	
}
