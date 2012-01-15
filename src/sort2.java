import java.io.*;
import java.util.*;

/**
 *  Sorts the lines in a file alphabetically.
 */
public class sort2
{
  /**
   *  Contains all lines in a file.
   */
  private List lines = new ArrayList ();

  /**
   *  Test method for this class
   */
  public static void main (String[] args)
  {
//    if (args.length == 2)
//    {
      sort2 fs = new sort2 ();
      try
      {
                long t1 = System.currentTimeMillis();
        fs.readFrom (run.dir+"/new"+Image1.name+"unsorted.txt");
        fs.sort ();
        fs.writeTo (run.dir+Image1.name+"all.txt");
                long t2 = System.currentTimeMillis();
                System.out.println("total: " + (t2 - t1) + "ms");
      }
      catch (IOException ex)
      {
        System.err.println ("Error: " + ex);
      }
 //   }
//    else
//    {
//      System.err.println ("Usage: FileSorter  ");
//      System.err.println (" - sorts the lines in a text file");
//    }
  }

  /**
   *  Sort the {@link #lines} list.
   */
  public void sort ()
  {
     Collections.sort (lines);
  }

  /**
   *  Read all lines from a file, and store them in {@link #lines}.
   */
  public void readFrom (String filename) throws IOException
  {
    BufferedReader br = new BufferedReader (new FileReader (filename));
    String s;
    do
    {
      s = br.readLine ();
      if (s != null)
      {
        lines.add (s);
      }
    }
    while (s != null);
  }

  /**
   *  Write all elements in {@link #lines} to a file.
   */
  public void writeTo (String filename) throws IOException
  {
    FileOutputStream fos = new FileOutputStream (filename);
    for (int i = 0 ; i < lines.size() ; i++)
    {
      fos.write (((String) lines.get(i)).getBytes ());
      fos.write ((byte) '\r');
      fos.write ((byte) '\n');
    }
    fos.close ();
  }
}