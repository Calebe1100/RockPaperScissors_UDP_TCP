import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppClient extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout.fxml"));
        Parent root = fxmlLoader.load();
        Scene screen = new Scene(root);
        primaryStage.setTitle("Tela básica JavaFX");
        primaryStage.setScene(screen);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}