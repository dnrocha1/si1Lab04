package com.daniyel.si1lab04.ws.controller;

import com.daniyel.si1lab04.ws.model.ToDoList;
import com.daniyel.si1lab04.ws.service.ToDoListService;
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
@RequestMapping(value = "/todoList")
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> getToDoListById(@PathVariable long id) {
        ToDoList toDoListFound = toDoListService.getToDoListById(id);
        return new ResponseEntity<>(toDoListFound, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ToDoList>> getAllToDoLists() {
        Collection<ToDoList> allToDoLists = toDoListService.getAllToDoLists();
        return new ResponseEntity<>(allToDoLists, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> createToDoList(@RequestBody ToDoList toDoList) {
        toDoListService.createToDoList(toDoList);
        return new ResponseEntity<>(toDoList, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ToDoList> deleteToDoList(@PathVariable long id) {
        boolean deleted = toDoListService.deleteToDoList(id);
        HttpStatus status;
        if (deleted) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> updateToDoList(@RequestBody ToDoList toDoList) {
        ToDoList updatedToDoList = toDoListService.updateToDoList(toDoList);
        return new ResponseEntity<>(updatedToDoList, HttpStatus.OK);
    }
}
