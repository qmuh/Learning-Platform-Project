package backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import backend.database.Database;
import backend.userSession.ClientSession;
import backend.userSession.ProfessorSession;
import backend.userSession.StudentSession;
import shared.interfaces.ServerInfo;
import shared.interfaces.UserInfo;
import shared.objects.LoginInfo;
import shared.objects.User;

/**
 * Provides a class to host a
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class Server implements ServerInfo, UserInfo
{
	/**
	 * Allows the server to start up and connect to the client
	 */
	private ServerSocket serverSocket;

	/**
	 * Handles multiple clients on different threads
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
			e.printStackTrace();
		}
	}

	/**
	 * While the server is running, accept new connections and handle logins.
	 */
	public void runServer()
	{
		boolean isRunning = true;

		while (isRunning)
		{
			try
			{
				Socket incomingConnection = serverSocket.accept();
				threadPool.execute(new LoginHandler(incomingConnection));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		shutdown();
	}

	/**
	 * Shut the server down.
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
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			System.err.println("Unable to shutdown server thread pool.");
			e.printStackTrace();
		}
	}

	/**
	 * Provides a class that authenticates a user and serves ONE login request.
	 */
	private class LoginHandler implements Runnable
	{
		/**
		 * The socket of the user to handle.
		 */
		private Socket socket;

		public LoginHandler(Socket guestUser)
		{
			socket = guestUser;
		}

		@Override
		public void run()
		{
			try
			{
				ObjectInputStream objectIn = new ObjectInputStream(
						socket.getInputStream());
				ObjectOutputStream objectOut = new ObjectOutputStream(
						socket.getOutputStream());

				LoginInfo loginInfo = (LoginInfo) objectIn.readObject();

				User myUser = database.getUserTable().validateUser(
						loginInfo.getUsername(), loginInfo.getPassword());

				System.out.println("USER: " + myUser);

				objectOut.writeObject(myUser);
				objectOut.flush();

				ClientSession clientSession = null;

				if (myUser.getUserType().equals(USER_PROFESSOR))
				{
					clientSession = new ProfessorSession(socket);

				} else if (myUser.getUserType().equals(USER_STUDENT))
				{
					clientSession = new StudentSession(socket);

				} else
				{
					// User is not logged in.
					return;

				}

				clientSession.setDatabase(database);
				clientSession.setUser(myUser);
				clientSession.run();

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
	 * Starts the server.
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String[] args)
	{
		Server myServer = new Server(PORT_NUMBER);
		myServer.runServer();
	}
}
