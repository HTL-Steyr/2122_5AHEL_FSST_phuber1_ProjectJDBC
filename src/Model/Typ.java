/*package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Typ {

    private int typId;
    private String typ;

    private Typ(int typId, String typ) {
        this.typId = typId;
        this.typ = typ;
    }

    public int getTypId() {
        return typId;
    }

    public String getTyp() {
        return typ;
    }

    public static Typ getById(int typId) {

        Typ result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Typ WHERE typ_id = ?");

            statement.setInt(1, typId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new Typ(results.getInt("typ_id"), results.getString("typ"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Typ> loadAll() {
        ObservableList<Typ> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT * FROM phuber1_Typ");

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                result.add(new Typ(results.getInt("typ_id"), results.getString("typ")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ObservableList<Kategorie> loadCategories(Typ typ) {

        ObservableList<Kategorie> result = Kategorie.loadAll(this.typId);
        return result;
    }
}
*/