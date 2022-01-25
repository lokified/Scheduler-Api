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
    //Update
    void update(int id,String sessionName, String invitationLink, String startTime, String endTime, String description,String type);
    //Delete
    void deleteAllSessions();
    void deleteSessionById(int id);
}
