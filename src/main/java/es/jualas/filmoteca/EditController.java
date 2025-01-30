package es.jualas.filmoteca;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EditController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private TextField posterUrlTextField;
    @FXML
    private ImageView posterImageView;
    @FXML
    private Button addMovieButton;
    @FXML
    private Label ratingValueLabel; // Añadir esta línea

    private Pelicula pelicula;

    @FXML
    public void initialize() {
        // Inicializar el controlador si es necesario
        ratingSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ratingValueLabel.setText(String.format("%.1f", newValue.floatValue()));
        });
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
        if (pelicula.getId() != 0) {
            titleTextField.setText(pelicula.getTitle());
            yearTextField.setText(String.valueOf(pelicula.getYear()));
            descriptionTextArea.setText(pelicula.getDescription());
            ratingSlider.setValue(pelicula.getRating());
            posterUrlTextField.setText(pelicula.getPoster());
            posterImageView.setImage(new Image(pelicula.getPoster()));
            ratingValueLabel.setText(String.format("%.1f", pelicula.getRating())); // Añadir esta línea
        } else {
            titleTextField.clear();
            yearTextField.clear();
            descriptionTextArea.clear();
            ratingSlider.setValue(0);
            posterUrlTextField.clear();
            posterImageView.setImage(null);
            ratingValueLabel.setText("0.0"); // Añadir esta línea
        }
    }

    @FXML
    protected void onPosterUrlEntered() {
        // Actualizar la imagen del póster cuando se introduce una URL
        String url = posterUrlTextField.getText();
        if (url != null && !url.isEmpty()) {
            Image image = new Image(url);
            posterImageView.setImage(image);
        }
    }

    @FXML
    protected void onAcceptClick() {
        // Verificar que todos los campos estén completos
        if (titleTextField.getText().isEmpty() ||
            yearTextField.getText().isEmpty() ||
            descriptionTextArea.getText().isEmpty() ||
            posterUrlTextField.getText().isEmpty()) {

            // Mostrar una alerta de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Que Pajaro eres, como buscas romper la aplicacion.\nAnda completa todos los campos antes de guardar.");
            alert.showAndWait();
            return;
        }

        // Validar el año
        int year;
        try {
            year = Integer.parseInt(yearTextField.getText());
            int currentYear = java.time.Year.now().getValue();
            if (year < 1900 || year > currentYear) {
                throw new NumberFormatException("Año fuera de rango");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Año inválido");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, introduce un año válido entre 1900 y " + java.time.Year.now().getValue() + ".");
            alert.showAndWait();
            return;
        }

        // Validar la URL del póster
        try {
            new java.net.URL(posterUrlTextField.getText());
        } catch (java.net.MalformedURLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("URL inválida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, introduce una URL válida para el póster.");
            alert.showAndWait();
            return;
        }

        // Mostrar una alerta de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de cambios");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de los cambios realizados?");

        // Esperar la respuesta del usuario
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Actualizar los datos de la película
                pelicula.setTitle(titleTextField.getText());
                pelicula.setYear(year);
                pelicula.setDescription(descriptionTextArea.getText());
                pelicula.setRating((float) ratingSlider.getValue());
                pelicula.setPoster(posterUrlTextField.getText());

                // Agregar la nueva película a la lista si es nueva
                if (pelicula.getId() == 0) {
                    pelicula.setId(DatosFilmoteca.getListaPeliculas().size() + 1); // Asignar un nuevo ID
                    DatosFilmoteca.getListaPeliculas().add(pelicula);
                }

                // Guardar la lista de películas actualizada en el archivo JSON
                DatosFilmoteca.getInstancia().guardarDatos();

                // Cerrar la ventana
                Stage stage = (Stage) addMovieButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    protected void onCancelClick() {
        // Cerrar la ventana sin guardar los cambios
        Stage stage = (Stage) addMovieButton.getScene().getWindow();
        stage.close();
    }


}