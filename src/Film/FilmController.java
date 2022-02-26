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

    public void categoryListViewClicked(MouseEvent mouseEvent) { //if you click on a category in the list, all films assigned to the category are loaded in the filmListView
        Kategorie k = categoryListView.getSelectionModel().getSelectedItem();
        filmListView.setItems(k.loadFilms());
    }

    public void filmListViewClicked(MouseEvent mouseEvent) { //clicking on a movie will load all the information about the movie
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



    public void bearbeitenButtonClicked(ActionEvent actionEvent) { //if the Bearbeiten button gets clicked, the information about the film can be edited
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

    public void hinzufügenButtonClicked(ActionEvent actionEvent) { //when the Hinzufügen button gets clicked, all labels and text fields gets set to " "
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

    public void löschenButtonClicked(ActionEvent actionEvent) { //if the Löschen button gets clicked, the movie gets deleted from the databank
        Film f = filmListView.getSelectionModel().getSelectedItem();

        f.deleteFilm(f.getFilmId());
    }

    public void speicherButtonClicked(ActionEvent actionEvent) { //if the Speichern button gets clicked, a new movie will be added
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
