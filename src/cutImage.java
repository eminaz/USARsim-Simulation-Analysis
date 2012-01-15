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

public class cutImage {
	public static ArrayList<Robotevent2> robotevents2=new ArrayList<Robotevent2>();
	public static String initialtime=Image1.initialtime;
	public static void main(String[] args)
    throws IOException
 {

	   String logName="/Users/hal/Desktop/Mark/051616_P_Op2 Image Real.txt";


	   FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/s.txt");
	    BufferedWriter sr = new BufferedWriter(fstream);
	    sr.write(logName);
	    sr.newLine(); sr.newLine();	 

	   
       System.out.println(logName);
       
       FileReader prereader = new FileReader(logName);
       Scanner prein = new Scanner(prereader);
       
       String[][] store=new String[25][35];
       for(int i=1;i<25;i++){
    	   for(int j=1;j<35;j++){
    		   store[i][j]="";
    	   }	   
       }


       String tempbotNo="";
       String begin="";
       String end="";
       String tempbotNo3="";
       String begin3="";
       String end3="";
       int time=0;
       int lastbot = 0;
       String lastselect = null;
       
          while (prein.hasNextLine())
          {
              String line = prein.nextLine();
              int imagebegin=line.indexOf("ImageBegin");
              int imageend=line.indexOf("ImageEnd");

             if(imagebegin!=-1) {

                     double xmp,ymp,thetamp;
                     char stemp;
                     int pmp=0;
                     String smp="";
                     ArrayList<String> samp = new ArrayList<String> ();
                     
                     while(pmp<line.length()){
                        stemp=line.charAt(pmp);
                        if((stemp!=' ')&&(stemp!=',')&&(stemp!='[')&&(stemp!=']')){
                            smp+=stemp;

                        }//if
                        else{
                            if(smp!=""){
                                samp.add(smp);
                                smp="";
                            }//if
                        }//else
                        pmp++;
                          }//while
                     samp.add(smp);                   
                     String botNo=samp.get(3);
                     int robotNo=Integer.parseInt(botNo);
                     String vicNo=samp.get(8);
                     int vic=Integer.parseInt(vicNo);
                     store[robotNo][vic]=line;
             }
                     
             
          if(imageend!=-1) {
              double xmp,ymp,thetamp;
              char stemp;
              int pmp=0;
              String smp="";
              ArrayList<String> samp = new ArrayList<String> ();
              
              while(pmp<line.length()){
                 stemp=line.charAt(pmp);
                 if((stemp!=' ')&&(stemp!=',')&&(stemp!='[')&&(stemp!=']')){
                     smp+=stemp;

                 }//if
                 else{
                     if(smp!=""){
                         samp.add(smp);
                         smp="";
                     }//if
                 }//else
                 pmp++;
                   }//while
              samp.add(smp);
            
              String timePoint=samp.get(0);
              String botNo=samp.get(3);
              int robotNo=Integer.parseInt(botNo);
              String vicNo=samp.get(8);
              int vic=Integer.parseInt(vicNo);
              String beginline=store[robotNo][vic];
              if(beginline!=""){    
            	  pmp=0;smp="";samp = new ArrayList<String> ();
                  while(pmp<beginline.length()){
                     stemp=beginline.charAt(pmp);
                     if((stemp!=' ')&&(stemp!=',')&&(stemp!='[')&&(stemp!=']')){
                         smp+=stemp;

                     }//if
                     else{
                         if(smp!=""){
                             samp.add(smp);
                             smp="";
                         }//if
                     }//else
                     pmp++;
                       }//while
                  samp.add(smp);
                  String timePoint2=samp.get(0);
                  String botNo2=samp.get(3);
                  int robotNo2=Integer.parseInt(botNo2);
                  String vicNo2=samp.get(8);
                  int vic2=Integer.parseInt(vicNo2);
            	  if(robotNo==robotNo2 && vic2==vic){ 
            		  if(Integer.parseInt(timePoint.substring(4))-Integer.parseInt(timePoint2.substring(4))>2000){

            			  
            			  print(beginline);
            			  print(line);
            			  store[robotNo][vic]="";
            		  }
            		  
            	  }
            	 
            	  
              }
              
              }
      }

             
 }
	
	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(5));
		int initial=Integer.valueOf(initialtime.substring(5));
		int time2=(time-initial)/1000;
		str=time2/60+":"+time2%60;
		return str;
	}
	 private static void print(String string) {
			System.out.println(string);
		}
	 
	 public static int convertbool(String str){
		 if(str.equals("true")){
			 return 1;
		 }
		 return 0;
	 }
}