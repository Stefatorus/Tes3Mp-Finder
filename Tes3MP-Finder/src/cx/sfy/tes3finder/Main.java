package cx.sfy.tes3finder;

import java.io.IOException;
import java.util.Scanner;

import cx.sfy.tes3finder.map.MapGenerator;

public class Main {

	// Refresh the map each 10 seconds
	public final static int refreshrate = 10;
	
	public static String target = "";
	
	// in preparation for showing the map.
	public static boolean showmap = true;
	
	private static String address;
	
	public static boolean isMoved(String stg) {
		String search = target;
		
		return stg.contains(search);
	}
	
	public static void main(String[] args) throws IOException {
		Runtime r = Runtime.getRuntime();
		
		Scanner kb = new Scanner(System.in);
		
		System.out.print("Please input server ip and port: ");
		address = kb.nextLine();
		
		String argument = "";
		
		if (!address.isEmpty()) {
			argument+=" " + address;
		}
		
		kb.close();
		
		Process pr = r.exec("tes3mp.exe" + argument);
		
		MapGenerator.Enabler();
		MapGenerator.toggleMap(true);
		
		Scanner s = new Scanner(pr.getInputStream());
		
		while (s.hasNextLine()) {
			String stg = s.nextLine();
			
			watchdogGame(stg);
			LocationManager.checkLocation(stg);
			CommandManager.checkCommand(stg);
		}

		
		s.close();
	}
	
	public static void watchdogGame(String stg) {
		if (!stg.contains("] [ERR]: Connection lost.")) {
			return;
		}
		
		System.out.println("The server has closed the connection. Tes3Mp wrapper now shutting down.");
		
		System.exit(0);
	}
	
}
