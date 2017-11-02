package JUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import Exceptions.ToDoItemNotFoundException;
import todolist.Status;
import todolist.ToDoItem;
import todolist.ToDoList;

class JUnitTestToDoList {

	@Test
	void testAddTaskToList_() {
		ToDoList todoListTest = new ToDoList();
		
		//LocalDate today1 = LocalDate.now();
		LocalDateTime anotherDay2 = LocalDateTime.of(2017, Month.JUNE, 24, 15, 15,24);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", anotherDay2);
		
		assertTrue(todoListTest.addToDo(t1));	
		assertTrue(todoListTest.addToDo(t1));
		assertTrue(todoListTest.size() == 2);
	}
	
	@Test
	void testRemoveToDoItemFromList() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime anotherDay2 = LocalDateTime.of(2017, Month.JUNE, 24, 15, 15,24);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", anotherDay2);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", anotherDay2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", anotherDay2);
		
		assertTrue(todoListTest.addToDo(t1));	
		assertTrue(todoListTest.addToDo(t2));
		assertTrue(todoListTest.addToDo(t3));	
		
		int idToRemove = todoListTest.getToDoListItem(1).getId();
		
		System.out.println("id to remove: " + idToRemove);
		
		assertTrue(todoListTest.size() == 3);
		
		System.out.println(todoListTest);
		
		assertTrue(todoListTest.removeToDoItemByID(idToRemove));
		assertTrue(todoListTest.size() == 2);
		System.out.println(todoListTest);

	}
	
	@Test
	void testCheckDeadlines() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime today = LocalDateTime.now();
		
		LocalDateTime overDueDate1 = today.minusDays(4);
		LocalDateTime overDueDate2 = today.minusHours(4);
		LocalDateTime notOverDueDate1 = today.plusDays(6);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", overDueDate1);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", overDueDate2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", notOverDueDate1);
		
		todoListTest.addToDo(t1);
		todoListTest.addToDo(t2);
		todoListTest.addToDo(t3);
		
		//System.out.println(todoListTest);
		
		todoListTest.checkDeadlines();
		
		//System.out.println(todoListTest);
		
		assertTrue(todoListTest.getToDoListItem(0).getStatus() == Status.OVERDUE);
		
		assertTrue(todoListTest.getToDoListItem(1).getStatus() == Status.OVERDUE);
		
		assertTrue(todoListTest.getToDoListItem(2).getStatus() == Status.OPEN);
	}
	
	@Test
	void testSetNewStatus() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime today = LocalDateTime.now();
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", today);
		
		todoListTest.addToDo(t1);
		
		
		assertTrue(todoListTest.getToDoListItem(0).getStatus() == Status.OPEN);
		
		assertTrue(todoListTest.getToDoListItem(0).setStatus(Status.COMPLETE));
		
		assertTrue(todoListTest.getToDoListItem(0).getStatus() == Status.COMPLETE);
	}
	
	@Test
	void testFindToDoItem() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime overDueDate1 = today.minusDays(4);
		LocalDateTime overDueDate2 = today.minusHours(4);
		LocalDateTime notOverDueDate1 = today.plusDays(6);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", overDueDate1);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", overDueDate2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", notOverDueDate1);
		
		todoListTest.addToDo(t1);
		todoListTest.addToDo(t2);
		todoListTest.addToDo(t3);
				
		int itemToFind = todoListTest.getToDoListItem(1).getId();
		
		try {
			ToDoItem foundToDoItem = todoListTest.findToDoItemByID(itemToFind); 

			//System.out.println(foundToDoItem.getClass().getSimpleName().toString());

			assertTrue(foundToDoItem.getClass().getSimpleName().equals("ToDoItem"));
			
			System.out.println(foundToDoItem);
			
		} catch (ToDoItemNotFoundException e) {
		
			System.out.println("ToDoItemNotFoundException");
			fail("ToDoItemNotFoundException");
		
		//e.printStackTrace();
		}
	
	}
	
	@Test
	void testRemoveItemsInStatusComplete() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime overDueDate1 = today.minusDays(4);
		LocalDateTime overDueDate2 = today.minusHours(4);
		LocalDateTime notOverDueDate1 = today.plusDays(6);
		LocalDateTime notOverDueDate2 = today.plusDays(6);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", overDueDate1);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", overDueDate2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", notOverDueDate1);
		ToDoItem t4 = new ToDoItem("Aktivitet4", "Description4", notOverDueDate2);
		
		todoListTest.addToDo(t1);
		todoListTest.addToDo(t2);
		todoListTest.addToDo(t3);
		todoListTest.addToDo(t4);
		
		assertTrue(todoListTest.getToDoListItem(0).setStatus(Status.COMPLETE));
		assertTrue(todoListTest.getToDoListItem(2).setStatus(Status.COMPLETE));
		assertTrue(todoListTest.size() == 4);
		
		todoListTest.removeCompleted();
		assertTrue(todoListTest.size() == 2);
			
	}
}
