import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class steam {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("SteamBills.txt"));

		int priceCategoryWeights[] = {0,0,0,0};
		int total = 0;

		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			int endOfPrice = line.indexOf('€');
			if(endOfPrice != -1){
				line = line.substring(line.indexOf(' '), endOfPrice);
				line = line.substring(9);
				line = line.replace(',', '.');
				line = line.replace('-', '0');
				double paid = Double.parseDouble(line);
				total += paid;
				if(paid >= 40.0) priceCategoryWeights[0] += paid;
				else if (paid >= 20.0) priceCategoryWeights[1] += paid;
				else if (paid >= 10) priceCategoryWeights[2] += paid;
				else priceCategoryWeights[3] += paid;
			}
		}

		System.out.println("Total: " + total + "€");
		System.out.println("40+: " + priceCategoryWeights[0] + "€, 20+: " + priceCategoryWeights[1] + "€, 10+: " + priceCategoryWeights[2] + "€, The rest: " + priceCategoryWeights[3] + "€");

		scanner.close();
	}

}
