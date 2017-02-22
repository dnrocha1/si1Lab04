package com.daniyel.si1lab04.ws.repository;

import com.daniyel.si1lab04.ws.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniyel on 31/01/2017.
 */

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long>{
}
