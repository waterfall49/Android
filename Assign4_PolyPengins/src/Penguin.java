
public class Penguin extends Animal{
	
	public double bloodPressure;
	
	public Penguin(String sex, double weight, double bloodPressure, String gpsinfo){
		this.species = "Penguin";
		this.sex = sex;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.mygps = new GPS();
		mygps.appendArray(gpsinfo);
	}

	@Override
	public String doString(){
	String info = String.format("- Species: %s, Sex: %s, Weight : %.1f, Blood Pressure: %.1f,\n   GPS information: %s",
			 					this.species, this.sex, this.weight, this.bloodPressure, this.mygps.gpsArray.get(0));
	return info;
	}//end report

}//end class
