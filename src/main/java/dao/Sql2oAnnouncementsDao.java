package dao;

import models.Announcements;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAnnouncementsDao implements AnnouncementsDao {

    private final Sql2o sql2o;

    public Sql2oAnnouncementsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //adds announcements
    @Override
    public void add(Announcements announcements) {
        String sql = "INSERT INTO announcements (title, userId, description) VALUES (:title, :userId, :description)";

        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(announcements)
                    .executeUpdate()
                    .getKey();

            announcements.setId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //lists all announcements
    @Override
    public List<Announcements> getAll(){
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM announcements";
            return conn.createQuery(sql)
                    .executeAndFetch(Announcements.class);
        }
    }

    //show announcements by the user
    @Override
    public List<Announcements> getUserAnnouncement(int userId) {

        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM announcements WHERE userId = :userId";
            return conn.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(Announcements.class);
        }
    }

    //finds a particular announcement
    @Override
    public Announcements findById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM announcements WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Announcements.class);
        }
    }

    //updates an announcement
    @Override
    public void update(int id, String title, int userId, String description) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE announcements SET (title, userId, description) = (:title, :userId, :description) WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("userId", userId)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //deletes an announcement
    @Override
    public void deleteById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "DELETE FROM announcements WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //deletes all announcements
    @Override
    public void clearAll() {
        String sql = "DELETE from announcements";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
