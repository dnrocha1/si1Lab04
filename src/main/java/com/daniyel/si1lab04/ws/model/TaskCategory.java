package com.daniyel.si1lab04.ws.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniyel on 29/01/2017.
 */

@Entity
public class TaskCategory {

    @Id
    @Column(name = "task_category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @OneToMany(targetEntity = Task.class, mappedBy = "taskCategory", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public TaskCategory() {
    }

    public TaskCategory(Long id, String title) {
        this.id = id;
        this.title = this.title;
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
