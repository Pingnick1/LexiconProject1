package todolist;
import java.time.LocalDate;

public class ToDoItem
{
	private LocalDate startDate;
	private LocalDate endDate;
	
	private String activityName;
	private String description;
	private Status status;
	
	public ToDoItem(String activityName, String description, LocalDate deadlineDate)
	{
		super();
		//this.createDate = createDate;
		this.status = Status.OPEN;
		this.activityName = activityName;
		this.description = description;
		this.endDate = deadlineDate;
	}
	public LocalDate getCreateDate()
	{
		return startDate;
	}
	public void setCreateDate(LocalDate createDate)
	{
		this.startDate = createDate;
	}
	public String getActivityName()
	{
		return this.activityName;
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
		return endDate;
	}
	public void setDeadlineDate(LocalDate deadlineDate)
	{
		this.endDate = deadlineDate;
	}
	
	
}
