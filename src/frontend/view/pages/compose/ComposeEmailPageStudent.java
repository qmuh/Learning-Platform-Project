package frontend.view.pages.compose;

import shared.objects.Course;

import shared.objects.Student;

/**
 * Provides a class that represents a page to compose an email for the student. 
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class ComposeEmailPageStudent extends ComposeEmailPage
{

	private static final long serialVersionUID = 1L;

	public ComposeEmailPageStudent(Course course, Student student)
	{
		super(course, student);
	}
}
