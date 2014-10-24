import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int maxCount = 0;
		ArrayList<String> maxWords = new ArrayList<String>();
		while(in.hasNextLine()) {
			String line = in.nextLine();
			Matcher m = Pattern.compile("[a-zA-Z0-9]+").matcher(line);
			while(m.find()) {
				String word = line.substring(m.start(), m.end()).toLowerCase();
				if(!map.containsKey(word))
					map.put(word, 0);
				map.put(word, map.get(word) + 1);
				if(map.get(word) > maxCount) {
					maxCount = map.get(word);
					maxWords.clear();
					maxWords.add(word);
				}
				else if(map.get(word) == maxCount)
					maxWords.add(word);
			}
		}
		System.out.println(maxCount + " occurrences");
		for(String w : maxWords)
			System.out.println(w);
	}

}
