package todolist;
import java.time.LocalDate;

public class ToDoItem
{
	private LocalDate startDate;
	private LocalDate endDate;
	
	private String activityTitle;
	private String description;
	private Status status;
	
	public ToDoItem(String activityName, String description, LocalDate deadlineDate)
	{
		super();
		this.startDate = LocalDate.now();
		this.status = Status.OPEN;
		this.activityTitle = activityName;
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
	
	public String getActivityTitle()
	{
		return this.activityTitle;
	}
	
	public void setActivityTitle(String activityName)
	{
		this.activityTitle = activityName;
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

	@Override
	public String toString() {
		return "startDate: " + startDate + ", endDate: " + endDate + ", activityName: " + activityTitle
				+ ", description: " + description + ", status:" + status + "";
	}
	
	
	
	
}
