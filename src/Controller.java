import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    public Button filmButton;
    public Button SerieButton;

    //When launching the program, a scene with two buttons opens.
    //One button for movies and one button for series
    //If one button gets clicked, another scene opens with all the movies or all series

    public void filmButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Film/filmscene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Film");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serieButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Serie/seriescene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Serie");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

