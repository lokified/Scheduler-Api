package dao;

import models.Session;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.List;

public class Sql2oSessionDao implements SessionDao {
    private  Sql2o sql2o;



    @Override
    public void addSession(Session session) {
        String sql = "INSERT INTO sessions (invitationLink, sessionTime, type,description) VALUES (:invitationLink, :sessionTime, :type,:description)";

        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(session)
                    .executeUpdate()
                    .getKey();

            session.setId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Session> getAllSessions() {
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM sessions";
            return conn.createQuery(sql)
                    .executeAndFetch(Session.class);
        }
    }

    @Override
    public Session getSessionById(int id) {
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM sessions WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Session.class);
        }
    }

    @Override
    public List<User> getUserBySession(int sessionId) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE sessionId = :sessionId")
                    .addParameter("sessionId",sessionId)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void update(int id,String invitationLink, Timestamp sessionTime, String description,String type) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE sessions SET (invitationLink, sessionTime, type,description) = (:invitationLink, :sessionTime, :type,:description) WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("invitationLink", invitationLink)
                    .addParameter("sessionTime", sessionTime)
                    .addParameter("type", type)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteAllSessions() {
        String sql = "DELETE from sessions";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteSessionById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "DELETE FROM sessions WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
