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

public class Selectrobot2 {
	public static ArrayList<Robotevent2> robotevents2=new ArrayList<Robotevent2>();
	public static String initialtime="1305578315466";
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
            
            final double PI=3.1415926536;
            int VICTIMNO=34;
            final int ROBOTNO=24;
       //int VICTIMNO=5;
            String[] selectarr=new String[ROBOTNO+1];
            for(int b=1;b<=ROBOTNO;b++){
            	selectarr[b]="";  	
            }
            
            int totalteleop=0;
            int totalteleoptime=0;
            
            
           
       
	   String logName="/Users/hal/Desktop/Mark/usar_20110516_043821_user.log";
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
       int time=0;
       int lastbot = 0;
       String lastselect = null;
       
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
              int auto=line.indexOf("AUTO");
              
             
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
                   print(samp+"");
                   String sNo=samp.get(4);
                   int someNo=Integer.parseInt(sNo);
                   
                   String timePoint=samp.get(0);
                   if(initialtime==""){
                  	 initialtime=timePoint;
                   }
                   String bool=samp.get(9);
                  
                   String timePoint2 = convertime(timePoint);
                   String x=samp.get(5).substring(2);
                   String y=samp.get(6).substring(2);
                   String theta=samp.get(7).substring(6);
                   int deltaTime;
                   if(lastselect!=null){
                	   print(someNo+"");
                   deltaTime=Integer.valueOf(timePoint.substring(4))-Integer.valueOf(lastselect.substring(4));
                   sr.write(timePoint+" "+timePoint2+" victMark "+someNo+"  "+x+" "+y+" "+theta +" "+ convertbool(bool)
                   +" "+lastbot);
                		   //+ " timeSinceSelectByRobot "+lastbot+" "+deltaTime+" "+(double)((double)deltaTime/1000.00)+" seconds");
                   sr.newLine();
                   }
                   else{               
                       sr.write(timePoint+" "+timePoint2+" victMark "+someNo+" "+x+" "+y+" "+theta+" "+ convertbool(bool));
                       sr.newLine();
                   }

                   
             }

             if(selectRobot!=-1) {//there is the key word
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
                     sr.write(timePoint+" "+timePoint2+" Select "+botNo);
                     sr.newLine();
//                     Robotevent2 re=new Robotevent2(timePoint,"SelectRobot",botNo);
//                    robotevents2.add(re);
                     selectarr[robotNo]=timePoint;
                     lastselect=timePoint;
                     lastbot=robotNo;
                     
                     if((begin!="")&&(end!="")){
                	 String begin2 = convertime(begin);
                	 String end2 = convertime(end);
                	 time=Integer.valueOf(end.substring(4))-Integer.valueOf(begin.substring(4));
                     sr.write(end+" "+end2+" teleopEnds "+tempbotNo);
                    		 //+" totalTime "+ time +" "+(double)((double)time/1000.00)+" seconds");
                     sr.newLine();
                     
                     totalteleoptime=totalteleoptime+time;
                     begin="";
                     end="";
                     tempbotNo="";
                     }
             }
             
             if(teleop2!=-1 && camera==-1   && auto==-1) {

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
 //                if(!tempbotNo.equals(botNo) && end!=begin  && auto==-1){
                 if(!tempbotNo.equals(botNo)){
//                 Robotevent re=new Robotevent(begin,end, "teleoperation",botNo);
//                 robotevents.add(re);
                	 if((begin!="")&&(end!="")){
                	 String begin2 = convertime(begin);
                	 String end2 = convertime(end);
                	 time=Integer.valueOf(end.substring(4))-Integer.valueOf(begin.substring(4));
                     sr.write(end+" "+end2+" teleopEnds "+tempbotNo);
                    		 //+" totalTime "+ time +" "+(double)((double)time/1000.00)+" seconds");
                     sr.newLine();
                     
                     totalteleoptime=totalteleoptime+time;
                     
                	 }
                 sr.write(timePoint+" "+convertime(timePoint)+" teleopBegins "+botNo);
                 sr.newLine();
                 tempbotNo=botNo;
                 begin=timePoint;
                 end=timePoint;
                 totalteleop++;
//                 print(begin);
                 }
//                 else if(samp.get(3).equals("AUTO")){
//                	 String end2 = convertime(end);
//                     sr.write(end+" "+end2+"   teleop ends      "+tempbotNo+"  ");
//                     sr.newLine();	 
//                 }
                 
                 else{
                	 if(begin==""){
                         sr.write(timePoint+" "+convertime(timePoint)+" teleopBegins "+botNo);
                         sr.newLine();
                         tempbotNo=botNo;
                         begin=timePoint;
                         end=timePoint;
                         totalteleop++;
                	 }
                	 
                	 
                	 
                	 end=timePoint;
                 }
//
         }
             
             
             
          }
          //to account for the last piece of time
          print(end+" "+begin);
   
          time=Integer.valueOf(end.substring(4))-Integer.valueOf(begin.substring(4));
          totalteleoptime=totalteleoptime+time;
          String end2 = convertime(end);
          sr.write(end+" "+end2+"  teleopEnds "+tempbotNo);
          sr.newLine();
          sr.newLine();
          sr.write("totalTeleoperation: "+totalteleop);
          sr.newLine();
          sr.write("totalTeleoperationTime: "+totalteleoptime+" ("+(double)((double)totalteleoptime/1000.00)+" seconds)");
          
          
//      	for(int i=0;i<robotevents2.size();i++){
//      		Robotevent2 re=robotevents2.get(i);
//   //   		System.out.print(re.time+" "+re.type+" "+re.botno+"\n");
//      	}
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
	 
	 public static int convertbool(String str){
		 if(str.equals("true")){
			 return 1;
		 }
		 return 0;
	 }
}