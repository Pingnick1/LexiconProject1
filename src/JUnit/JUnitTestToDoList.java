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
	void testAddTaskToList() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testAddTaskToList:");
		System.out.println("-------------------------------------------------------");
		
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
		System.out.println("-------------------------------------------------------");
		System.out.println("testRemoveToDoItemFromList:");
		System.out.println("-------------------------------------------------------");
		
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime anotherDay2 = LocalDateTime.of(2017, Month.JUNE, 24, 15, 15,24);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", anotherDay2);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", anotherDay2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", anotherDay2);
		
		assertTrue(todoListTest.addToDo(t1));	
		assertTrue(todoListTest.addToDo(t2));
		assertTrue(todoListTest.addToDo(t3));	
		
		int idToRemove = todoListTest.getToDoListItemByIndex(1).getId();
		
		System.out.println("id to remove: " + idToRemove);
		
		assertTrue(todoListTest.size() == 3);
		
		System.out.println(todoListTest);
		
		assertTrue(todoListTest.removeToDoItemByID(idToRemove));
		assertTrue(todoListTest.size() == 2);
		System.out.println(todoListTest);

	}
	
	@Test
	void testCheckDeadlines() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testCheckDeadlines:");
		System.out.println("-------------------------------------------------------");
		
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
		
		assertTrue(todoListTest.getToDoListItemByIndex(0).getStatus() == Status.OVERDUE);
		
		assertTrue(todoListTest.getToDoListItemByIndex(1).getStatus() == Status.OVERDUE);
		
		assertTrue(todoListTest.getToDoListItemByIndex(2).getStatus() == Status.OPEN);
	}
	
	@Test
	void testSetNewStatus() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testSetNewStatus:");
		System.out.println("-------------------------------------------------------");
		
		ToDoList todoListTest = new ToDoList();
		
		LocalDateTime today = LocalDateTime.now();
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", today);
		
		todoListTest.addToDo(t1);
		
		
		assertTrue(todoListTest.getToDoListItemByIndex(0).getStatus() == Status.OPEN);
		
		assertTrue(todoListTest.getToDoListItemByIndex(0).setStatus(Status.COMPLETE));
		
		assertTrue(todoListTest.getToDoListItemByIndex(0).getStatus() == Status.COMPLETE);
	}
	
	@Test
	void testFindToDoItem() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testFindToDoItem:");
		System.out.println("-------------------------------------------------------");
		
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
				
		int itemToFind = todoListTest.getToDoListItemByIndex(1).getId();
		
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
		System.out.println("-------------------------------------------------------");
		System.out.println("testRemoveItemsInStatusComplete:");
		System.out.println("-------------------------------------------------------");
		
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
		
		assertTrue(todoListTest.getToDoListItemByIndex(0).setStatus(Status.COMPLETE));
		assertTrue(todoListTest.getToDoListItemByIndex(2).setStatus(Status.COMPLETE));
		assertTrue(todoListTest.size() == 4);
		
		todoListTest.removeCompleted();
		assertTrue(todoListTest.size() == 2);
			
	}
	
	@Test
	void testGetToDoListItemByIndex() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testGetToDoListItemByIndex:");
		System.out.println("-------------------------------------------------------");
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
		
		ToDoItem newItem = todoListTest.getToDoListItemByIndex(1);
		System.out.println("newItem.getTitle: " + newItem.getActivityTitle().toString() + ", t2.getTitle: " + t2.getActivityTitle().toString());
		assertTrue(newItem.getActivityTitle().toString().equals(t2.getActivityTitle().toString()));
		
		
	}
	
	@Test
	void testFindToDoItemByTitle() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testFindToDoItemByTitle:");
		System.out.println("-------------------------------------------------------");
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
		
		try {
			ToDoItem foundItem = todoListTest.findToDoItemByTitle("Aktivitet2");
			
			assertTrue(foundItem.getActivityTitle().toString().equals(t2.getActivityTitle().toString()));
		} catch (ToDoItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ToDoItemNotFoundException");
		}
		
	}
	
	@Test
	void testAutoRemoveCompleteOverdue() {
		System.out.println("-------------------------------------------------------");
		System.out.println("testAutoRemoveCompleteOverdue:");
		System.out.println("-------------------------------------------------------");
		
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
		
		System.out.println(todoListTest);
		
		todoListTest.autoRemoveCompleteOverdue();
		
		System.out.println(todoListTest);
	}
}
