package todolist;
import java.time.LocalDateTime;

public class ToDoItem
{
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	private String activityTitle;
	private String description;
	private Status status;

	private static int id = 0;
	
	public ToDoItem(String activityName, String description, LocalDateTime deadlineDate)
	{
		super();
		id++;
		this.startDate = LocalDateTime.now();
		this.status = Status.OPEN;
		this.activityTitle = activityName;
		this.description = description;
		this.endDate = deadlineDate;
	}
	
	public static int getId()
	{
		return id;
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
	

	@Override
	public String toString() {
		return "startDate: " + startDate + ", endDate: " + endDate + ", activityName: " + activityTitle
				+ ", description: " + description + ", status:" + status + "";
	}


	public LocalDateTime getStartDate()
	{
		return startDate;
	}

	public LocalDateTime getEndDate()
	{
		return endDate;
	}


	public void setEndDate(LocalDateTime endDate)
	{
		this.endDate = endDate;
	}


	public Status getStatus()
	{
		return status;
	}


	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public Boolean isOverDue()
	{
		if ( LocalDateTime.now().isAfter(this.endDate))
		{
			this.status=Status.OVERDUE;
			return true;
		}
		else
			return false;
	}
	
	
}
