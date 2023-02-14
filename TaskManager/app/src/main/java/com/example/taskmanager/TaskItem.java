package com.example.taskmanager;
//pojo
public class TaskItem {
    private String category;
    private String name;
    private String date;
    private String time;
    private String status;

    public TaskItem(String category, String name, String date, String time, String status) {
        this.category = category;
        this.name = name;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
