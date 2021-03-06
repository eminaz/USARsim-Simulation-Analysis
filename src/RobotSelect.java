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

public class RobotSelect {
	public static String initialtime=Image1.initialtime;
	public static String toRemove1;
	public static String toRemove2;
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

		final double PI=3.1415926536;
		int VICTIMNO=34;
		final int ROBOTNO=24;

		String[] selectarr=new String[ROBOTNO+1];
		for(int b=1;b<=ROBOTNO;b++){
			selectarr[b]="";  	
		}

		int totalteleop=0;
		int totalteleoptime=0;


		String logName=Image1.logName;


		FileWriter fstream = new FileWriter(run.dir+"/new/selectrobot.txt");
		BufferedWriter sr = new BufferedWriter(fstream);
//		sr.write(logName);
		sr.newLine(); sr.newLine();	 


//		System.out.println(logName);

		FileReader prereader = new FileReader(logName);
		Scanner prein = new Scanner(prereader);

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
			int mapper=line.indexOf("Mapper");



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
//				print(samp+"");
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

			if(selectRobot!=-1 && mapper==-1) {//there is the key word
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
					//end the teleop events when there is select
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
				String botNo=samp.get(2);
				botNo=botNo.substring(3);
				int robotNo=Integer.parseInt(botNo);

				String timePoint=samp.get(0);
				if(initialtime==""){
					initialtime=timePoint;
				}

				if(!tempbotNo.equals(botNo)){  //if teleop of different robot
					if((begin!="")&&(end!="")){ 
						String begin2 = convertime(begin);
						String end2 = convertime(end);
						time=Integer.valueOf(end.substring(4))-Integer.valueOf(begin.substring(4));
						sr.write(end+" "+end2+" teleopEnds "+tempbotNo);
						//+" totalTime "+ time +" "+(double)((double)time/1000.00)+" seconds");
						sr.newLine();

						totalteleoptime=totalteleoptime+time;

					}
					//now is the beginning of a new teleop event
					sr.write(timePoint+" "+convertime(timePoint)+" teleopBegins "+botNo);
					sr.newLine();
					tempbotNo=botNo;
					begin=timePoint;
					end=timePoint;
					totalteleop++;
				}

				else{ //if teleop of the same robot as last time
					if(begin==""){ //if it's the beginning
						sr.write(timePoint+" "+convertime(timePoint)+" teleopBegins "+botNo);
						sr.newLine();
						tempbotNo=botNo;
						begin=timePoint;
						end=timePoint;
						totalteleop++;
					}
					end=timePoint; //update the ending time
				}
			}



		}
		//to account for the last piece of time
		print(end+" "+begin);

		if(begin!=""){
		time=Integer.valueOf(end.substring(4))-Integer.valueOf(begin.substring(4));
		totalteleoptime=totalteleoptime+time;
		String end2 = convertime(end);
		sr.write(end+" "+end2+"  teleopEnds "+tempbotNo);
		}
		sr.newLine();
		sr.newLine();
		toRemove1="total-Teleoperation: "+totalteleop;
		sr.write(toRemove1);
		sr.newLine();
		toRemove2="total-Teleoperation-Time: "+totalteleoptime+" ("+(double)((double)totalteleoptime/1000.00)+" seconds)";
		sr.write(toRemove2);


		sr.close();
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

	public static int convertbool(String str){
		if(str.equals("true")){
			return 1;
		}
		return 0;
	}
}