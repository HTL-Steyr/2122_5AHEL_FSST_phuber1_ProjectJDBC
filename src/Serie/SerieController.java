package Serie;

import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class SerieController {
    public ListView<Kategorie> categoryListView;
    public ListView<Serie> serieListView;
    public TextField SerieIDLabel;
    public TextField SerieLabel;
    public TextField categoryLabel;
    public TextField staffelLabel;
    public TextField folgenlängeLabel;
    public TextField ratingLabel;
    public TextArea descriptionTextArea;
    public TextField folgenLabel;
    public TextField regisseurLabel;
    public TextField statusLabel;
    public Button bearbeitenButton;
    public Button hinzufügenButton;
    public Button löschenButton;
    public Button speichernButton;
    public ComboBox producerComboBox;
    public ComboBox actorComboBox;

    public void initialize() {

        categoryListView.setItems(Kategorie.loadAll());

    }


    public void categoryListViewClicked(MouseEvent mouseEvent) {
        Kategorie k = categoryListView.getSelectionModel().getSelectedItem();
        serieListView.setItems(k.loadSerie());
    }

    public void filmListViewClicked(MouseEvent mouseEvent) {
        Serie s = serieListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            SerieIDLabel.setText(String.valueOf(s.getSerieId()));
            SerieLabel.setText(s.getTitle());
            if (s.getCategory() != null) {
                categoryLabel.setText(String.valueOf(s.getCategory()));
            } else {
                categoryLabel.setText("Work in progress...");
            }
            staffelLabel.setText(String.valueOf(s.getSeasons()));
            ratingLabel.setText(s.getRating());
            descriptionTextArea.setText(s.getDescription());
            folgenLabel.setText(String.valueOf(s.getEpisodes()));
            folgenlängeLabel.setText(s.getLength());
            statusLabel.setText(s.getStatus());
        }
        Serie s2 = Serie.loadInfo(s.getSerieId());
        producerComboBox.setItems(Produzent.getAllByFilmId(s.getSerieId()));
        regisseurLabel.setText(s2.getRegisseur().getFirstName() + " " + s2.getRegisseur().getLastName());
        actorComboBox.setItems(Schauspieler.getAllByFilmId(s.getSerieId()));
    }

    public void bearbeitenButtonClicked(ActionEvent actionEvent) {
        Serie f = serieListView.getSelectionModel().getSelectedItem();
        f.setSerieId(Integer.parseInt(SerieIDLabel.getText()));
        f.setTitle(SerieLabel.getText());
        f.setLength(folgenlängeLabel.getText());
        f.setRating(ratingLabel.getText());
        f.setDescription(descriptionTextArea.getText());
        f.setStatus(statusLabel.getText());
        f.setEpisodes(Integer.parseInt(folgenLabel.getText()));
        f.setSeasons(Integer.parseInt(staffelLabel.getText()));
        f.setTypeId(1);
        f.bearbeitenSerie();
    }

    public void hinzufügenButtonClicked(ActionEvent actionEvent) {
        SerieIDLabel.setText("");
        SerieLabel.setText("");
        folgenlängeLabel.setText("");
        staffelLabel.setText("");
        folgenLabel.setText("");
        ratingLabel.setText("");
        categoryLabel.setText("");
        descriptionTextArea.setText("");
        statusLabel.setText("");
        actorComboBox.getItems().clear();
        producerComboBox.getItems().clear();
        regisseurLabel.setText("");
    }

    public void löschenButtonClicked(ActionEvent actionEvent) {
        Serie f = serieListView.getSelectionModel().getSelectedItem();

        f.deleteSerie(f.getSerieId());
    }

    public void speichernButtonClicked(ActionEvent actionEvent) {
        Serie f = new Serie();
        f.setSerieId(Integer.parseInt(SerieIDLabel.getText()));
        f.setTitle(SerieLabel.getText());
        f.setLength(folgenlängeLabel.getText());
        f.setRating(ratingLabel.getText());
        f.setDescription(descriptionTextArea.getText());
        f.setStatus(statusLabel.getText());
        f.setSeasons(Integer.parseInt(staffelLabel.getText()));
        f.setEpisodes(Integer.parseInt(folgenLabel.getText()));
        f.setTypeId(1);
        f.addSerie();
    }
}
