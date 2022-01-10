package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produzent {
    private int producerId;
    private String firstName;
    private String lastName;

    private Produzent(int producerId, String firstName, String lastName){
        this.producerId = producerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getProducerId(){return producerId;}

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public static ObservableList<Produzent> getAllByFilmId(int filmId) {
        ObservableList<Produzent> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Filme f INNER JOIN phuber1_Filmproduzent fp ON f.film_id = fp.film_id INNER JOIN phuber1_Produzent p ON fp.produzent_id = p.produzent_id WHERE f.film_id = ?");

            statement.setInt(1, filmId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result.add(new Produzent(results.getInt("produzent_id"), results.getString("first_name"), results.getString("last_name")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Produzent> getAllBySerieId(int serieId) {
        ObservableList<Produzent> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Serien f INNER JOIN phuber1_Serienproduzent fp ON f.serien_id = fp.serien_id INNER JOIN phuber1_Produzent p ON fp.produzent_id = p.produzent_id WHERE f.serien_id = ?");

            statement.setInt(1, serieId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result.add(new Produzent(results.getInt("produzent_id"), results.getString("first_name"), results.getString("last_name")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String toString(){
        return producerId + " - " + firstName + " " + lastName;
    }

}
