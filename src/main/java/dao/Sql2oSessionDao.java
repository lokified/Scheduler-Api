package dao;

import models.Session;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.List;

public class Sql2oSessionDao implements SessionDao {
    private final  Sql2o sql2o;

    public Sql2oSessionDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void addSession(Session session) {
        String sql = "INSERT INTO sessions (sessionName, invitationLink, startTime, endTime,description, type) VALUES (:sessionName, :invitationLink, :startTime, :endTime,:description, :type)";

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
    public void update(int id,String sessionName, String invitationLink, Timestamp startTime, Timestamp endTime, String description,String type) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE sessions SET (sessionName, invitationLink, startTime, endTime, type,description) = (:sessionName, :invitationLink, :startTime, :endTime ,:description, :type) WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("sessionName", sessionName)
                    .addParameter("invitationLink", invitationLink)
                    .addParameter("startTime", startTime)
                    .addParameter("endTime", endTime)
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
