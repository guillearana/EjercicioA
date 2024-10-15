package es.guillearana.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EncuestaController extends Component {

    @FXML
    private Button BtAceptar;

    @FXML
    private Button BtCancelar;

    @FXML
    private CheckBox CheBDeporte;

    @FXML
    private ChoiceBox<String> ChoBEdad;

    @FXML
    private ListView<String> LVCual;

    @FXML
    private Label LbCine;

    @FXML
    private Label LbCompras;

    @FXML
    private Label LbCual;

    @FXML
    private Label LbEdad;

    @FXML
    private Label LbEncuesta;

    @FXML
    private Label LbGrado;

    @FXML
    private Label LbHermanos;

    @FXML
    private Label LbProfesion;

    @FXML
    private Label LbSexo;

    @FXML
    private Label LbTelevision;

    @FXML
    private RadioButton RdBnHombre;

    @FXML
    private RadioButton RdBnMujer;

    @FXML
    private RadioButton RdBnOtro;

    @FXML
    private Slider SdCine;

    @FXML
    private Slider SdCompras;

    @FXML
    private Slider SdTelevision;

    @FXML
    private TextArea TAHermanos;

    @FXML
    private TextArea TAProfesion;

    @FXML
    private ToggleGroup grupo;

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

    @FXML
    void aceptar(ActionEvent event) {
        String profesion = TAProfesion.getText().trim(); // Obtener el texto de la profesión
        String numeroHermanosStr = TAHermanos.getText().trim(); // Obtener el texto de los hermanos
        String deportesSeleccionados = LVCual.getSelectionModel().getSelectedItems().toString(); // Obtener deportes seleccionados
        String edadSeleccionada = (String) ChoBEdad.getValue(); // Obtener edad seleccionada
        String sexoSeleccionado = RdBnHombre.isSelected() ? "Hombre" : RdBnMujer.isSelected() ? "Mujer" : RdBnOtro.isSelected() ? "Otro" : ""; // Obtener sexo

        ArrayList<Object> errores = new ArrayList<>(); // Lista para almacenar los errores

        // Validación de campos
        if (profesion.isEmpty()) {
            errores.add("El campo 'Profesión' no puede estar vacío.");
        }

        if (numeroHermanosStr.isEmpty()) {
            errores.add("El campo 'Número de hermanos' no puede estar vacío.");
        } else {
            try {
                Integer.parseInt(numeroHermanosStr); // Verificar que el número de hermanos es numérico
            } catch (NumberFormatException e) {
                errores.add("El campo 'Número de hermanos' debe ser un número.");
            }
        }

        if (errores.isEmpty()) {
            StringBuilder mensaje = new StringBuilder("Información de la encuesta:\n");
            mensaje.append("Edad: ").append(edadSeleccionada).append("\n");
            mensaje.append("Sexo: ").append(sexoSeleccionado).append("\n");
            mensaje.append("Número de hermanos: ").append(numeroHermanosStr).append("\n");
            mensaje.append("Profesión: ").append(profesion).append("\n");
            mensaje.append("Deportes practicados: ").append(deportesSeleccionados).append("\n");

            // Mostrar la información en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Información de la Encuesta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar todos los errores a la vez
            JOptionPane.showMessageDialog(null, String.join("\n", errores), "Errores de validación", JOptionPane.ERROR_MESSAGE);
        }
    }



    @FXML
    void cancelar(ActionEvent event) {
        System.exit(0); // Cierra la aplicación
    }

    @FXML
    void deporte(ActionEvent event) {

    }

}
