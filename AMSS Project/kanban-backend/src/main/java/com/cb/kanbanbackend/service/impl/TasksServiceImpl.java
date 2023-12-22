package com.cb.kanbanbackend.service.impl;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.dto.res.OpenTasksRes;
import com.cb.kanbanbackend.entity.TasksEntity;
import com.cb.kanbanbackend.exception.CommonException;
import com.cb.kanbanbackend.repo.TasksRepo;
import com.cb.kanbanbackend.service.TasksService;
import com.cb.kanbanbackend.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
//Implementiert TasksServices

@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    TasksRepo tasksRepo;

    @Override
    public Integer CreateTask(TaskReqDto entity) throws Exception {
        TasksEntity task = new TasksEntity(entity);
        if (task.getPriorityId() != null) {
            task.setPriorityId(entity.getPriorityId());
        } else {
            task.setPriorityId(3);
        }
        task.setCreatedDate(LocalDate.now());
        task = tasksRepo.save(task);
        return task.getId();
    }

    @Override
    public List<TasksEntity> GetTasksByModuleId(Integer mid) throws Exception {
        return tasksRepo.findAllByModuleId(mid);
    }

    @Override
    public Integer UpdateTask(TaskReqDto entity) throws Exception {
        TasksEntity task = new TasksEntity(entity);
        task.setId(entity.getId());
        tasksRepo.save(task);
        return task.getId();
    }

    @Override
    public void DeleteTask(int id) throws Exception {
        try {
            tasksRepo.deleteById(id);
        } catch (Exception e) {
            throw new CommonException("FAILED_TO_DELETE_TASK");
        }
    }

    @Override
    public List<OpenTasksRes> getOpenTasks(int userId) {
        return tasksRepo.getOpenTasks(userId, Status.TASK_STATUS_OPEN);
    }

    @Override
    public List<OpenTasksRes> getHighPriorityTasks(int userId) {
        return tasksRepo.getHighPriorityTasks(userId, Status.TASK_PRIORITY_HIGH, Status.TASK_STATUS_FINISHED, Status.TASK_STATUS_LEARNED);
    }

    @Override
    public Integer UpdateTaskPriority(TasksEntity tasksEntity) {
        TasksEntity task = tasksRepo.save(tasksEntity);
        return task.getId();
    }
}
