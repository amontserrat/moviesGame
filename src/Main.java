
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
	private FileUtilities fileUtilities;
	private Game game;
	
	
	public Main() {
		//super();
		this.fileUtilities = new FileUtilities();
		this.game = new Game();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Main app = new Main();
		app.run();
	}
	
	private void run() {
		String fileName = "../src/movies.txt";
		List<String> movieList = null;
		
		try {
			movieList = fileUtilities.readFile(fileName);
		} catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println("Some Other Exception");
        }
		
		if (movieList != null) {
			Scanner s = new Scanner(System.in);
			char play;
			
			do {
				String randomMovie = game.getMovie(movieList);
				
				game.runGame(randomMovie);
				System.out.println("\n\nPlay again? (Y/N)\n");
				play = s.next().charAt(0);
				game.clearScreen();
			} while (Character.toUpperCase(play) == 'Y');
			s.close();
		}
		
		
	}
}
