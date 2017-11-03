package todolist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exceptions.ToDoItemNotFoundException;

public class ToDoList
{
	List<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	public void autoRemove(ToDoList toDoList) throws IndexOutOfBoundsException, ToDoItemNotFoundException
	{
		Iterator<ToDoItem> it1 = toDoList.toDoList.iterator();
		while (it1.hasNext())
		{
			ToDoItem tmpItem = it1.next();
			if (tmpItem.getStatus() == Status.OVERDUE || (tmpItem.getStatus() == Status.COMPLETE))
			{
				toDoList.removeToDoItemByID(tmpItem.getId());
			}
		}
	}
	public ToDoItem searchForTitle(ToDoList toDoList, String Search) throws IndexOutOfBoundsException, ToDoItemNotFoundException
	{
		Iterator<ToDoItem> it1 = toDoList.toDoList.iterator();
		int i = 1;
		ToDoItem tmpItem = null;
		while (it1.hasNext())
		{
			tmpItem=toDoList.getToDoListItem(i);
			if (Search.equalsIgnoreCase(tmpItem.getActivityTitle()))
			{
				return tmpItem;
			}
			i++;
		}
		System.out.println("Could not find "+ Search);
		return null;
	}

	/***************************************************************************
	* Function:			addToDo
	* Args:				ToDoItem
	* Returns:			Boolean
	* Throws:			N/A
	* Description:		
	***************************************************************************/
	public Boolean addToDo(ToDoItem item)
	{
		toDoList.add(item);
		return true;
	}
	
	
	/***************************************************************************
	* Function:			size
	* Args:				N/A
	* Returns:			int
	* Throws:			N/A
	* Description:		
	***************************************************************************/
	public int size() {
		return this.toDoList.size();
	}
	
	/***************************************************************************
	* Function:			removeToDoItem
	* Args:				int index
	* Returns:			Boolean
	* Throws:			IndexOutOfBoundsException
	* Description:		
	 * @throws ToDoItemNotFoundException 
	***************************************************************************/
	public Boolean removeToDoItemByID(int itemID)
	{			
		try {
			int indexToRemove = this.indexOfID(itemID);
			
			//System.out.println("itemID: " + itemID + ", indexToRemove:" + indexToRemove);
			
			if(this.toDoList.remove(indexToRemove) != null) {
				return true; 
			}
		} 
		catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();
			System.out.println("IndexOutOfBoundsException! item ID: " + itemID);
		}
		catch (ToDoItemNotFoundException e) {
			//e.printStackTrace();
			System.out.println("ToDoItemNotFoundException! item ID: " + itemID );
		}
		finally {
			System.out.println("Finally");
		}
		
		return false;
				
	}

	/***************************************************************************
	* Function:			findToDoItemByID
	* Args:				Integer toDoItemID
	* Returns:			ToDoItem
	* Throws:			ToDoItemNotFoundException
	* Description:		
	***************************************************************************/
	public ToDoItem findToDoItemByID(Integer toDoItemID) throws ToDoItemNotFoundException
	{
		Iterator<ToDoItem> it1 = this.toDoList.iterator();

		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpItem = it1.next();
			//System.out.println("Jämför toDoItemID:" + toDoItemID.intValue() + " getID:" + tmpItem.getId());
			
			if(tmpItem.getId() == toDoItemID.intValue()) {
				//System.out.println("Match! toDoItemID:" + toDoItemID.intValue() + " getID:" + tmpItem.getId());
				return tmpItem;
			}
		}
		throw new ToDoItemNotFoundException();
	}
	
	/***************************************************************************
	* Function:			findToDoItemByID
	* Args:				Integer toDoItemID
	* Returns:			ToDoItem
	* Throws:			ToDoItemNotFoundException
	* Description:		
	***************************************************************************/
	public int indexOfID(Integer toDoItemID) throws ToDoItemNotFoundException
	{
		Iterator<ToDoItem> it1 = this.toDoList.iterator();

		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpItem = it1.next();
			int index = toDoList.indexOf(tmpItem);
			//System.out.println("Index: " + index);
			//System.out.println("Jämför toDoItemID:" + toDoItemID.intValue() + " getID:" + tmpItem.getId() + "Index:" + index);
			
			if(tmpItem.getId() == toDoItemID.intValue()) {
				//System.out.println("Match! toDoItemID:" + toDoItemID.intValue() + " getID:" + tmpItem.getId());
				return index;
			}
		}
		throw new ToDoItemNotFoundException();
	}
	
	
	/***************************************************************************
	* Function:			checkDeadlines
	* Args:				N/A
	* Returns:			N/A
	* Throws:			N/A
	* Description:		Kolla deadlines i listan och uppdatera status där det behövs
	***************************************************************************/
	public void checkDeadlines()
	{
		Iterator<ToDoItem> it1 = this.toDoList.iterator();

		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpItem = it1.next();
			if(tmpItem.isOverDue()) {
				tmpItem.setStatus(Status.OVERDUE);
			}
		}
	}
	
	/***************************************************************************
	* Function:			getToDoListItem
	* Args:				int index
	* Returns:			ToDoItem
	* Throws:			IndexOutOfBoundsException
	* Description:		Kolla deadlines i listan och uppdatera status där det behövs
	***************************************************************************/
	public ToDoItem getToDoListItem(int index) throws IndexOutOfBoundsException
	{
		try {
			return this.toDoList.get(index);
		} catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();
			System.out.println("IndexOutOfBoundsException! Index: " + index );
		}
		return null;
		
	}
	
	/***************************************************************************
	* Function:			removeCompleted
	* Args:				N/A
	* Returns:			N/A
	* Throws:			N/A
	* Description:		Iterates through the list and removes status==COMPLETE items
	***************************************************************************/
	public void removeCompleted() {
		Iterator<ToDoItem> it1 = this.toDoList.iterator();

		int counter = 0;
		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpItem = it1.next();
			if(tmpItem.getStatus() == Status.COMPLETE) {
				it1.remove();
				counter++;
				//this.removeToDoItem(index);
			}
		}
		System.out.println("Antal borttagna poster i status COMPLETE:" + counter);
	}
	
	/***************************************************************************
	* Function:			toString
	* Args:				N/A
	* Returns:			String
	* Throws:			N/A
	* Description:		
	***************************************************************************/
	@Override
	public String toString() {
		Iterator<ToDoItem> it1 = this.toDoList.iterator();
		
		String tmpReturnString = "";
		int counter = 1;
		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpString = it1.next();
			tmpReturnString += counter++ + ". " + tmpString + " \n";
		}
		return tmpReturnString;
	}
	
	
	
	
}
