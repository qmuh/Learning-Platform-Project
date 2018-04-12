package frontend.view.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;

import shared.objects.Course;

final public class AssignmentPageStudent extends AssignmentPage
{
	private static final long serialVersionUID = 1L;

	private File selectedFile;
	
	
	
	public AssignmentPageStudent(Course course)
	{
		super(course);
	}

	@Override
	protected JScrollPane createAssignmentPanel()
	{
		JScrollPane scrollPane = new JScrollPane(itemDisplay);
		return scrollPane;
	}

	public void setFile(File selectedFile)
	{
		this.selectedFile = selectedFile;
		
	}

	public File getFile()
	{
		return selectedFile;
	}

	public String getDate()
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

}
