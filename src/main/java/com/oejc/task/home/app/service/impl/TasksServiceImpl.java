package com.oejc.task.home.app.service.impl;


import com.oejc.task.home.app.model.entity.Tasks;
import com.oejc.task.home.app.model.repositories.TasksRepository;
import com.oejc.task.home.app.service.TasksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;

    public TasksServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Tasks save(Tasks tasks) {
        return this.tasksRepository.save(tasks);
    }

    @Override
    public List<Tasks> findTasks(Tasks tasks) {
        List<Tasks> listTasks = this.tasksRepository.findAll();

        return listTasks.stream().
                filter(t -> localDateToString(t.getDueDate()).equals(localDateToString(tasks.getDueDate())))
                .filter(t -> t.getPriority().name().equals(tasks.getPriority().name()) )
                .filter(t -> t.getCompleted() == t.getCompleted())
                .collect(Collectors.toList());
    }

    private String localDateToString(LocalDate localDate) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);
    }

    @Override
    public Optional<Tasks> findId(String id) {
        Optional<Tasks> t = this.tasksRepository.findById(id);
        return t;
    }

    @Override
    public Tasks update(Tasks tasks) {
        Optional<Tasks> t = this.tasksRepository.findById(tasks.getId());
        if (t.isPresent()) {
            Tasks tkBS = t.get();
            this.tasksRepository.save(tkBS);
        }
        return null;
    }

    @Override
    public void delete(Tasks tasks) {
        Optional<Tasks> t = this.tasksRepository.findById(tasks.getId());
        if (t.isPresent()) {
            Tasks tkBS = t.get();
            this.tasksRepository.deleteById(tkBS.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tasks> findAll() {
        return this.tasksRepository.findAll();
    }
}