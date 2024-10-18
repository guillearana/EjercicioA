package es.guillearana.ejercicioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación de encuesta.
 * Esta clase extiende Application y es responsable de iniciar la aplicación
 * y mostrar la interfaz de usuario.
 */
public class Encuesta extends Application {

    /**
     * Método principal que inicia la aplicación.
     * Carga el archivo FXML y configura la escena de la aplicación.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML que define la interfaz de usuario
        FXMLLoader fxmlLoader = new FXMLLoader(Encuesta.class.getResource("hello-view.fxml"));

        // Crear una nueva escena a partir del contenido cargado del FXML
        Scene scene = new Scene(fxmlLoader.load(), 550, 550);

        // Establecer el título de la ventana
        stage.setTitle("Encuesta");

        // Establecer dimensiones mínimas de la ventana
        stage.setMinWidth(550);
        stage.setMinHeight(550);

        // Establecer la escena en la ventana principal
        stage.setScene(scene);

        // Mostrar la ventana
        stage.show();
    }

    /**
     * Método principal que se utiliza para ejecutar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Lanzar la aplicación JavaFX
        launch();
    }
}
