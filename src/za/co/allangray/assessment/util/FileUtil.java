package za.co.allangray.assessment.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author radingwanes
 *
 * Singleton file manager class to read data from uploaded files
 *  
 */
public class FileUtil {
	
	private static FileUtil instance;
	
	private FileUtil(){}
	
	public static synchronized FileUtil getInstance () {
		
		if (instance == null) {
			instance = new FileUtil();
		}
		return instance;
	}
	


	public List<String> readFile (String file) throws FileNotFoundException, IOException {
		List<String> fileLines = new ArrayList<>();
		
                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                    for(String line; (line = br.readLine()) != null; ) {
                        fileLines.add(line);
                    }
                }
		
		return fileLines;
	}

}
