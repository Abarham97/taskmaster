package com.example.myapplication;

import androidx.room.Entity;

@Entity
public class Task {
    public Long id;
    private String title;
    private String body;
    private State state;
    public enum State {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETE
    }

    public Task(String title, String body, State state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public State getState() {
        return state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setState(State state) {
        this.state = state;
    }
}
