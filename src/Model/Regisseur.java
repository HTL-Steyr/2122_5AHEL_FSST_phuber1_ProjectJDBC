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

    public int getRegisseurId(){return regisseurId;}

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public static Regisseur getById(int regisseurId) {
        Regisseur result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Regisseur WHERE regisseur_id = ?");

            statement.setInt(1, regisseurId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
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
