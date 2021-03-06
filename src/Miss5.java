import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;
import java.lang.Integer;
/*
        @Zheng Ma
*/

public class Miss5 {
	public static ArrayList<botevent> botevents=new ArrayList<botevent>();
	public static String initialtime="1305578315466";
	public static int k=0;
	public static int start=0;
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

            bot[] botarr=new bot[ROBOTNO+1];
		    for(int b=1;b<=ROBOTNO;b++){
		    	botarr[b]=new bot();
		    	botarr[b].no=b;
		    	botarr[b].eventlist=new ArrayList<botevent>();    
		    	botarr[b].standing=1;
		    }
		    
		    botarr=loadArray("/Users/hal/Desktop/Mark/array2");
		    
		    
            int bn=0;
            int en=0;

       //final int ROBOTNO=2;
       
	   String logName="/Users/hal/Desktop/Mark/usar_20110516_043821_user.log";
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
   //    prereader.skip(1199999);
       
       Scanner prein = new Scanner(prereader);
       
       //String midLog=logName+"_Af.log";
       //PrintWriter preout = new PrintWriter(midLog);
       int subjectNo=0;    //default is 0, but need to be related to the log file name
       int victimNo=1;
       boolean cover=false;

             
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
                     
                     
                     
                     
                     
                     if(start==0){
                     if(Integer.valueOf(timePoint.substring(4))>Integer.valueOf("1305579314628".substring(4))){
                    	 start=1;
                     }
                     }
                     if(Integer.valueOf(timePoint.substring(4))>Integer.valueOf("1305579814628".substring(4))){
                    	 Miss3.saveArray("/Users/hal/Desktop/Mark/array3",botarr);
                    	 return;
                     }
                     
                     
                     
                     
                     
                     
                     if(start==1){
                     
                    
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

//angle4                            	
                                double abdeg=Math.atan((Math.abs(deltaY))/(Math.abs(deltaX)));
                                if((deltaX>0)&&(deltaY>0)) degreeRtoV=180.0/PI*abdeg;
                                else if ((deltaX>0)&&(deltaY<0) ) degreeRtoV=360-180.0/PI*abdeg;
                                else if ((deltaX<0)&&(deltaY<0) ) degreeRtoV=180+180.0/PI*abdeg;
                                else degreeRtoV=180-180.0/PI*abdeg;
                            	
                            }
                                                        
                            double degreeRnose=180.0*thetamp/PI;
                                        if(degreeRnose<0){
                                            degreeRnose=360+degreeRnose;
                                        }
                            double viewDegree=Math.abs(degreeRnose-degreeRtoV);
                            int maxSeeTemp=0;
                            
                            if (viewDegree>180) viewDegree=360-viewDegree;

                            rob=botarr[robotNo];
                        	ArrayList<botevent> el=rob.eventlist;
                        	int contain=containvic(el,vic);
                        	if(contain==-1){
                        		if(Miss3.botarr[robotNo]!=null){
                        		el=Miss3.botarr[robotNo].eventlist;
                        		contain=Miss3.containvic(el, vic);
                        		if(contain!=-1){
                            //		print("asdfhhghssgh");

                        		}}
                        	}
                        	
                       // 	print(Miss3.botarr[robotNo]+" "+robotNo);
                        	
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
                                  	  print(be.appearTime+" "+convertime(be.appearTime)+" ImageBegin "
                              	            +" "+be.botno+" "+" "+be.xmp+" "+be.ymp+" "+" "+be.xppm+" "+be.yppm+" "
                            	            +be.vicno+" "+victimPosition[be.vicno-1][0]+" "+victimPosition[be.vicno-1][1]);
                                	bn++;
                                	}
                                	else{
                              		  e=el.get(contain);
                            		  e.endTime=timePoint;
                                	}
                            	}
                                

                            

                              else { // robot is not in the red
                            	  
 //                           		  event e=v.eventlist.get(v.eventlist.size()-1);
 //                           		  e.endTime=timePoint;
 //                           	  }
 //                           	  print(containvic(el,v)+" "+el);
                            	  if(contain!=-1){
                            		  e=el.get(contain);
//                            		  if(e.endTime==""){
//                            		  e.endTime=timePoint;
//                            		  }
                          //  		  if(Integer.valueOf(e.endTime.substring(4))-Integer.valueOf(e.appearTime.substring(4))>2000){
  //                          		  botevents.add(e);
//                            		  print(e.endTime.substring(4)+" "+e.appearTime.substring(4));
//                            		  print(e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//            	                            +")  "+e.botno+"      "+convertime(e.appearTime)+"    "+convertime(e.endTime)
//            	                            +"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")    "+e.viewdegree+'\n');
//
//                                    	  out.write(e.appearTime+ "  "+e.endTime+"  "+convertime(e.appearTime)+"    "+convertime(e.endTime)
//                                  	            +e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//                                    	            +")  "+e.botno+"      "+"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")    "+e.viewdegree+'\n');
//                                    	           out.newLine();
                                    	  print(e.endTime+" "+convertime(e.endTime)+" ImageEnd "
                                  	            +" "+e.botno+" "+" "+e.xmp+" "+e.ymp+" "+" "+e.xppm+" "+e.yppm+" "
                                	            +e.vicno+" "+victimPosition[e.vicno-1][0]+" "+victimPosition[e.vicno-1][1]);

                                    	  en++;
                                    	  //                                      	            		"    "+e.viewdegree+'\n');
                                      	           //out.newLine();
//                            		  k++;
//                            		  if(k>10){                           			  
//                            			  print(""+convertime(e.endTime));     	
//                            			  k=0;
//                            		  }

                         //   		  }
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
                     
                        } catch (IOException e2) {
                            System.out.println("err:"+e2.getMessage());
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
          }
//          System.out.println(e.endTime);
//          for(int i=1;i<ROBOTNO;i++){
//              bot rob = botarr[i];
//          	ArrayList<botevent> el=rob.eventlist;
////          	int contain=containvic(el,vic);
//          	if(!el.isEmpty()){
//          		for(int l=0;l<el.size();l++){
//          			if(!(el.get(l).endTime.isEmpty())){
//          				e=el.get(l);
//          	   	  print(e.endTime+"  "+convertime(e.endTime)+"  ImageEnd  "
//        	            +" "+e.botno+" "+" "+e.xmp+" "+e.ymp+" "+" "+e.xppm+" "+e.yppm+" "
//        	            +e.vicno+" "+" "+victimPosition[e.vicno-1][0]+" "+victimPosition[e.vicno-1][1]);
//          			en++;
//          			}
//          		}
//          	}
//          }
 
          
 //         print(en+" "+bn);
//   	  print(e.endTime+"  "+convertime(e.endTime)+"  Image end  "
//	            +e.vicno+"    "+"("+victimPosition[e.vicno-1][0]+","+victimPosition[e.vicno-1][1]
//	            +")  "+e.botno+"      "+"  x,y:("+e.xmp+","+e.ymp+")  "+"  ("+e.xppm+","+e.yppm+")");
         

          
          
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
 





public static bot[] loadArray(String filename) {

	try {

	FileInputStream fis = new FileInputStream(filename);

	GZIPInputStream gzis = new GZIPInputStream(fis);

	ObjectInputStream in = new ObjectInputStream(gzis);

	bot[] gelezen_veld = (bot[])in.readObject();

	in.close();

	return gelezen_veld;

	}

	catch (Exception e) {

	System.out.println(e);

	}

	return null;

	}





}
