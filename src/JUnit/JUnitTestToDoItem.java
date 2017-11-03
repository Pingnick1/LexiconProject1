package JUnit;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import todolist.ToDoItem;

class JUnitTestToDoItem
{
	@Test
	void testisOverDue() throws Exception
	{
		LocalDateTime testDate1 = LocalDateTime.now();
		testDate1 = testDate1.plusDays(1);
		LocalDateTime testDate2 = LocalDateTime.now();
		testDate2 = testDate2.minusDays(1);
		ToDoItem t1 = new ToDoItem("Aktivitet1", "Description1", testDate1);
		ToDoItem t2 = new ToDoItem("Aktivitet1", "Description1", testDate2);
		assertEquals(t1.isOverDue(), false);
		assertEquals(t2.isOverDue(), true);
	}
}