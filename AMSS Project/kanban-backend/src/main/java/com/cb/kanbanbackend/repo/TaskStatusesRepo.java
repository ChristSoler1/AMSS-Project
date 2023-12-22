package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.TaskStatusesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusesRepo extends JpaRepository<TaskStatusesEntity,Integer> {
}
