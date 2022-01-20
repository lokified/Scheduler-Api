package dao;

import models.Announcements;
import models.Modules;

import java.util.List;

public interface ModulesDao {

    //adds module
    void add(Modules module);

    //lists all modules
    List<Modules> getAll();
    //List<Announcements> getAnnouncementsOfModule(int moduleId);
    Modules findById(int id);

    //updates
    void update(int id, String name, int userId);

    //delete
    void deleteById(int id);
    void clearAll();
}
