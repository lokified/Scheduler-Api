package models;

import java.util.Objects;

public class User {
    private int id;
    private String position;
    private String email;
    private String name;
    private String modules;

    public User(String position, String email, String name, String modules) {
        this.position = position;
        this.email = email;
        this.name = name;
        this.modules = modules;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && position.equals(user.position) && email.equals(user.email) && name.equals(user.name) && modules.equals(user.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, email, name, modules);
    }
}
