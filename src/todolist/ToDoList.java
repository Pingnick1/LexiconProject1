package todolist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exceptions.ToDoItemNotFoundException;

public class ToDoList
{
	List<ToDoItem> toDoList = new ArrayList<ToDoItem>();
	
	
	/***************************************************************************
	 * Add ToDoItem to ArrayList
	 * @param item
	 * @return Boolean
	 ***************************************************************************/
	public Boolean addToDo(ToDoItem newToDoItem)
	{
		this.toDoList.add(newToDoItem);
		return true;
	}
	
	
	/***************************************************************************
	* Returns size of the ArrayList in ToDoList
	* @return	int size	
	***************************************************************************/
	public int size() {
		return this.toDoList.size();
	}
	
	/***************************************************************************
	* Remove ToDoItem by unique ID
	* @param int itemID
	* @return Boolean 		 			 
	***************************************************************************/
	public Boolean removeToDoItemByID(int itemID)
	{			
		try {
			int indexToRemove = this.getIndexOfID(itemID);
			
			System.out.println("itemID: " + itemID + ", indexToRemove:" + indexToRemove);
			
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

		return false;
				
	}

	/***************************************************************************
	* Find ToDoItem object on unique ID
	* @param Integer toDoItemID
	* @return ToDoItem
	* @throws ToDoItemNotFoundException		
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
	* Sök efter title och returnera objekt om hittas.
	* @param  String "searchString"
	* @return ToDoItem
	* @throws ToDoItemNotFoundException	
	***************************************************************************/
	public ToDoItem findToDoItemByTitle(String searchString) throws ToDoItemNotFoundException
	{
		Iterator<ToDoItem> it1 = this.toDoList.iterator();
		
		while (it1.hasNext())
		{
			ToDoItem tmpItem = it1.next();
			
			if (searchString.equalsIgnoreCase(tmpItem.getActivityTitle().toString()))
			{
				return tmpItem;
			}

		} 
		throw new ToDoItemNotFoundException(); 

	}
	
	/***************************************************************************
	* Get index in ArrayList of position by ID.
	* @param Integer toDoItemID
	* @return todo.ToDoItem
	* @throws ToDoItemNotFoundException	 
	***************************************************************************/
	public int getIndexOfID(Integer toDoItemID) throws ToDoItemNotFoundException
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
	* Kolla deadlines i listan och uppdatera status där det behövs.
	* @param	None
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
	* Automatic remove all objects that are status overdue or completed
	***************************************************************************/
	public void autoRemoveCompleteOverdue()
	{
		Iterator<ToDoItem> it1 = this.toDoList.iterator();
		while (it1.hasNext())
		{
			ToDoItem tmpItem = it1.next();
			
			System.out.println("Testing: \"" + tmpItem.getActivityTitle() + "\" which are in state: " + tmpItem.getStatus());
			
			tmpItem.isOverDue();	//Update status if needed
			
			// todoListTest.removeToDoItemByID(idToRemove)
			
			if ( tmpItem.getStatus().equals(Status.OVERDUE) || (tmpItem.getStatus().equals(Status.COMPLETE)) )
			{			
				
				System.out.println("Removing " + tmpItem.getActivityTitle() + " wtih ID:" + tmpItem.getId() + " which are in state: " + tmpItem.getStatus());
				it1.remove();	
			}
		}
	}
	
	/***************************************************************************
	 * Kolla deadlines i listan och uppdatera status där det behövs	
	 * @param int index
	 * @return	ToDoItem
	 * @throws IndexOutOfBoundsException
	 ***************************************************************************/
	public ToDoItem getToDoListItemByIndex(int index) throws IndexOutOfBoundsException
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
	* Iterates through the list and removes status == COMPLETE items		
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
	* @return String
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