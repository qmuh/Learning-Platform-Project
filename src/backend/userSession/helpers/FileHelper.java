package backend.userSession.helpers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import shared.objects.Assignment;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */

public class FileHelper
{

	public void storeFile(byte[] file, Assignment data)
	{

		String toSplit[] = data.getPath().split("/");
		
		File newFile = new File("/Users/qasimmuhammad/Desktop/Database" + "/"+ (toSplit[toSplit.length -1]));
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
