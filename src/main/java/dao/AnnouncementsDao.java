package dao;

import models.Announcements;
import models.User;

import java.util.List;

public interface AnnouncementsDao {

    //create
    void add(Announcements announcements);

    //read
    List<Announcements> getAll();
    List<Announcements> getUserAnnouncement(int userId);
    Announcements findById(int id);

    //update
    void update(int id, String title, int userId, String description);

    //delete
    void deleteById(int id);
    void clearAll();
}
