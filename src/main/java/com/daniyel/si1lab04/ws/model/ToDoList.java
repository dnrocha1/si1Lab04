package com.daniyel.si1lab04.ws.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniyel on 31/01/2017.
 */

@Entity
public class ToDoList {

    @Id
    @Column(name = "todo_list_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    //mappedBy correto?
    @OneToMany(targetEntity = Task.class, mappedBy = "toDoList", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public ToDoList() {
    }

    public ToDoList(Long id, String title, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.tasks = tasks;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
