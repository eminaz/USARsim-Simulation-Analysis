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

public class Image2 {
	public static ArrayList<botevent> botevents=new ArrayList<botevent>();
	public static String initialtime="";
	public static int k=0;
	public static int start=0;

	final static double PI=3.1415926536;
	static int VICTIMNO=34;
	final static int ROBOTNO=24;
	public static bot[] botarr=new bot[ROBOTNO+1];



	public static void main(String[] args)
	throws IOException
	{
		String logName=Image1.logName;
		String map=Image1.map;

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
//	    botarr=Image1.loadArray("/Users/hal/Desktop/Mark/array1");

		int bn=0;
		int en=0;


		FileWriter fstream = new FileWriter("/Users/hal/Desktop/Mark/new/image2.txt");
		BufferedWriter out = new BufferedWriter(fstream);
//		out.write(logName);
//		out.newLine(); out.newLine();	    
//		out.write("vicNo (x,y)    robotNo   begin      end             Robot(xpm,ypm)   (xppm,yppm)              viewdegree");    
//		out.newLine();


//		System.out.println(logName);

		FileReader prereader = new FileReader(logName);

		//skip to the other part of the log file
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

			//deal with whether the robot falls
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
					rob.standing=0;   //standing=0 means the robot falls
				}
			}



			//search the lines that have "IMAGE BOT"
			if(kimage!=-1) {//there is the key word
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
				xmp=Double.parseDouble(samp.get(6));
				ymp=Double.parseDouble(samp.get(7));
				thetamp=Double.parseDouble(samp.get(8));
				String botNo=samp.get(2);
				botNo=botNo.substring(3);
				int robotNo=Integer.parseInt(botNo);
				String timePoint=samp.get(0);
				if(initialtime==""){
					initialtime=timePoint;
					print(timePoint);
				}                     

				
                if(start==0){
                    if(Integer.valueOf(timePoint.substring(4))>Integer.valueOf(initialtime.substring(4))+301234){
                   	 start=1;
                    }
                    }
                    
                    if(Integer.valueOf(timePoint.substring(4))>Integer.valueOf(initialtime.substring(4))+601234){
//                   	 Miss3.saveArray("/Users/hal/Desktop/Mark/array2",botarr);
                    	out.close();
                   	 return;
                    }
                    if(start==1){


				int r = 0,g = 0,b = 0,alpha,rgb;

				bot rob=botarr[robotNo];
				if(rob.standing==1){       
					for (int vic=1;vic<=VICTIMNO;vic++){ 
						try {
							String stringFile="/Users/hal/Desktop/Mark/"+map+"/Output"+vic+".ppm";

							int yppm=(int)((ymp-(-15.625))*40-1000);//y position in ppm file
							int xppm=(int)((xmp-(-125))*40-2200);    //x position
							Main.colorPPM(stringFile, (int)xppm, (int)yppm);
							byte[] color2=Main.color2;

							r=color2[0];g=color2[1];b=color2[2];
							Main.color2=new byte[3];


							double degreeRtoV=0;
							double myTheta=0;    //the degree between victim and robot
							double deltaX=victimPosition[vic-1][0]-xppm;
							double deltaY=victimPosition[vic-1][1]-yppm;
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

							//if the color is right and the viewDegree is within the range
							if ((r==-1)&&(g==0)&&(b==0)&&(viewDegree<=30)){
								
								if(contain==-1){ //if it has not appeared
									botevent be = new botevent();
									be.appearTime=""+timePoint;
									be.vicno=vic;
									be.viewdegree=viewDegree;  
									be.xmp= xmp; be.ymp= ymp;
									be.xppm=xppm; be.yppm=yppm;
									be.botno=robotNo;
									be.endTime=timePoint;
									el.add(be);
									out.write
									(be.appearTime+" "+convertime(be.appearTime)+" ImageBegin "
											+" "+be.botno+" "+" "+be.xmp+" "+be.ymp+" "+" "+be.xppm+" "+be.yppm+" "
											+be.vicno+" "+victimPosition[be.vicno-1][0]+" "+victimPosition[be.vicno-1][1]);
									bn++;
								}
								else{ //if it has appeared
									e=el.get(contain);
									e.endTime=timePoint; //update the endTime 
									e.vicno=vic;
								}
							}
							else { // robot is not in the red                       		
								if(contain!=-1){  //if it has appeared
									e=el.get(contain);   //get the botevent   
									if(Integer.valueOf(e.endTime.substring(4))-Integer.valueOf(e.appearTime.substring(4))>2000){
									en++;
									out.write
								//	print
									(e.appearTime+" "+convertime(e.appearTime)+" ImageBegin "
											+" "+e.botno+" "+" "+e.xmp+" "+e.ymp+" "+" "+e.xppm+" "+e.yppm+" "
											+e.vicno+" "+victimPosition[e.vicno-1][0]+" "+victimPosition[e.vicno-1][1]);
									bn++;
									out.newLine();
									out.write
									//print
									(e.endTime+" "+convertime(e.endTime)+" ImageEnd "
											+" "+e.botno+" "+" "+e.xmp+" "+e.ymp+" "+" "+e.xppm+" "+e.yppm+" "
											+e.vicno+" "+victimPosition[e.vicno-1][0]+" "+victimPosition[e.vicno-1][1]);
									el.remove(e);
									out.newLine();
									}

								}
							}

						} catch (IOException e2) {
							System.out.println("err:"+e2.getMessage());
						}//end try
					}
				}//for vic

			} //processed a line
		}          
		//deal with the last pieces of the data, the ending events which didn't get chance to be printed
//		for(int i=1;i<ROBOTNO;i++){
//			bot rob = botarr[i];
//			ArrayList<botevent> el=rob.eventlist;
//			if(!el.isEmpty()){
//				for(int l=0;l<el.size();l++){
//					if(!(el.get(l).endTime.isEmpty())){
//						e=el.get(l);
//						print(e.endTime+"  "+convertime(e.endTime)+"  ImageEnd  "
//								+" "+e.botno+" "+" "+e.xmp+" "+e.ymp+" "+" "+e.xppm+" "+e.yppm+" "
//								+e.vicno+" "+" "+victimPosition[e.vicno-1][0]+" "+victimPosition[e.vicno-1][1]);
//						en++;
//					}
//				}
//			}
//		}
			
			
		}
	}

//convert time to minutes and seconds
	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(4));
		int initial=Integer.valueOf(initialtime.substring(4));
		int time2=(time-initial)/1000;
		str=time2/60+":"+time2%60;
		return str;
	}

//see whether the victim has appeared for that robot.
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


//save the array so that I can continue to use the information
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

