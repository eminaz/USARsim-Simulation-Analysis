import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class run3 {


	public static FileWriter fstream;
	public static BufferedWriter out;
	
	public static void main(String[] args) throws IOException{
		 fstream = new FileWriter("/Users/hal/Desktop/Mark/image.txt");
		 out = new BufferedWriter(fstream);
		
//
//		run2.main(args); print("finish");
//		run.main(args); print("finish");

		 
		 
		 
		 
			Image1.main(args); print("finish"); 
			Image2.main(args); print("finish");
			Image3.main(args); print("finish");
			Image4.main(args); print("finish");
			Image5.main(args); print("finish");
		
			run3.out.close();
	}
	
	
	
	
	private static void print(String string) {
		System.out.println(string);
	}




}
