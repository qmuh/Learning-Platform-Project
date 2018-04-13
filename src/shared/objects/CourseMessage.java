//package shared.objects;
//
//import java.io.Serializable;
//
///**
// * Provides a class to represent an course object.
// * 
// * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
// *         (30017293)
// * @version 1.0
// * @since April 13, 2018
// */
//public class CourseMessage implements Serializable
//{
//	/**
//	 * The version of the class.
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Course ID
//	 */
//	private int courseId;
//
//	/**
//	 * Id related to course, can be of prof or student
//	 */
//	private int userId;
//
//	/**
//	 * Name of the student/professor
//	 */
//	private String name;
//
//	/**
//	 * Initializes the object
//	 * 
//	 * @param course
//	 * @param user
//	 */
//	public CourseMessage(int course, int user)
//	{
//		courseId = course;
//		userId = user;
//	}
//
//	/**
//	 * @param course
//	 * @param userName
//	 */
//	public CourseMessage(int course, String userName)
//	{
//		courseId = course;
//		name = userName;
//	}
//
//	public int getCourseId()
//	{
//		return courseId;
//	}
//
//	public int getUserId()
//	{
//		return userId;
//	}
//
//	public String getName()
//	{
//		return name;
//	}
//
//}
