package ParsingAndTokenizing;

import java.util.regex.*;

public class Search {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("1 a12 234b");
		while(m.find()){
			System.out.println(m.start()+ " ");
		}
	}

}
