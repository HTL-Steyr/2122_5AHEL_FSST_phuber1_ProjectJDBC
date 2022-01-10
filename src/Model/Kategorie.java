package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategorie {

    private int categoryId;
    private String name;


    Kategorie(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;

    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public static Kategorie getById(int categoryId) {
        //load one single department with the given id
        Kategorie result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Kategorie WHERE kategorie_id = ?");

            statement.setInt(1, categoryId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new Kategorie(results.getInt("kategorie_id"), results.getString("name"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Kategorie> loadAll() {
        ObservableList<Kategorie> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Kategorie");

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                result.add(new Kategorie(results.getInt("Kategorie_id"), results.getString("name")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public ObservableList<Film> loadFilms() {

        ObservableList<Film> result = Film.loadAll(this.categoryId);
        return result;
    }

    public ObservableList<Serie> loadSerie() {

        ObservableList<Serie> result1 = Serie.loadAll(this.categoryId);
        return result1;
    }

    public String toString(){
        return name;
    }
}


