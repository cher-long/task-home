package com.oejc.task.home.app.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class GenericEntity {
    @Id
    private String id;
}
