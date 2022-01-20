import com.google.gson.Gson;
import dao.Sql2oAnnouncementsDao;
import dao.Sql2oModulesDao;
import dao.Sql2oSessionDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Announcements;
import models.Modules;
import models.Session;
import models.User;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        String connectionString ="jdbc:postgresql://localhost:5432/scheduler";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","lokified");
        Sql2oSessionDao sessionDao = new Sql2oSessionDao(sql2o);
        Sql2oUserDao userDao = new Sql2oUserDao(sql2o);
        Sql2oAnnouncementsDao announcementsDao = new Sql2oAnnouncementsDao(sql2o);
        Sql2oModulesDao modulesDao = new Sql2oModulesDao(sql2o);

        Gson gson = new Gson();

        //sessions
        // get all sessions
        get("/sessions", "application/json", (request, response) -> {
            if (sessionDao.getAllSessions().size() > 0) {
                return gson.toJson(sessionDao.getAllSessions());
            }
            else {
                return "no sessions available";
            }
        });

        // get session by id
        get("/sessions/:sessionId", "application/json", (request, response) -> {
            int sessionId = Integer.parseInt(request.params("sessionId"));

            Session foundSession = sessionDao.getSessionById(sessionId);

            if (foundSession == null) {
                throw new ApiException(404, String.format("No session with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(foundSession);
            }
        });

        //add a session
        post("/sessions/new", "application/json", (req,res) -> {
            Session session = gson.fromJson(req.body(), Session.class);

            sessionDao.addSession(session);
            res.status(201);
            res.type("application/json");
            return gson.toJson(session);
        });

        //deletes a session
        delete("sessions/:id/delete","application/json",(req,res) -> {
            int sessionId = Integer.parseInt(req.params("id"));
            Session foundSession = sessionDao.getSessionById(sessionId);
            if(foundSession == null) {
                throw new  ApiException(404,String.format("No sessions wih the id: %s exists",req.params("id")));
            }
            sessionDao.deleteSessionById(sessionId);
            return gson.toJson("successfully deleted");
        });

        //deletes all session
//        delete("sessions/delete","application/json",(req,res) -> {
//            Session session = sessionDao.getAllSessions();
//            return gson.toJson("successfully deleted");
//        });

        //user
        //add a user
        post("/user/new", "application/json", (req,res) -> {
            User user = gson.fromJson(req.body(), User.class);

            userDao.addUser(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        //find user by id
        get("/user/:userId", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));

            User foundUser = userDao.getUserById(userId);

            if (foundUser == null) {
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(foundUser);
            }
        });

        //get module by user
        get("/userModule/:userId", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));

            Modules foundUserModule = userDao.getModuleByUser(userId);

            return gson.toJson(foundUserModule);

        });

        //show all users
        get("/users", "application/json", (request, response) -> {
            if (userDao.getAllUsers().size() > 0) {
                return gson.toJson(userDao.getAllUsers());
            }
            else {
                return "no users available";
            }
        });

        //delete a user
        delete("user/:id/delete","application/json",(req,res) -> {
            int userId = Integer.parseInt(req.params("id"));
            User foundUser = userDao.getUserById(userId);
            if(foundUser == null) {
                throw new  ApiException(404,String.format("No user with the id: %s exists",req.params("id")));
            }
            userDao.deleteUserById(userId);
            return gson.toJson("successfully deleted");
        });

        //modules

        //add a module
        post("/module/new", "application/json", (req,res) -> {
            Modules modules = gson.fromJson(req.body(), Modules.class);

            modulesDao.add(modules);
            res.status(201);
            res.type("application/json");
            return gson.toJson(modules);
        });

        //add user to a module
        post("/modules/:moduleId/user/new", "application/json", (request, response) -> {
            int moduleId = Integer.parseInt(request.params("moduleId"));
            Modules newModules = modulesDao.findById(moduleId);
            User newUser = gson.fromJson(request.body(), User.class);
            userDao.addUser(newUser);
            newUser.setModules(newModules.getName());
            modulesDao.addUserToModule(newModules, newUser);
            response.status(201);
            response.type("application/json");
            return gson.toJson(newUser);
        });

        //find module by Id
        get("/modules/:moduleId", "application/json", (request, response) -> {
            int moduleId = Integer.parseInt(request.params("moduleId"));

            Modules foundModule = modulesDao.findById(moduleId);

            if (foundModule == null) {
                throw new ApiException(404, String.format("No module with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(foundModule);
            }
        });

        //show all modules
        get("/modules", "application/json", (request, response) -> {
            if (modulesDao.getAll().size() > 0) {
                return gson.toJson(modulesDao.getAll());
            }
            else {
                return "no modules available";
            }
        });

        //show users in a module
        get("/modules/:id/users", "application/json", (req, res) -> {
            int moduleId = Integer.parseInt(req.params("id"));
            Modules moduleToFind = modulesDao.findById(moduleId);
            if (moduleToFind == null){
                throw new ApiException(404, String.format("No modules with the id: \"%s\" exists", req.params("id")));
            }
            else if (modulesDao.getUsersByModule(moduleId).size() == 0){
                return "{\"message\":\"I'm sorry, but no users are listed for this models.\"}";
            }
            else {
                return gson.toJson(modulesDao.getUsersByModule(moduleId));
            }
        });

        //delete a module
        delete("module/:id/delete","application/json",(req,res) -> {
            int moduleId = Integer.parseInt(req.params("id"));
            Modules foundModule = modulesDao.findById(moduleId);
            if(foundModule == null) {
                throw new  ApiException(404,String.format("No module with the id: %s exists",req.params("id")));
            }
            modulesDao.deleteById(moduleId);
            return gson.toJson("successfully deleted");
        });

        //announcements

        //add an announcement
        post("/announcements/new", "application/json", (req,res) -> {
            Announcements announcements = gson.fromJson(req.body(), Announcements.class);

            announcementsDao.add(announcements);
            res.status(201);
            res.type("application/json");
            return gson.toJson(announcements);
        });

        //show all announcements
        get("/announcements", "application/json", (request, response) -> {
            if (announcementsDao.getAll().size() > 0) {
                return gson.toJson(announcementsDao.getAll());
            }
            else {
                return "no announcements available";
            }
        });

        //find particular announcements
        get("/announcements/:id", "application/json", (request, response) -> {
            int announcementId = Integer.parseInt(request.params("id"));

            Announcements foundAnnouncements = announcementsDao.findById(announcementId);

            if (foundAnnouncements == null) {
                throw new ApiException(404, String.format("No announcement with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(foundAnnouncements);
            }
        });

        //show user announcement
        get("/announcements/user/:userId", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));

            List<Announcements> foundAnnouncements = announcementsDao.getUserAnnouncement(userId);

            if (foundAnnouncements == null) {
                throw new ApiException(404, String.format("No announcement with the user: \"%s\" exists", request.params("userId")));
            }
            else {
                return gson.toJson(foundAnnouncements);
            }
        });

        //delete an announcement
        delete("announcements/:id/delete","application/json",(req,res) -> {
            int announcementId = Integer.parseInt(req.params("id"));
            Announcements foundAnnouncements = announcementsDao.findById(announcementId);
            if(foundAnnouncements == null) {
                throw new  ApiException(404,String.format("No announcement with the id: %s exists",req.params("id")));
            }
            announcementsDao.deleteById(announcementId);
            return gson.toJson("successfully deleted");
        });
    }
}
