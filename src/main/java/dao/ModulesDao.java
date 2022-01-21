package dao;

import models.Announcements;
import models.Modules;
import models.User;

import java.util.List;

public interface ModulesDao {

    //adds module
    void add(Modules module);

    //lists all modules
    List<Modules> getAll();
    List<User> getUsersByModule(int moduleId);
    void addUserToModule(Modules modules, User user);
    //List<Announcements> getAnnouncementsOfModule(int moduleId);
    Modules findById(int id);

    //updates
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();
}
