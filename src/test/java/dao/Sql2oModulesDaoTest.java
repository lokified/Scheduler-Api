package dao;

import models.Announcements;
import models.Modules;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oModulesDaoTest {
    private static Connection conn;
    private static Sql2oModulesDao modulesDao;

    @BeforeEach
    public void setUp() {
        String connectionString ="jdbc:postgresql://localhost:5432/scheduler_test";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","lokified");
        modulesDao = new Sql2oModulesDao(sql2o);
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
        Modules anotherModule = new Modules("Angular",1);
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        assertEquals(2, modulesDao.getAll().size());
    }

    @Test
    public void findById_returnsCorrectModules() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular",1);
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
        Modules anotherModule = new Modules("Angular",1);
        modulesDao.add(anotherModule);
        modulesDao.update(anotherModule.getId(), "Blockers",3);
        Modules updatedModule = modulesDao.findById(anotherModule.getId());
        assertNotEquals(initialName, updatedModule.getName());
    }

    @Test
    public void deleteById_deletesModuleCorrectly() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular",1);
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        modulesDao.deleteById(testModules.getId());
        assertEquals(1, modulesDao.getAll().size());
    }

    @Test
    public void clearAll_deletesAllModulesCorrectly() {
        Modules testModules = setUpModules();
        Modules anotherModule = new Modules("Angular",1);
        modulesDao.add(testModules);
        modulesDao.add(anotherModule);
        modulesDao.clearAll();
        assertEquals(0, modulesDao.getAll().size());
    }

    //helper
    public Modules setUpModules() {
        return new Modules("Java-spark",1);
    }

}