package dao;

import models.Announcements;
import models.Modules;
import models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oModulesDaoTest {
    private static Connection conn;
    private static Sql2oModulesDao modulesDao;
    private static Sql2oUserDao userDao;

    @BeforeEach
    public void setUp() {
        String connectionString ="jdbc:postgresql://localhost:5432/scheduler_test";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","lokified");
        modulesDao = new Sql2oModulesDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("clearing databases");
        modulesDao.clearAll();
    }

    @AfterAll
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_addsModulesCorrectly(){
        Modules testModules = setUpModules();
        modulesDao.add(testModules);
        assertEquals(1, modulesDao.getAll().size());
    }

    @Test
    public void getAll_returnsALlModules() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular");
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        assertEquals(2, modulesDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectModules() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular");
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);

        Modules foundModule = modulesDao.findById(testModules.getId());
        assertEquals("Java-spark", foundModule.getName());
        Modules foundOtherModule = modulesDao.findById(anotherModule.getId());
        assertEquals("Angular", foundOtherModule.getName());
    }

   @Test
    public void update_updatesNameCorrectly() {
        String initialName = "Angular";
        Modules anotherModule = new Modules("Angular");
        modulesDao.add(anotherModule);
        modulesDao.update(anotherModule.getId(), "Blockers");
        Modules updatedModule = modulesDao.findById(anotherModule.getId());
        assertNotEquals(initialName, updatedModule.getName());
    }

    @Test
    public void getUserByModule_returnsUsersCorrectly() throws Exception {
        User testUser = setUpUser();
        userDao.addUser(testUser);

        Modules testModules = setUpModules();
        modulesDao.add(testModules);
        modulesDao.addUserToModule(testModules, testUser);

        User[] users = {testUser};

        assertEquals(Arrays.asList(users), modulesDao.getUsersByModule(testModules.getId()));
    }

    @Test
    public void deleteById_deletesModuleCorrectly() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular");
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        modulesDao.deleteById(testModules.getId());
        assertEquals(1, modulesDao.getAll().size());
    }

    @Test
    public void clearAll_deletesAllModulesCorrectly() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular");
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        modulesDao.clearAll();
        assertEquals(0, modulesDao.getAll().size());
    }

    //helper
    public Modules setUpModules() {
        return new Modules("Java-spark");
    }

    public User setUpUser() {
        return new User("TM","sio.gmail","Sheldon","Angular");
    }

}