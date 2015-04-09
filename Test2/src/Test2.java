import java.io.*;
import java.util.Scanner;

public class Test2 {

	public static void checkFood(String username) throws BadFoodException {
		if (username.equals("apple")) {
			System.out.println("It is good for eating ");
		} else {
			throw new BadFoodException(username);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		boolean good = false;

		do {
			System.out.println("Enter a user name : ");
			String username = input.next();
			try {
				checkFood(username);

			} catch (BadFoodException e) {
				System.out.println("\n" + e);
			}
		} while (good == false);
	}
}
