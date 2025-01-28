package es.jualas.filmoteca;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DatosFilmoteca {

    // Instancia única de la clase (patrón Singleton)
    private static DatosFilmoteca instancia = null;

    // Lista Observable de películas que se utilizará para mostrar las películas en la interfaz y notificará los cambios
    private static ObservableList<Pelicula> listaPeliculas = FXCollections.observableArrayList();

    // Constructor privado para evitar la creación de instancias adicionales
    private static void DatosFilmoteca() {
    }

    // Método para obtener la instancia única de la clase
    // Si la instancia no ha sido creada, se crea y se devuelve. Si ya existe, se devuelve la instancia ya creada.
    // Este método garantiza que solo haya una instancia de la clase DatosFilmoteca en la aplicación.
    public static DatosFilmoteca getInstancia() {
        if (instancia == null) {
            instancia = new DatosFilmoteca();
        }
        return instancia;
    }

    // Método para obtener la lista observable de películas
    public static ObservableList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    // Método para establecer la lista observable de películas
    public static void setListaPeliculas(ObservableList<Pelicula> listaPeliculas) {
        DatosFilmoteca.listaPeliculas = listaPeliculas;
    }

    // Método para guardar la lista de películas en un archivo JSON
    public void guardarDatos() {
        List<Pelicula> listaPeliculas = getListaPeliculas();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("datos/peliculas.json");

        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, listaPeliculas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}