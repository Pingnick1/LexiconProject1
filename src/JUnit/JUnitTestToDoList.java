package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import todolist.ToDoItem;
import todolist.ToDoList;

class JUnitTestToDoList {

	@Test
	void testAddTaskToList_() {
		ToDoList todoListTest = new ToDoList();
		
		LocalDate today1 = LocalDate.now();
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", today1);
		
		assertTrue(todoListTest.addToDo(t1));	
		assertTrue(todoListTest.addToDo(t1));
		assertTrue(todoListTest.size() == 2);
	}
	
	@Test
	void testAddToDoItem() {
		
	}

}
