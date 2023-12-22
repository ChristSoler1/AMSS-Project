package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.entity.TaskStatusesEntity;

import java.util.List;

//Interface TaskStatuses
public interface TaskStatusesService {
    List<TaskStatusesEntity> getTaskStatus() throws Exception;
}
