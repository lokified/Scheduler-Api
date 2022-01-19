import com.google.gson.Gson;
import dao.Sql2oAnnouncementsDao;
import dao.Sql2oSessionDao;
import dao.Sql2oUserDao;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {

        Sql2oSessionDao sessionDao = new Sql2oSessionDao();
        Sql2oUserDao userDao = new Sql2oUserDao();
        Sql2oAnnouncementsDao newsDao = new Sql2oAnnouncementsDao();

        Gson gson = new Gson();
        // get all sessions
        get("/sessions", "application/json", (request, response) -> {
            return gson.toJson(sessionDao.getAllSessions());
        });
        // get session by id
        get("/sessions/:sessionId", "application/json", (request, response) -> {
            int sessionId = Integer.parseInt(request.params("sessionId"));
            return gson.toJson(sessionDao.getSessionById(sessionId));
        });

    }
}
