package backend;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class Server
{

	/**
	 * Allows the server to start up and connect to the client
	 */
	private ServerSocket myServer;
	
	/**
	 * Used to allow multi-threading
	 */
	private ExecutorService threadPool;
	
	/**
	 * Connects and initializes to a database
	 */
	Database database;

	
	public void runServer() {
		
	}
	
	public static void main(String[] args)
	{
		
	}
}
