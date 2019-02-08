package cx.sfy.tes3finder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationManager {

	private static final String locregex = "Server says DedicatedPlayer (.*.) moved to (.*.)";
	private static final String coordregex = "(-?[0-9]+), (-?[0-9]+)";
	
	private static final Pattern locpt = Pattern.compile(locregex, Pattern.MULTILINE);
	private static final Pattern coordpt = Pattern.compile(coordregex, Pattern.MULTILINE);
	
	public static Map<String, String> locations = new ConcurrentHashMap<String, String>();
	public static Map<String, String> outsides = new ConcurrentHashMap<String, String>();
	
	public static void checkLocation(String stg) {
		Matcher matcher = locpt.matcher(stg);
		
		if (!matcher.find()) {
			return;
		}
		
		String player = matcher.group(1);
		String cell = matcher.group(2);
		
		locations.put(player, cell);
		
		if (isCoordonate(cell)) {
			outsides.put(player, cell);
		}
	}
	
	public static boolean isCoordonate(String cell) {
		Matcher matcher = coordpt.matcher(cell);
		
		return matcher.find();
	}
	
	public static int[] getCoordonate(String player) {
		int[] coords = new int[]{8092, 8092};
		
		if (outsides.containsKey(player)) {
			String cell = outsides.get(player);
			
			System.out.println(cell);
			
			Matcher matcher = coordpt.matcher(cell);
			matcher.find();
			
			coords[0] = Integer.valueOf(matcher.group(1));
			coords[1] = Integer.valueOf(matcher.group(2));
		}
		
		return coords;
	}

	public static String getLocation(String player) {
		if (!locations.containsKey(player)) {
			System.out.println("That player has not been found in our database!");
			return "UNKNOWN";
		}
		
		String cell = locations.get(player);
		
		System.out.println(player + " is situated at " + cell + ".");
		
		return cell;
	}
	
}
