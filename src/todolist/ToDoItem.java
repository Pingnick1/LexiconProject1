package todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDoItem
{
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private String activityTitle;
	private String description;
	private Status status;

	private static int counter = 0;
	private int id = 0;

	public ToDoItem(String activityName, String description, LocalDateTime deadlineDate)
	{
		super();
		counter++;
		this.id = counter;
		this.startDate = LocalDateTime.now();
		this.status = Status.OPEN;
		this.activityTitle = activityName;
		this.description = description;
		this.endDate = deadlineDate;
	}

	public int getId()
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
	public String toString()
	{
		int t = 10 - activityTitle.length();
		StringBuilder buffer = new StringBuilder();
		int i = 0;
		while (i < t)
		{
			buffer.append(" ");
			i++;
		}
		String activityTitleF = activityTitle + buffer;
		t = 20 - description.length();
		i = 0;
		buffer.setLength(0);
		while (i < t)
		{
			buffer.append(" ");
			i++;
		}
		String descriptionF = description + buffer;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		return id + " " + startDate.format(formatter) + " " + endDate.format(formatter) + " " + activityTitleF
				+ " " + descriptionF + " " + status;
	}

	/*
	 * @Override public String toString() { DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"); return id + " startDate: " +
	 * startDate.format(formatter) + ", endDate: " + endDate.format(formatter) +
	 * ", activityName: " + activityTitle + ", description: " + description +
	 * ", status:" + status + ""; }
	 */
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

	public Boolean setStatus(Status status)
	{
		this.status = status;
		return true;
	}

	public Boolean isOverDue()
	{
		if (LocalDateTime.now().isAfter(this.endDate))
		{
			this.status = Status.OVERDUE;
			return true;
		}
		else
			return false;
	}

	public void setStartDate(LocalDateTime startDate)
	{
		this.startDate = startDate;
	}

}
