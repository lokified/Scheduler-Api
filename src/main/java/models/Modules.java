package models;

import java.util.Objects;

public class Modules {
    private int id;
    private String name;
    private int userId;

    public Modules(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Modules modules = (Modules) o;

        if (id != modules.id) return false;
        if (userId != modules.userId) return false;
        return name.equals(modules.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + userId;
        return result;
    }
}
