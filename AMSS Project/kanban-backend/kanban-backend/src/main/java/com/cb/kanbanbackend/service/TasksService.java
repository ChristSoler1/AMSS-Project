package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.entity.ModulesEntity;
import com.cb.kanbanbackend.entity.TaskStatusesEntity;
import com.cb.kanbanbackend.entity.TasksEntity;

import java.util.List;

public interface TasksService {
    Integer CreateTask(TaskReqDto entity);

    List<TasksEntity> GetTasksByModuleId(Integer mid);
}
