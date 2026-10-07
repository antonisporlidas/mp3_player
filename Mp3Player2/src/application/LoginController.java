package application;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    // to username kai to passwword einai ayta
    private static final String VALID_USER = "user";
    private static final String VALID_PASS = "pass";

    @FXML
    public void handleLoginButton(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(VALID_USER) && password.equals(VALID_PASS)) {
            // Σύνδεση Επιτυχής
            try {
                // Βρες το FXML του κύριου player (Sample.fxml)
                URL mainFxmlUrl = getClass().getResource("Sample.fxml");
                if (mainFxmlUrl == null) {
                    errorLabel.setText("Σφάλμα: Το κύριο FXML (Sample.fxml) δεν βρέθηκε.");
                    return;
                }
                
                Parent root = FXMLLoader.load(mainFxmlUrl);
                Scene scene = new Scene(root);
                
                // Φόρτωσε το CSS στο νέο Scene
                String cssPath = getClass().getResource("application.css").toExternalForm();
                scene.getStylesheets().add(cssPath);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("MP3 Player");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                errorLabel.setText("Σφάλμα φόρτωσης του κύριου παραθύρου.");
            }
        } else {
            // Σφάλμα σύνδεσης
            errorLabel.setText("Λάθος όνομα χρήστη ή κωδικός.");
        }
    }
}