package com.daniyel.si1lab04.ws.service;

import com.daniyel.si1lab04.ws.model.TaskCategory;
import com.daniyel.si1lab04.ws.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Daniyel on 31/01/2017.
 */
@Service
public class TaskCategoryService {

    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    public TaskCategory getTaskCategoryById(long id) {
        return taskCategoryRepository.findOne(id);
    }

    public Collection<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    public void createTaskCategory(TaskCategory taskCategory) {
        taskCategoryRepository.save(taskCategory);
    }

    public boolean deleteTaskCategory(long id) {
        boolean result = false;
        TaskCategory taskCategoryFound = taskCategoryRepository.findOne(id);
        if (taskCategoryFound != null) {
            taskCategoryRepository.delete(id);
            result = true;
        }
        return result;
    }

    public TaskCategory updateTaskCategory(TaskCategory taskCategory) {
        taskCategoryRepository.save(taskCategory);
        return this.getTaskCategoryById(taskCategory.getId());
    }
}
