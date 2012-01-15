import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class run2 {
	

	
	
	
	public static void main(String[] args) throws IOException{
//		Image6.main(args); print("finish"); run3.out.close();
//		Image7.main(args); print("finish");
//		Image8.main(args); print("finish");
//		Image9.main(args); print("finish");
//		Image10.main(args); print("finish");
		
		Teleop.main(args);
		DisplayToMarked.main(args);
		Marked.main(args);
		Pitch.main(args);
	}
	
	
	
	
	private static void print(String string) {
		System.out.println(string);
	}
}
