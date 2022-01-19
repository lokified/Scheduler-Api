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
    List<Modules> getUserByModule(int moduleId);


    //Update

    //Delete
    void deleteAllUsers();
    void deleteUserById(int id);
}
