import java.io.*;
import java.util.*;
import java.lang.Object;



public class sort {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader
        (new FileReader(run.dir+Image1.name+"all.txt"));
//        (new FileReader("/Users/hal/Desktop/Mark/033117_P_Op1_S Teleop.txt"));
//       (new FileReader("/Users/hal/Desktop/Mark/033117_P_Op1_S Display to Marked.txt"));
        Map<String, String> map=new TreeMap<String, String>();
        String line="";
        while((line=reader.readLine())!=null){
                map.put(getField(line),line);
        }
        reader.close();
        
        int p=0;
        for(String val : map.values()){

                System.out.println(val);
                if(p>300){
    //          wait(1);
                p=0;
                }
                p++;
        }
    }

    private static String getField(String line) {
        return line.split("     ")[0];
    }
    
    public static void wait (int n){
        long t0,t1;
        t0=System.currentTimeMillis();
        do{
            t1=System.currentTimeMillis();
        }
        while (t1-t0<1000);
}
    
    
}