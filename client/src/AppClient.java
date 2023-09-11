import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppClient extends Application {
    Stage _primStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        _primStage = primaryStage;
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

    public void closeStage() {
        _primStage.close();
    }
}