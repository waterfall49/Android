
public class Walrus extends Animal{
	
	public String dentalHealth;
	
	public Walrus(String sex, double weight, String dentalHealth, String gpsinfo){
		this.species = "Walrus";
		this.sex = sex;
		this.weight = weight;
		this.dentalHealth = dentalHealth;
		this.mygps = new GPS();
		mygps.appendArray(gpsinfo);
	}

	@Override
	public String doString(){
		String info = String.format("- Species: %s, Sex: %s, Weight : %.1f Dental Health: %s,\n   GPS information: %s",
				                    this.species, this.sex, this.weight, this.dentalHealth, this.mygps.gpsArray.get(0));
		return info;
	}

}
