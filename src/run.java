import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.lang.Integer;

public class run {

	public static int num;
	public static int from;
	public static int to;
	public static int botnum;
	
	public static String dir="C:/Documents and Settings/Fei/Desktop/Mark/";
	
	public static void main(String[] args) throws Exception{
		
		FileReader prereader = new FileReader(dir+"namelist.txt");
		Scanner prein = new Scanner(prereader);
		while (prein.hasNextLine())
		{
			String line = prein.nextLine();
			print(line);
			Image1.logName=dir+line.split(" ")[2].trim()+".log";
			Image1.PSName=dir+line.split(" ")[1].trim()+".txt";
//			print(Image1.PSName);
			Image1.name=line.split(" ")[0].trim()+" ";
			
			
//			print(Image1.name);
//		Image1.logName="/Users/hal/Desktop/Mark/"+ "usar_20110331_060448_user.log";
//		Image1.PSName="/Users/hal/Desktop/Mark/"+ "MRCSDataLog_20110331_060448.txt";
//		Image1.name="033117_P_Op1_S ";
		
		TestVictimLocation.main(args);
		Image1.map="map"+TestVictimLocation.map;
		if(TestVictimLocation.map==1){
			Image1.victimPosition=Image1.victimPosition1;
		}
		else if(TestVictimLocation.map==2){
			Image1.victimPosition=Image1.victimPosition2;
		}
		else if(TestVictimLocation.map==3){
			Image1.victimPosition=Image1.victimPosition3;
		}
		
		
		Image1.main(args); //System.gc();

		
		if(Image1.name.substring(13, 14).equals("E")){
			Suggest.main(args);
		}
		else if(Image1.name.substring(13, 14).equals("S")){
			Passive.main(args);
		}
		RobotSelect.main(args);
		Merge.main(args);
		sort2.main(args);
		
		Teleop.main(args);
		DisplayToMarked.main(args);
		Marked.main(args);
		Pitch.main(args);
		RemoveLine.main(args);
		Delete.main(args);
		Image1.initialtime="";
		}
	}
//	
//	public static void main2(String[] args) throws IOException{
//		Image6.main(args); print("finish");
//		Image7.main(args); print("finish");
//		Image8.main(args); print("finish");
//		Image9.main(args); print("finish");
//		Image10.main(args); print("finish");
	
//	}
//	
	
//	public void combine() throws IOException{
//		
//		
//		FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/combine.txt");
//		BufferedWriter out = new BufferedWriter(fstream);
//		
//		FileReader rstream=new FileReader("/Users/hal/Desktop/Mark/image1.txt");
//		
//		
//		
//		
//		
//		
//	}
	
	
	

	
	private static void print(String string) {
		System.out.println(string);
	}
	
}
