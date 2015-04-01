import java.util.*;
public class Learnlist {

	public static void main(String[] args){
		String[] things = {"oua","carne","lapte","miere"};
		List<String> list1 = new ArrayList<String>(); 
		
		for(String l1 : things)
		{ 
			list1.add(l1);
		}
		
		List<String> list2 = new ArrayList<String>();
		String[] things2 = {"carne","lapte"};
		for(String l2:things2)
		{
			list2.add(l2);
		}
		for(int i=0;i<list1.size();i++)
			System.out.print(list1.get(i)+" ");
		System.out.println("\nDelete the 'carne and lapte' and after my list is :");
		editlist(list1,list2);
		System.out.println();
		for(int i=0;i<list1.size();i++)
			System.out.print(list1.get(i)+" ");
	}
	public static void editlist(Collection<String> x,Collection<String> y){
		Iterator<String> it = x.iterator();
		while(it.hasNext()){
			if(y.contains(it.next()))
				it.remove();
		}
	}
}
