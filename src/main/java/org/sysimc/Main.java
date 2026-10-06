package org.sysimc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sysimc.utils.PathFXML;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root =
                fxmlLoader.load(new FileInputStream(PathFXML.pathBase()+ "\\main-view.fxml"));
        Scene scene = new Scene(root, 800, 600
        );
        scene.getStylesheets().add(Paths.get(PathFXML.pathBase(), "button-glass.css").toUri().toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
