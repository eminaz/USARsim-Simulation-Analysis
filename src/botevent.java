import java.io.Serializable;


public class botevent implements Serializable{

	public int botno;
	public String appearTime;
	public String endTime;
	public int vicno;
	public double viewdegree;
//	public String prevTime;
	public double xmp,ymp;
	public int xppm,yppm;
	
	public botevent(){
		appearTime="";
		endTime="";
		vicno=0;
		viewdegree=0;
//		prevTime="";
		xmp=0.0;ymp=0.0;xppm=0;yppm=0;
	}
	
	
}
