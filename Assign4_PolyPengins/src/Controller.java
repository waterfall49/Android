
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
	
	public Penguin penguin;
	public Sealion sealion;
	public Walrus walrus;
	public Animal myAnimal;
	
	/**
	 * Create constructor
	 */
	public Controller(){};
	
	public void generateObject(String sex, double weight, double bp, String gps){
		penguin = new Penguin(sex, weight, bp, gps);
		myAnimal = penguin;
	}
	
	public void generateObject(String sex, double weight, int spot, String gps){
		sealion = new Sealion(sex, weight, spot, gps);
		myAnimal = sealion;
	}
	
	public void generateObject(String sex, double weight, String dh, String gps){
		walrus = new Walrus(sex, weight, dh, gps);
		myAnimal = walrus;
	}
	

	public void doWrite(String data) {
		
		File file = new File("resource/info.txt");
		FileWriter fr = null;
		try {
			fr = new FileWriter(file, true);
			
			if(!file.exists()){
				file.createNewFile();
			} 
		fr.write(data);
		fr.write("\n");
		
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		finally{
			
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//end doWrite
	
	public String doRead(String fileName) {
		String info = "< The Result > \n";
		try {

			BufferedReader br = new BufferedReader(new FileReader("./resource/info.txt"));
			String s = null;
			while((s=br.readLine())!=null) { //while stuff to read
				
				info += s + "\n";
			}
			
			br.close();
			
		} catch(IOException e) //specific ie "close" to error
		{
			System.out.println(e.getMessage());
		}
		
		catch(Exception e) //general
		{
			System.out.println(e.getMessage());
		}
		return info;
		
	} //end doRead
	
	
}//end class
