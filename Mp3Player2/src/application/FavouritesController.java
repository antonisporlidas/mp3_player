package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable; // Πρόσθεσε αυτό το import
import javafx.scene.control.ListView;


public class FavouritesController implements Initializable { 

    @FXML
    private ListView<String> favoritesList;

    
    @Override 
    public void initialize(URL location, ResourceBundle resources) {
       
        
       
        favoritesList.setItems(SampleController.favourites);
        
        
    }
}