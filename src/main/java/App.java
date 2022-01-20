import com.google.gson.Gson;
import dao.Sql2oAnnouncementsDao;
import dao.Sql2oModulesDao;
import dao.Sql2oSessionDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Session;
import org.sql2o.Sql2o;

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

    }
}
