package dao;

import models.Modules;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final  Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (position, email, name,moduleId) VALUES (:position, :email, :name,:moduleId)";

        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();

            user.setId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM users";
            return conn.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User getUserById(int id) {
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM users WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public List<Modules> getModuleByUser(int userId) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM modules WHERE userId = :userId")
                    .addParameter("userId",userId)
                    .executeAndFetch(Modules.class);
        }
    }

    @Override
    public void update(int id,String position, String email, String name, int moduleId) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE users SET (position, email, name,moduleId) = (:position, :email, :name,:moduleId) WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("position", position)
                    .addParameter("email", email)
                    .addParameter("name", name)
                    .addParameter("moduleId", moduleId)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteAllUsers() {
            String sql = "DELETE from users";
            try (Connection conn = sql2o.open()) {
                conn.createQuery(sql)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
    }

    @Override
    public void deleteUserById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "DELETE FROM users WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
