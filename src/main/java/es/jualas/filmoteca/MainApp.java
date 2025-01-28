package es.jualas.filmoteca;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import javafx.application.Application;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.image.Image;
        import javafx.stage.Stage;

        import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        public class MainApp extends Application {

            private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

            @Override
            public void start(Stage stage) throws IOException {
                // Cargar el archivo FXML y crear la escena
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                stage.setTitle("Filmoteca");

                // Establecer el icono de la aplicación
                InputStream iconStream = getClass().getResourceAsStream("/es/jualas/filmoteca/logo.png");
                if (iconStream != null) {
                    Image icon = new Image(iconStream);
                    stage.getIcons().add(icon);
                } else {
                    LOGGER.warning("Icono no encontrado: /es/jualas/filmoteca/logo.png");
                }

                // Configurar la escena y mostrar la ventana
                stage.setScene(scene);
                stage.setResizable(false); // Deshabilitar la redimensión de la ventana
                stage.show();
            }

            public static void main(String[] args) {
                // Iniciar la aplicación
                launch();
            }

            public void init() {
                // Cargar datos desde el archivo JSON al iniciar la aplicación
                LOGGER.info("Cargando datos desde fichero datos/peliculas.json");
                DatosFilmoteca datosFilmoteca = DatosFilmoteca.getInstancia();
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    // Leer la lista de películas desde el archivo JSON
                    List<Pelicula> lista = objectMapper.readValue(new File("datos/peliculas.json"),
                                            objectMapper.getTypeFactory().constructCollectionType(List.class, Pelicula.class));

                    // Establecer la lista de películas en DatosFilmoteca
                    DatosFilmoteca.getListaPeliculas().setAll(lista);
                } catch (IOException e) {
                    // Manejar errores de lectura del archivo JSON
                    LOGGER.log(Level.SEVERE, "ERROR al cargar los datos. La aplicación no puede iniciarse", e);
                    System.exit(1);
                }
            }

            public void stop() {
                // Guardar los datos en el archivo JSON al cerrar la aplicación
                ObservableList<Pelicula> listaPeliculas = DatosFilmoteca.getInstancia().getListaPeliculas();
                LOGGER.info(listaPeliculas.toString());
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    // Escribir la lista de películas en el archivo JSON
                    objectMapper.writeValue(new File("datos/peliculas.json"), listaPeliculas);
                } catch (IOException e) {
                    // Manejar errores de escritura del archivo JSON
                    LOGGER.log(Level.SEVERE, "ERROR no se ha podido guardar los datos de la aplicación", e);
                }
            }
        }