package com.cb.kanbanbackend.service;

import com.cb.kanbanbackend.dto.req.TaskReqDto;
import com.cb.kanbanbackend.dto.res.OpenTasksRes;
import com.cb.kanbanbackend.entity.TasksEntity;

import java.util.List;

//Interface Tasks
public interface TasksService {
    Integer CreateTask(TaskReqDto entity) throws Exception;

    List<TasksEntity> GetTasksByModuleId(Integer mid) throws Exception;

    Integer UpdateTask(TaskReqDto entity)throws Exception;

    void DeleteTask(int id)throws Exception;

    List<OpenTasksRes> getOpenTasks(int userId);

    List<OpenTasksRes> getHighPriorityTasks(int userId);

    Integer UpdateTaskPriority(TasksEntity tasksEntity);
}
