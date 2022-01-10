package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Schauspieler {
    private int actorId;
    private String firstName;
    private String lastName;

    private Schauspieler(int actorId, String firstName, String lastName){
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId(){return actorId;}

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public static ObservableList<Schauspieler> getAllByFilmId(int filmId) {
        ObservableList<Schauspieler> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Filme f INNER JOIN phuber1_Filmschauspieler fp ON f.film_id = fp.film_id INNER JOIN phuber1_Schauspieler p ON fp.schauspieler_id = p.schauspieler_id WHERE f.film_id = ?");

            statement.setInt(1, filmId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result.add(new Schauspieler(results.getInt("schauspieler_id"), results.getString("first_name"), results.getString("last_name")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Schauspieler> getAllBySerieId(int serieId) {
        ObservableList<Schauspieler> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Serien f INNER JOIN phuber1_Serienschauspieler fp ON f.serien_id = fp.serien_id INNER JOIN phuber1_Schauspieler p ON fp.schauspieler_id = p.schauspieler_id WHERE f.serien_id = ?");

            statement.setInt(1, serieId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result.add(new Schauspieler(results.getInt("schauspieler_id"), results.getString("first_name"), results.getString("last_name")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String toString(){
        return actorId + " - " + firstName + " " + lastName;
    }

}


