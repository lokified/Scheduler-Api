package dao;

import models.Session;
import models.User;

import java.sql.Timestamp;
import java.util.List;

public interface SessionDao {
    //Create
    void addSession(Session session);

    //Read
    List<Session> getAllSessions();
    Session getSessionById(int id);
    List<User> getUserBySession(int sessionId);
    //Update
    void update(int id,String invitationLink, Timestamp sessionTime, String description,String type);
    //Delete
    void deleteAllSessions();
    void deleteSessionById(int id);
}
