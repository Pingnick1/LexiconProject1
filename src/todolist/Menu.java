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
	/**
	 * This function will take a input and format a LocalDateTime variable
	 * 
	 * @param <void> The type of object you want to load
	 * @return returns a LocalDateTime
	 * @throws IllegalStateException 
	 */
	public static LocalDateTime localDateTimeFormater()
	{
		LocalDateTime date;
		while (true)
		{
			System.out.println("Print date in this format: yyyy/mm/dd hh:mm");
			Scanner sc = new Scanner(System.in);
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
	 * This function will take a ToDoItem and gives the user a choice to what to change 
	 * 
	 * @param <ToDoItem> The item you want to change
	 */
	public static void editItem(ToDoItem toDoItem)
	{
		System.out.println(toDoItem);
		System.out.println("What do you want to change?");
		System.out.println("1) Start date");
		System.out.println("2) Deadline");
		System.out.println("3) Name of activity");
		System.out.println("4) Description of activity");
		System.out.println("5) Status");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
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
			System.out.println("Type OPEN, INPROGRESS, COMPLETE or OVERDUE");
			String status = sc.nextLine();
			if (status.equals("OPEN"))
			{
				toDoItem.setStatus(Status.OPEN);
			}
			else if (status.equals("INPROGRESS"))
			{
				toDoItem.setStatus(Status.INPROGRESS);
			}
			else if (status.equals("COMPLETE"))
			{
				toDoItem.setStatus(Status.COMPLETE);
			}
			else if (status.equals("OVERDUE"))
			{
				toDoItem.setStatus(Status.OVERDUE);
			}
			else
			{
				System.out.println("Invaild choice!");
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
	 * @param <void> The type of object you want to load
	 * @return returns a ToDoItem
	 */
	public static ToDoItem newitem()
	{
		LocalDateTime deadlineDate = localDateTimeFormater();
		Scanner sc = new Scanner(System.in);
		System.out.print("Name = ");
		String name = sc.nextLine();
		System.out.print("Description = ");
		String description = sc.nextLine();
		ToDoItem toDoItem = new ToDoItem(name, description, deadlineDate);
		return toDoItem;
	}

	/**
	 * This function clears the screen(Wont work on eclipse) and prints out the choices to the user
	 * @param <void>
	 */
	public static void printInstructions()
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
	}

	public final static void clearConsole()
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
	 * @param <void>
	 */
	public static void print()
	{
		System.out.println("Id   Start date       End date         Name       Description          Status");
	}
	/**
	 * This is the where the program starts.
	 * User is given choices by text print out to the screen.
	 * User can pick choices with keyboard with values from 0 to 9.
	 * @param <void> The type of object you want to load
	 * @throws ToDoItemNotFoundException 
	 */
	public static void  mainMenu() throws ToDoItemNotFoundException
	{
		boolean quit = false;
		int choice;
		String str = null;
		Scanner sc = new Scanner(System.in);
		ToDoList toDoList = new ToDoList();

		while (quit == false)
		{
			printInstructions();
			choice = sc.nextInt();
			switch (choice)
			{
			case 1: // Add item
				toDoList.addToDo(newitem());
				break;
			case 2: // Remove item
				choice = sc.nextInt();
				toDoList.removeToDoItemByID(choice);
				break;
			case 3: // Edit item
				System.out.println(toDoList);
				System.out.println("Choose item to edit");
				choice = sc.nextInt();
				editItem(toDoList.findToDoItemByID(choice));
				break;
			case 4: // Check if deadline has been exceeded
				toDoList.checkDeadlines();
				break;
			case 5: // Remove done/expired items
				toDoList.autoRemove(toDoList);
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
				str = sc.next();
				toDoList.searchForTitle(toDoList, str);
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
}
