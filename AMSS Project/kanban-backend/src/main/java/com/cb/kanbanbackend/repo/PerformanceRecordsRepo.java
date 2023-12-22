package com.cb.kanbanbackend.repo;

import com.cb.kanbanbackend.entity.PerformanceRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRecordsRepo extends JpaRepository<PerformanceRecordsEntity,Integer> {
}
