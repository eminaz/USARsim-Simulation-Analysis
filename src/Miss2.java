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

public class Miss2 {
	public static ArrayList<botevent> botevents=new ArrayList<botevent>();
	public static String initialtime="";
	public static int k=0;
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
//            vict[] victarr=new vict[VICTIMNO+1];
//            for(int a=1;a<=VICTIMNO;a++){
//            victarr[a]=new vict();
//            victarr[a].no=a;
//            victarr[a].x=victimPosition[a-1][0];
//            victarr[a].y=victimPosition[a-1][1];
//            victarr[a].eventlist=new ArrayList<event>();
//       }
            bot[] botarr=new bot[ROBOTNO+1];
            for(int b=1;b<=ROBOTNO;b++){
            	botarr[b]=new bot();
            	botarr[b].no=b;
            	botarr[b].eventlist=new ArrayList<botevent>();    
            	botarr[b].standing=1;
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
	    FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/out.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write(logName);
	    out.newLine(); out.newLine();	    
	    out.write("vicNo (x,y)    robotNo   begin      end             Robot(xpm,ypm)   (xppm,yppm)              viewdegree");    
	    out.newLine();
	    
	   
	   
	   
//       if (args.length>0) {
//               logName=args[0];
//               if (args.length>1) VICTIMNO=Integer.parseInt(args[1]);               
//           }
       System.out.println(logName);
       
       FileReader prereader = new FileReader(logName);
       
       //skip to the other half of the log file
//       prereader.skip(24999999);
       
       Scanner prein = new Scanner(prereader);
       
       //String midLog=logName+"_Af.log";
       //PrintWriter preout = new PrintWriter(midLog);
       int subjectNo=0;    //default is 0, but need to be related to the log file name
       int victimNo=1;
       boolean cover=false;

             //System.out.println("If you use args, args[0] is filename, args[1] is victim number. victim map should be victim_?.ppm at c:\\temp\\miss\\");
//             System.out.println("[victim#,robot#]\tmaxRobotSaw\ttimes_i/o\tmissed\t@\tperiod\tin&out_time");
             for (int vic=0;vic<=VICTIMNO;vic++){ 
                  for (int rob=0;rob<=ROBOTNO;rob++){
                      met[vic][rob]=0;
                      robotCanSee[vic][rob]=false;
                  }
              }
             
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
             /* 
             if(selectRobot!=-1) {
                     String ts=line.substring(29, line.length());
                     teleOpNo=Integer.parseInt(ts);
                     ts=line.substring(0,13);
                     teleOpTime=Long.parseLong(ts);
                     //System.out.println("teleOpTime="+teleOpTime);
                 }
             */
              
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
//                  print(""+samp);
                  String botNo=samp.get(4);
                  botNo=botNo.substring(3);
                  int robotNo=Integer.parseInt(botNo);
                  String pitchval=samp.get(6);
                  pitchval=pitchval.substring(6,pitchval.length());
 //                 print(robotNo+" "+pitchval);
                  
                  bot rob=botarr[robotNo];
                  if(Math.abs(pitch)>1){
                	  rob.standing=0;   
//                	  print(""+robotNo);
                  }
              }
              
              

              //search the lines that have "IMAGE BOT"
//              if(false){
             if(kimage!=-1) {//there is the key word
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
                    // System.out.println(samp);
                     
                     //print [... IMAGE ...]
                     xmp=Double.parseDouble(samp.get(6));
                     ymp=Double.parseDouble(samp.get(7));
                     thetamp=Double.parseDouble(samp.get(8));
                     //System.out.println(samp);
                    // System.out.println("xmp="+xmp+" ymp="+ymp+" angle"+thetamp);
                     
                     String botNo=samp.get(2);
                     botNo=botNo.substring(3);
                     int robotNo=Integer.parseInt(botNo);
                     
                     String timePoint=samp.get(0);
                     if(initialtime==""){
                    	 initialtime=timePoint;
                    	 print(timePoint);
                     }
                     int r = 0,g = 0,b = 0,alpha,rgb;
//                     print("timePoint "+timePoint);

                     bot rob=botarr[robotNo];
                     if(rob.standing==1){       
//                     for (int vic=1;vic<=VICTIMNO;vic++)
                     for (int vic=1;vic<=VICTIMNO;vic++){ //VICTIMNO
                      try {
 //                                   String stringFile="/Users/hal/Desktop/Mark/Output3_7.ppm";
                                   String stringFile="/Users/hal/Desktop/Mark/map1/Output"+vic+".ppm";
//                                    File f = new File(stringFile);
//                                    BufferedImage image = ImageIO.read(f);
                                    //RandomAccessFile raf = new RandomAccessFile(f, "r");
                                //    int r,g,b,alpha,rgb;
                                    //for(int i=1;i<100;i++){
                                        int yppm=(int)((ymp-(-15.625))*40-1000);//y position in ppm file
                                        int xppm=(int)((xmp-(-125))*40-2200);    //x position
                                        Main.colorPPM(stringFile, (int)xppm, (int)yppm);
                                        byte[] color2=Main.color2;
//                                        System.out.println(color2[0]+" "+color2[1]+" "+color2[2]);
//                                        rgb=image.getRGB(xppm, yppm);
//                                        alpha = ((rgb >> 24) & 0xff);
//                                        r = ((rgb >> 16) & 0xff);
//                                        g = ((rgb >> 8) & 0xff);
//                                        b = ((rgb ) & 0xff);
                                        r=color2[0];g=color2[1];b=color2[2];
                                        Main.color2=new byte[3];
                            
 //                           String s=robotSee[vic][robotNo];
                            
//                            if (s!=null)
//                            {
//                                //System.out.println("s length is"+s.length());
//                                stemp=s.charAt(s.length()-2); //last char should be a "\t"
//                            }
//                            else stemp=' ';
                            
                            double degreeRtoV=0;
                            double myTheta=0;    //the degree between victim and robot
                            double deltaX=victimPosition[vic-1][0]-xppm;
                            double deltaY=victimPosition[vic-1][1]-yppm;
                           // System.out.print("X deltaX "+xppm+" "+deltaX);
                            if (deltaY!=0){
//angle1                           	
//                                double abdeg=Math.atan((Math.abs(deltaX))/(Math.abs(deltaY)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=270.0+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=90.0-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=90.0+180.0/PI*abdeg;
//                                else degreeRtoV=270-180.0/PI*abdeg;
//angle2                            	
//                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=180.0+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=180.0-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180.0/PI*abdeg;
//                                else degreeRtoV=360-180.0/PI*abdeg;
//angle3                            	
//                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=270.0+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=270.0-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=90+180.0/PI*abdeg;
//                                else degreeRtoV=90-180.0/PI*abdeg;
//angle4                            	
                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=180.0/PI*abdeg;
                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=360-180.0/PI*abdeg;
                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180+180.0/PI*abdeg;
                                else degreeRtoV=180-180.0/PI*abdeg;
//angle5
//                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=90+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=90-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=270+180.0/PI*abdeg;
//                                else degreeRtoV=270-180.0/PI*abdeg;
//angle6                            	
//                                double abdeg=Math.atan((Math.abs(deltaX))/(Math.abs(deltaY)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=180+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=360.0-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180.0/PI*abdeg;
//                                else degreeRtoV=180-180.0/PI*abdeg;
//angle7
//                              double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                              if((deltaX>0)&&(deltaY>0)) degreeRtoV=90+180.0/PI*abdeg;
//                              else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=90-180.0/PI*abdeg;
//                              else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=270+180.0/PI*abdeg;
//                              else degreeRtoV=270-180.0/PI*abdeg;
//angle8
//                              double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                              if((deltaX>0)&&(deltaY>0)) degreeRtoV=0+180.0/PI*abdeg;
//                              else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=360-180.0/PI*abdeg;
//                              else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180+180.0/PI*abdeg;
//                              else degreeRtoV=270-180.0/PI*abdeg;
//angle9
//                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=0+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=360-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180+180.0/PI*abdeg;
//                                else degreeRtoV=200-180.0/PI*abdeg;   
//angle10
//                              double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                              if((deltaX>0)&&(deltaY>0)) degreeRtoV=360-180.0/PI*abdeg;
//                              else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=0+180.0/PI*abdeg;
//                              else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180-180.0/PI*abdeg;
//                              else degreeRtoV=180+180.0/PI*abdeg;    
//angle11
//                              double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                              if((deltaX>0)&&(deltaY>0)) degreeRtoV=270-180.0/PI*abdeg;
//                              else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=270+180.0/PI*abdeg;
//                              else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=90-180.0/PI*abdeg;
//                              else degreeRtoV=90+180.0/PI*abdeg; 
//angle12(only robot10)
//                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=270+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=270-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=90+180.0/PI*abdeg;
//                                else degreeRtoV=90-180.0/PI*abdeg;
//angle13 (only robot10)
//                                double abdeg=Math.atan((Math.abs(deltaX))/(Math.abs(deltaY)));
//                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=0+180.0/PI*abdeg;
//                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=180.0-180.0/PI*abdeg;
//                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180+180.0/PI*abdeg;
//                                else degreeRtoV=360-180.0/PI*abdeg;

                            	
                            }
                                                        
                            double degreeRnose=180.0*thetamp/PI;
                                        if(degreeRnose<0){
                                            degreeRnose=360+degreeRnose;
                                        }
                            double viewDegree=Math.abs(degreeRnose-degreeRtoV);
                            int maxSeeTemp=0;
                            
                            if (viewDegree>180) viewDegree=360-viewDegree;
                            /*if(robotNo==24)
                                        {
                                            System.out.println("degreeRnose=" + degreeRnose);
                                            System.out.println("deltaX="+deltaX+",deltaY="+deltaY);
                                            System.out.println("degreeRtoV" + degreeRtoV);
                                            System.out.println("viewDegree=" + viewDegree);
                                        }*/
                            //vict v=victarr[vic];
                            rob=botarr[robotNo];
                        	ArrayList<botevent> el=rob.eventlist;
                        	int contain=containvic(el,vic);
 //                       	print(contain+"");

//                            if(victimMarked[vic]==false){

                            	if ((r==-1)&&(g==0)&&(b==0)&&(viewDegree<=30)){
//                                cover=true;                    //in hex point can be ff 00 00 or ed 1c 24, I don't know why, but they are look like red
 //                               robotCanSee[vic][robotNo]=true;
                                	if(contain==-1){
                                    	botevent be = new botevent();
                                    	be.appearTime=""+timePoint;
                                    	be.vicno=vic;
                                    	be.viewdegree=viewDegree;  
                                    	be.xmp= xmp; be.ymp= ymp;
                                    	be.xppm=xppm; be.yppm=yppm;
                                    	be.botno=robotNo;
                                    	be.endTime=timePoint;
                                    	el.add(be);
//                                	}
                                	}
                                	else{
                              		  botevent e=el.get(contain);
                            		  e.endTime=timePoint;
                                	}
                                
 //                             if(!el.isEmpty()){
                        //    	  print(el.get(0).vicno+" "+containvic(el,v));
 //                             }
                            	}
                                
//                                if(v.appearTime.equals("0")){
////                                	appearTime[vic]=""+timePoint;
//                                	v.appearTime=""+timePoint;
//                                	//System.out.println("okkkkk              okk"+timePoint+" "+appearTime[vic]);   
//                                }
//                                if(v.whichBot.equals("0")){
////                                	whichBot[vic]=""+robotNo;
//                                	v.whichBot=""+robotNo;
//                                	v.viewdegree=viewDegree;
//                                }
   //                             System.out.println("look "+viewDegree+" "+xppm+" "+yppm);                                                                    
   //                             System.out.println("at least one robot in red");
        //                        System.out.println("stemp1 "+stemp);
//                                if (stemp!='Y'){
//            //                    	System.out.println("beginning");
//                                    met[vic][robotNo]++;
//                                    robotTimePoint[vic][robotNo]=Long.parseLong(timePoint);
//                                    
//                                    
//                                    if (s!=null)
//                                        robotSee[vic][robotNo]+=timePoint+"Y\t";//must keep \t otherwise should modify line stemp=s.charAt(s.length()-2)
//                                    else
//                                        robotSee[vic][robotNo]=timePoint+"Y\t";
//                                    
//                                }

                            

                              else { // robot is not in the red
                            	  
 //                           		  event e=v.eventlist.get(v.eventlist.size()-1);
 //                           		  e.endTime=timePoint;
 //                           	  }
 //                           	  print(containvic(el,v)+" "+el);
                            	  if(contain!=-1){
                            		  botevent e=el.get(contain);
//                            		  if(e.endTime==""){
//                            		  e.endTime=timePoint;
//                            		  }
                            		  if(Integer.valueOf(e.endTime.substring(4))-Integer.valueOf(e.appearTime.substring(4))>2000){
  //                          		  botevents.add(e);
//                            		  print(e.endTime.substring(4)+" "+e.appearTime.substring(4));
//                            		  print(e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//            	                            +")  "+e.botno+"      "+convertime(e.appearTime)+"    "+convertime(e.endTime)
//            	                            +"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")    "+e.viewdegree+'\n');
//
                                    	  out.write(e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
                                    	                                                                          	            +")  "+e.botno+"      "+convertime(e.appearTime)+"    "+convertime(e.endTime)
                                    	                                                                          	            +"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")    "+e.viewdegree+'\n');
                                    	                                                                                out.newLine();
                            		  k++;
                            		  if(k>10){                           			  
                            			  print(""+convertime(e.endTime));     	
                            			  k=0;
                            		  }

                            		  }
                            		  el.remove(e);
//                            		  if(v.visibleprev==1){
//                                		  v.visibleprev=0;
//                                	  }
                            	  }
                            	  
                            	  
                         	  
                            	  
//                                robotCanSee[vic][robotNo]=false;
//            //                    System.out.println("stemp2 "+stemp);
//                                if (stemp=='Y')//check if the former status is "Y"
//                                {
//                                	//System.out.println("okkkkkkkkkkkkkkkk");
//                                    robotTimePoint[vic][robotNo]=Long.parseLong(timePoint);
//                                    
//                                    robotSee[vic][robotNo]+=timePoint+"N\t";
//                                    met[vic][robotNo]++;                                              
//                                }
//                              }//end of else
//   //                           System.out.println("robot timepoint "+robotTimePoint[vic][robotNo]);
//    //                          System.out.println("bot number "+robotNo+"victim number "+vic);
//                              
//                                  maxSeeTemp=0;
//                                for (int rn=1;rn<=robotNo;rn++){
//                                    if (robotCanSee[vic][rn]==true) maxSeeTemp++;
//                                    }
//                                if (maxSeeTemp>maxSee[vic]) maxSee[vic]=maxSeeTemp;
                                
                                
//                              System.out.print("victimNo,appearTime,robtNo ");

//                                

//                            }//end of if victimMarked
                            //raf.close();
                            }
                     
                        } catch (IOException e) {
                            System.out.println("err:"+e.getMessage());
                        }//end try
                     }
//                        if(r==-1 && g==0 && b==0){
//                        	int count=0;
//                            for(int z=0;z<events.size();z++){
//                        	//System.out.print(z+" "+appearTime[z]+" "+whichBot[z]+",  ");
//                        	vict v=events.get(z);
////                        	System.out.print(v.no+" "+v.x+" "+v.y+" "+v.appearTime+" "+v.whichBot+" "+v.viewdegree+",  ");
//                            if(v.whichBot!="0") count++;            
//                            }
//                            //if(count==VICTIMNO) {printv(victarr,VICTIMNO);return;}
//                            //else count=0;
//                        System.out.print("\n");
//                        }
                     }//for vic
          
                 } //processed a line
          }          
 

          
          
		//while, precessed a log 
          

          
//          int missTimes=0;
//          long avgMarkTime=0;
//          int v=0;
//          double robotNumWhenMark=0;
//          boolean victimLost=false;
//          int victimLostNum=0;
//          for (int i=1;i<=VICTIMNO;i++){
//              victimLost=false;
//              long avgVictTime=0;
//              int ro=0;
//              for (int j=1;j<=ROBOTNO;j++){
//                  if (robotSee[i][j]!=null) //the victim at least appeared on the screen once
//                      {
//                          missTimes+=met[i][j]/2;
//                          long period=(markedTime[i]-robotTimePoint[i][j]);                          
//                        if ((markedTime[i]>0)&&(period>0)){
//                            if(met[i][j]%2!=0){
//                                ro++;
//                                avgVictTime+=period;
//                            }
//                            System.out.println("["+i+","+j+"]\t"+maxSee[i]+"\t"+met[i][j]+"\t"+(met[i][j]/2)+"\t@ "+period+"\t\t"+robotSee[i][j]);
//                        }
//                        else{ 
//                            System.out.println("["+i+","+j+"]\t"+maxSee[i]+"\t"+met[i][j]+"\t"+(met[i][j]/2)+"\t@ "+"-1"+"\t\t"+robotSee[i][j]);
//                            victimLost=true;
//                        }
//                          //System.out.println();
//                      }
//              }
//              if (victimLost==true) victimLostNum++;
//              if (ro>0) {
//                  avgMarkTime+=avgVictTime/ro;
//                  v++;
//                  robotNumWhenMark+=ro;
//              }
//              
//          }
//          //System.out.println("robotNoWhenMark before avg="+robotNoWhenMark);
//          System.out.println();
//          if (v!=0){
//              avgMarkTime/=v;
//              robotNumWhenMark/=v;
//          }
          
          
//          ArrayList<event> el=v.eventlist;
//          for(int a=0;a<el.size();a++){
//        	  event e=el.get(a);
//          System.out.println(v.no+" "+e.appearTime+" "+e.endTime+" x "+e.x +" bot "+e.botlist+" "+e.viewdegree);
//          }
          
          
//          FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/out.txt");
//          BufferedWriter out = new BufferedWriter(fstream);
//          out.write("vicNo (x,y)    robotNo   begin      end             Robot(xpm,ypm)   (xppm,yppm)              viewdegree");    
//          out.newLine();
                    System.out.println("vicNo (x,y) robotNo   begin      end       Robot(xpm,ypm) (xppm,yppm)        viewdegree");
//          for(int p=1;p<=ROBOTNO;p++){
//        	  bot b=botarr[p];
//        	  ArrayList<botevent> el=b.eventlist;
//        	  if(el!=null){
//        		  for(int q=0;q<el.size();q++){
//                	  botevent e=el.get(q);
//                	  out.write(e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//                	            +")  "+b.no+" "+e.appearTime+"  "+e.endTime+"  x,y:("+e.xmp+","+e.ymp+")  "
//                	            +" ("+e.xppm+","+e.yppm+")  "+e.viewdegree+'\n');
//                      out.newLine();
//        		  }
//          }
//          }
//    	  if(botevents!=null){
//    		  for(int q=0;q<botevents.size();q++){
//            	  botevent e=botevents.get(q);
//            	  out.write(e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//            	            +")  "+e.botno+"      "+convertime(e.appearTime)+"    "+convertime(e.endTime)
//            	            +"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")    "+e.viewdegree+'\n');
//                  out.newLine();
//    		  }
//      }
          
          out.close();
          System.out.println("ok");
//          System.out.println("missTimes\t"+missTimes+"\tavgMarkTime\t"+avgMarkTime+"\tvic_deleted\t"+deletV+"\trobotNumWhenMark\t"+robotNumWhenMark+"\tvictimLostNum\t"+victimLostNum);
          //System.out.println("v="+v);
          //preout.close();
//          System.out.printf("Done"); 
 }
 
 
	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(4));
		int initial=Integer.valueOf(initialtime.substring(4));
		int time2=(time-initial)/1000;
//		print(time+" "+initial+" "+time2);
		str=time2/60+":"+time2%60;
		return str;
	}
	
	
          public static int containvic(ArrayList<botevent> el, int vic) {
        	  for(int m=0;m<el.size();m++){
        		  botevent be=el.get(m);
//    			  print(v.no+" "+be.vicno);
        		  

        		  if(be.vicno==vic){
//        			  if(be.endTime.equals("")){
        				  return m;
//        			  }
        		  }
        	  }
        	  return -1;
          }
//          public static int containvic2(ArrayList<botevent> el, vict v) {
//        	  for(int m=0;m<el.size();m++){
//        		  botevent be=el.get(m);
// //   			  print(v.no+" "+be.vicno);
//
//        		  if(be.vicno==v.no){
//        			  if(be.endTime==""){
//        				  return m;
//        			  }
//        		  }
//        	  }
//        	  return -1;
//          }

          
 private static void print(String string) {
		System.out.println(string);
	}


public  static void processAPoint(int subjectNo,int robotNo,String timePoint,double xmp,double ymp,double thetamp) {
}
 
}
