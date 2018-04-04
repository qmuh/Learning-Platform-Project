package backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import backend.table.*;
import sharedobjects.LoginInfo;

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

	public Server(int port)
	{
		try
		{
			this.serverSocket = new ServerSocket(port);
			this.threadPool = Executors.newCachedThreadPool();
			this.database = new Database();
		} catch (IOException e)
		{
			System.err.println("Server socket creation error.");
		}
	}

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
		
		public LoginHandler(Socket guestUser)
		{
			try
			{
				objectInputStream = new ObjectInputStream(
						guestUser.getInputStream());
				objectInputStream.reset();

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
				LoginInfo loginInfo = (LoginInfo) objectInputStream.readObject();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			// TODO: Process user login by connecting to the database.
			// create a new client session
		}
	}

	public static void main(String[] args)
	{

	}
}
