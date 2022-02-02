package Film;

import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class FilmController {

    public ListView<Kategorie> categoryListView;
    public ListView<Film> filmListView;
    public TextField filmIDLabel;
    public TextField filmLabel;
    public TextField categoryLabel;
    public TextField lengthLabel;
    public TextField ratingLabel;
    public TextArea descriptionTextArea;
    public Button bearbeitenButton;
    public Button hinzufügenButton;
    public Button löschenButton;
    public TextField statusLabel;
    public TextField regisseurLabel;
    public Button speicherButton;
    public ComboBox actorComboBox;
    public ComboBox producerComboBox;

    public void initialize() {

        categoryListView.setItems(Kategorie.loadAll());

    }

    public void categoryListViewClicked(MouseEvent mouseEvent) {
        Kategorie k = categoryListView.getSelectionModel().getSelectedItem();
        filmListView.setItems(k.loadFilms());
    }

    public void filmListViewClicked(MouseEvent mouseEvent) {
        Film f = filmListView.getSelectionModel().getSelectedItem();

        if (f != null) {
            filmIDLabel.setText(String.valueOf(f.getFilmId()));
            filmLabel.setText(f.getFilm());
            if (f.getCategory() != null) {
                categoryLabel.setText(String.valueOf(f.getCategory()));
            } else {
                categoryLabel.setText("Work in progress...");
            }
            lengthLabel.setText(f.getLength() + " Minuten");
            ratingLabel.setText(f.getRating());
            descriptionTextArea.setText(f.getDescription());
            statusLabel.setText(f.getStatus());

        }
        producerComboBox.setItems(Produzent.getAllByFilmId(f.getFilmId()));
        regisseurLabel.setText(String.valueOf(Regisseur.getAllByFilmId(f.getFilmId())));
        actorComboBox.setItems(Schauspieler.getAllByFilmId(f.getFilmId()));
    }



    public void bearbeitenButtonClicked(ActionEvent actionEvent) {
        Film f = filmListView.getSelectionModel().getSelectedItem();
        f.setFilmId(Integer.parseInt(filmIDLabel.getText()));
        f.setFilm(filmLabel.getText());
        f.setLength(Integer.parseInt(lengthLabel.getText()));
        f.setRating(ratingLabel.getText());
        f.setDescription(descriptionTextArea.getText());
        f.setStatus(statusLabel.getText());
        f.setTypeId(1);
        f.bearbeitenFilm();
    }

    public void hinzufügenButtonClicked(ActionEvent actionEvent) {
        filmIDLabel.setText("");
        filmLabel.setText("");
        lengthLabel.setText("");
        ratingLabel.setText("");
        categoryLabel.setText("");
        descriptionTextArea.setText("");
        statusLabel.setText("");
        actorComboBox.getItems().clear();
        producerComboBox.getItems().clear();
        regisseurLabel.setText("");
    }

    public void löschenButtonClicked(ActionEvent actionEvent) {
        Film f = filmListView.getSelectionModel().getSelectedItem();

        f.deleteFilm(f.getFilmId());
    }

    public void speicherButtonClicked(ActionEvent actionEvent) {
        Film f = new Film();
        f.setFilmId(Integer.parseInt(filmIDLabel.getText()));
        f.setFilm(filmLabel.getText());
        f.setLength(Integer.parseInt(lengthLabel.getText()));
        f.setRating(ratingLabel.getText());
        f.setDescription(descriptionTextArea.getText());
        f.setStatus(statusLabel.getText());
        f.setTypeId(1);
        f.addFilm();
    }
}
