package Strings;

public class Exercise {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder("abc");
		sb.append("def").reverse().insert(3, "---");
		System.out.println(sb);
		
		
	}

}
