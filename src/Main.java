/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.imageio.ImageIO;

/**
 *
 * @author hal
 */
public class Main {

	
	public static int[] color=new int[3];
	public static byte[] color2=new byte[3];
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	String stringFile1="/Users/hal/Desktop/Mark/Output3_7.ppm";
        String stringFile2="/Users/hal/Desktop/Mark/Output3_7.png";
        		

        int xp=3256;
        int yp=1925;

        xp=3000;
        yp=1500;
        
        xp=300;
        yp=300;

    }
    public static void colorPNG(String s,int x, int y) throws IOException{
        File f = new File(s);
        BufferedImage image = ImageIO.read(f);        
        System.out.println("width, height "+image.getWidth()+" "+image.getHeight());
        int r;
        int g;
        int b;
        int alpha;
        int rgb;
        rgb=image.getRGB(x, y);
        alpha = ((rgb >> 24) & 0xff);
        r = ((rgb >> 16) & 0xff);
        g = ((rgb >> 8) & 0xff);
        b = ((rgb ) & 0xff);
        System.out.println(r+" "+g+" "+b+" ");
        Color2Hex(r,g,b);
    }
    public static void colorPPM(String s,int x,int y) throws FileNotFoundException, IOException{
        File f = new File(s);
        RandomAccessFile raf = new RandomAccessFile(f, "r");
        byte r;
        byte g;
        byte b;
        int j=56+(y*2100+x)*3;
        raf.seek(j);
        r=raf.readByte();
        g=raf.readByte();
        b=raf.readByte();
        color2[0]=r;color2[1]=g;color2[2]=b;
        raf.close();

     }
     public static void Color2Hex(int r,int g,int b){
        Color c = new Color(r,g,b);
        System.out.println("hex: " + Integer.toHexString(c.getRGB() & 0x00ffffff));
     }
     public static void Hex2RGB(String colorStr){
    	int r=Integer.valueOf( colorStr.substring( 0, 2 ), 16 );
        int g=Integer.valueOf( colorStr.substring( 2, 4 ), 16 );
        int b=Integer.valueOf( colorStr.substring( 4, 6 ), 16 );
        System.out.println("hex2rgb: "+r+" "+g+" "+b);
        color[0]=r;color[1]=g;color[2]=b;
     }
}
