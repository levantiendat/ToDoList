package com.example.todolist;

public class ToDo {
    private int id;
    private String name;
    private String detail;

    public ToDo() {
        this.id = 0;
        this.name = "";
        this.detail = "";
    }

    public ToDo(int id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
