package backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import backend.database.*;
import backend.userSession.ProfessorSession;
import backend.userSession.StudentSession;
import sharedobjects.LoginInfo;
import sharedobjects.Professor;
import sharedobjects.Student;
import sharedobjects.User;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class Server
{

	/**
	 * Allows the server to start up and connect to the client
	 */
	private ServerSocket serverSocket;

	/**
	 * Used to allow multi-threading
	 */
	private ExecutorService threadPool;

	/**
	 * Connects and initializes to a database
	 */
	private Database database;

	/**
	 * Creates the server with a port number
	 * 
	 * @param port
	 *            The port number
	 */
	public Server(int port)
	{
		try
		{
			this.serverSocket = new ServerSocket(port);
			this.threadPool = Executors.newCachedThreadPool();
			this.database = new Database();
			System.out.println("Server started");
		} catch (IOException e)
		{
			System.err.println("Server socket creation error.");
		}
	}

	/**
	 * Runs the server
	 */
	public void runServer()
	{
		boolean isRunning = true;

		while (isRunning)
		{
			try
			{

				Socket incomingConnection = serverSocket.accept();
				LoginHandler loginHandler = new LoginHandler(
						incomingConnection);
				threadPool.execute(loginHandler);

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		shutdown();
	}

	/**
	 * Shuts the server down
	 */
	private void shutdown()
	{
		try
		{
			serverSocket.close();
			if (!threadPool.awaitTermination(30, TimeUnit.SECONDS))
			{
				System.err.println("Server thread shutdown timed out.");
			}
		} catch (IOException e)
		{
			System.err.println("Unable to shutdown server socket.");
		} catch (InterruptedException e)
		{
			System.err.println("Unable to shutdown server thread pool.");
		}
	}

	/**
	 * Provides a class that handles incoming connections using
	 *
	 * @author Jimmy Truong
	 *
	 */
	private class LoginHandler implements Runnable
	{
		private ObjectInputStream objectInputStream;
		private ObjectOutputStream objectOutputStream;
		private Socket mySocket;

		public LoginHandler(Socket guestUser)
		{
			try
			{
				mySocket = guestUser;
				objectInputStream = new ObjectInputStream(
						guestUser.getInputStream());
				objectOutputStream = new ObjectOutputStream(
						guestUser.getOutputStream());

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void run()
		{
			try
			{
				LoginInfo loginInfo = (LoginInfo) objectInputStream
						.readObject();
				User myUser = database.getUserTable().validateUser(
						loginInfo.getUsername(), loginInfo.getPassword());
				objectOutputStream.writeObject(myUser);
				objectOutputStream.flush();

				if (myUser.getUserType().equals("P"))
				{
					ProfessorSession handleProfessor = new ProfessorSession(
							mySocket);
					handleProfessor.setDatabase(database);
					handleProfessor.setProfessor((Professor) myUser);
					handleProfessor.run();
				}

				else if (myUser.getUserType().equals("P"))
				{
					StudentSession handleStudent = new StudentSession(mySocket);
					handleStudent.setDatabase(database);
					handleStudent.setStudent((Student) myUser);
					handleStudent.run();
				}
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Starts the server
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args)
	{
		Server myServer = new Server(8991);
		myServer.runServer();
	}
}
