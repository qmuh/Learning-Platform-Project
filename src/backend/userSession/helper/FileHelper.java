package backend.userSession.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import sharedobjects.Assignment;

public class FileHelper
{

	public void storeFile(byte[] file, Assignment data)
	{
		File newFile = new File(data.getTitle());
		try{
		if(! newFile.exists())
		newFile.createNewFile();
		FileOutputStream writer = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(writer);
		bos.write(file);
		bos.close();
		} catch(IOException e){
		e.printStackTrace();
		}
		
	}

}