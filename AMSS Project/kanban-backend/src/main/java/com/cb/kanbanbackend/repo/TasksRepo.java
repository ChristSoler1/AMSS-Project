package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.dto.res.OpenTasksRes;
import com.cb.kanbanbackend.entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<TasksEntity, Integer> {

    List<TasksEntity> findAllByModuleId(Integer mid);

    @Query("select new com.cb.kanbanbackend.dto.res.OpenTasksRes(t,m.title) from TasksEntity t, ModulesUsersEntity mu, ModulesEntity m where t.moduleId = mu.moduleId and mu.userId=?1 and t.statusId=?2 and m.id=mu.moduleId")
    List<OpenTasksRes> getOpenTasks(Integer userId, int taskStatusOpen);

    @Query("select new com.cb.kanbanbackend.dto.res.OpenTasksRes(t,m.title) from TasksEntity t, ModulesUsersEntity mu, ModulesEntity m where t.moduleId = mu.moduleId and mu.userId=?1 and t.priorityId=?2 and m.id=mu.moduleId and t.statusId <> ?3 and t.statusId <> ?4")
    List<OpenTasksRes> getHighPriorityTasks(int userId, int taskPriorityHigh, int taskStatusFinished, int taskStatusLearned);
}
