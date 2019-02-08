package cx.sfy.tes3finder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cx.sfy.tes3finder.map.MapGenerator;

public class CommandManager {

	private static final String regex = "\\] \\[INFO\\]: Player: \\/!(.*.)";
	private static final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

	public static void checkCommand(String stg) {
		Matcher matcher = pattern.matcher(stg);

		if (!matcher.find()) {
			return;
		}

		String[] args = matcher.group(1).split("\\s+");
		
		
		if (args[0].equalsIgnoreCase("settarget") && args.length >= 2) {
			StringBuilder name = new StringBuilder();
			
			for (int i = 1; i < args.length; i++) {
				name.append(args[i]).append(' ');
			}

			
			String target = name.toString().trim();
			System.out.println("The target was set to " + target);
			Main.target = target;
		} else if ((args[0].equalsIgnoreCase("getlocation") || args[0].equalsIgnoreCase("findplayer")) && args.length >= 2) {
			StringBuilder name = new StringBuilder();
			
			for (int i = 1; i < args.length; i++) {
				name.append(args[i]).append(' ');
			}

			
			LocationManager.getLocation(name.toString().trim());
		} else if (args[0].equalsIgnoreCase("togglemap") && args.length >= 1) {
			Main.showmap = !Main.showmap;
			MapGenerator.toggleMap(Main.showmap);
			System.out.println("Map Showing Up: " + Main.showmap);
		}

	}

}
