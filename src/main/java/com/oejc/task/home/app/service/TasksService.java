package com.oejc.task.home.app.service;

import com.oejc.task.home.app.model.entity.Tasks;

import java.util.List;
import java.util.Optional;

public interface TasksService {

    Tasks save(Tasks tasks);

    List<Tasks> findTasks(Tasks tasks);

    Optional<Tasks> findId(String id);

    Tasks update(Tasks tasks);

    void delete(Tasks tasks);

    List<Tasks> findAll();

}
