package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import todolist.ToDoItem;

class JUnitTestToDoItem {

	@Test
	void testToString() {
		
		LocalDate today1 = LocalDate.now();
		LocalDateTime anotherDay = LocalDateTime.of(2017, Month.JUNE, 24, 15, 15,24);
		
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", anotherDay);
		
		String s1 = "startDate: " + today1 + ", endDate: " + anotherDay +", activityName: Aktivitet1, description: Description1, status:OPEN";
				
		assertTrue(t1.toString().equals(s1));
		
		//System.out.println(s1);
		//System.out.println(t1);
	}

}
