package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Film {
    private int filmId;
    private String film;
    private int typeId;
    private String description;
    private int length;
    private String rating;
    private String status;
    private Kategorie category;
    private int categoryId;
    private int actorId;
    private int producerId;
    private int regisseurId;
    private Schauspieler actor;
    private Regisseur regisseur;
    private Produzent produzent;


    public Film(int filmId, String film, int typeId, String description, int length, String rating, String status, int categoryId) {
        this.filmId = filmId;
        this.film = film;
        this.typeId = typeId;
        this.description = description;
        this.length = length;
        this.rating = rating;
        this.status = status;
        this.categoryId = categoryId;


        /**
         * laden aller schauspieler, produzenten und regisseure
         */
    }

    public int getFilmId() {
        return filmId;
    }

    public String getFilm() {
        return film;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public String getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(Kategorie category) {
        this.category = category;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public Film() {
    }

    public static Film getById(int filmId) {
        Film result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct * FROM phuber1_Filme f INNER JOIN phuber1_Filmkategorie f1 ON f.film_id = f1.film_id INNER JOIN phuber1_Kategorie k ON f1.category_id = k.kategorie_id WHERE f.film_id = ?");

            statement.setInt(1, filmId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new Film(results.getInt("film_id"), results.getString("title"), results.getInt("type_id"), results.getString("description"),
                        results.getInt("länge"), results.getString("rating"), results.getString("status"), results.getInt("category_id"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ObservableList<Film> loadAll(int categoryId) {
        ObservableList<Film> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct /*f.*/*/*, f1.category_id*/ FROM phuber1_Filme f INNER JOIN phuber1_Filmkategorie f1 ON f.film_id = f1.film_id INNER JOIN phuber1_Kategorie k ON f1.category_id = k.kategorie_id WHERE f1.category_id = ?");


            statement.setInt(1, categoryId);

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                result.add(new Film(results.getInt("film_id"), results.getString("title"), results.getInt("type_id"), results.getString("description"),
                        results.getInt("länge"), results.getString("rating"), results.getString("status"), results.getInt("category_id")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public Film(int actorId, int producerId, int regisseurId) {
        this.actorId = actorId;
        this.producerId = producerId;
        this.regisseurId = regisseurId;
    }

    public Schauspieler getActor() {
        if (actor == null) {
            actor = (Schauspieler) Schauspieler.getAllByFilmId(this.actorId);
        }
        return actor;
    }

    public Produzent getProduzent() {
        if (produzent == null) {
            produzent = (Produzent) Produzent.getAllByFilmId(this.producerId);
        }
        return produzent;
    }

    public Regisseur getRegisseur() {
        if (regisseur == null) {
            regisseur = Regisseur.getById(this.regisseurId);
        }
        return regisseur;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public void setRegisseurId(int regisseurId) {
        this.regisseurId = regisseurId;
    }

    public void setActor(Schauspieler actor) {
        this.actor = actor;
    }

    public void setRegisseur(Regisseur regisseur) {
        this.regisseur = regisseur;
    }

    public void setProduzent(Produzent produzent) {
        this.produzent = produzent;
    }

    public static Film loadInfo(int filmId) {

        Film f = null;
        //ObservableList<Film> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct /*f.*/*/*, f1.category_id*/ FROM phuber1_Filme f \n" +
                    "INNER JOIN phuber1_Filmschauspieler fa ON f.film_id = fa.film_id INNER JOIN phuber1_Schauspieler a ON fa.schauspieler_id = a.schauspieler_id INNER JOIN phuber1_Filmproduzent fp ON f.film_id = fp.film_id INNER JOIN phuber1_Produzent p ON fp.produzent_id = p.produzent_id\n" +
                    "INNER JOIN phuber1_Filmregisseur fr ON f.film_id = fr.film_id INNER JOIN phuber1_Regisseur r ON fr.regisseur_id = r.regisseur_id WHERE f.film_id = ?");


            statement.setInt(1, filmId);

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                f = new Film(results.getInt("schauspieler_id"), results.getInt("regisseur_id"), results.getInt("produzent_id"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return f;
    }


    public String toString() {
        return film;
    }

    public Kategorie getCategory() {
        if (category == null) {
            category = Kategorie.getById(this.categoryId);
        }
        return category;
    }

    public void deleteFilm(int filmId) {
        Connection connection = Database.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM phuber1_Filme WHERE film_id = ?");
            statement.setInt(1, filmId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM phuber1_Filmkategorie WHERE film_id = ?");
            statement.setInt(1, filmId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM phuber1_Filmproduzent WHERE film_id = ?");
            statement.setInt(1, filmId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM phuber1_Filmregisseur WHERE film_id = ?");
            statement.setInt(1, filmId);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFilm() {
        Connection connection = Database.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO phuber1_Filme (film_id, title, type_id, rating, status, länge, description) values (?,?,?,?,?,?,?)");
            statement.setInt(1, filmId);
            statement.setString(2, film);
            statement.setInt(3, typeId);
            statement.setString(4, rating);
            statement.setString(5, status);
            statement.setInt(6, length);
            statement.setString(7, description);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bearbeitenFilm() {
        Connection connection = Database.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE FROM phuber1_Filme SET film_id = ?, type_id = ?, title = ?, rating = ?, status = ?, länge = ?, description = ?");
            statement.setInt(1, filmId);
            statement.setString(2, film);
            statement.setInt(3, typeId);
            statement.setString(4, rating);
            statement.setString(5, status);
            statement.setInt(6, length);
            statement.setString(7, description);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

