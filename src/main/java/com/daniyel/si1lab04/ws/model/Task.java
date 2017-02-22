package com.daniyel.si1lab04.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniyel on 29/01/2017.
 */

@Entity
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="is_completed", nullable = false)
    private boolean completed;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_priority", nullable = false)
    private TaskPriority taskPriority;

    @OneToMany(targetEntity = SubTask.class, mappedBy = "task", cascade = CascadeType.ALL)
    private List<SubTask> subTasks;

    @ManyToOne
    @JoinColumn(name = "task_category_id")
    private TaskCategory taskCategory;

    //criar tarefa obrigatoriamente passando a id da task! (nullable)
    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private ToDoList toDoList;

    public Task() {
    }

    public Task(Long id, String title, boolean isCompleted, String description, List<SubTask> subTasks,
                TaskPriority taskPriority, TaskCategory taskCategory) {
        this.id = id;
        this.title = title;
        this.completed = isCompleted;
        this.description = description;
        this.subTasks = subTasks;
        this.taskPriority = taskPriority;
        this.taskCategory = taskCategory;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskCategory getTaskCategory() {
        taskCategory.setTasks(null);
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public ToDoList getToDoList() {
        toDoList.setTasks(null);
        return toDoList;
    }
}
