package todolist;

public class Projekt1Main
{
	static Menu menu;

	public static void main(String[] args)
	{
		try
		{
			menu.mainMenu();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
