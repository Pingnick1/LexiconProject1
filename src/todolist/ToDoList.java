package todolist;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDoList
{
	List<ToDoItem> toDoList = new ArrayList<ToDoItem>();

	
	public Boolean addToDo(ToDoItem item)
	{
		toDoList.add(item);
		return true;
	}
	
	
	//Done
	public int size() {
		return this.toDoList.size();
	}
	
	public Boolean removeItem(ToDoItem item)
	{
		return null;
		
	}
	
	public ToDoItem findToDoItem ()
	{
		return null;
	}
	
	public boolean kollaDeadline()
	{
		return false;
	}
	
	public ToDoItem getToDoListItem (int index)
	{
		return this.toDoList.get(index);
	}


	@Override
	public String toString() {
		Iterator<ToDoItem> it1 = this.toDoList.iterator();
		
		String tmpReturnString = "";
		int counter = 1;
		// Better aproach for modify operations
		while (it1.hasNext()) {
			ToDoItem tmpString = it1.next();
			tmpReturnString += counter++ + ". " + tmpString + " \n";
			//System.out.println(tmpString);
			
		}
		return tmpReturnString;
		//return "ToDoList [toDoList=" + toDoList + "]";
	}
	
	
	
	
}
