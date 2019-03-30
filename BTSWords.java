import java.io.*;
import java.util.*;

public class BTSWords {
	public static void main(String [] args)throws IOException {
		Map<Integer, ArrayList<String>> imFineDist= calcWordDistOrder("I'm Fine", "I'mFine.txt");
		printOrder("I'm Fine", imFineDist);
		Map<Integer, ArrayList<String>> idolDist = calcWordDistOrder("IDOL", "IDOL.txt");
		printOrder("IDOL", idolDist);
		Map<Integer, ArrayList<String>> tUDist = calcWordDistOrder("Truth Untold", "TruthUntold.txt");
		printOrder("Truth Untold", tUDist);
		Map<Integer, ArrayList<String>> loveMazeDist = calcWordDistOrder("Love Maze", "LoveMaze.txt");
		printOrder("Love Maze", loveMazeDist);
		Map<Integer, ArrayList<String>> plutoDist = calcWordDistOrder("134340", "Pluto.txt");
		printOrder("134340", plutoDist);
		Map<Integer, ArrayList<String>> jumpDist = calcWordDistOrder("Jump", "Jump.txt");
		printOrder("Jump", jumpDist);
		Map<Integer, ArrayList<String>> maCityDist = calcWordDistOrder("Ma City", "MaCity.txt");
		printOrder("Ma City", maCityDist);
		Map<Integer, ArrayList<String>> dimpleDist = calcWordDistOrder("Dimple", "Dimple.txt");
		printOrder("Dimple", dimpleDist);
	}
	public static void printOrder(String songName, Map<Integer, ArrayList<String>> songDist) {
		System.out.println("***"+songName+"***");
		int k = 0;
		for(Integer i: songDist.keySet()) {
			ArrayList<String> names = songDist.get(i);
			k += names.size();
		}
		int h = 0;
		for(Integer i: songDist.keySet()) {
			ArrayList<String> names = songDist.get(i);
			if(names.size() > 1 && h == (songDist.size() - 1)) {
				k = 1;
			}
			System.out.print(k+") ");
			for(int m = 0; m < names.size(); m++) {
				System.out.print(names.get(m));
				if(m < names.size() - 1)
					System.out.print(", ");
			}
			System.out.println(": "+i);
			k -= names.size();
			h++;
		}
		System.out.println();
	}
	public static int[] calcWordDist(String inputFile)throws IOException {
		int count[] = new int[7];
		Scanner scan = new Scanner(new File(inputFile));
		ArrayList<String> current = new ArrayList<String>();
		int jungkook = 0;
		int v = 0;
		int jimin = 0;
		int rm = 0;
		int jhope = 0;
		int suga = 0;
		int jin = 0;
		boolean collectLines = false;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			if((line.equals("Jungkook") || line.equals("V") || line.equals("Jimin") || line.equals("RM")
				|| line.equals("JHope") || line.equals("Suga") || line.equals("Jin") || line.equals("BTS"))
				 && collectLines) {
				 	current.clear();
				 	current.add(line);
				 	collectLines = false;
				 }
			else if((line.equals("Jungkook") || line.equals("V") || line.equals("Jimin") || line.equals("RM")
				|| line.equals("JHope") || line.equals("Suga") || line.equals("Jin") || line.equals("BTS"))
				 && !collectLines) {
				 	current.add(line);
				 }
			else {
				collectLines = true;
				String[] words = line.split(" ");
				if(current.contains("Jungkook")) {
					jungkook += words.length;
				}
				if(current.contains("V")) {
					v += words.length;
				}
				if(current.contains("Jimin")) {
					jimin += words.length;
				}
				if(current.contains("RM")) {
					rm += words.length;
				}
				if(current.contains("JHope")) {
					jhope += words.length;
				}
				if(current.contains("Suga")) {
					suga += words.length;
				}
				if(current.contains("Jin")) {
					jin += words.length;
				}
				if(current.contains("BTS")) {
					jungkook += words.length;
					v += words.length;
					jimin += words.length;
					rm += words.length;
					jhope += words.length;
					suga += words.length;
					jin += words.length;
				}
			}
		}
		count[0] = jungkook;
		count[1] = v;
		count[2] = jimin;
		count[3] = rm;
		count[4] = jhope;
		count[5] = suga;
		count[6] = jin;
		return count;
	}
	public static Map<Integer, ArrayList<String>> calcWordDistOrder(String song, String inputFile)throws IOException {
		Map<Integer, ArrayList<String>> songTotals = new TreeMap<Integer, ArrayList<String>>();
		int[] count = calcWordDist(inputFile);
		String[] names = {"Jungkook", "V", "Jimin", "RM", "JHope", "Suga", "Jin"};
		for(int i = 0; i < count.length; i++) {
			int num = count[i];
			String name = names[i];
			if(num > 0) {
				if(songTotals.get(num) == null) {
					ArrayList<String> namesList = new ArrayList<String>();
					namesList.add(name);
					songTotals.put(num, namesList);
				}
				else {
					ArrayList<String> namesList = songTotals.get(num);
					namesList.add(name);
					songTotals.put(num, namesList);
				}
			}

		}
		return songTotals;
	}
}