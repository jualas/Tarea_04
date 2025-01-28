package es.jualas.filmoteca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pelicula {
    // Propiedad para el ID de la película
    private final IntegerProperty id = new SimpleIntegerProperty();
    // Propiedad para el título de la película
    private final StringProperty title = new SimpleStringProperty();
    // Propiedad para el año de la película
    private final IntegerProperty year = new SimpleIntegerProperty();
    // Propiedad para la descripción de la película
    private final StringProperty description = new SimpleStringProperty();
    // Propiedad para la calificación de la película
    private final FloatProperty rating = new SimpleFloatProperty();
    // Propiedad para la URL del póster de la película
    private final StringProperty poster = new SimpleStringProperty();

    // Constructor por defecto
    public Pelicula() {
    }

    // Constructor con parámetros
    public Pelicula(int id, String title, int year, String description, float rating, String poster) {
        this.id.set(id);
        this.title.set(title);
        this.year.set(year);
        this.description.set(description);
        this.rating.set(rating);
        this.poster.set(poster);
    }

    // Getter para el ID de la película
    public int getId() {
        return id.get();
    }

    // Getter de propiedad para el ID de la película
    public IntegerProperty idProperty() {
        return id;
    }

    // Setter privado para el ID de la película
    void setId(int id) {
        this.id.set(id);
    }

    // Getter para el título de la película
    public String getTitle() {
        return title.get();
    }

    // Getter de propiedad para el título de la película
    public StringProperty titleProperty() {
        return title;
    }

    // Setter para el título de la película
    public void setTitle(String title) {
        this.title.set(title);
    }

    // Getter para el año de la película
    public int getYear() {
        return year.get();
    }

    // Getter de propiedad para el año de la película
    public IntegerProperty yearProperty() {
        return year;
    }

    // Setter para el año de la película
    public void setYear(int year) {
        this.year.set(year);
    }

    // Getter para la descripción de la película
    public String getDescription() {
        return description.get();
    }

    // Getter de propiedad para la descripción de la película
    public StringProperty descriptionProperty() {
        return description;
    }

    // Setter para la descripción de la película
    public void setDescription(String description) {
        this.description.set(description);
    }

    // Getter para la calificación de la película
    public float getRating() {
        return rating.get();
    }

    // Getter de propiedad para la calificación de la película
    public FloatProperty ratingProperty() {
        return rating;
    }

    // Setter para la calificación de la película
    public void setRating(float rating) {
        this.rating.set(rating);
    }

    // Getter para la URL del póster de la película
    public String getPoster() {
        return poster.get();
    }

    // Getter de propiedad para la URL del póster de la película
    public StringProperty posterProperty() {
        return poster;
    }

    // Setter para la URL del póster de la película
    public void setPoster(String poster) {
        this.poster.set(poster);
    }

    // Sobrescribir el método toString para devolver una representación en cadena de la película
    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", title=" + title +
                ", year=" + year +
                ", description=" + description +
                ", rating=" + rating +
                ", poster=" + poster +
                '}';
    }
}