package Colection;
import java.util.*;

public class Colection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = new ArrayList<>();
		numbers.add(2);
		numbers.add(4);
		numbers.add(5);
		numbers.add(7);
		
		int s=0; //sum for parity numbers
		int s1=0;//sum for odd numbers
		for(Integer number:numbers)
		{
			System.out.print(number +" ");
		}
		System.out.println("");
		System.out.println("The Parity numbers are: ");
		for(Integer number:numbers)
			{
				if(number%2==0)
				{
					 s=s+number;
				 	System.out.print(number+" ");
				}	
				else 
					s1=s1+number;
			}
		
		System.out.println("\nThe Sum for Parity numbers is:" + s+" \nand for odd numbers is: "+s1);
	}
	

}
