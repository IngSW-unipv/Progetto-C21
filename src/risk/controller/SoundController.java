package risk.controller;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * @author utente
 *
 */
public class SoundController {
	
	/**
	 * MediaPlayer
	 */
	private MediaPlayer mediaPlayer;
	
	/**
	 * Song to play
	 */
	private Media h;
	
	
	
	/**
	 * Method that plays game music in loop
	 */
	public void gameMusic() {
		playInLoop("src/risk/view/songs/gameTheme.mp3");
	}
	
	/**
	 * Method that plays battle music in loop
	 */
	public void battleMusic() {
		playInLoop("src/risk/view/songs/battleTheme.mp3");
	}
	
	/**
	 * Method that plays trade music
	 */
	public void tradeSound() {
		playOneTime("src/risk/view/songs/tradeSound.mp3");
	}
	
	/**
	 * Method that plays conquered music
	 */
	public void conqueredSound() {
		playOneTime("src/risk/view/songs/conqueredSound.mp3");
	}
	
	/**
	 * Method that plays displacement music
	 */
	public void displacementSound() {
		playOneTime("src/risk/view/songs/displacementSound.mp3");
	}
	
	/**
	 * Method that plays card sound music
	 */
	public void cardSound() {
		playOneTime("src/risk/view/songs/cardSound.mp3");
	}
	
	public void winningSound() {
		playInLoop("src/risk/view/songs/winningSound.mp3");
	}
	
	/**
	 *
	 * Method that stops music
	 */
	public void stopMusic() {
		mediaPlayer.stop();
	}
	
	
	/**
	 * Method that plays song one time
	 * @param source is the path of song file
	 */
	private void playOneTime(String source) {
		h = new Media(Paths.get(source).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}
	
	/**
	 * Method that plays song in loop
	 * @param source is the path of song file
	 */
	private void playInLoop(String source) {
		h = new Media(Paths.get(source).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		 mediaPlayer.play();
	}
	
	
}
