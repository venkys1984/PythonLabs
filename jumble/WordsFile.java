//this file is used to create the "sorted" version of the dictionary file
//It takes one argument : the dictionary file on your linux machine eg. in /usr/share/dict/
//It outputs each word sorted by its characters followed by the original word
//Sort this output to get your reference file for the jumble words app

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class WordsFile
{
  public static void main(String[] args)
  {
    try
    {
      FileInputStream dict = new FileInputStream(args[0]);
      BufferedReader br = new BufferedReader(new InputStreamReader(dict));
      
      String line;
      while((line = br.readLine()) != null)
      {
		char[] letters = line.toCharArray();
		Arrays.sort(letters);
		String sorted = new String(letters);
		System.out.println(sorted + "," + line);  
	  }
    }
    catch(Exception e)
    {
	  e.printStackTrace();                	
	}
  }
}
