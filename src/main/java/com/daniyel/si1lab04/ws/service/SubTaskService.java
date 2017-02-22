package com.daniyel.si1lab04.ws.service;

import com.daniyel.si1lab04.ws.model.SubTask;
import com.daniyel.si1lab04.ws.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Daniyel on 30/01/2017.
 */

@Service
public class SubTaskService {

    @Autowired
    SubTaskRepository subTaskRepository;

    public SubTask getTaskById(long id) {
        return subTaskRepository.findOne(id);
    }

    public Collection<SubTask> getAllSubTasks() {
        return subTaskRepository.findAll();
    }

    public void createSubTask(SubTask subTask) {
        subTaskRepository.save(subTask);
    }

    public boolean deleteSubTask(long id) {
        boolean result = false;
        SubTask subTaskFound = subTaskRepository.findOne(id);
        if (subTaskFound != null) {
            subTaskRepository.delete(id);
            result = true;
        }
        return result;
    }

    public SubTask updateSubTask(SubTask subTask) {
        subTaskRepository.save(subTask);
        return this.getTaskById(subTask.getId());
    }
}
