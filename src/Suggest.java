import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.lang.Integer;
/*
        @Zheng Ma
*/

public class Suggest {
	public static ArrayList<Robotevent2> robotevents2=new ArrayList<Robotevent2>();
	public static String initialtime=Image1.initialtime;
	public static void main(String[] args)
    throws IOException
 {
	   String logName=Image1.PSName;

	   FileWriter fstream = new FileWriter(run.dir+"/new/suggest.txt");
	    BufferedWriter su = new BufferedWriter(fstream);
//	    su.write(logName);
	    su.newLine(); su.newLine();	 
	   
       System.out.println(logName);
       
       FileReader prereader = new FileReader(logName);
       Scanner prein = new Scanner(prereader);


          while (prein.hasNextLine())
          {
              String line = prein.nextLine();
              
              int suggest=line.indexOf("Suggestion");
              if(suggest!=-1){
            	  String time=line.substring(0,13);
            	  su.write(time+" "+convertime(time)+"  "+"Active-Suggestion-Made");
            	  su.newLine();
              }
              

          }
	    su.close();
 }
	
	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(5));
		int initial=Integer.valueOf(Image1.initialtime.substring(5));
		int time2=(time-initial)/1000;
		str=time2/60+":"+time2%60;
		return str;
	}
	
	 private static void print(String string) {
			System.out.println(string);
		}

}
