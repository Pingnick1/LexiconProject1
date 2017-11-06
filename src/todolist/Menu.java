package todolist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.MatchResult;

import Exceptions.ToDoItemNotFoundException;

/**
 * @author Ludwig
 *
 */
public class Menu
{
	private Scanner sc;

	public Menu()
	{
		super();
		this.sc = new Scanner(System.in);
	}

	/**
	 * This function will take a input and format a LocalDateTime variable
	 * 
	 * @param <void>
	 *                The type of object you want to load
	 * @return returns a LocalDateTime
	 * @throws IllegalStateException
	 */
	public LocalDateTime localDateTimeFormater()
	{
		LocalDateTime date;
		while (true)
		{
			System.out.println("Print date in this format: yyyy/mm/dd hh:mm");
			sc.findInLine("(\\d\\d\\d\\d)/(\\d\\d)/(\\d\\d) (\\d\\d):(\\d\\d)");
			try
			{
				MatchResult mr = sc.match();
				int year = Integer.parseInt(mr.group(1));
				int month = Integer.parseInt(mr.group(2));
				int day = Integer.parseInt(mr.group(3));
				int hour = Integer.parseInt(mr.group(4));
				int minute = Integer.parseInt(mr.group(5));
				date = LocalDateTime.of(year, month, day, hour, minute);
			}
			catch (IllegalStateException e)
			{
				System.err.println("Invalid Date!");
				continue;
			}
			return date;
		}
	}

	/**
	 * This function will take a ToDoItem and gives the user a choice to what to
	 * change
	 * 
	 * @param <ToDoItem>
	 *                The item you want to change
	 */
	public void editItem(ToDoItem toDoItem)
	{
		System.out.println(toDoItem);
		this.printEditMenu();
		int choice;
		choice = this.sc.nextInt();
		LocalDateTime date = null;
		switch (choice)
		{
		case 1:
			date = localDateTimeFormater();
			toDoItem.setStartDate(date);
			break;
		case 2:
			date = localDateTimeFormater();
			toDoItem.setEndDate(date);
			break;
		case 3:
			System.out.print("New name = ");
			String name = sc.next();
			toDoItem.setDescription(name);
			break;
		case 4:
			System.out.print("New description = ");
			String description = sc.next();
			toDoItem.setDescription(description);
			break;
		case 5:
			this.printStatusMenu();
			int choice1 = sc.nextInt();
			switch (choice1)
			{
			case 1:
				toDoItem.setStatus(Status.OPEN);
				break;

			case 2:
				toDoItem.setStatus(Status.INPROGRESS);
				break;

			case 3:
				toDoItem.setStatus(Status.COMPLETE);
				break;

			case 4:
				toDoItem.setStatus(Status.OVERDUE);
				break;
			}
			break;

		case 0:
			break;
		default:
			System.out.println("Invaild choice!");
			break;
		}
	}

	/**
	 * This function will take input from the user and and put in the list
	 * 
	 * @param <void>
	 *                The type of object you want to load
	 * @return returns a ToDoItem
	 */
	public ToDoItem newitem()
	{
		LocalDateTime deadlineDate = localDateTimeFormater();
		System.out.print("Name = ");
		String name = this.sc.nextLine();
		System.out.print("Description = ");
		String description = this.sc.nextLine();
		ToDoItem toDoItem = new ToDoItem(name, description, deadlineDate);
		return toDoItem;
	}

	/**
	 * This function clears the screen(Wont work on eclipse) and prints out the
	 * choices to the user
	 * 
	 * @param <void>
	 */
	public void printInstructions()
	{
		clearConsole();
		System.out.println("Menu");
		System.out.println("1) Add item");
		System.out.println("2) Remove item");
		System.out.println("3) Edit item");
		System.out.println("4) Check deadline");
		System.out.println("5) Remove done/expired items");
		System.out.println("6) Print all items");
		System.out.println("7) Search for Item");
		System.out.println("8) Save list");
		System.out.println("9) Load list");
		System.out.println("0) Exit");
		System.out.print("Choice:");
	}

	public void printStatusMenu()
	{
		System.out.println("Chose status:");
		System.out.println("1) OPEN");
		System.out.println("2) INPROGRESS");
		System.out.println("3) COMPLETE");
		System.out.println("4) OVERDUE");
		System.out.print("Choice:");
	}

	public void printEditMenu()
	{
		System.out.println("What do you want to change?");
		System.out.println("1) Start date");
		System.out.println("2) Deadline");
		System.out.println("3) Name of activity");
		System.out.println("4) Description of activity");
		System.out.println("5) Status");
		System.out.print("Choice:");
	}

	public final void clearConsole()
	{
		try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				Runtime.getRuntime().exec("cls");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{
			// Handle any exceptions.
		}
	}

	/**
	 * This function just makes the printing of the list nicer
	 * 
	 * @param <void>
	 */
	public void print()
	{
		System.out.println("Id Start date       \t End date         \tName       Description          Status");
	}

	/**
	 * This is the where the program starts. User is given choices by text print out
	 * to the screen. User can pick choices with keyboard with values from 0 to 9.
	 * 
	 * @param <void>
	 *                The type of object you want to load
	 * @throws ToDoItemNotFoundException
	 */

	public void mainMenu()
	{
		boolean quit = false;
		int choice;
		String str = null;
		ToDoList toDoList = new ToDoList();
		createTestObjects(toDoList);

		while (quit == false)
		{
			printInstructions();
			choice = this.sc.nextInt();
			switch (choice)
			{
			case 1: // Add item
				toDoList.addToDo(newitem());
				break;
			case 2: // Remove item
				choice = this.sc.nextInt();
				toDoList.removeToDoItemByID(choice);
				break;
			case 3: // Edit item
				System.out.println(toDoList);
				System.out.print("Choose item to edit:");
				choice = sc.nextInt();
				try
				{
					editItem(toDoList.findToDoItemByID(choice));
				}
				catch (ToDoItemNotFoundException e)
				{
					System.out.println("Choice with ID:" + choice + " not found. ToDoItemNotFoundException.");
				}
				break;
			case 4: // Check if deadline has been exceeded
				toDoList.checkDeadlines();
				break;
			case 5: // Remove done/expired items
				toDoList.autoRemoveCompleteOverdue();
				break;
			case 6: // Get the description for each item.
				if (toDoList.size() == 0)
					System.out.println("The list is empty");
				else
				{
					print();
					System.out.println(toDoList);
				}
				break;
			case 7: // Search for a specific Item
				System.out.print("Search for =");
				str = this.sc.next();
				try
				{
					ToDoItem item = toDoList.findToDoItemByTitle(str);
					System.out.println("Found " + item);
				}
				catch (ToDoItemNotFoundException e)
				{
					System.out.println("No item found");
				}
				break;
			case 8:
			{
				try
				{
					XmlIO.saveObject("todolist.xml", toDoList);
					break;
				}
				catch (IOException ex)
				{
					System.out.println("IOException!");
				}
				break;
			}
			case 9:
			{
				try
				{
					toDoList = XmlIO.loadObject("todolist.xml", ToDoList.class);
				}
				catch (IOException ex)
				{
					System.out.println("Could not load todolist.xml");
				}
				break;
			}
			case 0:
				quit = true;
				break;
			default:
				System.out.println("Invaild choice!");
				break;
			}
		}
		sc.close();
	}

	public void createTestObjects(ToDoList todoLIST)
	{

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime overDueDate1 = today.minusDays(4);
		LocalDateTime overDueDate2 = today.minusHours(4);
		LocalDateTime notOverDueDate1 = today.plusDays(6);

		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", overDueDate1);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", overDueDate2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", notOverDueDate1);

		todoLIST.addToDo(t1);
		todoLIST.addToDo(t2);
		todoLIST.addToDo(t3);
	}
}
