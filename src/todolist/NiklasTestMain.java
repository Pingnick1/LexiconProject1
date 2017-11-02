package todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import Exceptions.ToDoItemNotFoundException;
import javafx.util.converter.LocalDateStringConverter;

public class NiklasTestMain {

	public static void main(String[] args) throws ToDoItemNotFoundException {
		// TODO Auto-generated method stub
		
		//CharSequence t1 = "2017-12-24T10:10:10";
		//CharSequence format = "Y-M-d H:m:s";
		//DateTimeFormatter t = new DateTimeFormatterBuilder();
		ToDoList todoListTest = new ToDoList();
		
		
		
		LocalDate today1 = LocalDate.now();
		LocalDateTime today2 = LocalDateTime.now();
		LocalDateTime anotherDay1 = LocalDateTime.of(2017, Month.JUNE, 24, 12, 30, 24);
		LocalDateTime anotherDay2 = LocalDateTime.of(2016, Month.JUNE, 24, 15, 15,24);
		LocalDateTime anotherDay3 = LocalDateTime.of(2018, Month.JUNE, 24, 15, 15,24);
		LocalDateTime anotherDay4 = LocalDateTime.of(2017, Month.NOVEMBER, 01, 12, 35, 01);
		
		System.out.println("Today1: " + today1);
		System.out.println("Today2: " + today2);
		System.out.println("Deadline: " + anotherDay1);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", anotherDay1);
		ToDoItem t2 = new ToDoItem("Aktivitet2", "Description2", anotherDay2);
		ToDoItem t3 = new ToDoItem("Aktivitet3", "Description3", anotherDay3);
		ToDoItem t4 = new ToDoItem("Aktivitet4", "Description4", anotherDay4);
		
		System.out.println(t1);
		
		
		if(todoListTest.addToDo(t1) && todoListTest.addToDo(t2) && todoListTest.addToDo(t3) && todoListTest.addToDo(t4)) {
			System.out.println("Ett styck todo tillagt.");
			System.out.println("Antal todo: " + todoListTest.size());
			System.out.println("Skriv ut todolist:");
			//System.out.println(todoListTest);
			
		}
		else {
			System.out.println("Misslyckades att lägga till todo!");
		}
		
		System.out.println(todoListTest); 
		
		todoListTest.checkDeadlines();
		
		System.out.println(todoListTest);
		
		
		try {
			ToDoItem foundToDoItem = todoListTest.findToDoItemByID(3);
			System.out.println(foundToDoItem);
		} catch (ToDoItemNotFoundException e) {
			// TODO Auto-generated catch block
			
			System.out.println("ToDoItemNotFoundException");
			
			//e.printStackTrace();
		}
		
		todoListTest.getToDoListItemByIndex(2).setStatus(Status.COMPLETE);
		todoListTest.getToDoListItemByIndex(0).setStatus(Status.COMPLETE);
		System.out.println(todoListTest);
		todoListTest.removeCompleted();
		System.out.println(todoListTest);
		
		try {
			int index = todoListTest.indexOfID(2);
			System.out.println("Found index: " + index);
		} catch (ToDoItemNotFoundException e) {
			// TODO Auto-generated catch block
			
			System.out.println("ToDoItemNotFoundException");
			
			//e.printStackTrace();
		}
		
		
	}

}
