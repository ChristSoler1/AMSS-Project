package com.cb.kanbanbackend.dto.res;

import com.cb.kanbanbackend.entity.ModulesEntity;
import com.cb.kanbanbackend.entity.TasksEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

// Lombok Dto for response Modules
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ModuleResDto {
    private ModulesEntity entity;
    private String performanceRecordsName;
    private List<TaskRes> OpenTasks;
    private List<TaskRes> InProgressTasks;
    private List<TaskRes> FinishedTasks;
    private List<TaskRes> Learned;
    private String lstEffort;

    public ModuleResDto(ModulesEntity entity, String performanceRecordsName) {
        this.entity = entity;
        this.performanceRecordsName = performanceRecordsName;
    }
}
