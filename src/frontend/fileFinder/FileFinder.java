package fileFinder;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Provides a class with a findFile method to find the specified file if it is
 * in the current working directory, or a child of the current working
 * directory.
 * 
 * @author Jimmy Truong
 * @since March 20, 2018
 * @version 1.0
 */
abstract public class FileFinder {
  /**
   * Returns the file object that has the same name as the name provided.
   * 
   * @param fileName the file name to be found
   * @return the file if it is found
   * @throws FileNotFoundException thrown if the file is not found
   */
  static public File findFile(String fileName) throws FileNotFoundException {
    if (!fileName.toLowerCase().endsWith(".txt")) {
      fileName += ".txt";
    }

    File foundFile =
        fileSearch(new File(System.getProperty("user.dir")), fileName);
    if (foundFile == null) {
      throw new FileNotFoundException();
    }
    return foundFile;
  }

  /**
   * Recursively searches for the file that matches the file name.
   * 
   * File search implementation inspired by Ernest Friedman-Hill's StackOverflow
   * post at https://stackoverflow.com/a/6251802
   * 
   * @param currFile the current file or directory to analyze
   * @param fileName the name of the file to find
   * @return the file if it was found
   */
  static private File fileSearch(File currFile, String fileName) {
    File[] files = currFile.listFiles();

    for (int i = 0; i < files.length; i++) {
      if (files[i].getName().equals(fileName)) {
        return files[i];
      }
      // Test the directory to see if it has a file before proceeding
      if (files[i].isDirectory() && isInDirectory(files[i], fileName)) {
        return fileSearch(files[i], fileName);
      }
    }
    return null;
  }

  /**
   * Tests a directory to see if it contains the file name specified.
   * 
   * @param dir the directory to search
   * @param fileName the name of the file desired
   * @return true if the file name is somewhere inside this directory
   */
  static private boolean isInDirectory(File dir, String fileName) {
    File[] otherFiles = dir.listFiles();
    boolean result = false;

    for (int i = 0; i < otherFiles.length; i++) {
      // Search sub directory
      if (otherFiles[i].isDirectory()) {
        result = isInDirectory(otherFiles[i], fileName);
      } else if (otherFiles[i].getName().equals(fileName)) {
        return true;
      }
    }
    return result;
  }
}
