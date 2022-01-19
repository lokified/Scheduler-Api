package dao;

import models.Announcements;
import models.Modules;
import models.Session;
import models.User;

import java.util.List;

public interface SessionDao {
    //Create
    void addSession(Session session);

    //Read
    List<Session> getAllSessions();
    Session getSessionById(int id);
    List<User> getSessionByUser(int userId);
    List<Modules> getModuleByUser(int moduleId);
    List<Announcements> getAnnouncementsByUser(int AnnouncementId);



    //Update

    //Delete
    void deleteAllSessions();
    void deleteSessionById(int id);
}
