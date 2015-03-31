import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Class1 {

	private static final Map<Integer, Worker> workerById = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		workerById.put(1, new Worker(1,"Radu",2000));
		workerById.put(2,new Worker(2,"Mihai",1500));
		workerById.put(3,new Worker(3,"Andrei",3000));
		
		System.out.println("Initial salaries of workers");
		//Display the person's
		Set<Map.Entry<Integer,Worker>> entrySet  = workerById.entrySet();
		for(Map.Entry<Integer,Worker> entry : entrySet){
			Worker worker = entry.getValue();
			System.out.println(worker.getName()+": "+worker.getSalary());
		}
		
		
		System.out.println("\nNew Salary for :");
		
		Iterator<Integer> itr = workerById.keySet().iterator();
		while(itr.hasNext())
		{
			Worker current = workerById.get(itr.next());
			if((int)(current.getSalary())<3000)
			{
				int increasedSalary = (int)(current.getSalary())+500;
				current.setSalary(increasedSalary);
				System.out.println(current.getName()+" "+current.getSalary());
			}
			
		}
		
		
	}

	private static class Worker {
		private int id;
		private String name;
		private int salary;

		public Worker(int id, String name, int salary) {
			this.id = id;
			this.name = name;
			this.salary = salary;
		}

		public final int getId() {
			return id;
		}

		public final String getName() {
			return name;
		}

		public final int getSalary() {
			return salary;
		}

		public final void setId(int id) {
			this.id = id;
		}

		public final void setName(String name) {
			this.name = name;
		}

		public final void setSalary(int salary) {
			this.salary = salary;
		}
	}
}
