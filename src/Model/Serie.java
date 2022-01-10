package Model;

import com.mysql.jdbc.PacketTooBigException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Serie {
    private int serieId;
    private String title;
    private int typeId;
    private String description;
    private int seasons;
    private int episodes;
    private String length;
    private String rating;
    private String status;
    private Schauspieler actor;
    private int actorId;
    private Kategorie category;
    private int categoryId;
    private int producerId;
    private int regisseurId;
    private Regisseur regisseur;
    private Produzent produzent;



    private Serie(int serieId, String title, int typeId, String description, int seasons, int episodes, String length, String rating, String status, int categoryId) {
        this.serieId = serieId;
        this.title = title;
        this.typeId = typeId;
        this.description = description;
        this.length = length;
        this.seasons = seasons;
        this.episodes = episodes;
        this.rating = rating;
        this.status = status;
        this.categoryId = categoryId;

    }

    public String getTitle(){return title;}

    public String getDescription(){return description;}

    public String getLength(){return length;}

    public int getSeasons(){return seasons;}

    public int getEpisodes(){return episodes;}

    public String getRating(){return rating;}

    public String getStatus(){return status;}

    public int getSerieId(){return serieId;}

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setLength(String length) {
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


    public Serie() {
    }

    public static Serie getById(int serieId) {
        Serie result = null;

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct * FROM phuber1_Serien s INNER JOIN phuber1_Serienkategorie s1 ON s.serien_id = s1.serien_id INNER JOIN phuber1_Kategorie k ON s1.kategorie_id = k.kategorie_id WHERE s.serien_id = ?");

            statement.setInt(1, serieId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                result = new Serie(results.getInt("serien_id"), results.getString("title"), results.getInt("type_id"), results.getString("description")
                        ,results.getInt("staffeln"), results.getInt("folgen"),results.getString("folgenlänge"), results.getString("rating"), results.getString("status"), results.getInt("category_id"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static ObservableList<Serie> loadAll(int categoryId) {
        ObservableList<Serie> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct * FROM phuber1_Serien s INNER JOIN phuber1_Serienkategorie s1 ON s.serien_id = s1.serien_id INNER JOIN phuber1_Kategorie k ON s1.kategorie_id = k.kategorie_id WHERE s1.kategorie_id = ?");
            statement.setInt(1,categoryId);

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                result.add(new Serie(results.getInt("serien_id"), results.getString("title"), results.getInt("type_id"), results.getString("description")
                         ,results.getInt("staffeln"), results.getInt("folgen"), results.getString("folgenlänge"), results.getString("rating"), results.getString("status"), results.getInt("kategorie_id")));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Serie(int actorId, int producerId, int regisseurId) {
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

    public static Serie loadInfo(int serieId) {

        Serie s = null;
        //ObservableList<Film> result = FXCollections.observableArrayList();

        try {
            Connection c = Database.getInstance();
            PreparedStatement statement = c.prepareStatement("SELECT distinct /*f.*/*/*, f1.category_id*/ FROM phuber1_Serien f \n" +
                    "INNER JOIN phuber1_Serienschauspieler fa ON f.serien_id = fa.serien_id INNER JOIN phuber1_Schauspieler a ON fa.schauspieler_id = a.schauspieler_id INNER JOIN phuber1_Serienproduzent fp ON f.serien_id = fp.serien_id INNER JOIN phuber1_Produzent p ON fp.produzent_id = p.produzent_id\n" +
                    "INNER JOIN phuber1_Serienregisseur fr ON f.serien_id = fr.serien_id INNER JOIN phuber1_Regisseur r ON fr.regisseur_id = r.regisseur_id WHERE f.serien_id = ?");


            statement.setInt(1, serieId);

            ResultSet results = statement.executeQuery();

            while (results.next()) {

                s = new Serie(results.getInt("schauspieler_id"), results.getInt("regisseur_id"), results.getInt("produzent_id"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    public String toString(){
        return title;
    }

    public Kategorie getCategory() {
        if (category == null) {
            category = Kategorie.getById(this.categoryId);
        }
        return category;
    }

    public void deleteSerie(int serieId){
        Connection connection = Database.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM phuber1_Serien WHERE serien_id = ?");
            statement.setInt(1,serieId);

            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addSerie(){
        Connection connection = Database.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO phuber1_Serien (serien_id, type_id, title, description, staffeln, folgen, folgenlänge, rating, status) values (?,?,?,?,?,?,?,?,?)");
            statement.setInt(1,serieId);
            statement.setInt(2,typeId);
            statement.setString(3,title);
            statement.setString(4,description);
            statement.setInt(5,seasons);
            statement.setInt(6,episodes);
            statement.setString(7,length);
            statement.setString(8,rating);
            statement.setString(9,status);


            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void bearbeitenSerie(){
        Connection connection = Database.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE FROM phuber1_Serien SET serien_id = ?, type_id = ?, title = ?, description = ?, staffeln = ?, folgen = ?, folgenlänge = ?, rating = ?, status = ?");
            statement.setInt(1,serieId);
            statement.setInt(2,typeId);
            statement.setString(3,title);
            statement.setString(4,description);
            statement.setInt(5,seasons);
            statement.setInt(6,episodes);
            statement.setString(7,length);
            statement.setString(8,rating);
            statement.setString(9,status);

            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}


