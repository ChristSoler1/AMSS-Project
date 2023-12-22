package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.entity.TaskStatusesEntity;
import com.cb.kanbanbackend.repo.TaskStatusesRepo;
import com.cb.kanbanbackend.service.TaskStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//Implementiert TasksStatusesService

@Service
public class TaskStatusesServiceImpl implements TaskStatusesService {

    @Autowired
    TaskStatusesRepo taskStatusesRepo;

    @Override
    public List<TaskStatusesEntity> getTaskStatus() throws Exception {
        return taskStatusesRepo.findAll();
    }
}
