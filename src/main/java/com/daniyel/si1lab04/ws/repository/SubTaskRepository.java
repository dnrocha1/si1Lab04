package com.daniyel.si1lab04.ws.repository;

import com.daniyel.si1lab04.ws.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniyel on 30/01/2017.
 */

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long>{
}
