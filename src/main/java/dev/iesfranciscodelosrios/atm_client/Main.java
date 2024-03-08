package dev.iesfranciscodelosrios.atm_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    private static Scene scene;
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        scene=new Scene(loadFXML("Login"));
        this.stage.setScene(scene);
        this.stage.show();
        stage.setResizable(false);
        stage.setTitle("Santander");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("photo/logo.png"))));
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();

    }
    public static void main(String[] args) {
        launch(args);
    }
}