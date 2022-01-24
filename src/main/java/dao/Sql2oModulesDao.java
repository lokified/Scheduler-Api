package dao;

import models.Modules;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oModulesDao implements ModulesDao{

    private final Sql2o sql2o;

    public Sql2oModulesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //adds a module
    @Override
    public void add(Modules modules) {

        String sql = "INSERT INTO modules (name) VALUES (:name)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql)
                    .bind(modules)
                    .executeUpdate()
                    .getKey();
            modules.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //lists all modules
    @Override
    public List<Modules> getAll() {
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM modules";
            return conn.createQuery(sql)
                    .executeAndFetch(Modules.class);
        }
    }

    //show a specific module
    @Override
    public Modules findById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "SELECT * FROM modules WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Modules.class);
        }
    }

    //adds a user to a module
    @Override
    public void addUserToModule(Modules module, User user) {
        try(Connection conn = sql2o.open()){
            String sql = "INSERT INTO modules_users(moduleId, userId) VALUES (:moduleId, :userId)";
            conn.createQuery(sql)
                    .addParameter("moduleId", module.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to add user into module " + ex);
        }
    }

    //get users in a module
    @Override
    public List<User> getUsersByModule(int moduleId) {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE moduleId = :moduleId";

        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("moduleId",moduleId)
                    .executeAndFetch(User.class);
        }
//        String joinQuery = "SELECT userId FROM modules_users WHERE moduleId = :moduleId";
//
//        try (Connection con = sql2o.open()) {
//            List<Integer> allUsersIds = con.createQuery(joinQuery)
//                    .addParameter("moduleId", moduleId)
//                    .executeAndFetch(Integer.class);
//
//            for (Integer userId : allUsersIds){
//                String userQuery = "SELECT * FROM users WHERE id = :userId";
//                users.add(
//                        con.createQuery(userQuery)
//                                .addParameter("userId", userId)
//                                .executeAndFetchFirst(User.class));
//            }
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//        return users;
    }

    //updates a module
    @Override
    public void update(int id, String name) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE modules SET name = :name WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    //deletes a specific module
    @Override
    public void deleteById(int id) {
        try (Connection conn = sql2o.open()){
            String sql = "DELETE from modules WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //deletes all modules

    @Override
    public void clearAll() {
        try (Connection conn = sql2o.open()){
            String sql = "DELETE from modules";
            conn.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
