/*package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;



import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}*/
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            //  Φόρτωσε ΠΡΩΤΑ το Login.fxml
            URL loginFxmlUrl = getClass().getResource("Login.fxml");
            if (loginFxmlUrl == null) {
                System.err.println("Δεν βρέθηκε το Login.fxml. Η εφαρμογή δεν μπορεί να ξεκινήσει.");
                return;
            }
            
            Parent root = FXMLLoader.load(loginFxmlUrl);
            
            Scene scene = new Scene(root);
            
            //  Φόρτωσε το CSS για το Login παράθυρο
            URL cssResource = getClass().getResource("application.css");
            if (cssResource != null) {
                scene.getStylesheets().add(cssResource.toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle("Εφαρμογή Σύνδεσης");
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
