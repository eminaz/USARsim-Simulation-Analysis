/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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



/**
 *
 * @author hal
 */
public class TestVictimLocation {
    public static int VICTIMNO=34;
    public final int ROBOTNO=24;
    public final double PI=3.1415926536;
    public static int map;
    
//    public static String mapnumber="";
    //positions in map 1
    public static int[][] victimPosition1={{270,119},{358,118},{721,128},{786,159},{898,202},{1102,72},{1371,129},{1869,106},{1966,138},{896,258},{1558,241},
    {265,331},{109,635},{264,700},{628,536},{701,574},{782,515},{931,592},{1109,424},{1395,377},{1465,425},{1619,451},
    {1991,492},{1256,659},{1706,623},{1884,661},{348,941},{1163,966},{1256,964},{1412,988},{1430,1032},{1698,1009},{1509,703},
    {1641,389}};
    
    public static int[][] victimPosition2={{270,119},{358,118},{721,128},{551,162},{878,	198},{1102,	72},{1371,	129},{1869,	106},{1966,	138},{896,	258},
    {1558,	241},{262,	465},{109,	635},{869,	535},{628,	536},{701,	576},{782,	515},{1109,	424},{1282,	382},{1378,	429},{1504,	459},{1511,	396},{1991,	492},
    {1832,	600},{1756,	668},{1350,	668},{348,	941},{1909,	716},{1256,	964},{1412,	988},{1430,	1032},{1698,	1009},{421,	562},{849,	965}};
    
    public static int[][] victimPosition3={    {270,	119},{358,	118},{721,	128},{786,	159},{898,	202},{1102,	72},{1513,	126},{1712,	106},
    {1966,	138},{655,	254},{1366,	241},{417,	327},{104,	395},{423,	566},{580,	498},{701,	574},{782,	515},{1109,	424},{921,	519},{1395,	377},
    {1762,	385},{1779,	460},{1805,	494},{1388,	662},{1705,	623}, {1334,	429},{348,	941},{1163,	966},{1256,	964},{1412,	988},{1430,	1032},{1698,	1009},
    {1998,	649},{1722,	698}};
    
    public static int[][] victimPosition=victimPosition1;
    public static ArrayList<Double> incorrectx=new ArrayList<Double>();
    public static ArrayList<Double> incorrecty=new ArrayList<Double>();
    public static ArrayList<Double> deleted=new ArrayList<Double>();

    int [][] met= new int[VICTIMNO+1][ROBOTNO+1];
    long [][] robotTimePoint= new long [VICTIMNO+1][ROBOTNO+1];
    String[][] robotSee=new String[VICTIMNO+1][ROBOTNO+1];	//each victim-robot has a status of string
    boolean [][] robotCanSee= new boolean [VICTIMNO+1][ROBOTNO+1];
    boolean [] victimMarked=new boolean [VICTIMNO+1];
    long [] markedTime=new long [VICTIMNO+1];
    long teleOpNo,teleOpTime;
    int [] maxSee=new int [VICTIMNO+1];

    public static void main(String[] args) throws FileNotFoundException, IOException {
    FileWriter outFile = new FileWriter("output1.txt");
    PrintWriter out = new PrintWriter(outFile);

    String logName1=Image1.logName;
    process2(logName1);
    out.close();
    }

	//Get the mark position on the map
    public static double[] getmark(String line){
        double markp[]=new double[7];
        double xmp,ymp;
        char stemp;
        int pmp=0;
        String smp="";
        ArrayList<String> samp = new ArrayList<String> ();

        while(pmp<line.length()){
         stemp=line.charAt(pmp);
         
         if((stemp!=' ')&&(stemp!=',')&&(stemp!='[')&&(stemp!=']')&&(stemp!=':')&&(stemp!='x')&&(stemp!='y')&&(stemp!='=')){
             smp+=stemp;
         }
         else{
             if(smp!=""){
                     samp.add(smp);
                     smp="";
             }
         }
         pmp++;
         }
        samp.add(smp);
        long markT=Long.parseLong(samp.get(0));
        xmp=Double.parseDouble(samp.get(4));
        ymp=Double.parseDouble(samp.get(5));
        double update=0;
        if(samp.get(9).matches("true")){
        	update=1;
        }
        double mapper=1;
        if(samp.get(2).matches("Mapper2")){
        	mapper=2;
        }
        int yppm=(int)((ymp-(-15.625))*40-1000);//y position in ppm file
        int xppm=(int)((xmp-(-125))*40-2200);//x position
        markp[0]=xppm;
        markp[1]=yppm;
        markp[2]=markT;
        markp[3]=update;
        markp[4]=mapper;
        markp[5]=xmp;
        markp[6]=ymp;
        return markp;
    }
    
    
    
    public static double hdist(int[][] vp, ArrayList<Double> mx, ArrayList<Double> my, ArrayList<Double> md){
    	ArrayList<Double> rx=new ArrayList<Double>(mx);
    	ArrayList<Double> ry=new ArrayList<Double>(my);
    	Collections.copy(rx, mx);
    	Collections.copy(ry, my);
    	for(int n=0;n<mx.size();n++){
    		if(md.get(n)==0.0){
    			int r=rx.indexOf(rx.get(n));
    			rx.set(r, -99.0);
    			ry.set(r, -99.0);
    			rx.set(n, -99.0);
    			ry.set(n, -99.0);
    			}
    		}
    	for(int k=0;k<rx.size();k++){
    		if(rx.get(k)==-99.0){
    			rx.remove(k);
    			ry.remove(k);
    			k--;
    		}
    	}
    	//System.out.println(mx);
    	//System.out.println(rx);
        double temp[][]=new double[mx.size()][3];
        double [] distance=new double[VICTIMNO];
        double [] distanceCopy=new double[VICTIMNO];
        double smallest;
        Double[] X = rx.toArray(new Double[rx.size()]);
        Double[] Y = ry.toArray(new Double[ry.size()]);
        double[] maxlist = new double[X.length];
        for(int i=0;i<X.length;i++){
            for(int j=0;j<vp.length;j++){
            distance[j]=Math.sqrt((vp[j][1]-Y[i])*(vp[j][1]-Y[i])+(vp[j][0]-X[i])*(vp[j][0]-X[i]));
            distanceCopy[j]=distance[j];
            }
            Arrays.sort(distance);
            smallest=distance[0];
            maxlist[i]=smallest;
            }
        Arrays.sort(maxlist);
        return maxlist[maxlist.length-1];
    }
    
    
    
    //Calculate victims found and delete by team
    public static double[][] markcount(int[][] vp, ArrayList<Double> mx, ArrayList<Double> my, ArrayList<Double> md,ArrayList<Double> mm){
        double temp[][]=new double[mx.size()+1][6];
        double [] distance=new double[VICTIMNO];
        double [] distanceCopy=new double[VICTIMNO];
        double smallest;
        int correctnum=0,correctnum1=0,correctnum2=0;
        int [] vn=new int[mx.size()];
        int [] find=new int[mx.size()];
        Double[] X = mx.toArray(new Double[mx.size()]);
        Double[] Y = my.toArray(new Double[my.size()]);
        Double[] D = md.toArray(new Double[md.size()]);
        for(int i=0;i<X.length;i++){
            for(int j=0;j<vp.length;j++){
            distance[j]=Math.sqrt((vp[j][1]-Y[i])*(vp[j][1]-Y[i])+(vp[j][0]-X[i])*(vp[j][0]-X[i]));
            distanceCopy[j]=distance[j];
            }
            Arrays.sort(distance);
            smallest=distance[0];
            for(int t=0;t<distanceCopy.length;t++){
                if((Math.abs(distanceCopy[t]-smallest))<0.1) {vn[i]=t;}
            }
            // 1-found mark; 0-mark too far; -1-deleted mark
	    if(smallest<40) {
	    	find[i]=1; correctnum++; //System.out.println(X[i]+" "+Y[i]);
	    	if(mm.get(i)==1){correctnum1++;}
	    	else{correctnum2++;}
	    }
            else{find[i]=0;
//    	    incorrectx.add(mx.get(i));
//    	    incorrecty.add(my.get(i));
    	    }
            if(D[i]==1){find[i]=-1;}
            if(D[i]==1 && smallest<40){correctnum--;correctnum--;
              if(mm.get(i)==1){correctnum1--;correctnum1--;}
              else{correctnum2--;correctnum2--;}
    
            }
//            if(smallest<40 && D[i]==0){correctnum++;}
            temp[i][0]=vn[i];
            temp[i][1]=smallest;
            temp[i][2]=find[i];
            temp[i][3]=mm.get(i);
            temp[i][5]=D[i];
        }
        if(temp.length>2){
        temp[0][4]=correctnum;
        temp[1][4]=correctnum1;
        temp[2][4]=correctnum2;
        return temp;
        }
		return temp;
    }
    
    //count the number of wrong marks made by the robots
    public static int counterror(int[][] vp, ArrayList<Double> mx, ArrayList<Double> my, ArrayList<Double> md,ArrayList<Double> mm){
    	int errnum=0;
    	double temp[][]=markcount(vp,mx,my,md,mm);
    	for(int i=0;i<temp.length;i++){
    		if(temp[i][2]==0){
    			errnum++;
    		}
    		if(temp[i][2]==0 && temp[i][5]==1){
    			errnum--;errnum--;
    		}
    	}
    	return errnum;
    }
    //count the number of errors made by operator 1
//    public static int counterr1(int[][] vp, ArrayList<Double> mx, ArrayList<Double> my, ArrayList<Double> md,ArrayList<Double> mm){
//    	int err1=0;
//    	double temp[][]=markcount(vp,mx,my,md,mm);
//    	for(int i=0;i<temp.length;i++){
//    		if(temp[i][2]==0 && temp[i][3]==1 && temp[i][5]==0){
//    			err1++;
//    		}
//    		else if(temp[i][2]==0 && temp[i][3]==1 && temp[i][5]==1){
//    			err1--;err1--;
//    		}
//    	}
//    	return err1;
//    }
//    
//    public static int counterr2(int[][] vp, ArrayList<Double> mx, ArrayList<Double> my, ArrayList<Double> md,ArrayList<Double> mm){
//    	int err2=0;
//    	double temp[][]=markcount(vp,mx,my,md,mm);
//    	for(int i=0;i<temp.length;i++){
//    		if(temp[i][2]==0 && temp[i][3]==2 && temp[i][5]==0){
//    			err2++;
//    		}
//    		else if(temp[i][2]==0 && temp[i][3]==2 && temp[i][5]==1){
//    			err2--;err2--;
//    		}
//    	}
//    	return err2;
//    }

    
    public static void process2(String f) throws IOException{
    	try{
        ArrayList<Double> markX=new ArrayList<Double>();
           ArrayList<Double> markY=new ArrayList<Double>();
           ArrayList<Double> markT=new ArrayList<Double>();
           ArrayList<Double> markD=new ArrayList<Double>();
           ArrayList<Double> markU=new ArrayList<Double>();
           ArrayList<Double> markM=new ArrayList<Double>();
           ArrayList<Double> markx=new ArrayList<Double>();
           ArrayList<Double> marky=new ArrayList<Double>();
           Double[] markP;
           System.out.println(f);
           //FileReader prereader = new FileReader(f);
           File prereader = new File(f);  
           Scanner prein = new Scanner(prereader);
            int i=0;
            int h = 0;
          // FileWriter fwrite=new FileWriter("/Users/mark/Downloads/ok.txt") ; 
           //File fi=new File(f);
           
//            while(prein.hasNextLine()){
//            	String line =prein.nextLine();
//            	h++;
//            }

            String line="";
            while (prein.hasNextLine())
            {
                  line = prein.nextLine();
                  int kscan=line.indexOf("SCAN Bot");
                  int selectRobot=line.indexOf("SELECTROBOT");
                  int victMark=line.indexOf("VictMark");
                  int dataUpdate=line.indexOf("DATAUPDATE");
                  int dataDelete=line.indexOf("DATADELETE");
                  int hasTrue=line.indexOf("true");
                  int hasFalse=line.indexOf("false");
                  if((victMark!=-1)&&(dataUpdate==-1)&&(dataDelete==-1))
                  {
                      markX.add(new Double(getmark(line)[0]));
                      markY.add(new Double(getmark(line)[1]));
                      markT.add(new Double(getmark(line)[2]));
                      markU.add(new Double(getmark(line)[3]));
                      markM.add(new Double(getmark(line)[4]));
                      markx.add(new Double(getmark(line)[5]));
                      marky.add(new Double(getmark(line)[6]));
                      if (hasTrue!=-1) {markD.add(new Double(0));}
                      else{markD.add(new Double(1));}
                      i++;
                  }

            }
            
            double[][] temp1=markcount(victimPosition1,markX,markY,markD,markM);
            double[][] temp2=markcount(victimPosition2,markX,markY,markD,markM);
            double[][] temp3=markcount(victimPosition3,markX,markY,markD,markM);
            double no1=temp1[0][4],no2=temp2[0][4],no3=temp3[0][4];
//            double hd1=hdist(victimPosition1,markX,markY,markD);
//            double hd2=hdist(victimPosition2,markX,markY,markD);
//            double hd3=hdist(victimPosition3,markX,markY,markD);
//            System.out.println("hd 1,2,3: "+hd1+" "+hd2+" "+hd3);
//            System.out.println(no1+" "+no2+" "+no3);
            int mapnum=0;
            if(no2>no1 && no2>no3){
            	victimPosition=victimPosition2;
            	mapnum=2;
            }
            else if(no3>no2 && no3>no1){
            	victimPosition=victimPosition3;
            	mapnum=3;
            }
            else{
                victimPosition=victimPosition1;
                mapnum=1;
            }
            
           // victimPosition=victimPosition1;
//            for(int p=0;p<victimPosition.length;p++){
//        		double x=victimPosition[p][0];
//        		double y=victimPosition[p][1];
//        	}
//            int n=0,n1=0,n2=0;
//            int delnum=0;
//            ArrayList<Double> arr1x=new ArrayList<Double>();
//            ArrayList<Double> arr2x=new ArrayList<Double>();
//        	for(int k=0;k<markX.size();k++){
//        		Double x=markX.get(k);
//        		Double y=markY.get(k);
//        		Double update=markU.get(k);
//        		Double mapper=markM.get(k);
//        		if(update==1){
//        			n++;     			
//        			if(mapper==1){  arr1x.add(markX.get(k)); n1++;}
//        			else{    arr2x.add(markX.get(k)); n2++; }				
//        		}
//        		else{
//        			deleted.add(markX.get(k));
//        			n--;
//        			delnum++;
////        			if(mapper==1) { arr1x.remove(arr1x.indexOf(markX.get(k)));n1--;}
//        			if(mapper==1) { arr1x.remove(markX.get(k));n1--;}
////        			else { arr2x.remove(arr2x.indexOf(markX.get(k)));n2--;}
//        			else { arr2x.remove(markX.get(k));n2--;}
//        			}
//        	}
//        	int errnum=counterror(victimPosition,markX,markY,markD,markM);
//        	int err1=counterr1(victimPosition,markX,markY,markD,markM);
//        	int err2=counterr2(victimPosition,markX,markY,markD,markM);
//        	double[][] temp=markcount(victimPosition,markX,markY,markD,markM);
//        	int correctnum=(int) temp[0][4];
//        	int correctnum1=(int) temp[1][4];
//        	int correctnum2=(int) temp[2][4];
//        	int errnum=n-correctnum;
//        	int m=n-errnum,m1=n1-err1,m2=n2-err2;

//        	mapnumber="victimPosition"+mapnum;
//        	Image1.mapnumber=mapnumber;
//        	System.out.println(n);
        	map=mapnum;
        	System.out.println(mapnum);
//        	System.out.println(mapnum+" "+correctnum+" "+delnum+" "+errnum+" "+correctnum1+" "+correctnum2);
//        	System.out.println(arr1x+"\n"+arr2x);
//        	System.out.println("incorrectx "+incorrectx+"\n"+"incorrecty "+incorrecty);
//        	System.out.println("deleted "+deleted);
//        	System.out.println("markX "+markX+"\n"+"markY "+markY);
////            System.out.println("size2 "+markX.size());

    }
        	catch (IOException e) {
                System.out.println("err:"+e.getMessage());
            }
       
        }
	 private static void print(String string) {
			System.out.println(string);
		}
    
}