import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Correction {

	
	public static void main(String[] args)
	throws IOException
	{
		String logName="/Users/hal/Desktop/Mark/"+Image1.name+"all.txt";

	System.out.println(logName);

	FileReader prereader = new FileReader(logName);
	Scanner prein = new Scanner(prereader);
	//int a=0;
	String initialtime="";
	int ROBOTNO=34;
	
	String[] botarr=new String[ROBOTNO+1];
	for(int i=1;i<=ROBOTNO;i++){
		botarr[i]="";  	
	}
	
	


	while (prein.hasNextLine())
	{
		String line = prein.nextLine();

		int victMark=line.indexOf("victMark");
		int image=line.indexOf("Image");
		int begin=line.indexOf("Begin");
		int end=line.indexOf("End");
		int tte=line.indexOf("total-Teleoperation:");
		int ttet=line.indexOf("total-Teleoperation-Time");
		
		if(image!=-1){
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
 //           print(samp+"");
            

			String botNo=samp.get(3);
			int robotNo=Integer.parseInt(botNo);
			String timePoint=samp.get(0);
			
			if(initialtime==""){
				initialtime=timePoint;
				print(timePoint);
			}      
			
			
			if(Integer.valueOf(timePoint.substring(4))>Integer.valueOf(initialtime.substring(4))+1201234){
				print(timePoint +" "+robotNo+" "+samp.get(8)+" "+begin+" "+end);
				return;

			}
			
		}
	}
	
}
	
	

	private static void print(String string) {
		System.out.println(string);
	}
}