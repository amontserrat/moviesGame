
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is aimed to contain helper methods to reading files.
 * 
 * @author albert
 *
 */
public class FileUtilities {
	
	/**
	 * This method reads the passed file and returns a List of movies. Otherwise it will throw an IOException.
	 * 
	 * @throws IOException 
	 */
	public List<String> readFile(String file) throws IOException {
		List<String> movies = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
	    while (br.ready()) {
	        movies.add(br.readLine());
	    }
	    br.close();
	    
	    return movies;
	}

}
