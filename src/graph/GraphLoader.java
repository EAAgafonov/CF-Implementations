package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class GraphLoader {
	
	
	
	public static void loadMap(String filename, GraphQ map) {
		BufferedReader reader = null;
		Random rnd = new Random(7);
		
		try {
			String nextLine;
			reader = new BufferedReader(new FileReader(filename));
			while ((nextLine = reader.readLine()) != null) {
				ArrayList<String> tokens = splitInputString(nextLine);//должен вернуться list с 5ю элементами				
				
				GeographicPoint location1 = new GeographicPoint(Double.parseDouble(tokens.get(0)), Double.parseDouble(tokens.get(1)));
				GeographicPoint location2 = new GeographicPoint(Double.parseDouble(tokens.get(2)), Double.parseDouble(tokens.get(3)));

				map.addVertex(location1);
				map.addVertex(location2);
				map.addEdge(location1, location2, tokens.get(4), (int) ( Math.random() * 10 ));
				//(int) ( Math.random() * 10 )
				
			}
		} catch (Exception e) {
			System.out.println(e + " - GraphLoader.java");
		}
	}
		static ArrayList<String> splitInputString(String line) {
		ArrayList<String> tokens = new ArrayList<>();
		Pattern p = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
		Matcher m = p.matcher(line);
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
		
	}
}

