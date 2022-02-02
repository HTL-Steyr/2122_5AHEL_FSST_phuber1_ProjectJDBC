package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Regisseur {
    private int regisseurId;
    private String firstName;
    private String lastName;

    private Regisseur(int regisseurId, String firstName, String lastName){
        this.regisseurId = regisseurId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Regisseur getAllByFilmId(int filmId) {
       Regisseur result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Filme f INNER JOIN phuber1_Filmregisseur fr ON f.film_id = fr.film_id INNER JOIN phuber1_Regisseur r ON fr.regisseur_id = r.regisseur_id WHERE f.film_id = ?");

            statement.setInt(1, filmId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result = new Regisseur(results.getInt("regisseur_id"), results.getString("first_name"), results.getString("last_name"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Regisseur getAllBySerieId(int serieId) {
        Regisseur result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Serien s INNER JOIN phuber1_Serienregisseur sr ON s.serien_id = sr.serien_id INNER JOIN phuber1_Regisseur r ON sr.regisseur_id = r.regisseur_id WHERE s.serien_id = ?");

            statement.setInt(1, serieId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                result = new Regisseur(results.getInt("regisseur_id"), results.getString("first_name"), results.getString("last_name"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String toString(){
        return regisseurId + " - " + firstName + " " + lastName;
    }

}
