import java.io.File;
import java.io.FilenameFilter;
import java.util.*;


public class FirstClass{
	
	public static void main(String args[]) {
		ArrayList a = new ArrayList();
		
		for(int i=1;i<=10;i++)
			a.add(new Integer(i));
		
		Collections.shuffle(a);
		System.out.println("Vectorul amestecat: "+a);
		
		for(ListIterator it=a.listIterator(); it.hasNext();)
		{
			Integer x= (Integer) it.next();
			
			if(x.intValue()%2==0)
				it.set(new Integer(0));
		}
		System.out.print("Rezultate: "+a);
	}
}
