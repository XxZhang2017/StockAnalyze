import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;
public class TestRegular {
	
	public static void main(String args[]) throws IOException{
		
		DecimalFormat df = new DecimalFormat("0.000000"); 
	
		
		String s="^[-+]?[0-9]+\\.[0-9]+$|^[-+]?[0-9]+$";
		Pattern p=Pattern.compile(s);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	         
	    String line=br.readLine();
	    String[] num=line.split(" ");
	    
	    for(int i=0;i<num.length;i++) {
//only format string; if parse to double, format does not work;	    	
	      	Matcher m=p.matcher(num[i]);
	      	if(m.matches()) {
	      		double db=Double.parseDouble(num[i]);
	      		String formatted=df.format(db);
	      		System.out.println(formatted);
	      	}
	    }
 
               
	}
}
