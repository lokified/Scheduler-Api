package models;

import java.sql.Timestamp;

public class Session {
    private int id;
    private String invitationLink;
    private Timestamp sessionTime;
    private String description;

    private String type;

    public Session(String invitationLink, Timestamp sessionTime, String description, String type) {
        this.invitationLink = invitationLink;
        this.sessionTime = sessionTime;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public void setInvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }

    public Timestamp getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Timestamp sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (id != session.id) return false;
        if (!invitationLink.equals(session.invitationLink)) return false;
        if (!sessionTime.equals(session.sessionTime)) return false;
        if (!description.equals(session.description)) return false;
        return type.equals(session.type);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + invitationLink.hashCode();
        result = 31 * result + sessionTime.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
