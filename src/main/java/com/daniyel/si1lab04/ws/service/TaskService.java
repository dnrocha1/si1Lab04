package com.daniyel.si1lab04.ws.service;

import com.daniyel.si1lab04.ws.model.Task;
import com.daniyel.si1lab04.ws.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Daniyel on 29/01/2017.
 */

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    //regras de negocio

    public Task getTaskById(long id) {
        return taskRepository.findOne(id);
    }

    public Collection<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public boolean deleteTask(long id) {
        boolean result = false;
        Task taskFound = taskRepository.findOne(id);
        if (taskFound != null) {
            taskRepository.delete(id);
            result = true;
        }
        return result;
    }

    public Task updateTask(Task task) {
        taskRepository.save(task);
        return this.getTaskById(task.getId());
    }
}
