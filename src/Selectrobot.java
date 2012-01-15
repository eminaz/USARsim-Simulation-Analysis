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

public class Selectrobot {
	public static ArrayList<Robotevent> robotevents=new ArrayList<Robotevent>();
	public static String initialtime="";
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
			    
			    int [][] victimPosition=victimPosition1;
//            int //
//            [][] victimPosition={{1356,2573},{1825,1931},{1115,1441},{1110,2143},{1277,1917},
//                                 {1272,2109},{1522,2176},{1249,2168},{1489,2491},{1556,2498},
//                                 {1247,2538},{1246,2532},{1500,2764},{1384,1686},{1383,1564},
//                                 {1362,1360},{1426,1223},{1175,1195},{1263,866}, {977,1047},
//                                 {979,1145}, {1000,1482},{1005,1559},{1068,1699},{949,1876},
//                                 {1000,2296},{966,2485}, {1801,1117},{1822,2030},{1814,2194},
//                                 {1761,2471},{1860,2474},{1896,2227},{1773,1085}};
//                                 //
//		int [][] victimPosition={{1356,2573},{1825,1931}};
//         int [][] victimPosition={{3000,1900},{3500,1564},{3049,1264},{1049,1964},{2049,1964},{3200,1964},
//            		{3049,1964},{4065,1093},{3102,1247},{3752,1241},{3294,1083},{2767,1150},{2622,1554},
//            		{3056,1530}};

       //     [][] victimPosition={{4065,1093},{3102,1247},{3752,1241},{1773,1085},{3049,1964}};
		//int [][] victimPosition={{3000,1900},{3500,1564}};
            
            final double PI=3.1415926536;
            int VICTIMNO=34;
            final int ROBOTNO=24;
       //int VICTIMNO=5;
            bot[] botarr=new bot[ROBOTNO+1];
            for(int b=1;b<=ROBOTNO;b++){
            	botarr[b]=new bot();
            	botarr[b].no=b;
            	botarr[b].eventlist=new ArrayList<botevent>();          	
            }
            
           
       

       //final int ROBOTNO=2;
       int [][] met= new int[VICTIMNO+1][ROBOTNO+1];
       String[][] robotSee=new String[VICTIMNO+1][ROBOTNO+1];    //each victim-robot has a status of string
                                                   //timepoint0+Y/N+timepoint1+Y/N+...
       
       long [][] robotTimePoint= new long [VICTIMNO+1][ROBOTNO+1];
       boolean [][] robotCanSee= new boolean [VICTIMNO+1][ROBOTNO+1];
       boolean [] victimMarked=new boolean [VICTIMNO+1];
       long [] markedTime=new long [VICTIMNO+1];
       long teleOpNo,teleOpTime;
       int [] maxSee=new int [VICTIMNO+1];
       //int [][] victimeMultiSee=new int [VICTIMNO+1][ROBOTNO+1];
       int deletV=0;
       
       for(int i=1;i<=VICTIMNO;i++){
               victimMarked[i]=false;
               maxSee[i]=0;
       }
       
	   String logName="/Users/hal/Desktop/Mark/usar_20110516_043835_user.log";
	   //String logName="c:\\Users\\Zheng\\Documents\\ULab\\Miss&period\\data\\seperate\\S101\\usar_20090914_035836_user.log"; //to be processed file
       //String logName="c:\\miss\\ac01.log";

	   FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/selectrobot.txt");
	    BufferedWriter sr = new BufferedWriter(fstream);
	    sr.write(logName);
	    sr.newLine(); sr.newLine();	 

	   
       System.out.println(logName);
       
       FileReader prereader = new FileReader(logName);
       Scanner prein = new Scanner(prereader);
       //String midLog=logName+"_Af.log";
       //PrintWriter preout = new PrintWriter(midLog);
       int subjectNo=0;    //default is 0, but need to be related to the log file name
       int victimNo=1;
       boolean cover=false;

             //System.out.println("If you use args, args[0] is filename, args[1] is victim number. victim map should be victim_?.ppm at c:\\temp\\miss\\");
//             System.out.println("[victim#,robot#]\tmaxRobotSaw\ttimes_i/o\tmissed\t@\tperiod\tin&out_time");
//             for (int vic=0;vic<=VICTIMNO;vic++){ 
//                  for (int rob=0;rob<=ROBOTNO;rob++){
//                      met[vic][rob]=0;
//                      robotCanSee[vic][rob]=false;
//                  }
//              }
       String tempbotNo="";
       String begin="";
       String end="";
       String tempbotNo3="";
       String begin3="";
       String end3="";
          while (prein.hasNextLine())
          {
              String line = prein.nextLine();
              int kscan=line.indexOf("SCAN Bot");
              int kimage=line.indexOf("IMAGE Bot");
              int selectRobot=line.indexOf("SELECTROBOT");
              int teleop1=line.indexOf(": TELEOP");
              int teleop2=line.indexOf("TELEOP B");
              int victMark=line.indexOf("VictMark");
              int dataUpdate=line.indexOf("DATAUPDATE");
              int dataDelete=line.indexOf("DATADELETE");
              int hasTrue=line.indexOf("true");
              int hasFalse=line.indexOf("false");
              int camera=line.indexOf("Camera");
              
             
//             if(selectRobot!=-1) {
//                     String ts=line.substring(29, line.length());
//                     teleOpNo=Integer.parseInt(ts);
//                     ts=line.substring(0,13);
//                     teleOpTime=Long.parseLong(ts);
//                     System.out.println("teleOpTime="+teleOpTime);
//                     System.out.println(line);
//                 }
             if(victMark!=-1 && dataUpdate==-1 && dataDelete==-1){
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
                   int robotNo=Integer.parseInt(botNo);
                   
                   String timePoint=samp.get(0);
                   if(initialtime==""){
                  	 initialtime=timePoint;
                   }
                   String timePoint2 = convertime(timePoint);
                   String x=samp.get(5);
                   String y=samp.get(6);
                   String theta=samp.get(7);
                   print(samp+"");
                   sr.write(timePoint+"  "+timePoint2+"      victMark    "+botNo+"  "+x+" "+y+" "+theta);
                   sr.newLine();
                   
             }

             if(selectRobot!=-1) {//there is the key word
                     //preout.println(line);

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
  //                   System.out.println(samp);
                   
                     String botNo=samp.get(2);
                     botNo=botNo.substring(3);
                     int robotNo=Integer.parseInt(botNo);
                     
                     String timePoint=samp.get(0);
                     if(initialtime==""){
                    	 initialtime=timePoint;
                     }
                     String timePoint2 = convertime(timePoint);
                     sr.write(timePoint+"  "+timePoint2+"      Select       "+botNo+"  ");
                     sr.newLine();
 //                    Robotevent2 re=new Robotevent2(timePoint,"SelectRobot",botNo);
 //                    robotevents.add(re);

             }

//             if(teleop1!=-1) {
//            	 double xmp,ymp,thetamp;
//                 char stemp;
//                 int pmp=0;
//                 String smp="";
//                 ArrayList<String> samp = new ArrayList<String> ();
//                 
//                 while(pmp<line.length()){
//                    stemp=line.charAt(pmp);
//                    if((stemp!=' ')&&(stemp!=',')&&(stemp!='[')&&(stemp!=']')){
//                        smp+=stemp;
//
//                    }//if
//                    else{
//                        if(smp!=""){
//                            samp.add(smp);
//                            smp="";
//                        }//if
//                    }//else
//                    pmp++;
//                      }//while
//                 samp.add(smp);
// //                  System.out.println(samp);
//               
//                 String botNo=samp.get(4);
//                 botNo=botNo.substring(3);
//                 int robotNo=Integer.parseInt(botNo);
//                 
//                 String timePoint=samp.get(0);
//                 if(initialtime==""){
//                	 initialtime=timePoint;
//                 }
////                 timePoint=convertime(timePoint);
//                 if(!tempbotNo3.equals(botNo)){
////                   Robotevent re=new Robotevent(begin,end, "teleoperation",botNo);
////                   robotevents.add(re);
//                	 if((begin3!="")&&(end3!="")){
//                  	 String begin2 = convertime(begin3);
//                  	 String end2 = convertime(end3);
//    //                   sr.write("tel status    "+botNo+"  "+begin2+"~"+end2);
//   //                    sr.newLine();
//                  	 }
//                   tempbotNo3=botNo;
//                   begin3=timePoint;
//                   end3=timePoint;                   }
//                   else{
//                  	 end3=timePoint;
//                   }
//
//         }
             
             if(teleop2!=-1 && camera==-1) {

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
  //                 System.out.println(samp);
               
                 String botNo=samp.get(2);
                 botNo=botNo.substring(3);
                 int robotNo=Integer.parseInt(botNo);
                 
                 String timePoint=samp.get(0);
                 if(initialtime==""){
                	 initialtime=timePoint;
                 }
 //                System.out.println(tempbotNo+" "+botNo+" "+begin);
                 if(!tempbotNo.equals(botNo)){
//                 Robotevent re=new Robotevent(begin,end, "teleoperation",botNo);
//                 robotevents.add(re);
                	 if((begin!="")&&(end!="")){
                	 String begin2 = convertime(begin);
                	 String end2 = convertime(end);
                     sr.write(end+" "+end2+"   teleop ends      "+tempbotNo+"  ");
                     sr.newLine();
                	 }
                 sr.write(timePoint+" "+convertime(timePoint)+"  teleop begins   "+botNo);
                 sr.newLine();
                 tempbotNo=botNo;
                 begin=timePoint;
                 end=timePoint;
                 }
                 else{
                	 end=timePoint;
                 }
//
         }
             
             
             
             
             
             
             
          }
      	for(int i=0;i<robotevents.size();i++){
      		Robotevent re=robotevents.get(i);
   //   		System.out.print(re.time+" "+re.type+" "+re.botno+"\n");
      	}
	    sr.close();
 }
	
	public static String convertime(String timePoint){
		String str="";
//		System.out.println(timePoint);
		int time=Integer.valueOf(timePoint.substring(5));
		int initial=Integer.valueOf(initialtime.substring(5));
		int time2=(time-initial)/1000;
//		print(time+" "+initial+" "+time2);
		str=time2/60+":"+time2%60;
		return str;
	}
	 private static void print(String string) {
			System.out.println(string);
		}

	 
}