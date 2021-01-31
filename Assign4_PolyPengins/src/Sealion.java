
public class Sealion extends Animal{
	
	public int numberOfSpots;
	
	public Sealion(String sex, double weight, int numberOfSpots, String gpsinfo){
		this.species = "Sea Lion";
		this.sex = sex;
		this.weight = weight;
		this.numberOfSpots = numberOfSpots;
		this.mygps = new GPS();
		mygps.appendArray(gpsinfo);
	}

	@Override
	public String doString(){
		String info = String.format("- Species: %s, Sex: %s, Weight : %.1f, Number of Spots : %d,\n   GPS information: %s",
				 this.species, this.sex, this.weight, this.numberOfSpots, this.mygps.gpsArray.get(0));
		return info;
	}//end report

}//end class
