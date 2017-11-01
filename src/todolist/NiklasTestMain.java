package todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javafx.util.converter.LocalDateStringConverter;

public class NiklasTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//CharSequence t1 = "2017-12-24T10:10:10";
		//CharSequence format = "Y-M-d H:m:s";
		//DateTimeFormatter t = new DateTimeFormatterBuilder();
		ToDoList todoListTest = new ToDoList();
		
		
		
		LocalDate today1 = LocalDate.now();
		LocalDateTime today2 = LocalDateTime.now();
		LocalDateTime anotherDay = LocalDateTime.of(2017, Month.JUNE, 24, 12, 30, 24);
		
		System.out.println("Today1: " + today1);
		System.out.println("Today2: " + today2);
		System.out.println("Deadline: " + anotherDay);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", today1);
		
		if(todoListTest.addToDo(t1) && todoListTest.addToDo(t1) && todoListTest.addToDo(t1)) {
			System.out.println("Ett styck todo tillagt.");
			System.out.println("Antal todo: " + todoListTest.size());
			System.out.println("Skriv ut todolist:");
			System.out.println(todoListTest);
			
		}
		else {
			System.out.println("Misslyckades att lägga till todo!");
		}
		
		
		
		
		
		//deadlineDate = LocalDateTime.parse(t1);
		
		
		
		//
		
		
	}

}
