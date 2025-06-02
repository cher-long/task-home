package com.oejc.task.home.app.controllers;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.oejc.task.home.app.model.entity.Priority;
import com.oejc.task.home.app.model.entity.Tasks;
import com.oejc.task.home.app.service.TasksService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins="http://localhost:4200")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(tasksService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Tasks tasks) {
        return new ResponseEntity<>(tasksService.save(tasks), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> findByFilters(@RequestParam("dueDate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dueDate, @RequestParam Priority priority, @RequestParam Boolean completed) {
        Tasks tasks = new Tasks();
        tasks.setCompleted(completed);
        tasks.setPriority(priority);
        tasks.setDueDate(dueDate);
        return new ResponseEntity<>(this.tasksService.findTasks(tasks), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {

        Optional<Tasks> tkDb = this.tasksService.findId(id);
        if (tkDb.isPresent()) {
            return new ResponseEntity<>(tkDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Tasks tasks) {
        Optional<Tasks> op = this.tasksService.findId(id);
        if(op.isPresent()) {
            Tasks tk = op.get();
            tk.setDueDate(tasks.getDueDate());
            tk.setPriority(tasks.getPriority());
            tk.setCompleted(tasks.getCompleted());
            tk.setDescription(tasks.getDescription());
            tk.setTitle(tasks.getTitle());
            this.tasksService.save(tk);
            return new ResponseEntity<>(tk, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Tasks> tkDb = this.tasksService.findId(id);
        if(tkDb.isPresent()) {
            this.tasksService.delete(tkDb.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}