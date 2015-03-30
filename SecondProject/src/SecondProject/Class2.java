package SecondProject;

public class Class2 extends Class1{

	public String semnPct;
	public Class2(String afisare,String semnDePunctuatie){
		super(afisare);
		setSemn(semnDePunctuatie);
		
	}
	
	public void setSemn(String semn){
		this.semnPct = semn;
	}
	
	public String getSemn(){
		return semnPct;
	}
	public void afisarePropozitie(){
		System.out.println("Cuvantul introdus este :"+getName()+getSemn());
	}
	
}
