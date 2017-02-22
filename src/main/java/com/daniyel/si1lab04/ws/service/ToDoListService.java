package com.daniyel.si1lab04.ws.service;

import com.daniyel.si1lab04.ws.model.ToDoList;
import com.daniyel.si1lab04.ws.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Daniyel on 31/01/2017.
 */

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    public ToDoList getToDoListById(long id) {
        return toDoListRepository.findOne(id);
    }


    public Collection<ToDoList> getAllToDoLists() {
        return toDoListRepository.findAll();
    }

    public void createToDoList(ToDoList toDoList) {
        toDoListRepository.save(toDoList);
    }

    public boolean deleteToDoList(long id) {
        boolean result = false;
        ToDoList toDoListFound = toDoListRepository.findOne(id);
        if (toDoListFound != null) {
            toDoListRepository.delete(id);
            result = true;
        }
        return result;
    }

    public ToDoList updateToDoList(ToDoList toDoList) {
        toDoListRepository.save(toDoList);
        return this.getToDoListById(toDoList.getId());
    }
}
