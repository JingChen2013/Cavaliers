package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Alex Yang
 * 
 */
public class ReadinData {

	public int shopping[][]=new int[190000][4];
	public void convertTxtToJava() {
		Scanner path=new Scanner(System.in);
		System.out.println("Please input the path. Sample:/Users/Alex/Cavaliers/data/converted_data.txt");
		
		String txtFileToRead = path.nextLine();
		BufferedReader br = null;
		String line = "";
		String splitBy = "\t";
		int i=0;
		try {
			br = new BufferedReader(new FileReader(txtFileToRead));
			while ((line = br.readLine()) != null) {
				i=i+1;
				// split on comma(',')
				String[] Shop = line.split(splitBy);
				//get Shop[0-3] to store the 4 elements;
				for(int j=0;j<4;j++){
					shopping[i][j]=Integer.parseInt(Shop[j]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}