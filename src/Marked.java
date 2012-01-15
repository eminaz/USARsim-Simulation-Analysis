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
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.lang.Integer;
/*
        @Zheng Ma
 */

public class Marked {
	public static ArrayList<Robotevent2> robotevents2=new ArrayList<Robotevent2>();
	public static String initialtime=Image1.initialtime;
	public static void main(String[] args)
	throws IOException
	{
		
		String logName=run.dir+Image1.name+"all.txt";
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

		final int ROBOTNO=24;
		int VICTIMNO=34;

		int totalteleop=0;
		int totalteleoptime=0;

		FileWriter fstream = new FileWriter(run.dir+Image1.name+"VictMark Summary.txt");
		BufferedWriter sr = new BufferedWriter(fstream);
		sr.newLine(); sr.newLine();	 
		
		String[] timebotarr=new String[ROBOTNO+1];
		for(int b=1;b<=ROBOTNO;b++){
			timebotarr[b]="";  	
		}

		String[] timevmarr2=new String[ROBOTNO+1];
		for(int b=1;b<=ROBOTNO;b++){
			timevmarr2[b]="";  	
		}

		String[] victarr=new String[VICTIMNO+1];
		for(int b=1;b<=VICTIMNO;b++){
			victarr[b]="";  	
		}

		victclass[] victclasses=new victclass[VICTIMNO+1];
		for(int b=1;b<=VICTIMNO;b++){
			victclasses[b]=new victclass();
			victclasses[b].appearance=0;
			victclasses[b].x=victimPosition[b-1][0];
			victclasses[b].y=victimPosition[b-1][1];
			victclasses[b].CorrectlyMarked=0;
			victclasses[b].NotMarked=0;
			victclasses[b].Error=0;

		}

        //array of victMark
		ArrayList<String> vmarr=new ArrayList<String>();

		int bn=0;
		int en=0;
		int delnum = 0;
		int victnum=victimPosition.length;
		int victmarked = 0;


		System.out.println(logName);

		FileReader prereader = new FileReader(logName);
		Scanner prein = new Scanner(prereader);


		while (prein.hasNextLine())
		{
			String line = prein.nextLine();

			int victMark=line.indexOf("victMark");
			int image=line.indexOf("Image");
			int begin=line.indexOf("Begin");
			int end=line.indexOf("End");
			int tte=line.indexOf("total-Teleoperation:");
			int ttet=line.indexOf("total-Teleoperation-Time");
			
			if(tte!=-1 || ttet!=-1){
				sr.write(line);
				sr.newLine();
			}


			if(image!=-1 && begin!=-1){
				bn++; //update the number of begin events
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

				String time=samp.get(0);
				String botNo=samp.get(3);
				int robotNo=Integer.parseInt(botNo);
				timebotarr[robotNo]=time;
				String victno=samp.get(8);
				int v=Integer.valueOf(victno);
				victarr[v]="exist";

				victclasses[v].appearance++;  //update the number of appearance of each victim

			}



			if(image!=-1 && end!=-1){
				en++;  //update the number of end events
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
				String time=samp.get(0);
				String botNo=samp.get(3);
				int robotNo=Integer.parseInt(botNo);
				String victno=samp.get(8);
				int v=Integer.valueOf(victno);
				victarr[v]="exist"; //used to find how many victim appears
			}




			if(victMark!=-1){
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
				String time=samp.get(0);
				if(samp.size()>8){
				String botNo=samp.get(8);
				int robotNo=Integer.parseInt(botNo);
				//print(time+" "+botNo);
				String oldtime=timebotarr[robotNo];
				if(oldtime!=""){
					int deltaTime=Integer.valueOf(time.substring(4))-Integer.valueOf(oldtime.substring(4));
					//					print(time+ " victMark time-between-mark-event-and-the-previous-display "+deltaTime+" "+(double)((double)deltaTime/1000.00)+" seconds"+" robot "+botNo);
	//				print(oldtime+ " "+ time+" "+deltaTime+" "+(double)((double)deltaTime/1000.00)+" "+botNo);
				}
				else{
	//				print(-1+ " "+ time+" "+-1+" "+(double)((double)(-1)/1000.00)+" "+botNo);

					//print(time+ " victMark time-between-mark-event-and-the-previous-display "+-1+" "+(double)((double)(-1)/1000.00)+" seconds"+" robot "+botNo);

				}
				timevmarr2[robotNo]=time;
				}
				String bool=samp.get(7);
				if(bool.equals("1")){		
					victmarked++;
					vmarr.add(samp.get(4)+" "+samp.get(5)+" "+1);
				}
				else{ //if it's delete
					delnum++;  //the number of deleted victMark
					vmarr.add(samp.get(4)+" "+samp.get(5)+" "+0);
					victmarked--;

				}
			}
		}
				print(bn+" "+en);

		sr.write("victnum-number: "+victnum);
		sr.newLine();
		sr.write("victnum-marked: "+victmarked);
		sr.newLine();
		sr.write("victnum-deleted: "+delnum);
		sr.newLine();

		int victappeared=0;
		int validmark=0;
		for(int v=0;v<victarr.length;v++){
			if(victarr[v]!=""){
				victappeared++;
			}
		}
		sr.write("victnum-appeared: "+victappeared);
		sr.newLine();
		


		for(int z=0;z<vmarr.size();z++){
			double x = Double.valueOf(vmarr.get(z).split(" ")[0]).doubleValue();
			double y = Double.valueOf(vmarr.get(z).split(" ")[1]).doubleValue();
			int updateordelete=Integer.valueOf(vmarr.get(z).split(" ")[2]); 
			if(updateordelete==1){
				double validity=test(x,y,victimPosition);
				int i= (int) test2(x,y,victimPosition,validity);
				victclasses[i+1].NotMarked=0;
				if(validity<=40.0){
					validmark++;
					victclasses[i+1].CorrectlyMarked++;
				}
				else{
					victclasses[i+1].Error++;
		//			print("dsgg");

				}
			}
			else{  //if it's deleted
				double validity=test(x,y,victimPosition);
				if(validity<=40.0){
					validmark--;
					int i= (int) test2(x,y,victimPosition,validity);
					victclasses[i+1].CorrectlyMarked--;
					victclasses[i+1].NotMarked=1;
	//				print(victclasses[i+1].x+"");
				}
				else{
					int i= (int) test2(x,y,victimPosition,validity);
					victclasses[i+1].Error--;
	//				print(victclasses[i+1].x+"");


	//				print("dsgg");
				}
//				int i= (int) test2(x,y,victimPosition,validity);
//				victclasses[i+1].CorrectlyMarked--;
//				victclasses[i+1].NotMarked++;
//				victclasses[i+1].Error=0;
			}

		}

		for(int z=1;z<victclasses.length;z++){
		if(victclasses[z].CorrectlyMarked<=0 && victclasses[z].Error<=0){
			victclasses[z].NotMarked=1;
		}
	}

		sr.write("valid-marks: "+validmark);
		sr.newLine();

		int totalcorrect=0;
		int totalappear=0;
		int totalerror=0;
		int totalboth=0;
		int guessed=0;
		int appearnotmarked=0;
		for(int v=1;v<victclasses.length;v++){
			if(victclasses[v].CorrectlyMarked>0){
				totalcorrect++;
	//			print(victclasses[v].NotMarked+"");
			}
			if(victclasses[v].Error>0){
				totalerror++;
	//			print(victclasses[v].NotMarked+"");

			}
			if(victclasses[v].CorrectlyMarked>0 && victclasses[v].Error>0){
				totalboth++;
			}
			if(victclasses[v].appearance>0){
				totalappear++;
			}
			if(victclasses[v].CorrectlyMarked-victclasses[v].Error>0 && victclasses[v].appearance==0){
				guessed++;
			}
			if(victclasses[v].appearance>0&& victclasses[v].NotMarked>0){
				appearnotmarked++;
			}

		}

		// appearnotmarked=totalappear-totalerror-totalcorrect+totalboth;	   
		int incorrectmarked=victmarked-validmark;
		sr.write("number-of-victims-appear-but-not-marked "+appearnotmarked);
		print(appearnotmarked+" "+totalappear+" "+totalerror+" "+totalcorrect+" "+totalboth);
		sr.newLine();
		sr.write("number-of-correctly-marked-without-seeing-on-the-image(lucky) "+guessed);
		sr.newLine();
		sr.write("Incorrect-marks "+incorrectmarked);
		sr.newLine();


		//sr.write('\n'+"");
		sr.newLine();
		sr.newLine();

		sr.write(" x     y    appearance correctly-marked  notmarked error");
		sr.newLine();
		for(int v=1;v<victclasses.length;v++){
			victclass vc=victclasses[v];
			sr.write(vc.x+"   "+vc.y+"       "+vc.appearance+"         "+vc.CorrectlyMarked+"              "+vc.NotMarked+"           " +vc.Error);
			sr.newLine();
		}
		sr.close();

	}



	private static double test(double x, double y, int[][] victimPosition) {
		ArrayList<Double> distance=new ArrayList<Double>();

		int py=(int)((y-(-15.625))*40-1000);
		int px=(int)((x-(-125))*40-2200);   

		for(int i=0;i<victimPosition.length;i++){
			int vx=victimPosition[i][0];
			int vy=victimPosition[i][1];
			double d=Math.sqrt((vx-px)*(vx-px)+(vy-py)*(vy-py));
			distance.add(d);

		}
		Collections.sort(distance);
		Double smallest=distance.get(0);	

		return smallest;
	}

	private static double test2(double x, double y, int[][] victimPosition, double smallest) {

		int py=(int)((y-(-15.625))*40-1000);
		int px=(int)((x-(-125))*40-2200);   

		for(int i=0;i<victimPosition.length;i++){
			int vx=victimPosition[i][0];
			int vy=victimPosition[i][1];
			double d=Math.sqrt((vx-px)*(vx-px)+(vy-py)*(vy-py));
			if(d==smallest){
				return i;
			}

		}
		return -1;

	}


	public static String convertime(String timePoint){
		String str="";
		int time=Integer.valueOf(timePoint.substring(5));
		int initial=Integer.valueOf(initialtime.substring(5));
		int time2=(time-initial)/1000;
		str=time2/60+":"+time2%60;
		return str;
	}

	//see whether the robot has found this vic
	public static int containvic(ArrayList<botevent> el, int vic, int bot) {
		for(int m=0;m<el.size();m++){
			botevent be=el.get(m);
			if(be.vicno==vic && be.botno==bot){
				return m;
			}
		}
		return -1;
	}




	private static void print(String string) {
		System.out.println(string);
	}

}