package models;

import java.util.Objects;

public class User {
    private int id;
    private String position;
    private String email;
    private String name;
    private int moduleId;

    public User(String position, String email, String name, int moduleId) {
        this.position = position;
        this.email = email;
        this.name = name;
        this.moduleId = moduleId;
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

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (moduleId != user.moduleId) return false;
        if (!position.equals(user.position)) return false;
        if (!email.equals(user.email)) return false;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + position.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + moduleId;
        return result;
    }
}
