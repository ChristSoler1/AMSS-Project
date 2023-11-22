package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.entity.ModulesEntity;
import com.cb.kanbanbackend.entity.TasksEntity;
import com.cb.kanbanbackend.repo.TasksRepo;
import com.cb.kanbanbackend.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    TasksRepo tasksRepo;

    @Override
    public Integer CreateTask(TaskReqDto entity) {
        TasksEntity task = new TasksEntity(entity);
        task  = tasksRepo.save(task);
        return task.getId();
    }

    @Override
    public List<TasksEntity> GetTasksByModuleId(Integer mid) {
        return tasksRepo.findAllByModuleId(mid);
    }
}
