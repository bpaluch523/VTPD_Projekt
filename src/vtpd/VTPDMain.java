package vtpd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Bartek
 */
public class VTPDMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        stage.setTitle("VAG Tuning Parts Database");
        stage.getIcons().add(new Image("css/vag.png"));
        stage.setResizable(false);
        stage.setMaxHeight(540.0);
        stage.setMaxWidth(890.0);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}