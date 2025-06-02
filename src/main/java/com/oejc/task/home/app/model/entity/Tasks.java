package com.oejc.task.home.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks extends GenericEntity {


    private String title;

    private String description;

    private LocalDate dueDate;


    private Priority priority;

    private Boolean completed;



}