package com.daniyel.si1lab04.ws.controller;

import com.daniyel.si1lab04.ws.model.TaskCategory;
import com.daniyel.si1lab04.ws.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Daniyel on 31/01/2017.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/taskcategories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> getTaskCategoryById(@PathVariable long id) {
        TaskCategory taskCategoryFound = taskCategoryService.getTaskCategoryById(id);
        return new ResponseEntity<>(taskCategoryFound, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TaskCategory>> getAllTaskCategories() {
        Collection<TaskCategory> allTaskCategories = taskCategoryService.getAllTaskCategories();
        return new ResponseEntity<>(allTaskCategories, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> createTaskCategory(@RequestBody TaskCategory taskCategory) {
        taskCategoryService.createTaskCategory(taskCategory);
        return new ResponseEntity<>(taskCategory, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<TaskCategory> deleteTaskCategory(@PathVariable long id) {
        boolean deleted = taskCategoryService.deleteTaskCategory(id);
        HttpStatus status;
        if (deleted) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> updateTaskCategory(@RequestBody TaskCategory taskCategory) {
        TaskCategory updatedTaskCategory = taskCategoryService.updateTaskCategory(taskCategory);
        return new ResponseEntity<>(updatedTaskCategory, HttpStatus.OK);
    }
}
