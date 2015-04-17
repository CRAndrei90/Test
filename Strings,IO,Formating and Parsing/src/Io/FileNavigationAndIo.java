package Io;

import java.io.*;
import java.util.Scanner;

public class FileNavigationAndIo {

	public static void main(String[] args) {
				
		try{
			String io = null;
			Scanner sc = new Scanner(System.in);
			File dir = new File("DirectorLearn");
			dir.mkdir();
			File fileDen = new File(dir,"FirstText.txt");
			fileDen.createNewFile();
			FileWriter fw = new FileWriter(fileDen);
			BufferedWriter bw = new BufferedWriter(fw);
			io = sc.nextLine();
			bw.write(io);
			
			bw.close();
			fw.close();
			
			
			
			FileReader fr = new FileReader(fileDen);
			BufferedReader br = new BufferedReader(fr);
			String citire = br.readLine();
			System.out.println(citire);
			
			
		}catch(IOException e){}

	}

}
