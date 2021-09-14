package risk.controller;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundController {
	
	private MediaPlayer mediaPlayer;
	private Media h;
	
	public SoundController() {
		
	}
	
	
	public void gameMusic() {
		playInLoop("src/risk/view/songs/gameTheme.mp3");
	}
	
	public void battleMusic() {
		playInLoop("src/risk/view/songs/battleTheme.mp3");
	}
	
	public void tradeSound() {
		playOneTime("src/risk/view/songs/tradeSound.mp3");
	}
	
	public void conqueredSound() {
		playOneTime("src/risk/view/songs/conqueredSound.mp3");
	}
	
	public void displacementSound() {
		playOneTime("src/risk/view/songs/displacementSound.mp3");
	}
	
	public void cardSound() {
		playOneTime("src/risk/view/songs/cardSound.mp3");
	}
	
	public void stopMusic() {
		mediaPlayer.stop();
	}
	
	private void playOneTime(String source) {
		h = new Media(Paths.get(source).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}
	
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
