//package dao;
//
//import models.Announcements;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//public class Sql2oAnnouncementsDaoTest {
//
//    private static Connection conn;
//    private static Sql2oAnnouncementsDao announcementsDao;
//
//    @BeforeEach
//    public void setUp() {
//        String connectionString ="jdbc:postgresql://localhost:5432/scheduler_test";
//        Sql2o sql2o = new Sql2o(connectionString,"moringa","lokified");
//        announcementsDao = new Sql2oAnnouncementsDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.out.println("clearing databases");
//        announcementsDao.clearAll();
//    }
//
//    @AfterAll
//    public static void shutDown() throws Exception{
//        conn.close();
//        System.out.println("connection closed");
//    }
//
//    @Test
//    public void add_addsAnnouncements() {
//        Announcements testAnnouncements = setUpAnnouncements();
//        announcementsDao.add(testAnnouncements);
//        assertEquals(1, announcementsDao.getAll().size());
//    }
//
//    @Test
//    public void getAll_returnsALlAnnouncements() {
//        Announcements testAnnouncements = setUpAnnouncements();
//        Announcements anotherAnnouncement = new Announcements("Feedback",1,"wait for feedback");
//        announcementsDao.add(testAnnouncements);
//        announcementsDao.add(anotherAnnouncement);
//        assertEquals(2, announcementsDao.getAll().size());
//    }
//
//    @Test
//    public void getUserAnnouncement_returnsUserAnnouncementCorrectly() {
//        Announcements testAnnouncement = setUpAnnouncements();
//        announcementsDao.add(testAnnouncement);
//        Announcements anotherAnnouncement = new Announcements("Feedback",1,"wait for feedback");
//        announcementsDao.add(anotherAnnouncement);
//        Announcements otherAnnouncement = new Announcements("Onboarding",2,"wait for feedback");
//        announcementsDao.add(otherAnnouncement);
//        List<Announcements> selectedUserAnnouncements = announcementsDao.getUserAnnouncement(testAnnouncement.getUserId());
//        assertEquals(2, selectedUserAnnouncements.size());
//    }
//
//    @Test
//    public void findById_returnsCorrectAnnouncements() {
//        Announcements testAnnouncements = setUpAnnouncements();
//        Announcements anotherAnnouncement = new Announcements("Feedback",1,"wait for feedback");
//        announcementsDao.add(testAnnouncements);
//        announcementsDao.add(anotherAnnouncement);
//
//        Announcements foundAnnouncement = announcementsDao.findById(testAnnouncements.getId());
//        assertEquals("Standups", foundAnnouncement.getTitle());
//        Announcements foundOtherAnnouncement = announcementsDao.findById(anotherAnnouncement.getId());
//        assertEquals("Feedback", foundOtherAnnouncement.getTitle());
//    }
//
//    @Test
//    public void update_updatesTitleCorrectly() {
//        String initialTitle = "Standups";
//        Announcements testAnnouncements = setUpAnnouncements();
//        announcementsDao.add(testAnnouncements);
//        announcementsDao.update(testAnnouncements.getId(), "Blockers",3,"No standups today");
//        Announcements updatedAnnouncement = announcementsDao.findById(testAnnouncements.getId());
//        assertNotEquals(initialTitle, updatedAnnouncement.getTitle());
//    }
//
//    @Test
//    public void deleteById_deletesAnnouncementsCorrectly() {
//        Announcements testAnnouncement = setUpAnnouncements();
//        announcementsDao.add(testAnnouncement);
//        Announcements anotherAnnouncement = new Announcements("Feedback",1,"wait for feedback");
//        announcementsDao.add(anotherAnnouncement);
//        announcementsDao.deleteById(testAnnouncement.getId());
//        assertEquals(1, announcementsDao.getAll().size());
//    }
//
//    @Test
//    public void clearAll_deletesAllAnnouncementsCorrectly() {
//        Announcements testAnnouncement = setUpAnnouncements();
//        announcementsDao.add(testAnnouncement);
//        Announcements anotherAnnouncement = new Announcements("Feedback",1,"wait for feedback");
//        announcementsDao.add(anotherAnnouncement);
//        announcementsDao.clearAll();
//        assertEquals(0, announcementsDao.getAll().size());
//    }
//
//    //helpers
//    public Announcements setUpAnnouncements() {
//        return new Announcements("Standups",2,"No standups today");
//    }
//}
