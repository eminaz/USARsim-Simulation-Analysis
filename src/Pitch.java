import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import java.lang.Integer;
/*
        @Zheng Ma
*/

public class Pitch {
	public static ArrayList<botevent> botevents=new ArrayList<botevent>();
//	public static String initialtime=Image1.initialtime;
	public static int k=0;

    final static double PI=3.1415926536;
    static int VICTIMNO=34;
    final static int ROBOTNO=24;
    public static bot[] botarr=new bot[ROBOTNO+1];

	
	
	public static void main(String[] args)
    throws IOException
 {
		 int[][] victimPosition1={{270,119},{358,118},{721,128},{786,159},{898,202},{1102,72},{1371,129},{1869,106},{1966,138},{896,258},{1558,241},
			    {265,331},{109,635},{264,700},{628,536},{701,574},{782,515},{931,592},{1109,424},{1395,377},{1465,425},{1619,451},
			    {1991,492},{1256,659},{1706,623},{1884,661},{348,941},{1163,966},{1256,964},{1412,988},{1430,1032},{1698,1009},{1509,703},
			    {1641,389}};
			    
			    int[][] victimPosition2={{270,119},{358,118},{721,128},{551,162},{878,    198},{1102,    72},{1371,    129},{1869,    106},{1966,    138},{896,    258},
			    {1558,    241},{262,    465},{109,    635},{869,    535},{628,    536},{701,    576},{782,    515},{1109,    424},{1282,    382},{1378,    429},{1504,    459},{1511,    396},{1991,    492},
			    {1832,    600},{1756,    668},{1350,    668},{348,    941},{1909,    716},{1256,    964},{1412,    988},{1430,    1032},{1698,    1009},{421,    562},{849,    965}};
			    
			    int[][] victimPosition3={    {270,    119},{358,    118},{721,    128},{786,    159},{898,    202},{1102,    72},{1513,    126},{1712,    106},
			    {1966,    138},{655,    254},{1366,    241},{417,    327},{104,    395},{423,    566},{580,    498},{701,    574},{782,    515},{1109,    424},{921,    519},{1395,    377},
			    {1762,    385},{1779,    460},{1805,    494},{1388,    662},{1705,    623}, {1334,    429},{348,    941},{1163,    966},{1256,    964},{1412,    988},{1430,    1032},{1698,    1009},
			    {1998,    649},{1722,    698}};
			    
			   int [][] victimPosition=Image1.victimPosition;


			    for(int b=1;b<=ROBOTNO;b++){
			    	botarr[b]=new bot();
			    	botarr[b].no=b;
			    	botarr[b].eventlist=new ArrayList<botevent>();    
			    	botarr[b].standing=1;
			    }
            
            int bn=0;
            int en=0;


       
	   String logName=Image1.logName;

	    FileWriter fstream = new FileWriter(run.dir+Image1.name+"Pitch.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    
	    String initialtime=Image1.initialtime;
	    
	   
	   

       System.out.println(logName);
       
       FileReader prereader = new FileReader(logName);
       
       //skip to the other half of the log file
       prereader.skip(0);
       
       Scanner prein = new Scanner(prereader);

             
             String tempbotNo="";
             String begin="";
             String end="";
             String tempbotNo3="";
             String begin3="";
             String end3="";
             
             botevent e = new botevent();
             
             
          while (prein.hasNextLine())
          {
              String line = prein.nextLine();
              int kscan=line.indexOf("SCAN Bot");
              int kimage=line.indexOf("IMAGE Bot");
              int selectRobot=line.indexOf("SELECTROBOT");
              int victMark=line.indexOf("VictMark");
              int dataUpdate=line.indexOf("DATAUPDATE");
              int dataDelete=line.indexOf("DATADELETE");
              int hasTrue=line.indexOf("true");
              int hasFalse=line.indexOf("false");
              int pitch=line.indexOf("Pitch");
              int teleop1=line.indexOf(": TELEOP");
              int teleop2=line.indexOf("TELEOP B");
              int camera=line.indexOf("Camera");

  
              if(pitch!=-1){
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
                  String botNo=samp.get(4);
                  botNo=botNo.substring(3);
                  int robotNo=Integer.parseInt(botNo);
                  String pitchval=samp.get(6);
                  pitchval=pitchval.substring(6,pitchval.length());
                  
                  bot rob=botarr[robotNo];
                  if(Math.abs(pitch)!=0){
                	  if(rob.standing!=0){
                		  out.write(samp.get(0)+" "+convertime(samp.get(0))+" "+pitch+" "+robotNo);
                		  out.newLine();
                	  }
                	  rob.standing=0;   

                  }
              }
              
              
          }
          out.close();

 }
 
 
	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(4));
		int initial=Integer.valueOf(Image1.initialtime.substring(4));
		int time2=(time-initial)/1000;
		str=time2/60+":"+time2%60;
		return str;
	}
	
	
          public static int containvic(ArrayList<botevent> el, int vic) {
        	  for(int m=0;m<el.size();m++){
        		  botevent be=el.get(m);
        		  
        		  if(be.vicno==vic){
        				  return m;
        		  }
        	  }
        	  return -1;
          }

          
 private static void print(String string) {
		System.out.println(string);
	}


public  static void processAPoint(int subjectNo,int robotNo,String timePoint,double xmp,double ymp,double thetamp) {
}







public static void saveArray(String filename, bot[] botarr2) {

	try {

	FileOutputStream fos = new FileOutputStream(filename);

	GZIPOutputStream gzos = new GZIPOutputStream(fos);

	ObjectOutputStream out = new ObjectOutputStream(gzos);

	out.writeObject(botarr2);

	out.flush();

	out.close();

	}

	catch (IOException e) {

	System.out.println(e);

	}

	}








 
}
