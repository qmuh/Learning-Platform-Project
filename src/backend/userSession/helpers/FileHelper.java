package backend.userSession.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shared.objects.Assignment;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class FileHelper
{

	/**
	 * Stores file into a directory
	 * 
	 * @param file
	 *            The byte[] data from the file
	 * @param data
	 *            The assignment that is being stored
	 */
	public void storeFile(byte[] file, Assignment data)
	{

		File newFile = new File(data.getPath());
		try
		{
			if (!newFile.exists())
				newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(file);
			bos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Stores files into a directory
	 * 
	 * @param file
	 *            The byte[] data form the file
	 * @param submission
	 *            The assignment that is being stored
	 */
	public void storeFile(byte[] file, Submission submission)
	{
		File newFile = new File(submission.getPath());
		try
		{
			if (!newFile.exists())
				newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(file);
			bos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * For sending a file to the client
	 * 
	 * @param path
	 *            The path to bring the file back from
	 * @return The byte data for the file
	 */
	public byte[] receiveFile(String path)
	{
		File selectedFile = new File(path);

		long length = selectedFile.length();
		byte[] content = new byte[(int) length];
		try
		{
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int) length);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();

		}
		return content;

	}

	public void checkDir(String directory)
	{
		new File(directory).mkdirs();

	}
}
