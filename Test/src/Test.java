import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> var = new ArrayList<>();
		var.add(5);
		var.add(10);
		var.add(3);
		var.add(6);
		System.out.println("Minimum is :" + getMinFromList(var) + "\n");
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			String s=Integer.toString(i);
			map.put(s, rand.nextInt(20));
		}
		map.forEach((k, v) -> System.out.println("key:" + k + "  Value:" + v));
		
		String s1 = Integer.toString(getMinFromList(var));
		for (String val : map.keySet()) 
		{
			if(val.contains(s1))
				System.out.println("The minim value from list in Key MAP");
		}	
			
		if (map.containsKey(s1)) {
			Integer value = map.get(s1);
			System.out.println("The value for min key is : " + value );
		}
			
		
		
	}

	public static Integer getMinFromList(List<Integer> number) {

		int min = number.get(0);
		for (Integer s : number) {
			if (min > s)
				min = s;
		}
		return min;
	}

}
