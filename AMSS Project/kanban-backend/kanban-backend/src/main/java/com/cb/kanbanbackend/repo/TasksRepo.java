package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<TasksEntity,Integer> {

    List<TasksEntity> findAllByModuleId(Integer mid);

}
