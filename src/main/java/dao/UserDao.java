package dao;

import models.Modules;
import models.User;

import java.util.List;

public interface UserDao {
    //Create
    void addUser(User user);

    //Read
    List<User> getAllUsers();
    User getUserById(int id);
    List<Modules> getModuleByUser(int userId);


    //Update
    void update(int id,String position, String email, String name, int moduleId);

    //Delete
    void deleteAllUsers();
    void deleteUserById(int id);
}
