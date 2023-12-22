package com.cb.kanbanbackend.dto.res;

import com.cb.kanbanbackend.entity.TasksEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok Dto for response Tasks
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpenTasksRes {
    TasksEntity entity;
    String moduleName;
}
