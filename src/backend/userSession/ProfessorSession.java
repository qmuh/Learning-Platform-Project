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
			handleRecieve(interpreter);
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

	private void handleRecieve(String[] interpreter)
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
				//Vector<Student> myStudents = myDatabase.getStudentEnrollmentTable().
				//outputStream.writeObject(myCourses);
				//outputStream.flush();
				
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
		
		
		
	}

	
	
	
}
