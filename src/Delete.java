import java.io.File;

public class Delete {
  public static void main(String[] args) {
    String fileName =run.dir+Image1.name+"unsorted.txt"; 
    File f = new File(fileName);
    f.delete();
    String directoryName=run.dir+"/new";
    // A File object to represent the filename
    File directory = new File(directoryName);
    File[] files = directory.listFiles();

    for (File file : files){    

    	boolean success = file.delete();

    if (!success)
      throw new IllegalArgumentException("Delete: deletion failed");
  }
  }
}