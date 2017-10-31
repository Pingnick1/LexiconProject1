package todolist;
import java.time.LocalDate;

public class ToDoItem
{
	private LocalDate createDate;
	private String activityName;
	private String description;
	private LocalDate deadlineDate;
	public ToDoItem(String activityName, String description, LocalDate deadlineDate)
	{
		super();
		//this.createDate = createDate;
		this.activityName = activityName;
		this.description = description;
		this.deadlineDate = deadlineDate;
	}
	public LocalDate getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(LocalDate createDate)
	{
		this.createDate = createDate;
	}
	public String getActivityName()
	{
		return activityName;
	}
	public void setActivityName(String activityName)
	{
		this.activityName = activityName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public LocalDate getDeadlineDate()
	{
		return deadlineDate;
	}
	public void setDeadlineDate(LocalDate deadlineDate)
	{
		this.deadlineDate = deadlineDate;
	}
	
	
}
