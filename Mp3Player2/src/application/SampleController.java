package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController implements Initializable{
	@FXML
	private Pane pane;
	@FXML
	private Label titleLabel;
	@FXML
	private Button playButton,pauseButton,resetButton,previousButton,nextButton,addFavButton,removeFavButton,showFavButton;
	@FXML
	private ComboBox<String> speedBox;
	@FXML
	private Slider volumeSlider;
	@FXML
	private ProgressBar songProgressBar;
	
	private Media media;
	private MediaPlayer mediaPlayer;
	
	private File directory;
	private File[] files;
	
	private ArrayList<File> songs;
	
	private int songNumber;
	private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
	private Timer timer;
	private TimerTask task;
	private boolean running;
	
	//oi epomenes 2 seires einai gia thn lista agaphmenwn
	@FXML
	public ListView<String> songList;

    // Η λίστα αγαπημένων (κοινή για όλη την εφαρμογή)
    public static ObservableList<String> favourites = FXCollections.observableArrayList();
    
    private Stage favouritesStage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		songs = new ArrayList<File>();
		directory = new File("music");
		files = directory.listFiles(); // pairnei ola ta arxeia apo ton directory
		
		if(files != null) {
			for(File file : files) {
				
				songs.add(file);
				System.out.println(file);
			}
		}
		media = new Media(songs.get(songNumber).toURI().toString());	
		mediaPlayer = new 	MediaPlayer(media); 
		titleLabel.setText(songs.get(songNumber).getName());
		
		
		setupTitleGlow();
        
		
		for(int i=0; i < speeds.length; i++) {
			speedBox.getItems().add(Integer.toString(speeds[i]));
		}
		speedBox.setOnAction(this::changeSpeed); // to setOnAction orizei thn methodo changeSpeed na ektelestei otan oloklhrwthei mia energeia opws na to epilexoume me to pontiki 
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				mediaPlayer.setVolume(volumeSlider.getValue()*0.01);// ftiaxnoume thn mpara volume
				
			}
			
		});
		songProgressBar.setStyle("--fx-accent: #00FF00;");
		
		
	
		if (songList != null) {
		    songList.getItems().addAll(
		        "/Mp3Player2/music/SNIK feat. SLOGAN - SHOWTIME (Official Music Video) [FhfGZGTv7zo].mp3",
		        "/Mp3Player2/music/SNIK ft. Noizy - GANGO (Official Music Video).mp3",
		        "/Mp3Player2/music/Ypo - Maradona.mp3",
		        "/Mp3Player2/music/Γιάννης Πλούταρχος - Κοίταξέ Με feat. Diana - Official Audio Release.mp3"
		    );
		}
        
		
	}
	public void playMedia() {
		beginTimer();
		changeSpeed(null);
		mediaPlayer.setVolume(volumeSlider.getValue()*0.01);// ftiaxnoume thn mpara volume
		mediaPlayer.play();
	}
	public void pauseMedia() {
		mediaPlayer.pause();
		cancelTimer();
	}
	public void resetMedia() {
		mediaPlayer.seek(Duration.seconds(0));
		songProgressBar.setProgress(0);
	}
	public void previousMedia() {
		if(songNumber > 0 ) {
			songNumber--;
			mediaPlayer.stop();
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());	
			mediaPlayer = new 	MediaPlayer(media); 
			titleLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
			
		}else {
			songNumber=songs.size() - 1;
			mediaPlayer.stop();
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());	
			mediaPlayer = new 	MediaPlayer(media); 
			titleLabel.setText(songs.get(songNumber).getName());
			playMedia();
		}
	}
	public void nextMedia() {
		if(songNumber < songs.size() -1 ) {
			songNumber++;
			mediaPlayer.stop();
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());	
			mediaPlayer = new 	MediaPlayer(media); 
			titleLabel.setText(songs.get(songNumber).getName());
			
			playMedia();
			
		}else {
			songNumber=0;
			mediaPlayer.stop();
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());	
			mediaPlayer = new 	MediaPlayer(media); 
			titleLabel.setText(songs.get(songNumber).getName());
			playMedia();
		}
	}
	public void changeSpeed(ActionEvent event) {
		
		if(speedBox.getValue()==null) {
			mediaPlayer.setRate(1);
		}else {
			mediaPlayer.setRate(Integer.parseInt(speedBox.getValue()) * 0.01);
		}
	}
	public void beginTimer() {
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				running = true;
				double current = mediaPlayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				System.out.println(current/end);
				songProgressBar.setProgress(current/end);
				
				if((current/end)==1 ) {
					cancelTimer();
				}
			}
			
		};
		timer.schedule(task, 0, 1000);
	}
	public void cancelTimer() {
		running=false;
		timer.cancel();
	}
	 private void setupTitleGlow() {
	        // αρχικό effect
	        DropShadow ds = new DropShadow();
	        ds.setOffsetX(0);
	        ds.setOffsetY(0);
	        ds.setRadius(12);
	        ds.setColor(Color.rgb(79, 195, 247, 0.5)); // αρχικό γαλάζιο με alpha
	        titleLabel.setEffect(ds);

	        // Timeline για breathing glow: radius και opacity
	        Timeline glowTimeline = new Timeline(
	            new KeyFrame(Duration.ZERO,
	                new KeyValue(ds.radiusProperty(), 8),
	                new KeyValue(ds.colorProperty(), Color.rgb(79,195,247, 0.35))
	            ),
	            new KeyFrame(Duration.seconds(1.6),
	                new KeyValue(ds.radiusProperty(), 22),
	                new KeyValue(ds.colorProperty(), Color.rgb(79,195,247, 0.95))
	            ),
	            new KeyFrame(Duration.seconds(3.2),
	                new KeyValue(ds.radiusProperty(), 8),
	                new KeyValue(ds.colorProperty(), Color.rgb(79,195,247, 0.35))
	            )
	        );
	        glowTimeline.setCycleCount(Animation.INDEFINITE);
	        glowTimeline.setAutoReverse(true);
	        glowTimeline.play();
	   }

	   
	    @FXML
	    private void addToFavourites() {
	        if (songList == null) {
	            System.out.println("songList ΔΕΝ έχει οριστεί στο FXML");
	            return;
	        }

	        String selected = songList.getSelectionModel().getSelectedItem();

	        if (selected != null && !favourites.contains(selected)) {
	            favourites.add(selected);
	            System.out.println("Προστέθηκε στα αγαπημένα: " + selected);
	        }
	    }
	    @FXML
	    private void removeFromFavourites() {
	        if (songList == null) {
	            System.out.println("songList ΔΕΝ έχει οριστεί στο FXML");
	            return;
	        }

	        String selected = songList.getSelectionModel().getSelectedItem();

	        if (selected != null) {
	            favourites.remove(selected);
	            System.out.println("Αφαιρέθηκε από τα αγαπημένα: " + selected);
	        }
	    }
	    @FXML
	    
	    public void openFavouritesWindow() {
	        
	        //  ΕΛΕΓΧΟΣ: Αν το Stage υπάρχει και είναι ανοιχτό, απλώς το φέρνουμε μπροστά.
	        if (favouritesStage != null && favouritesStage.isShowing()) {
	            favouritesStage.toFront();
	            return; // Τέλος
	        }
	        
	        //  Αν δεν υπάρχει ή έκλεισε, το δημιουργούμε
	        try {
	            URL fxmlUrl = getClass().getResource("Favourites.fxml"); 

	            if (fxmlUrl == null) {
	                System.err.println("Σφάλμα: Το αρχείο FXML 'Favourites.fxml' δεν βρέθηκε.");
	                return;
	            }

	            FXMLLoader loader = new FXMLLoader(fxmlUrl);
	            Parent root = loader.load();
	            
	            // ΔΗΜΙΟΥΡΓΙΑ ΤΟΥ STAGE
	            favouritesStage = new Stage(); // Χρησιμοποιούμε το πεδίο
	            favouritesStage.setTitle("Αγαπημένα");
	            favouritesStage.initModality(Modality.NONE); 
	            
	            Scene scene = new Scene(root);
	            
	            // Φόρτωση του CSS 
	            String cssPath = getClass().getResource("application.css").toExternalForm();
	            scene.getStylesheets().add(cssPath);
	            
	            favouritesStage.setScene(scene);
	            favouritesStage.show();

	        } catch (IOException e) {
	            System.err.println("Σφάλμα φόρτωσης του favourites.fxml: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    	

}
