package es.guillearana.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.util.ArrayList;

/**
 * Controlador de la interfaz de usuario de la encuesta.
 * Esta clase maneja la lógica detrás de los elementos de la interfaz y la validación de los datos ingresados por el usuario.
 */
public class EncuestaController {

    @FXML
    private Button BtAceptar; // Botón para aceptar la encuesta

    @FXML
    private Button BtCancelar; // Botón para cancelar la encuesta

    @FXML
    private CheckBox CheBDeporte; // Casilla de verificación para indicar si el usuario practica deportes

    @FXML
    private ChoiceBox<String> ChoBEdad; // Cuadro de selección para elegir la edad

    @FXML
    private ListView<String> LVCual; // Lista para seleccionar deportes

    @FXML
    private Label LbCine; // Etiqueta para cine

    @FXML
    private Label LbCompras; // Etiqueta para compras

    @FXML
    private Label LbCual; // Etiqueta para deportes seleccionados

    @FXML
    private Label LbEdad; // Etiqueta para edad

    @FXML
    private Label LbEncuesta; // Etiqueta para la encuesta

    @FXML
    private Label LbGrado; // Etiqueta para grado

    @FXML
    private Label LbHermanos; // Etiqueta para el número de hermanos

    @FXML
    private Label LbProfesion; // Etiqueta para profesión

    @FXML
    private Label LbSexo; // Etiqueta para sexo

    @FXML
    private Label LbTelevision; // Etiqueta para televisión

    @FXML
    private RadioButton RdBnHombre; // Botón de opción para seleccionar hombre

    @FXML
    private RadioButton RdBnMujer; // Botón de opción para seleccionar mujer

    @FXML
    private RadioButton RdBnOtro; // Botón de opción para seleccionar otro género

    @FXML
    private Slider SdCine; // Control deslizante para calificar el cine

    @FXML
    private Slider SdCompras; // Control deslizante para calificar las compras

    @FXML
    private Slider SdTelevision; // Control deslizante para calificar la televisión

    @FXML
    private TextArea TAHermanos; // Área de texto para el número de hermanos

    @FXML
    private TextArea TAProfesion; // Área de texto para la profesión

    @FXML
    private ToggleGroup grupo; // Grupo de botones de opción para el sexo

    /**
     * Método que se llama al inicializar el controlador.
     * Configura las opciones de la interfaz de usuario.
     */
    @FXML
    public void initialize() {
        ChoBEdad.getItems().addAll(
                "Menores de 18",
                "Entre 18 y 30",
                "Entre 31 y 50",
                "Entre 51 y 70",
                "Mayores de 70"
        );

        // Inicializar el ListView con las opciones de deportes
        LVCual.setItems(FXCollections.observableArrayList(
                "Tenis",
                "Fútbol",
                "Baloncesto",
                "Natación",
                "Ciclismo",
                "Otro"
        ));

        // Configurar el ListView para permitir selección múltiple
        LVCual.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Desactivar el ListView al inicio
        LVCual.setDisable(true);

        // Listener para el CheckBox
        CheBDeporte.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Activar o desactivar el ListView según el estado del CheckBox
            LVCual.setDisable(!newValue);
        });
    }

    /**
     * Método que se ejecuta al presionar el botón "Aceptar".
     * Valida los datos ingresados por el usuario y muestra un mensaje de información o error.
     *
     * @param event Evento que se genera al presionar el botón.
     */
    @FXML
    void aceptar(ActionEvent event) {
        String profesion = TAProfesion.getText().trim(); // Obtener el texto de la profesión
        String numeroHermanosStr = TAHermanos.getText().trim(); // Obtener el texto de los hermanos
        ObservableList<String> deportesSeleccionadosList = LVCual.getSelectionModel().getSelectedItems(); // Obtener deportes seleccionados
        String edadSeleccionada = (String) ChoBEdad.getValue(); // Obtener edad seleccionada
        String sexoSeleccionado = RdBnHombre.isSelected() ? "Hombre" : RdBnMujer.isSelected() ? "Mujer" : RdBnOtro.isSelected() ? "Otro" : ""; // Obtener sexo

        ArrayList<String> errores = validarDatos(profesion, numeroHermanosStr, deportesSeleccionadosList); // Validar los datos ingresados

        if (errores.isEmpty()) {
            // Crear el mensaje de información
            StringBuilder mensaje = new StringBuilder("Información de la encuesta:\n");
            mensaje.append("Edad: ").append(edadSeleccionada).append("\n");
            mensaje.append("Sexo: ").append(sexoSeleccionado).append("\n");
            mensaje.append("Número de hermanos: ").append(numeroHermanosStr).append("\n");
            mensaje.append("Profesión: ").append(profesion).append("\n");
            mensaje.append("Deportes practicados: ").append(deportesSeleccionadosList).append("\n");

            // Mostrar la información en un cuadro de diálogo de tipo INFORMATION
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Información de la Encuesta");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText(mensaje.toString());
            infoAlert.showAndWait();
        } else {
            mostrarErrores(errores); // Muestra los errores
        }
    }

    /**
     * Valida los datos ingresados por el usuario.
     *
     * @param profesion             La profesión ingresada por el usuario.
     * @param numeroHermanosStr    El número de hermanos ingresado por el usuario.
     * @param deportesSeleccionados La lista de deportes seleccionados.
     * @return Una lista de errores encontrados durante la validación.
     */
    private ArrayList<String> validarDatos(String profesion, String numeroHermanosStr, ObservableList<String> deportesSeleccionados) {
        ArrayList<String> errores = new ArrayList<>(); // Lista para almacenar los errores

        // Validación de campos
        if (profesion.isEmpty()) {
            errores.add("Hay que rellenar el campo profesión");
        }

        // Validar que el campo 'Número de hermanos' esté rellenado
        if (numeroHermanosStr.trim().isEmpty()) {
            errores.add("Hay que rellenar el campo número de hermanos");
        }

        // Validar que se haya seleccionado al menos un deporte si la casilla de deportes está marcada
        if (CheBDeporte.isSelected() && deportesSeleccionados.isEmpty()) {
            errores.add("Tienes que indicar los deportes que utilizas");
        }

        return errores; // Devolver la lista de errores
    }

    /**
     * Muestra un cuadro de diálogo con los errores encontrados.
     *
     * @param errores La lista de errores a mostrar.
     */
    private void mostrarErrores(ArrayList<String> errores) {
        StringBuilder mensajeErrores = new StringBuilder();

        // Recorrer la lista de errores y añadir cada uno al mensaje
        for (String error : errores) {
            mensajeErrores.append(error).append("\n");
        }

        // Crear un Alert de tipo ERROR para mostrar los errores
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errores de validación");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(mensajeErrores.toString());

        // Mostrar los errores
        errorAlert.showAndWait();
    }


    /**
     * Método que se ejecuta al presionar el botón "Cancelar".
     * Cierra la aplicación.
     *
     * @param event Evento que se genera al presionar el botón.
     */
    @FXML
    void cancelar(ActionEvent event) {
        System.exit(0); // Cierra la aplicación
    }

    /**
     * Método que se ejecuta al interactuar con el checkbox de deportes.
     * Actualmente no implementa ninguna funcionalidad.
     *
     * @param event Evento que se genera al interactuar con el checkbox.
     */
    @FXML
    void deporte(ActionEvent event) {
        // Método aún no implementado
    }
}
