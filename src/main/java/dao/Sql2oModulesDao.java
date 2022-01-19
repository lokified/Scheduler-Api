package dao;

import models.Modules;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oModulesDao implements ModulesDao{

    private final Sql2o sql2o;

    public Sql2oModulesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //adds a module
    @Override
    public void add(Modules modules) {

        String sql = "INSERT INTO modules (name, userId) VALUES (:name, :userId)";
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

    //updates a module
    @Override
    public void update(int id, String name, int userId) {
        try (Connection conn = sql2o.open()){
            String sql = "UPDATE modules SET (name, userId) = (:name,:userId) WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("userId", userId)
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
