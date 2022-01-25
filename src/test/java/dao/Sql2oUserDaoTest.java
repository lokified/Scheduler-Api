//package dao;
//
//import models.Modules;
//import models.User;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class Sql2oUserDaoTest {
//    private static Connection conn;
//    private static Sql2oUserDao userDao;
//
//    @BeforeEach
//    public void setUp() {
//        String connectionString ="jdbc:postgresql://localhost:5432/scheduler_test";
//        Sql2o sql2o = new Sql2o(connectionString,"moringa","lokified");
//        userDao = new Sql2oUserDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.out.println("clearing databases");
//        userDao.getAllUsers();
//    }
//
//    @AfterAll
//    public static void shutDown() throws Exception{
//        conn.close();
//        System.out.println("connection closed");
//    }
//
//    @Test
//    public void update_updatesNameCorrectly() {
//        String initialName = "Sheldon";
//        User testUser = new User("TM","hjshd@hjkh.com","Sheldon","Angular");
//        userDao.addUser(testUser);
//        userDao.update(testUser.getId(), "TM","hjshd@hjkh.com","Okware","Angular");
//        User updatedUser = userDao.getUserById(testUser.getId());
//        assertNotEquals(initialName, updatedUser.getName());
//    }
//
//}