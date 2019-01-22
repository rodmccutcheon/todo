package com.rodmccutcheon.todo.web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.ZoneId;

@Entity
public class Todo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Boolean completed;
    private String timeZone = "UTC";

    protected Todo() { }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(Long id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        ZoneId.of(timeZone); // will throw if the zone is not valid
        this.timeZone = timeZone;
    }
}
