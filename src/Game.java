
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * This Class contains the main logics of the program
 * @author albert
 *
 */
public class Game {

	/**
	 * This method selects a random movie from the passed list of movies
	 * @param movieList	- the List of movies
	 * @return	a random movie title
	 */
	public String getMovie(List<String> movieList) {
		int randomMovie;
		
		Random r = new Random();
		
		//Get a number between 0 and the size of the List of movies
		randomMovie = r.nextInt(movieList.size());
		
		return movieList.get(randomMovie);
	}
	
	
	/**
	 * This method replaces all characters from the passed String into underscores
	 * @param movieTitle - the string the be replaced with underscores
	 * @return	a new String composed of as many underscores as the passed movie title characters had
	 */
	private String getUnderscores(String movieTitle) {
		
		return movieTitle.replaceAll("[a-zA-Z]", "_");
	}

	
	/**
	 * Updates the movie title with the letters given so far
	 * @param movie - the movie title
	 * @param letters - List of the letters proposed so far
	 * @return the movie title filled with the letters proposed so far. The remaining letters
	 * are left as underscores
	 */
	private String updateAnswer(String movie, List<Character> letters) {
		int index;
		
		// get a string made up of as many underscores as characters of the movie
		StringBuilder updatedAnswer = new StringBuilder(getUnderscores(movie));
		
		for (char c : letters) {
			index = movie.indexOf(c);
			// replace underscores by the proposed letters so far
			updatedAnswer.setCharAt(index, c);
			while (index >= 0) {
				index = movie.indexOf(c, index+1);
				if (index >= 0) {
					updatedAnswer.setCharAt(index, c);
				}
			}
		}
		
		return updatedAnswer.toString();
	}

	
	/**
	 * Checks if the answer is completed, i.e. all letters of the movie title have been discovered.
	 * @param answer - the movie answer to be checked
	 * @return true is all letters have been discovered
	 */
	private boolean isCompleted(String answer) {
		for (int i = 0; i < answer.length(); i++) {
			if (answer.charAt(i) == '_') {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * 
	 * @param randomMovie
	 */
	public void runGame(String randomMovie) {
		boolean solved = false;
		int lifes = 10;
		Scanner s = new Scanner(System.in);
		String answer = this.getUnderscores(randomMovie);
		List<Character> letters = new ArrayList<Character>();
		char letter;
		
		while (!solved && lifes > 0) {
			
			runningOutput(lifes, answer);
			
			letter = s.next().charAt(0);
			
			if (randomMovie.indexOf(letter) >= 0) {
				letters.add(letter);
				answer = this.updateAnswer(randomMovie, letters);
				
				if (this.isCompleted(answer)) {
					solved = true;
				}
			} else {
				lifes -= 1;
			}
			clearScreen();
		}
		//s.close();
		
		if (solved) {
			System.out.println("You won!");
		} else {
			System.out.println("You lose...");
		}
	}
	
	
	// Display output from the running game
	private void runningOutput(int lifes, String answer) {
		System.out.println("You have " + lifes + " lifes left \n");
		System.out.println(answer.replaceAll(".", "$0 ") + "\n");				
		System.out.println("Enter a character: ");
	}

	
	// Clear the Console screen
	public void clearScreen() {
		System.out.print("\033[H\033[2J");  
	    System.out.flush(); 
	}
}
