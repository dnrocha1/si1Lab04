package com.daniyel.si1lab04.ws.controller;

import com.daniyel.si1lab04.ws.model.SubTask;
import com.daniyel.si1lab04.ws.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Daniyel on 30/01/2017.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/subtasks")
public class SubTaskController {

    @Autowired
    SubTaskService subTaskService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubTask> getTaskById(@PathVariable long id) {
        SubTask subTaskFound = subTaskService.getTaskById(id);
        return new ResponseEntity<SubTask>(subTaskFound, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SubTask>> getAllSubTasks() {
        Collection<SubTask> allSubTasks = subTaskService.getAllSubTasks();
        return new ResponseEntity<>(allSubTasks, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubTask> createSubTask(@RequestBody SubTask subTask) {
        subTaskService.createSubTask(subTask);
        return new ResponseEntity<>(subTask, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<SubTask> deleteSubTask(@PathVariable long id) {
        boolean deleted = subTaskService.deleteSubTask(id);
        HttpStatus status;
        if (deleted) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubTask> updateTask(@RequestBody SubTask subTask) {
        SubTask updatedSubTask = subTaskService.updateSubTask(subTask);
        return new ResponseEntity<>(updatedSubTask, HttpStatus.OK);
    }
}
