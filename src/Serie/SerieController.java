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


    public void categoryListViewClicked(MouseEvent mouseEvent) { //if you click on a category in the list, all series assigned to the category are loaded in the serieListView
        Kategorie k = categoryListView.getSelectionModel().getSelectedItem();
        serieListView.setItems(k.loadSerie());
    }

    public void serieListViewClicked(MouseEvent mouseEvent) { //clicking on a series will load all the information about the series
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
        producerComboBox.setItems(Produzent.getAllBySerieId(s.getSerieId()));
        regisseurLabel.setText(String.valueOf(Regisseur.getAllBySerieId(s.getSerieId())));
        actorComboBox.setItems(Schauspieler.getAllBySerieId(s.getSerieId()));
    }

    public void bearbeitenButtonClicked(ActionEvent actionEvent) { //if the Bearbeiten button gets clicked, the information about the series can be edited
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

    public void hinzufügenButtonClicked(ActionEvent actionEvent) { //when the Hinzufügen button gets clicked, all labels and text fields gets set to " "
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

    public void löschenButtonClicked(ActionEvent actionEvent) { //if the Löschen button gets clicked, the series gets deleted from the databank
        Serie f = serieListView.getSelectionModel().getSelectedItem();

        f.deleteSerie(f.getSerieId());
    }

    public void speichernButtonClicked(ActionEvent actionEvent) { //if the Speichern button gets clicked, a new series will be added
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
