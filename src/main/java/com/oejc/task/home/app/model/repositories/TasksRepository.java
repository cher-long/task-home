package com.oejc.task.home.app.model.repositories;

import com.oejc.task.home.app.model.entity.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TasksRepository extends MongoRepository<Tasks, String> {
}
