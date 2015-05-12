import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkypeSnafu {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("Skype3.txt"));

		//Example: [10-08-2014 00:47:48] LD-samaaa~: Uh guess no one is here either
		int totalDays = 0;

		String names[] = {"Eggs",
				"Stuff",
				"Riz",
				"LD",
				"Hiroko",
				"Horyu",
				"Warby",
				"Tuor",
				"Fummo",
				"Vegedus",
				"Karuama",
				"Male",
				"Sefrol",
				"Artan",
				"Calek Albion",
				"frankydcoolsevelt",
				"Daniel",
				"beeare",
				"alopunk",
				"Wazit",
				"Tenshi",
				"Mirak",
		"Misc"};
		int posterAmount[] = new int[names.length];
		for (int i = 0; i < posterAmount.length; i++) {
			posterAmount[i] = 0;
		}
		
		//15508 = 7076 + 
		
		String a = "^\\[\\d\\d.................\\] ";
		//Pattern dateFormat = Pattern.compile(Pattern.quote("[..[-]..[-]....[ ]..[:]..[:]..]"));
		Pattern dateFormat = Pattern.compile("^\\[\\d\\d.................\\].\\w");
		Pattern posters[] = {
				Pattern.compile(a+"Eggs-o'-dis-kun~:|Exodis-kun~:"),
				Pattern.compile(a+"Stuff-chan~:"),
				Pattern.compile(a+"Riz-chaaan~:"),
				Pattern.compile(a+"LD-samaaa~:"),
				Pattern.compile(a+"Hiroko-imouto~:"),
				Pattern.compile(a+"Horyu.b-b|"+a+"Hiryu.b.b.aka..~:"),
				Pattern.compile(a+"Warby-kun~:"),
				Pattern.compile(a+"Tuor-onii-sama!~:"),
				Pattern.compile(a+"Fummo-chan~:"),
				Pattern.compile(a+"Vegedus-dono~:"),
				Pattern.compile(a+"Karuama-oneechan~:|"+a+"Bananamancer"),
				Pattern.compile(a+"Male The  Grape Ape:|"+a+"thefighterofthenightman:"),
				Pattern.compile(a+"Sefrol-kuuun~:"),
				Pattern.compile(a+"Artan:|"+a+"Artann|"+a+"Artan/Millo:"),
				Pattern.compile(a+"calek01:|"+a+"Calek Albion:"),
				Pattern.compile(a+"frankydcoolsevelt:"),
				Pattern.compile(a+"Daniel Skeete:|"+a+"daniel,skeete:"),
				Pattern.compile(a+"beeare:|"+a+"BR"),
				Pattern.compile(a+"alopunk:"),
				Pattern.compile(a+"Wazit:"),
				Pattern.compile(a+"Tenshi-kun~:"),
				Pattern.compile(a+"Mirak-kun~:")};

		int timeStart = 12;
		

		Matcher matcher;
		//enum statisticsName = {lines, 
		int lines = 0;
		int messagesInHour[] = new int[24];
		messagesInHour[1]++;

		//Validater
		for (int i = 0; i < posters.length; i++) {
			Pattern name = Pattern.compile(names[i]);
			if( !name.matcher(posters[i].toString()).find() ) System.out.println("!"+i + names[i]);
		}
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();

			matcher = dateFormat.matcher(line);
			if(matcher.find()){
				lines++;
				//System.out.println(line);
				messagesInHour[Integer.parseInt(line.substring(timeStart,timeStart+2))]++;

				//Finding and recording who made the post		
				for (int i = 0; i < posters.length; i++) {
					matcher = posters[i].matcher(line);
					if(matcher.find()){
						//For examining a specific poster
						///if(i==0) System.out.println(line);
						posterAmount[i]++;
						//if(line.charAt(matcher.end()+1)=='[')System.out.println(line);
						break;
					} else if(i+1 == posters.length) {posterAmount[i+1]++; 
					//System.out.println(line);
					}
				}

			}
		}

		System.out.println("Posts: " + lines);
		/*System.out.println("Posts per hour (GMT+1):");

		for (int i = 0; i < messagesInHour.length; i++) {
			System.out.print(i + ": " + messagesInHour[i] + ", ");
			if(i == 11) System.out.println();
		}
		System.out.println("\n\nPosts per hour blocks:");
		int hourBlocks[] = new int[4];
		for (int i = 0; i < hourBlocks.length; i++) {
			hourBlocks[i] = messagesInHour[i*6]+messagesInHour[i*6+1]+messagesInHour[i*6+2]+messagesInHour[i*6+3]+messagesInHour[i*6+4]+messagesInHour[i*6+5];
			System.out.println(i*6+"-"+(i*6+5)+": "+hourBlocks[i]);
		}*/

		int total = 0;
		System.out.print("\nAmount of posts by specific poster:");
		for (int i = 0; i < posterAmount.length; i++) {
			if(i%4==0) System.out.println();
			System.out.print(names[i] + ": " + posterAmount[i]+ ",  ");
			total+= posterAmount[i];
		}
		System.out.println("\n"+total);
		scanner.close();
	}

}


//matcher.group();
//matcher.start();
//matcher.end();
