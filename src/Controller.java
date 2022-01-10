import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    public Button filmButton;
    public Button SerieButton;

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

