package com.example.week08;

public class Member {
    private String id;
    private String name;
    private String className;
    private float score;

    public Member(String id, String name, String className, float score) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
