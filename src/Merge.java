import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class Merge{


/** Takes all files in a specified directory and merge them together...*/
	public static void mergeAllFiles(String fileDir) throws FileNotFoundException, IOException {
		
		File dirSrc = new File(fileDir);
		File[] list = dirSrc.listFiles();
		for(int j=0; j<list.length; j++){ 
			String lines; 
			String srcFile = list[j].getPath(); 
			String outFile = run.dir+"/new"+Image1.name+"unsorted.txt"; 
			BufferedReader inFile=new BufferedReader(new FileReader(new File(srcFile))); BufferedWriter outPut=new BufferedWriter(new FileWriter(outFile, true)); while((lines=inFile.readLine()) != null) {
				outPut.write(lines);
				outPut.newLine();
			}
			outPut.flush(); 
			outPut.close(); 
			inFile.close(); 
		} }
	
	
	 public static void main(String[] args) {
		    try {
		    	mergeAllFiles(run.dir+"/new");
		    } catch (IOException e) {
		      System.err.println(e.getMessage());
		    }
		  }

}



