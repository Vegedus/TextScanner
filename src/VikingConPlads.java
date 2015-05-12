import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class VikingConPlads {

	public static void main(String[] args) throws FileNotFoundException {

		boolean names = true;

		int antalScenarier = 0;
		Scanner scanner2 = new Scanner(new File("Viking Navne.txt"));

		while(scanner2.hasNextLine()){
			scanner2.nextLine();
			antalScenarier++;
		}

		Pattern[] scenarieNavne = new Pattern[antalScenarier];
		scanner2 = new Scanner(new File("Viking Navne.txt"));

		int n = 0;
		while(scanner2.hasNextLine()){
			String line = scanner2.nextLine();
			scenarieNavne[n] = Pattern.compile(line);
			n++;
		}
		scanner2.close();

		Scanner scanner = new Scanner(new File("Viking.txt"));


		String string = "kunne ikke få plads på";
		Pattern req = Pattern.compile(string);

		Matcher matcher;

		@SuppressWarnings("unchecked")
		HashMap<String, Integer> results = new HashMap<String, Integer>(antalScenarier);


		while(scanner.hasNextLine()){
			String line = scanner.nextLine();

			matcher = req.matcher(line);
			if(matcher.find()){

				String key = line.substring(matcher.end()+1, line.length()-1);

				for (int i = 0; i < scenarieNavne.length; i++) {

					matcher = scenarieNavne[i].matcher(line);

					if(names){
						key = scenarieNavne[i].toString();
						if(matcher.find()){
							if (results.get(key) == null){ results.put(key, 1);
							} else results.put(key, results.get(key)+1);
							break;
						}
					} else{
						matcher = scenarieNavne[i].matcher(line);
						if(matcher.find()) break;
						else if(i == scenarieNavne.length-1){
							results.put(key, 9999);
						}	
					}

				}
			}
		}


		String[] keys = new String[results.size()];
		results.keySet().toArray(keys);
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i] + "\t\t" +results.get(keys[i]));
		}

		//System.out.println(results.toString());
		scanner.close();
	}

}
